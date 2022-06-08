package br.com.unialfa.contasapagar.enuns;

public enum Type {

    PAY("Pagamento"), RECIVE("Recebimento");

    Type(String type) {
        this.type = type;
    }

    private final String type;
    public String getType() {
        return type;
    }
}