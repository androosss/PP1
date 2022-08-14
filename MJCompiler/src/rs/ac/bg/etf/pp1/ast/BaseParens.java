// generated with ast extension for cup
// version 0.8
// 14/7/2022 22:17:41


package rs.ac.bg.etf.pp1.ast;

public class BaseParens extends BaseFactor {

    private ExprWrapper ExprWrapper;

    public BaseParens (ExprWrapper ExprWrapper) {
        this.ExprWrapper=ExprWrapper;
        if(ExprWrapper!=null) ExprWrapper.setParent(this);
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
        if(ExprWrapper!=null) ExprWrapper.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(ExprWrapper!=null) ExprWrapper.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(ExprWrapper!=null) ExprWrapper.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("BaseParens(\n");

        if(ExprWrapper!=null)
            buffer.append(ExprWrapper.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [BaseParens]");
        return buffer.toString();
    }
}
