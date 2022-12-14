// generated with ast extension for cup
// version 0.8
// 20/7/2022 8:51:46


package rs.ac.bg.etf.pp1.ast;

public class PrintSt extends Statement {

    private ExprWrapper ExprWrapper;
    private PrintOptional PrintOptional;

    public PrintSt (ExprWrapper ExprWrapper, PrintOptional PrintOptional) {
        this.ExprWrapper=ExprWrapper;
        if(ExprWrapper!=null) ExprWrapper.setParent(this);
        this.PrintOptional=PrintOptional;
        if(PrintOptional!=null) PrintOptional.setParent(this);
    }

    public ExprWrapper getExprWrapper() {
        return ExprWrapper;
    }

    public void setExprWrapper(ExprWrapper ExprWrapper) {
        this.ExprWrapper=ExprWrapper;
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
        if(ExprWrapper!=null) ExprWrapper.accept(visitor);
        if(PrintOptional!=null) PrintOptional.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(ExprWrapper!=null) ExprWrapper.traverseTopDown(visitor);
        if(PrintOptional!=null) PrintOptional.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(ExprWrapper!=null) ExprWrapper.traverseBottomUp(visitor);
        if(PrintOptional!=null) PrintOptional.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("PrintSt(\n");

        if(ExprWrapper!=null)
            buffer.append(ExprWrapper.toString("  "+tab));
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
