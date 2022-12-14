// generated with ast extension for cup
// version 0.8
// 20/7/2022 8:51:46


package rs.ac.bg.etf.pp1.ast;

public class DesignatorStatementV extends DesignatorStatement {

    private Designator Designator;
    private RightSide RightSide;

    public DesignatorStatementV (Designator Designator, RightSide RightSide) {
        this.Designator=Designator;
        if(Designator!=null) Designator.setParent(this);
        this.RightSide=RightSide;
        if(RightSide!=null) RightSide.setParent(this);
    }

    public Designator getDesignator() {
        return Designator;
    }

    public void setDesignator(Designator Designator) {
        this.Designator=Designator;
    }

    public RightSide getRightSide() {
        return RightSide;
    }

    public void setRightSide(RightSide RightSide) {
        this.RightSide=RightSide;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(Designator!=null) Designator.accept(visitor);
        if(RightSide!=null) RightSide.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(Designator!=null) Designator.traverseTopDown(visitor);
        if(RightSide!=null) RightSide.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(Designator!=null) Designator.traverseBottomUp(visitor);
        if(RightSide!=null) RightSide.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("DesignatorStatementV(\n");

        if(Designator!=null)
            buffer.append(Designator.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(RightSide!=null)
            buffer.append(RightSide.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [DesignatorStatementV]");
        return buffer.toString();
    }
}
