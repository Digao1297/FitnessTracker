package br.com.fitnesstracker;

public class Register {
    private String type;
    private double result;
    private String createdDate;

    public Register(String type, double result, String createdDate) {
        this.type = type;
        this.result = result;
        this.createdDate = createdDate;
    }

    public String getType() {
        return type;
    }

    public double getResult() {
        return result;
    }

    public String getCreatedDate() {
        return createdDate;
    }
}
