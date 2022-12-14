// generated with ast extension for cup
// version 0.8
// 20/7/2022 8:51:46


package rs.ac.bg.etf.pp1.ast;

public class ConstDecl implements SyntaxNode {

    private SyntaxNode parent;
    private int line;
    private StandardType StandardType;
    private Constants Constants;

    public ConstDecl (StandardType StandardType, Constants Constants) {
        this.StandardType=StandardType;
        if(StandardType!=null) StandardType.setParent(this);
        this.Constants=Constants;
        if(Constants!=null) Constants.setParent(this);
    }

    public StandardType getStandardType() {
        return StandardType;
    }

    public void setStandardType(StandardType StandardType) {
        this.StandardType=StandardType;
    }

    public Constants getConstants() {
        return Constants;
    }

    public void setConstants(Constants Constants) {
        this.Constants=Constants;
    }

    public SyntaxNode getParent() {
        return parent;
    }

    public void setParent(SyntaxNode parent) {
        this.parent=parent;
    }

    public int getLine() {
        return line;
    }

    public void setLine(int line) {
        this.line=line;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(StandardType!=null) StandardType.accept(visitor);
        if(Constants!=null) Constants.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(StandardType!=null) StandardType.traverseTopDown(visitor);
        if(Constants!=null) Constants.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(StandardType!=null) StandardType.traverseBottomUp(visitor);
        if(Constants!=null) Constants.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("ConstDecl(\n");

        if(StandardType!=null)
            buffer.append(StandardType.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(Constants!=null)
            buffer.append(Constants.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [ConstDecl]");
        return buffer.toString();
    }
}
