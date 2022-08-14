// generated with ast extension for cup
// version 0.8
// 14/7/2022 22:17:41


package rs.ac.bg.etf.pp1.ast;

public interface Visitor { 

    public void visit(Mulop Mulop);
    public void visit(ExprWrapper ExprWrapper);
    public void visit(ArrayOptional ArrayOptional);
    public void visit(Constants Constants);
    public void visit(Variable Variable);
    public void visit(StatementList StatementList);
    public void visit(ActParams ActParams);
    public void visit(PrintOptional PrintOptional);
    public void visit(Addop Addop);
    public void visit(ReturnOptional ReturnOptional);
    public void visit(DeclList DeclList);
    public void visit(Term Term);
    public void visit(MinusOptional MinusOptional);
    public void visit(FormParams FormParams);
    public void visit(CallOptional CallOptional);
    public void visit(RightSide RightSide);
    public void visit(BaseFactor BaseFactor);
    public void visit(Expr Expr);
    public void visit(Variables Variables);
    public void visit(DesignatorStatement DesignatorStatement);
    public void visit(FormParamList FormParamList);
    public void visit(Statement Statement);
    public void visit(VarDecl VarDecl);
    public void visit(Type Type);
    public void visit(Declaration Declaration);
    public void visit(MethodDeclList MethodDeclList);
    public void visit(ConstantValues ConstantValues);
    public void visit(FormParam FormParam);
    public void visit(ModOp ModOp);
    public void visit(DivOp DivOp);
    public void visit(MultOp MultOp);
    public void visit(MinusOp MinusOp);
    public void visit(PlusOp PlusOp);
    public void visit(NoActParam NoActParam);
    public void visit(HaveActParams HaveActParams);
    public void visit(NoCallOpt NoCallOpt);
    public void visit(CallOpt CallOpt);
    public void visit(BaseParens BaseParens);
    public void visit(BaseNew BaseNew);
    public void visit(BaseConst BaseConst);
    public void visit(BaseCall BaseCall);
    public void visit(NoMinusOpt NoMinusOpt);
    public void visit(MinusOpt MinusOpt);
    public void visit(Factor Factor);
    public void visit(SingleFactor SingleFactor);
    public void visit(MultipleFactors MultipleFactors);
    public void visit(SingleTerm SingleTerm);
    public void visit(MultipleTerms MultipleTerms);
    public void visit(NotZeroExpr NotZeroExpr);
    public void visit(NormalExpr NormalExpr);
    public void visit(MinusMinus MinusMinus);
    public void visit(PlusPlus PlusPlus);
    public void visit(EqualAssign EqualAssign);
    public void visit(NoArrayOpt NoArrayOpt);
    public void visit(ArrayOpt ArrayOpt);
    public void visit(DesignatorName DesignatorName);
    public void visit(Designator Designator);
    public void visit(DesignatorError DesignatorError);
    public void visit(DesignatorStatementV DesignatorStatementV);
    public void visit(NoReturnOpt NoReturnOpt);
    public void visit(ReturnOpt ReturnOpt);
    public void visit(NoPrintOpt NoPrintOpt);
    public void visit(PrintOpt PrintOpt);
    public void visit(StBlock StBlock);
    public void visit(ReturnSt ReturnSt);
    public void visit(PrintSt PrintSt);
    public void visit(ReadSt ReadSt);
    public void visit(DesignatorSt DesignatorSt);
    public void visit(NoStatements NoStatements);
    public void visit(HaveStatements HaveStatements);
    public void visit(FormParamArray FormParamArray);
    public void visit(FormParamNormal FormParamNormal);
    public void visit(SingleFormParam SingleFormParam);
    public void visit(MultipleFormParams MultipleFormParams);
    public void visit(NoFormParams NoFormParams);
    public void visit(HaveFormParams HaveFormParams);
    public void visit(MethodName MethodName);
    public void visit(MethodDecl MethodDecl);
    public void visit(NoMetDecl NoMetDecl);
    public void visit(MethodDeclarations MethodDeclarations);
    public void visit(ConstChar ConstChar);
    public void visit(ConstBool ConstBool);
    public void visit(ConstInt ConstInt);
    public void visit(Constant Constant);
    public void visit(SingleConstant SingleConstant);
    public void visit(MultipleConstants MultipleConstants);
    public void visit(ConstDecl ConstDecl);
    public void visit(VariableArray VariableArray);
    public void visit(VariableNormal VariableNormal);
    public void visit(SingleVariable SingleVariable);
    public void visit(VariablesList VariablesList);
    public void visit(StandardType StandardType);
    public void visit(VoidType VoidType);
    public void visit(StandardTypes StandardTypes);
    public void visit(VarDeclV VarDeclV);
    public void visit(DeclarationErr DeclarationErr);
    public void visit(DeclarationConst DeclarationConst);
    public void visit(DeclarationVar DeclarationVar);
    public void visit(NoDeclList NoDeclList);
    public void visit(DeclsList DeclsList);
    public void visit(ProgName ProgName);
    public void visit(Program Program);

}
