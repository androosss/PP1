package rs.ac.bg.etf.pp1;

import org.apache.log4j.Logger;

import rs.ac.bg.etf.pp1.ast.*;
import rs.etf.pp1.mj.runtime.*;
import rs.etf.pp1.symboltable.*;
import rs.etf.pp1.symboltable.concepts.*;


/* 
Za b koristi Code.put(0) 
Za s koristi Code.put2(0)
Za w koristi Code.put4(0)
1 load b ... 		Load
 		 ..., val 	push(local[b]);
2..5 load_n ... 		Load (n = 0..3)
			..., val 	push(local[n]);
6 store b ..., val 	Store
		  ... 		local[b] = pop();
7..10 store_n ..., val 	Store (n = 0..3)
			  ... 		local[n] = pop();
11 getstatic s ... 		Load staticke promenljive
			   ..., val push(data[s]);
12 putstatic s ..., val Store staticke promenljive
			   ... 		data[s] = pop();
13 getfield s ..., adr 	Load polja objekta
			  ..., val 	adr = pop()/4; push(heap[adr+s]);
14 putfield s ..., adr, val Store polja objekta
			  ... 			val = pop(); adr = pop()/4;
			  ... 	heap[adr+s] = val;
15..20 const_n ... 	Load konstante (n = 0..5)
			   ..., val push(n)
21 const_m1 ... 	Load konstante -1
			..., -1 push(-1)
22 const w ... 		Load konstante
		   ..., val push(w)
23 add ..., val1, val2 	Sabiranje
	   ..., val1+val2 	push(pop() + pop());
24 sub ..., val1, val2 	Oduzimanje
	   ..., val1-val2 	push(-pop() + pop());
25 mul ..., val1, val2 	Mno?enje
	   ..., val1*val2	push(pop() * pop());
26 div ..., val1, val2 	Deljenje
	   ..., val1/val2 	x = pop(); push(pop() / x);
27 rem ..., val1, val2 	Ostatak pri celobrojnom deljenju
	   ..., val1%val2 	x = pop(); push(pop() % x);
28 neg ..., val 	Promena predznaka
	   ..., -val 	push(-pop());
29 shl ..., val 	Aritmeticko pomeranje ulevo
	   ..., val1 	x = pop(); push(pop() << x);
30 shr ..., val 	Aritmeticko pomeranje udesno
	   ..., val1 	x = pop(); push(pop() >> x);
31 inc b1, b2 ... 	Inkrementiranje
			  ... 	local[b1] = local[b1] + b2;
32 new s ... 	  Novi objekat
		 ..., adr alocirati oblast od s bajtova;
				  inicijalizovati oblast nulama;
				  push(adr(oblast));
33 newarray b ..., n 	Novi niz
			  ..., adr 	n = pop();
			     		if (b==0) alocirati niz sa n elemenata velicine bajta;
				 		else if (b==1) alocirati niz sa n elemenata velicine reci;
				 		inicijalizovati niz nulama;
				 		push(adr(niz));
34 aload ..., adr, index Load elementa niza (+ provera indeksa)
		 ..., val 		 i = pop(); adr = pop()/4+1;
						 push(heap[adr+i]);
35 astore ..., adr, index, val Store elementa niza (+ provera indeksa)
		  ...  				   val = pop(); i = pop(); adr = pop()/4+1;
			 				   heap[adr+i] = val;
36 baload ..., adr, index Load elementa niza bajtova (+ provera indeksa)
		  ..., val 		  i = pop(); adr = pop()/4+1; 
		     			  x= heap[adr+i/4]; 
		     			  push(bytei%4 of x);
37 bastore ..., adr, index, val Store elementa niza bajtova (+ provera indeksa)
		   ...  				val = pop(); i = pop(); adr = pop()/4+1;
			  					x = heap[adr+i/4];
			  					set byte i%4 in x;
			  					heap[adr+i/4] = x;
38 arraylength ..., adr Dohvatanje du?ine niza
			   ..., len adr = pop();
				  		push(heap[adr]);
39 pop ..., val Skidanje elementa sa vrha steka
	   ... 		dummy = pop();
40 dup ..., val 	 Udvajanje elementa na vrhu steka
	   ..., val, val x = pop(); push(x); push(x);
41 dup2 ..., v1, v2 		Udvajanje prva dva elementa na vrhu steka
		..., v1, v2, v1, v2 y = pop(); x = pop();
		   					push(x); push(y); push(x); push(y);
Adresa skoka je relativna u odnosu na pocetak instrukcije skoka.
42 jmp s Bezuslovni skok
		 pc = pc + s;
43..48 j<cond> s ..., val1, val2 Uslovni skok (eq, ne, lt, le, gt, ge)
				 ...  			 y = pop(); x = pop();
								 if (x cond y) pc = pc + s;
49 call s Poziv metode
		  PUSH(pc+3); pc := pc + s;
50 return Povratak iz metode
		  pc = POP();
51 enter b1, b2 Pocetak obrade metode
				psize = b1; lsize = b2; // u recima
				PUSH(fp); fp = sp; sp = sp + lsize;
				inicijalizovati akt. zapis svim
				nulama;
				for (i=psize-1; i>=0; i--) local[i] = pop();
52 exit Kraj obrade metode
		sp = fp; fp = POP();
53 read ... 	 Operacija citanja
		... ,val readInt(x); push(x);
		  // cita sa standardnog ulaza
54 print ... ,val, width Operacija ispisa
		 ...  			 width = pop(); writeInt(pop(), width);
// vrsi ispis na standardni izlaz
55 bread ...	  	Operacija citanja bajta
		 ..., val 	readChar(ch); push(ch);
56 bprint ..., val, width Operacija ispisa bajta
		  ... 			  width = pop(); writeChar(pop(), width);
57 trap b Generi?e run time gre?ku
		  zavisno od vrednosti b se ispisuje odgovarajuca
		  poruka o gre?ci;
		  prekid izvr?avanja;
59 dup_x1 ...,val2, val1 
		  ...,val1, val2, val1
	Umetanje kopije vr?ne vrednosti ispod druge
	vrednosti sa vrha steka izraza.
	vrednost sa vrha steka se kopira i ubacuje
	ispod druge vrednosti sa vrha steka izraza.
60 dup_x2 ...,val1, val2, val3 
		  ...,val3, val1, val2, val3
	Umetanje kopije vr?ne vrednosti ispod
	trece vrednosti sa vrha steka izraza.
	vrednost sa vrha steka se kopira i
	ubacuje ispod trece vrednosti sa
	vrha steka izraza.
 */

