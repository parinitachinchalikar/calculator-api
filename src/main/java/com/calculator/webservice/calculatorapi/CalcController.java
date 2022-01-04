package com.calculator.webservice.calculatorapi;

import org.springframework.web.bind.annotation.*;

@RestController
public class CalcController {

    @RequestMapping( method = RequestMethod.GET, value = "/add/{x}/{y}")
    public Result add(@PathVariable int x, @PathVariable int y){
        return new Result(x,y,x + y);
    }

    @RequestMapping( method = RequestMethod.GET, value = "/sub/{x}/{y}")
    public Result sub(@PathVariable int x, @PathVariable int y) {
        return new Result(x, y, x - y);
    }

    @RequestMapping( method = RequestMethod.GET, value = "/mul/{x}/{y}")
    public Result mul(@PathVariable int x, @PathVariable int y) {
        return new Result(x, y, x * y);
    }

    @RequestMapping( method = RequestMethod.GET, value = "/div/{x}/{y}")
    public Result div(@PathVariable int x, @PathVariable int y) {
        return new Result(x, y, x / y);
    }
}
