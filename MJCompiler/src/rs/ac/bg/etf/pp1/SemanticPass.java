package rs.ac.bg.etf.pp1;
import org.apache.log4j.Logger;

import rs.ac.bg.etf.pp1.ast.*;
import rs.etf.pp1.symboltable.Tab;
import rs.etf.pp1.symboltable.concepts.Obj;
import rs.etf.pp1.symboltable.concepts.Struct;

public class SemanticPass extends VisitorAdaptor {

	boolean errorDetected = false;
	int printCallCount = 0;
	Obj currentMethod = null;
	boolean returnFound = false;

	Logger log = Logger.getLogger(getClass());
	private Obj currentProgram;
	private Struct currentType;
	private int constVal;
	private Struct constType;
	private Struct boolType;
	private Struct retType;
	private String currentMethodName;
	private boolean mainExists=false;
	private int fpCnt=0;
	public int NumberOfGlobals=0;
	
	public String toString(Struct struct) {
		String ret="";
		switch(struct.getKind()){
		case 0:
			ret+="void";
			break;
		case 1:
			ret+="int";
			break;
		case 2:
			ret+="char";
			break;
		case 3:
			ret+="array";
			ret+="["+toString(struct.getElemType())+"]";
			break;
		case 4:
			ret+="class";
			break;
		case 5:
			ret+="bool";
			break;
		case 6:
			ret+="enum";
			break;
		case 7:
			ret+="interface";
			break;
		}
		return ret;
	}
	
	public void report_error(String message, SyntaxNode info) {
		errorDetected = true;
		StringBuilder msg = new StringBuilder(message);
		int line = (info == null) ? 0: info.getLine();
		if (line != 0)
			msg.append (" na liniji ").append(line);
		log.error(msg.toString());
	}

	public void report_info(String message, SyntaxNode info) {
		StringBuilder msg = new StringBuilder(message); 
		int line = (info == null) ? 0: info.getLine();
		if (line != 0)
			msg.append (" na liniji ").append(line);
		log.info(msg.toString());
	}
	
	public boolean passed() {
		return !errorDetected;
	}
	
	public void boolInit() {
		boolType = new Struct(Struct.Bool);
		Obj boolObj = Tab.insert(Obj.Type, "bool", boolType);
		boolObj.setAdr(-1);
		boolObj.setLevel(-1);
		boolType = Tab.find("bool").getType();
	}
	
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//SYMBOL TABLE
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
	/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	//PROGRAM
	/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	@Override
	public void visit(ProgName progName) {
		currentProgram = Tab.insert(Obj.Prog, progName.getProgName(), Tab.noType);
		Tab.openScope();
	}
	
	@Override
	public void visit(Program program) {
		if(!mainExists) {
			report_error("Main funkcija ne postoji!",program);
		}
			
		NumberOfGlobals = Tab.currentScope.getnVars();
		Tab.chainLocalSymbols(currentProgram);
		Tab.closeScope();
	}
	
	/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	//TYPES
	/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	@Override
	public void visit(StandardType standardType) {
		String currentTypeName = standardType.getTypeName();
		Obj typeObj = Tab.find(currentTypeName);
		if(typeObj == Tab.noObj) {
			report_error("Nepostojeci tip: "+currentTypeName,standardType);
			currentType=Tab.noType;
			return;
		}
		if(typeObj.getKind() != Obj.Type) {
			report_error("Pogresan tip: "+currentTypeName ,standardType);
			currentType=Tab.noType;
			return;
		}
		currentType = typeObj.getType();
		standardType.struct=currentType;
	}
	
	public void visit(VoidType voidType) {
		currentType=Tab.noType;
	}
	