public class CodeGenerator extends VisitorAdaptor {
	Logger log = Logger.getLogger(getClass());
	
	private int mainPc=0;
	
	public int getMainPc() {
		return mainPc;
	}
	
	public void report_fatal(String message, SyntaxNode info) {
		StringBuilder msg = new StringBuilder(message);
		int line = (info == null) ? 0: info.getLine();
		if (line != 0)
			msg.append (" na liniji ").append(line);
		log.fatal(msg.toString());
		System.exit(1);
	}
	
	/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	//METHODS
	/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
	public void visit(MethodName methodName) {
		if (methodName.getMethodName().equals("main"))
			mainPc=Code.pc;
		
		Code.put(Code.enter);
		Code.put(methodName.obj.getLevel());
		// bag, getLocalSymbols vraca i konstante i varijable iako, u deklaraciji kaze
		// Meth: kolekcija lokalnih promenljivih
		// private SymbolDataStructure locals;
		// tako da je drugi parametar za enter veci nego sto mora da bude,
		// ali nije previse bitno jer je bitno samo da se alocira dovoljno prostora,
		// a alocira se vise nego dovoljno
		Code.put(methodName.obj.getLocalSymbols().size());
	}
	
	public void visit(MethodDecl methodDecl) {
		Code.put(Code.exit);
		Code.put(Code.return_);		
	}
	
	/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	//DESIGNATOR
	/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
	// Array adr push na stack
	public void visit(DesignatorName designatorName) {
		if(((Designator)designatorName.getParent()).getArrayOptional() instanceof ArrayOpt) {
			Code.load(designatorName.obj);
		}
	}
	
