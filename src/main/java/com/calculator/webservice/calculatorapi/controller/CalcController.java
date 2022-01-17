package com.calculator.webservice.calculatorapi.controller;

import com.calculator.webservice.calculatorapi.model.User;
import com.calculator.webservice.calculatorapi.repository.UserRepo;
import com.calculator.webservice.calculatorapi.service.CalcService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
public class CalcController {

    @Autowired
    private CalcService calcService;

    @Autowired
    private UserRepo userRepo;

    @RequestMapping(method = RequestMethod.GET, value = "/users")
    public List<User> users(){
        return userRepo.findAll();
    }

    @RequestMapping( method = RequestMethod.GET, value = "/add/{x}/{y}")
    public int add(@PathVariable int x, @PathVariable int y){
        return calcService.sum(x, y);
    }
    //test comment added
    @RequestMapping( method = RequestMethod.GET, value = "/sub/{x}/{y}")
    public int sub(@PathVariable int x, @PathVariable int y) {
        return calcService.difference(x,y);
    }

    @RequestMapping( method = RequestMethod.GET, value = "/mul/{x}/{y}")
    public int mul(@PathVariable int x, @PathVariable int y) {
        return calcService.multiply(x,y);
    }

    @RequestMapping( method = RequestMethod.GET, value = "/v2/mul/{x}/{y}")
    public int multiplication(@PathVariable int x, @PathVariable int y) {
        return calcService.multiplication(x,y);
    }

    @RequestMapping( method = RequestMethod.GET, value = "/div/{x}/{y}")
    public int div(@PathVariable int x, @PathVariable int y) {
        return calcService.divide(x,y);
    }

    @RequestMapping( method = RequestMethod.GET, value = "/v2/div/{x}/{y}")
    public int division(@PathVariable int x, @PathVariable int y) {
        return calcService.division(x,y);
    }


}