	/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	//CONSTANTS
	/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	@Override
	public void visit(Constant constant) {
		if(Tab.currentScope().findSymbol(constant.getConstName())!= null) {
			report_error("Duplo definisanje simbola: "+constant.getConstName() ,constant);
			return ;
		}
		if(!constType.assignableTo(currentType))
		{
			report_error("Neodgovarajuci tip kosntante:"+constant.getConstName(),constant);
		}
		Obj constantObj = Tab.insert(Obj.Con, constant.getConstName(), currentType);
		constantObj.setAdr(constVal);
	}
	
	@Override
	public void visit(ConstBool constBool) {
		constVal = constBool.getB1().equals("true")?1:0;//constBool.getB1(); 
		constType = boolType;
		constBool.struct=boolType;
	}
	
	@Override
	public void visit(ConstChar constChar) {
		constVal = constChar.getC1().charAt(1);//constChar.getC1(); 
		constType = Tab.charType;
		constChar.struct= Tab.charType;
	}
	
	@Override
	public void visit(ConstInt constInt) {
		constVal = constInt.getN1(); 
		constType = Tab.intType;
		constInt.struct= Tab.intType;
	}
	
	/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	//VARIABLES
	/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	@Override
	public void visit(VariableNormal variableNormal) {
		if(Tab.currentScope().findSymbol(variableNormal.getVarName())!= null) {
			report_error("Duplo definisanje simbola: "+variableNormal.getVarName() ,variableNormal);
			return ;
		}
		Tab.insert(Obj.Var, variableNormal.getVarName(), currentType);
	}
	
	@Override
	public void visit(VariableArray variableArray) {
		if(Tab.currentScope().findSymbol(variableArray.getVarName())!= null) {
			report_error("Duplo definisanje simbola: "+variableArray.getVarName() ,variableArray);
			return ;
		}
		Tab.insert(Obj.Var, variableArray.getVarName(), new Struct(Struct.Array,currentType));
	}
	
	/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	//METHODS
	/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	public void visit(MethodName methodName) {
		currentMethodName = methodName.getMethodName();
		if (currentMethodName.equals("main"))
		{
			if(currentType!=Tab.noType)
				report_error("Main methoda mora biti void!",methodName);
			mainExists=true;
		}
		if (!Tab.find(currentMethodName).equals(Tab.noObj)) {
			report_error("Metoda ne moze deklarisana, ime se vec koristi",methodName);
		}
		fpCnt=0;
		retType=currentType;
		currentMethod = Tab.insert(Obj.Meth,currentMethodName, retType);
		methodName.obj=currentMethod;
		Tab.openScope();
	}
	
	public void visit(MethodDecl methodDecl) {
		if(fpCnt>0 && currentMethodName.equals("main"))
			report_error("Main methoda ne moze imati parametre!",methodDecl);
		Tab.chainLocalSymbols(currentMethod);
		Tab.closeScope();
	}
	/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	//METHODS-FORM_PARAMS
	/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////	
	@Override
	public void visit(FormParamNormal formParamNormal) {
		if(Tab.currentScope().findSymbol(formParamNormal.getFormParamName())!= null) {
			report_error("Duplo definisanje simbola: "+formParamNormal.getFormParamName() ,formParamNormal);
			return ;
		}
		Obj fpObj=Tab.insert(Obj.Var, formParamNormal.getFormParamName(), currentType);
		fpObj.setFpPos(1);
		fpCnt++;
		currentMethod.setLevel(currentMethod.getLevel()+1);
	}
	
	@Override
	public void visit(FormParamArray formParamArray) {
		if(Tab.currentScope().findSymbol(formParamArray.getFormParamName())!= null) {
			report_error("Duplo definisanje simbola: "+formParamArray.getFormParamName() ,formParamArray);
			return ;
		}
		Obj fpObj=Tab.insert(Obj.Var, formParamArray.getFormParamName(), new Struct(Struct.Array,currentType));
		fpObj.setFpPos(1);
		fpCnt++;
		currentMethod.setLevel(currentMethod.getLevel()+1);
	}
	
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//CONTEXT CONDITIONS
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
	/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	//EXPR
	/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
	@Override
	public void visit(BaseConst baseConst) {
		baseConst.struct=baseConst.getConstantValues().struct;
	}
	
