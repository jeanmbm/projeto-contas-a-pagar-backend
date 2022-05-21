package br.com.unialfa.contasapagar.enuns;

public enum OperationType {

    UPDATE("U"), CREATE("C");

    OperationType(String ot) {
        this.operationType = ot;
    }

    private String operationType;
    public String getOperationType() {
        return operationType;
    }
}