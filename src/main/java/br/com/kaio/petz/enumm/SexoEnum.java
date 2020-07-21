package br.com.kaio.petz.enumm;

public enum SexoEnum {
    MASCULINO("M"),
    FEMININO("F");

    private String val;

    SexoEnum(String val){
        this.val=val;
    }

    public String getVal() {
        return val;
    }
}
