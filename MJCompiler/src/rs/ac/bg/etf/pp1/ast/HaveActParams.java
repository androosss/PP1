// generated with ast extension for cup
// version 0.8
// 20/7/2022 8:51:46


package rs.ac.bg.etf.pp1.ast;

public class HaveActParams extends ActParams {

    private ActParams ActParams;
    private ExprWrapper ExprWrapper;

    public HaveActParams (ActParams ActParams, ExprWrapper ExprWrapper) {
        this.ActParams=ActParams;
        if(ActParams!=null) ActParams.setParent(this);
        this.ExprWrapper=ExprWrapper;
        if(ExprWrapper!=null) ExprWrapper.setParent(this);
    }

    public ActParams getActParams() {
        return ActParams;
    }

    public void setActParams(ActParams ActParams) {
        this.ActParams=ActParams;
    }

    public ExprWrapper getExprWrapper() {
        return ExprWrapper;
    }

    public void setExprWrapper(ExprWrapper ExprWrapper) {
        this.ExprWrapper=ExprWrapper;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(ActParams!=null) ActParams.accept(visitor);
        if(ExprWrapper!=null) ExprWrapper.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(ActParams!=null) ActParams.traverseTopDown(visitor);
        if(ExprWrapper!=null) ExprWrapper.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(ActParams!=null) ActParams.traverseBottomUp(visitor);
        if(ExprWrapper!=null) ExprWrapper.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("HaveActParams(\n");

        if(ActParams!=null)
            buffer.append(ActParams.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(ExprWrapper!=null)
            buffer.append(ExprWrapper.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [HaveActParams]");
        return buffer.toString();
    }
}
