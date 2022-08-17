// generated with ast extension for cup
// version 0.8
// 17/7/2022 21:58:48


package rs.ac.bg.etf.pp1.ast;

public class BaseConst extends BaseFactor {

    private ConstantValues ConstantValues;

    public BaseConst (ConstantValues ConstantValues) {
        this.ConstantValues=ConstantValues;
        if(ConstantValues!=null) ConstantValues.setParent(this);
    }

    public ConstantValues getConstantValues() {
        return ConstantValues;
    }

    public void setConstantValues(ConstantValues ConstantValues) {
        this.ConstantValues=ConstantValues;
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
        buffer.append("BaseConst(\n");

        if(ConstantValues!=null)
            buffer.append(ConstantValues.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [BaseConst]");
        return buffer.toString();
    }
}