	@Override
	public void visit(BaseCall baseCall) {
		if(baseCall.getCallOptional() instanceof CallOpt) {
			report_error("Pozivi metoda nisi podrzani",baseCall);
			baseCall.struct=Tab.noType;
		} else {
			if(baseCall.getDesignator().obj.getKind()!=Obj.Var && baseCall.getDesignator().obj.getKind()!=Obj.Con
			   && baseCall.getDesignator().obj.getKind()!=Obj.Elem) {
				report_error("Samo promenjive, konstante i elementi niza mogu da se koriste u izrazima",baseCall);
			}
			baseCall.struct=baseCall.getDesignator().obj.getType();
		}
	}
	
	@Override
	public void visit(BaseParens baseParens) {
		baseParens.struct=baseParens.getExprWrapper().struct;
	}
	
	@Override
	public void visit(BaseNew baseNew) {
		if(baseNew.getArrayOptional() instanceof NoArrayOpt) {
			report_error("Klase nisu podrzane",baseNew);
			baseNew.struct=Tab.noType;
		} else {
			baseNew.struct=new Struct(Struct.Array,baseNew.getStandardType().struct);
		}
	}
	
	@Override
	public void visit(ArrayOpt arrayOpt) {
		if(arrayOpt.getExprWrapper().struct!=Tab.intType) {
			report_error("Niz moze da se indeksira samo integerima",arrayOpt);
		}
	}
	
	@Override
	public void visit(Factor factor) {
		if (factor.getMinusOptional() instanceof MinusOpt) {
			//System.out.println(factor.getBaseFactor().toString());
			if (factor.getBaseFactor().struct.equals(Tab.intType)) {
				factor.struct=Tab.intType;
			} else {
				report_error("Negacija vrednosti koja nije integer",factor);
				factor.struct=Tab.noType;
			}
		} else {
			factor.struct=factor.getBaseFactor().struct;
		}
	}
	
	@Override
	public void visit(SingleFactor singleFactor) {
		singleFactor.struct=singleFactor.getFactor().struct;
	}
	
	@Override
	public void visit(MultipleFactors multipleFactors) {
		if (multipleFactors.getTerm().struct!=Tab.intType || multipleFactors.getFactor().struct!=Tab.intType) {
			report_error("Racunske operacije mogu da se vrse samo nad integerima",multipleFactors);
			multipleFactors.struct=Tab.noType;
		} else {
			multipleFactors.struct=Tab.intType;
		}
	}
	
	@Override
	public void visit(SingleTerm singleTerm) {
		singleTerm.struct=singleTerm.getTerm().struct;
	}
	
	@Override
	public void visit(MultipleTerms multipleTerms) {
		if (multipleTerms.getTerm().struct!=Tab.intType || multipleTerms.getExpr().struct!=Tab.intType) {
			report_error("Racunske operacije mogu da se vrse samo nad integerima",multipleTerms);
			multipleTerms.struct=Tab.noType;
		} else {
			multipleTerms.struct=Tab.intType;
		}
	}
	
	@Override
	public void visit(NormalExpr normalExpr) {
		normalExpr.struct=normalExpr.getExpr().struct;
	}
	
	@Override
	public void visit(NotZeroExpr notZeroExpr) {
		if(notZeroExpr.getExpr().struct!=Tab.intType || notZeroExpr.getExpr1().struct!=Tab.intType){
			report_error("Oba operanda moraju da budu integeri za ?? operator",notZeroExpr);
			notZeroExpr.struct=Tab.noType;
		} else {
			notZeroExpr.struct=Tab.intType;
		}
	}
	
	/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	//DESIGNATOR
	/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
	public void visit(DesignatorName designatorName) {
		designatorName.obj=Tab.find(designatorName.getDesignatorName());
	}
	
