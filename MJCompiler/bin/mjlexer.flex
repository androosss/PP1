package rs.ac.bg.etf.pp1;

import java_cup.runtime.Symbol;

%%

%{

	// ukljucivanje informacije o poziciji tokena
	private Symbol new_symbol(int type) {
		return new Symbol(type, yyline+1, yycolumn);
	}
	
	// ukljucivanje informacije o poziciji tokena
	private Symbol new_symbol(int type, Object value) {
		return new Symbol(type, yyline+1, yycolumn, value);
	}

%}

%cup
%line
%column

%xstate COMMENT

%eofval{
	return new_symbol(sym.EOF);
%eofval}

%%

" " 	{ }
"\b" 	{ }
"\t" 	{ }
"\r\n" 	{ }
"\f" 	{ }

"program"   { return new_symbol(sym.PROG, yytext()); }
"print" 	{ return new_symbol(sym.PRINT, yytext()); }
"read"   	{ return new_symbol(sym.READ, yytext()); }
"const"   	{ return new_symbol(sym.CONST, yytext()); }
//"enum"   	{ return new_symbol(sym.ENUM, yytext()); }
//"goto"	 	{ return new_symbol(sym.GOTO, yytext()); }
"return" 	{ return new_symbol(sym.RETURN, yytext()); }
"void" 		{ return new_symbol(sym.VOID, yytext()); }
"new" 		{ return new_symbol(sym.NEW, yytext()); }

"+" 		{ return new_symbol(sym.PLUS, yytext()); }
"-" 		{ return new_symbol(sym.MINUS, yytext()); }
"++" 		{ return new_symbol(sym.PLUSPLUS, yytext()); }
"--" 		{ return new_symbol(sym.MINUSMINUS, yytext()); }
"*" 		{ return new_symbol(sym.MUL, yytext()); }
"/" 		{ return new_symbol(sym.DIV, yytext()); }
"%" 		{ return new_symbol(sym.MOD, yytext()); }
"??"         { return new_symbol(sym.NOTZERO, yytext()); }

"=" 		{ return new_symbol(sym.EQUAL, yytext()); }
";" 		{ return new_symbol(sym.SEMI, yytext()); }
//":" 		{ return new_symbol(sym.DOUBLE, yytext()); }
"," 		{ return new_symbol(sym.COMMA, yytext()); }
//"." 		{ return new_symbol(sym.DOT, yytext()); }

//"||" 		{ return new_symbol(sym.LOGOR, yytext()); }
//"&&" 		{ return new_symbol(sym.LOGAND, yytext()); }

//"==" 		{ return new_symbol(sym.EQUALS, yytext()); }
//"!=" 		{ return new_symbol(sym.NEQUAL, yytext()); }
//"<" 		{ return new_symbol(sym.LESS, yytext()); }
//">" 		{ return new_symbol(sym.GREAT, yytext()); }
//"<=" 		{ return new_symbol(sym.LESSEQU, yytext()); }
//">="		{ return new_symbol(sym.GREATEQU, yytext()); }

"(" 		{ return new_symbol(sym.LPAREN, yytext()); }
")" 		{ return new_symbol(sym.RPAREN, yytext()); }
"[" 		{ return new_symbol(sym.LANGLE, yytext()); }
"]" 		{ return new_symbol(sym.RANGLE, yytext()); }
"{" 		{ return new_symbol(sym.LBRACE, yytext()); }
"}"			{ return new_symbol(sym.RBRACE, yytext()); }

<YYINITIAL> "//" 		     { yybegin(COMMENT); }
<COMMENT> .      { yybegin(COMMENT); }
<COMMENT> "\r\n" { yybegin(YYINITIAL); }

[0-9]+  { return new_symbol(sym.NUMBER, new Integer (yytext())); }
"true" | "false"		{ return new_symbol(sym.BOOL, yytext());}
"'"[ -~]"'"		{ return new_symbol(sym.CHAR, yytext()); }

([a-z]|[A-Z])[a-zA-Z0-9_]* 	{return new_symbol (sym.IDENT, yytext()); }

. { System.err.println("Leksicka greska ("+yytext()+") u liniji "+(yyline+1)+" kolona " +yycolumn); }






