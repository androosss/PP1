// generated with ast extension for cup
// version 0.8
// 20/7/2022 8:51:46


package rs.ac.bg.etf.pp1.ast;

public class MultipleConstants extends Constants {

    private Constants Constants;
    private Constant Constant;

    public MultipleConstants (Constants Constants, Constant Constant) {
        this.Constants=Constants;
        if(Constants!=null) Constants.setParent(this);
        this.Constant=Constant;
        if(Constant!=null) Constant.setParent(this);
    }

    public Constants getConstants() {
        return Constants;
    }

    public void setConstants(Constants Constants) {
        this.Constants=Constants;
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
        if(Constants!=null) Constants.accept(visitor);
        if(Constant!=null) Constant.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(Constants!=null) Constants.traverseTopDown(visitor);
        if(Constant!=null) Constant.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(Constants!=null) Constants.traverseBottomUp(visitor);
        if(Constant!=null) Constant.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("MultipleConstants(\n");

        if(Constants!=null)
            buffer.append(Constants.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(Constant!=null)
            buffer.append(Constant.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [MultipleConstants]");
        return buffer.toString();
    }
}
