// generated with ast extension for cup
// version 0.8
// 20/7/2022 8:51:46


package rs.ac.bg.etf.pp1.ast;

public class DesignatorError extends DesignatorStatement {

    public DesignatorError () {
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("DesignatorError(\n");

        buffer.append(tab);
        buffer.append(") [DesignatorError]");
        return buffer.toString();
    }
}
