// generated with ast extension for cup
// version 0.8
// 20/7/2022 8:51:46


package rs.ac.bg.etf.pp1.ast;

public class VarDeclV extends VarDecl {

    private StandardType StandardType;
    private Variables Variables;

    public VarDeclV (StandardType StandardType, Variables Variables) {
        this.StandardType=StandardType;
        if(StandardType!=null) StandardType.setParent(this);
        this.Variables=Variables;
        if(Variables!=null) Variables.setParent(this);
    }

    public StandardType getStandardType() {
        return StandardType;
    }

    public void setStandardType(StandardType StandardType) {
        this.StandardType=StandardType;
    }

    public Variables getVariables() {
        return Variables;
    }

    public void setVariables(Variables Variables) {
        this.Variables=Variables;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(StandardType!=null) StandardType.accept(visitor);
        if(Variables!=null) Variables.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(StandardType!=null) StandardType.traverseTopDown(visitor);
        if(Variables!=null) Variables.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(StandardType!=null) StandardType.traverseBottomUp(visitor);
        if(Variables!=null) Variables.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("VarDeclV(\n");

        if(StandardType!=null)
            buffer.append(StandardType.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(Variables!=null)
            buffer.append(Variables.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [VarDeclV]");
        return buffer.toString();
    }
}
