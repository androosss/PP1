// generated with ast extension for cup
// version 0.8
// 8/7/2022 18:40:30


package rs.ac.bg.etf.pp1.ast;

public class PlusPlus extends RightSide {

    public PlusPlus () {
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
        buffer.append("PlusPlus(\n");

        buffer.append(tab);
        buffer.append(") [PlusPlus]");
        return buffer.toString();
    }
}