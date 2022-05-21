package br.com.unialfa.contasapagar.enuns;

public enum Type {

    PAY("P"), RECIVE("R");

    Type(String type) {
        this.type = type;
    }

    private final String type;
    public String getType() {
        return type;
    }
}