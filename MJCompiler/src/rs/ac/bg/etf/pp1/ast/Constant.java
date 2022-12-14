// generated with ast extension for cup
// version 0.8
// 20/7/2022 8:51:46


package rs.ac.bg.etf.pp1.ast;

public class Constant implements SyntaxNode {

    private SyntaxNode parent;
    private int line;
    private String constName;
    private ConstantValues ConstantValues;

    public Constant (String constName, ConstantValues ConstantValues) {
        this.constName=constName;
        this.ConstantValues=ConstantValues;
        if(ConstantValues!=null) ConstantValues.setParent(this);
    }

    public String getConstName() {
        return constName;
    }

    public void setConstName(String constName) {
        this.constName=constName;
    }

    public ConstantValues getConstantValues() {
        return ConstantValues;
    }

    public void setConstantValues(ConstantValues ConstantValues) {
        this.ConstantValues=ConstantValues;
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
        if(ConstantValues!=null) ConstantValues.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(ConstantValues!=null) ConstantValues.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(ConstantValues!=null) ConstantValues.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("Constant(\n");

        buffer.append(" "+tab+constName);
        buffer.append("\n");

        if(ConstantValues!=null)
            buffer.append(ConstantValues.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [Constant]");
        return buffer.toString();
    }
}
