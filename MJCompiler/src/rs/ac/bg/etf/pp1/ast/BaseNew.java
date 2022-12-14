// generated with ast extension for cup
// version 0.8
// 20/7/2022 8:51:46


package rs.ac.bg.etf.pp1.ast;

public class BaseNew extends BaseFactor {

    private StandardType StandardType;
    private ArrayOptional ArrayOptional;

    public BaseNew (StandardType StandardType, ArrayOptional ArrayOptional) {
        this.StandardType=StandardType;
        if(StandardType!=null) StandardType.setParent(this);
        this.ArrayOptional=ArrayOptional;
        if(ArrayOptional!=null) ArrayOptional.setParent(this);
    }

    public StandardType getStandardType() {
        return StandardType;
    }

    public void setStandardType(StandardType StandardType) {
        this.StandardType=StandardType;
    }

    public ArrayOptional getArrayOptional() {
        return ArrayOptional;
    }

    public void setArrayOptional(ArrayOptional ArrayOptional) {
        this.ArrayOptional=ArrayOptional;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(StandardType!=null) StandardType.accept(visitor);
        if(ArrayOptional!=null) ArrayOptional.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(StandardType!=null) StandardType.traverseTopDown(visitor);
        if(ArrayOptional!=null) ArrayOptional.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(StandardType!=null) StandardType.traverseBottomUp(visitor);
        if(ArrayOptional!=null) ArrayOptional.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("BaseNew(\n");

        if(StandardType!=null)
            buffer.append(StandardType.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(ArrayOptional!=null)
            buffer.append(ArrayOptional.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [BaseNew]");
        return buffer.toString();
    }
}
