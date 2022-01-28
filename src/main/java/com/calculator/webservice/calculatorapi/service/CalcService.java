package com.calculator.webservice.calculatorapi.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

//@Service
@Component
public class CalcService {

//    @Autowired
    private AuthenticationManager authenticationManager;

    public void authenticate(String username, String password) throws Exception {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
        } catch (DisabledException e) {
            throw new Exception("USER_DISABLED", e);
        } catch (BadCredentialsException e) {
            throw new Exception("INVALID_CREDENTIALS", e);
        }
    }

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
        if(( firstnum == 0 ) || (secondnum == 0))
            return 0;
        while(firstnum >= secondnum){
            firstnum = firstnum - secondnum;
            div++ ;
        }
        return div ;
    }

}
