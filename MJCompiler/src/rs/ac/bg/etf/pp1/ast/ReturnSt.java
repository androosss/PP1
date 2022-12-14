// generated with ast extension for cup
// version 0.8
// 20/7/2022 8:51:46


package rs.ac.bg.etf.pp1.ast;

public class ReturnSt extends Statement {

    private ReturnOptional ReturnOptional;

    public ReturnSt (ReturnOptional ReturnOptional) {
        this.ReturnOptional=ReturnOptional;
        if(ReturnOptional!=null) ReturnOptional.setParent(this);
    }

    public ReturnOptional getReturnOptional() {
        return ReturnOptional;
    }

    public void setReturnOptional(ReturnOptional ReturnOptional) {
        this.ReturnOptional=ReturnOptional;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(ReturnOptional!=null) ReturnOptional.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(ReturnOptional!=null) ReturnOptional.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(ReturnOptional!=null) ReturnOptional.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("ReturnSt(\n");

        if(ReturnOptional!=null)
            buffer.append(ReturnOptional.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [ReturnSt]");
        return buffer.toString();
    }
}
