// generated with ast extension for cup
// version 0.8
// 20/7/2022 8:51:46


package rs.ac.bg.etf.pp1.ast;

public class SingleConstant extends Constants {

    private Constant Constant;

    public SingleConstant (Constant Constant) {
        this.Constant=Constant;
        if(Constant!=null) Constant.setParent(this);
    }

    public Constant getConstant() {
        return Constant;
    }

    public void setConstant(Constant Constant) {
        this.Constant=Constant;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(Constant!=null) Constant.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(Constant!=null) Constant.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(Constant!=null) Constant.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("SingleConstant(\n");

        if(Constant!=null)
            buffer.append(Constant.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [SingleConstant]");
        return buffer.toString();
    }
}
