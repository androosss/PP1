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
	int nVars;

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
		constVal = constBool.getB1(); 
		constType = boolType;
	}
	
	@Override
	public void visit(ConstChar constChar) {
		constVal = constChar.getC1(); 
		constType = Tab.charType;
	}
	
	@Override
	public void visit(ConstInt constInt) {
		constVal = constInt.getN1(); 
		constType = Tab.intType;
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
		fpCnt=0;
		retType=currentType;
		currentMethod = Tab.insert(Obj.Meth,currentMethodName, retType);
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
	
	/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	//CONTEXT CONDITIONS
	/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
	/*@Override
	public void visit(Constant constant) {
		
	}
	
	@Override
	public void visit(Constant constant) {
		
	}
	
	
	@Override
	public void visit(Constant constant) {
		
	}
	
	@Override
	public void visit(Constant constant) {
		
	}
	
	@Override
	public void visit(Constant constant) {
		
	}*/
	
	
	
	
}