	/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	//EXPR
	/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
	public void visit(BaseConst baseConst) {
		if (baseConst.getConstantValues() instanceof ConstBool) {
			Code.loadConst(((ConstBool)baseConst.getConstantValues()).getB1().equals("true")?1:0);
		}
		if (baseConst.getConstantValues() instanceof ConstInt) {
			Code.loadConst(((ConstInt)baseConst.getConstantValues()).getN1());
		}
		if (baseConst.getConstantValues() instanceof ConstChar) {
			Code.loadConst(((ConstChar)baseConst.getConstantValues()).getC1().charAt(1));
		}
	}
	
	public void visit(BaseCall baseCall) {
		Code.load(baseCall.getDesignator().obj);
	}
	
	public void visit(BaseParens baseParens) {
		//Expr je vec na stacku
	}
	
	public void visit(BaseNew baseNew) {
		Code.put(Code.newarray);
		if(baseNew.getStandardType().getTypeName().equals("char")) {
			Code.put(0);
		} else {
			Code.put(1);
		}
	}
	
	public void visit(Factor factor) {
		if (factor.getMinusOptional() instanceof MinusOpt) {
			Code.put(Code.neg);
		}
	}
	
	public void visit(MultipleFactors multipleFactors) {
		if (multipleFactors.getMulop() instanceof MultOp) {
			Code.put(Code.mul);
		}
		if (multipleFactors.getMulop() instanceof DivOp) {
			Code.put(Code.div);
		}
		if (multipleFactors.getMulop() instanceof ModOp) {
			Code.put(Code.rem);
		}
	}
	
	public void visit(MultipleTerms multipleTerms) {
		if (multipleTerms.getAddop() instanceof PlusOp) {
			Code.put(Code.add);
		}
		if (multipleTerms.getAddop() instanceof MinusOp) {
			Code.put(Code.sub);
		}
	}
	
	public void visit(NotZeroExpr notZeroExpr) {
		Code.put(Code.dup_x1);
		Code.put(Code.pop);
		Code.put(Code.dup);
		Code.put(Code.const_n);
		Code.put(Code.jcc+Code.eq);
		Code.put2(5);
		Code.put(Code.dup_x1);
		Code.put(Code.pop);
		Code.put(Code.pop);
	}
	
	/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	//PRINT
	/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
	public void visit(PrintSt printSt) {
		if (printSt.getExprWrapper().struct==Tab.charType) {
			Code.put(Code.bprint);
		} else {
			Code.put(Code.print);
		}
	}
	
	public void visit(PrintOpt printOpt) {
		Code.loadConst(printOpt.getN1());
	}
	
	public void visit(NoPrintOpt noPrintOpt) {
		Code.loadConst(0);
	}
	
	/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	//READ
	/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
	public void visit(ReadSt readSt) {
		if(readSt.getDesignator().obj.getType()!=Tab.intType) {
			Code.put(Code.bread);
		} else {
			Code.put(Code.read);
		}
		Code.store(readSt.getDesignator().obj);
	}
	
	/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	//RETURN
	/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
	public void visit(ReturnSt returnSt) {
		Code.put(Code.exit);
		Code.put(Code.return_);
	}
	
	/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	//DESIGNATOR STATEMENT
	/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
	public void visit(DesignatorStatementV designatorStatement) {
		if(designatorStatement.getRightSide() instanceof EqualAssign){
			Code.store(designatorStatement.getDesignator().obj);
		}
		if(designatorStatement.getRightSide() instanceof PlusPlus){
			if (designatorStatement.getDesignator().obj.getKind()==Obj.Elem) {
				Code.put(Code.dup2);
			}
			Code.load(designatorStatement.getDesignator().obj);
			Code.put(Code.const_1);
			Code.put(Code.add);
			Code.store(designatorStatement.getDesignator().obj);
		}
		if(designatorStatement.getRightSide() instanceof MinusMinus){
			if (designatorStatement.getDesignator().obj.getKind()==Obj.Elem) {
				Code.put(Code.dup2);
			}
			Code.load(designatorStatement.getDesignator().obj);
			Code.put(Code.const_1);
			Code.put(Code.sub);
			Code.store(designatorStatement.getDesignator().obj);
		}
	}
	
}
