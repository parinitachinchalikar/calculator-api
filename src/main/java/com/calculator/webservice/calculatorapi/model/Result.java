package com.calculator.webservice.calculatorapi.model;
import javax.validation.constraints.NotNull;

public class Result {

    @NotNull
    private int a;

    @NotNull
    private int b;

    @NotNull
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
