// generated with ast extension for cup
// version 0.8
// 8/7/2022 18:36:20


package rs.ac.bg.etf.pp1.ast;

public class MultipleBase extends BaseFactor {

    private BaseExpr BaseExpr;
    private Expop Expop;
    private BaseFactor BaseFactor;

    public MultipleBase (BaseExpr BaseExpr, Expop Expop, BaseFactor BaseFactor) {
        this.BaseExpr=BaseExpr;
        if(BaseExpr!=null) BaseExpr.setParent(this);
        this.Expop=Expop;
        if(Expop!=null) Expop.setParent(this);
        this.BaseFactor=BaseFactor;
        if(BaseFactor!=null) BaseFactor.setParent(this);
    }

    public BaseExpr getBaseExpr() {
        return BaseExpr;
    }

    public void setBaseExpr(BaseExpr BaseExpr) {
        this.BaseExpr=BaseExpr;
    }

    public Expop getExpop() {
        return Expop;
    }

    public void setExpop(Expop Expop) {
        this.Expop=Expop;
    }

    public BaseFactor getBaseFactor() {
        return BaseFactor;
    }

    public void setBaseFactor(BaseFactor BaseFactor) {
        this.BaseFactor=BaseFactor;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(BaseExpr!=null) BaseExpr.accept(visitor);
        if(Expop!=null) Expop.accept(visitor);
        if(BaseFactor!=null) BaseFactor.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(BaseExpr!=null) BaseExpr.traverseTopDown(visitor);
        if(Expop!=null) Expop.traverseTopDown(visitor);
        if(BaseFactor!=null) BaseFactor.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(BaseExpr!=null) BaseExpr.traverseBottomUp(visitor);
        if(Expop!=null) Expop.traverseBottomUp(visitor);
        if(BaseFactor!=null) BaseFactor.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("MultipleBase(\n");

        if(BaseExpr!=null)
            buffer.append(BaseExpr.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(Expop!=null)
            buffer.append(Expop.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(BaseFactor!=null)
            buffer.append(BaseFactor.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [MultipleBase]");
        return buffer.toString();
    }
}
