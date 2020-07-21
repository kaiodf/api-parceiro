package br.com.kaio.petz.enumm;

public enum EspecieEnum {
    CANINA("C"),
    FELINA("F");

    private String val;

    EspecieEnum(String val){
        this.val=val;
    }

    public String getVal() {
        return val;
    }

}
