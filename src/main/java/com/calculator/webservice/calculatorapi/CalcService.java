package com.calculator.webservice.calculatorapi;

import org.springframework.stereotype.Service;

@Service
public class CalcService {

    public int sum(int firstnum, int secondnum){
        return firstnum + secondnum ;
    }

    public int difference(int firstnum, int secondnum){
        return firstnum - secondnum;
    }

    public int multiply(int firstnum, int secondnum){
        return firstnum * secondnum ;
    }

    //Multiplication without arithmetic operator
    public int multiplication(int firstnum, int secondnum){
        if(( firstnum == 0 ) || (secondnum == 0))
            return 0;
        else
            return (firstnum + (multiplication(firstnum, secondnum - 1)));
    }

    public int divide(int firstnum, int secondnum){
        return firstnum / secondnum ;
    }

    //Division without mod operator
    public int division(int firstnum, int secondnum){
        int div = 0 ;
        while(firstnum >= secondnum){
            firstnum = firstnum - secondnum;
            div++ ;
        }
        return div ;
    }

}
