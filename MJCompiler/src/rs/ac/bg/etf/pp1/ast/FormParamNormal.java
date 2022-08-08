// generated with ast extension for cup
// version 0.8
// 8/7/2022 18:40:30


package rs.ac.bg.etf.pp1.ast;

public class FormParamNormal extends FormParam {

    private StandardType StandardType;
    private String formParamName;

    public FormParamNormal (StandardType StandardType, String formParamName) {
        this.StandardType=StandardType;
        if(StandardType!=null) StandardType.setParent(this);
        this.formParamName=formParamName;
    }

    public StandardType getStandardType() {
        return StandardType;
    }

    public void setStandardType(StandardType StandardType) {
        this.StandardType=StandardType;
    }

    public String getFormParamName() {
        return formParamName;
    }

    public void setFormParamName(String formParamName) {
        this.formParamName=formParamName;
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
        buffer.append("FormParamNormal(\n");

        if(StandardType!=null)
            buffer.append(StandardType.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(" "+tab+formParamName);
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [FormParamNormal]");
        return buffer.toString();
    }
}
