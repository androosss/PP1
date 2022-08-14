// generated with ast extension for cup
// version 0.8
// 8/7/2022 18:40:30


package rs.ac.bg.etf.pp1.ast;

public class PrintSt extends Statement {

    private Expr Expr;
    private PrintOptional PrintOptional;

    public PrintSt (Expr Expr, PrintOptional PrintOptional) {
        this.Expr=Expr;
        if(Expr!=null) Expr.setParent(this);
        this.PrintOptional=PrintOptional;
        if(PrintOptional!=null) PrintOptional.setParent(this);
    }

    public Expr getExpr() {
        return Expr;
    }

    public void setExpr(Expr Expr) {
        this.Expr=Expr;
    }

    public PrintOptional getPrintOptional() {
        return PrintOptional;
    }

    public void setPrintOptional(PrintOptional PrintOptional) {
        this.PrintOptional=PrintOptional;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(Expr!=null) Expr.accept(visitor);
        if(PrintOptional!=null) PrintOptional.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(Expr!=null) Expr.traverseTopDown(visitor);
        if(PrintOptional!=null) PrintOptional.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(Expr!=null) Expr.traverseBottomUp(visitor);
        if(PrintOptional!=null) PrintOptional.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("PrintSt(\n");

        if(Expr!=null)
            buffer.append(Expr.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(PrintOptional!=null)
            buffer.append(PrintOptional.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [PrintSt]");
        return buffer.toString();
    }
}
