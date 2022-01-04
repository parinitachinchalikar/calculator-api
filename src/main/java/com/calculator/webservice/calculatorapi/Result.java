package com.calculator.webservice.calculatorapi;

public class Result {

    private int a;
    private int b;
    private int result;

    public Result() {
    }

    public Result(int a, int b, int result) {
        this.a = a;
        this.b = b;
        this.result = result;
    }

    public int getA() {
        return a;
    }

    public int getB() {
        return b;
    }

    public int getResult() {
        return result;
    }
}
