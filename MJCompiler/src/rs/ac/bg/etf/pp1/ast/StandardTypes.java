// generated with ast extension for cup
// version 0.8
// 14/7/2022 22:17:41


package rs.ac.bg.etf.pp1.ast;

public class StandardTypes extends Type {

    private StandardType StandardType;

    public StandardTypes (StandardType StandardType) {
        this.StandardType=StandardType;
        if(StandardType!=null) StandardType.setParent(this);
    }

    public StandardType getStandardType() {
        return StandardType;
    }

    public void setStandardType(StandardType StandardType) {
        this.StandardType=StandardType;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(StandardType!=null) StandardType.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(StandardType!=null) StandardType.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(StandardType!=null) StandardType.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("StandardTypes(\n");

        if(StandardType!=null)
            buffer.append(StandardType.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [StandardTypes]");
        return buffer.toString();
    }
}