	@Override
	public void visit(Designator designator) {
		designator.obj=designator.getDesignatorName().obj;
		if (designator.obj.equals(Tab.noObj)) {
			report_error("Simbol nije deklarisan",designator);
		}
		if (designator.getArrayOptional() instanceof ArrayOpt) {
			if(designator.obj.getType().getKind()!=Struct.Array || designator.obj.getKind()!=Obj.Var) {
				report_error("Simbol nije niz, ne moze da se pristupa njegovim elementima",designator);
			} else {
				report_info("Pristup elementu niza: "+designator.obj.getName()+" tip: "+toString(designator.obj.getType()),designator);
				designator.obj= new Obj(Obj.Elem, designator.getDesignatorName()+"[i]",designator.obj.getType().getElemType());
			}
		} else {
			if(designator.obj.getLevel()>0) {
				report_info("Pristup lokalnoj promenjivoj: "+designator.obj.getName()+" ,tip: "+toString(designator.obj.getType()),designator);
			} else {
				report_info("Pristup globalnoj promenjivoj: "+designator.obj.getName()+" ,tip: "+toString(designator.obj.getType()),designator);
			}
		}
	}
	
	/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	//DESIGNATOR STATEMENT
	/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
	@Override
	public void visit(DesignatorStatementV designatorStatement) {
		Struct designatorStruct=designatorStatement.getDesignator().obj.getType();
		if (designatorStatement.getDesignator().obj.getKind()!=Obj.Var && designatorStatement.getDesignator().obj.getKind()!=Obj.Elem) {
			report_error("Ne moze da se izvrsi dodela simbolu",designatorStatement);
		}
		if(designatorStatement.getRightSide() instanceof PlusPlus && designatorStruct !=Tab.intType) {
			report_error("Operator ++ moze da se koristi samo za integere",designatorStatement);
		}
		if(designatorStatement.getRightSide() instanceof MinusMinus && designatorStruct!=Tab.intType) {
			report_error("Operator -- moze da se koristi samo za integere",designatorStatement);
		}	
		if(designatorStatement.getRightSide() instanceof EqualAssign && 
		   !((EqualAssign)designatorStatement.getRightSide()).getExprWrapper().struct.assignableTo(designatorStruct) ) {
			report_error("Dodela nije moguca, tipovi nisu kompatibilni",designatorStatement);
		}
	}
	
	/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	//READ
	/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
	public void visit(ReadSt readSt) {
		Struct designatorStruct=readSt.getDesignator().obj.getType();
		if (readSt.getDesignator().obj.getKind()!=Obj.Var && readSt.getDesignator().obj.getKind()!=Obj.Elem) {
			report_error("Ne moze da se cita simbol koji nije promenjiva ili element niza",readSt);
		}
		if (designatorStruct!=Tab.intType && designatorStruct!=boolType && designatorStruct!=Tab.charType) {
			report_error("Nije moguce citati u promenjive koje nisu integer,char ili bool",readSt);
		}
	}
	
	/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	//PRINT
	/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
	public void visit(PrintSt printSt) {
		if(printSt.getExprWrapper().struct!=Tab.intType && printSt.getExprWrapper().struct!=boolType && printSt.getExprWrapper().struct!=Tab.charType) {
			report_error("Nije moguce stampati promenjive koje nisu tipa integer,char ili bool",printSt);
		}
	}
	
	/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	//RETURN
	/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
	public void visit(ReturnSt returnSt) {
		if(returnSt.getReturnOptional() instanceof NoReturnOpt && retType!=Tab.noType) {
			report_error("Return je prazan iako metodin povratni tip nije void",returnSt);
		}
		if(returnSt.getReturnOptional() instanceof ReturnOpt && retType!=((ReturnOpt)returnSt.getReturnOptional()).getExprWrapper().struct) {
			report_error("Return vraca tip koji je razlicit ot metodinog povratnog tipa",returnSt);
		}
	}	
	
}



