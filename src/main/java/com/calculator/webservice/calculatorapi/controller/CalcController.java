package com.calculator.webservice.calculatorapi.controller;

import com.calculator.webservice.calculatorapi.model.JwtRequest;
import com.calculator.webservice.calculatorapi.model.JwtResponse;
import com.calculator.webservice.calculatorapi.model.User;
import com.calculator.webservice.calculatorapi.model.UserDTO;
import com.calculator.webservice.calculatorapi.repository.UserDao;
import com.calculator.webservice.calculatorapi.service.CalcService;
import com.calculator.webservice.calculatorapi.service.JwtUserDetailsService;
import com.calculator.webservice.calculatorapi.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

//@Import(SecurityConfig.class)
@RestController
@CrossOrigin
public class CalcController {

    @Autowired
    private CalcService calcService;

    @Autowired
    private UserDao userDao;



    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private JwtUserDetailsService userDetailsService;

    @RequestMapping(method = RequestMethod.POST, value = "/authenticate")
    public ResponseEntity<?> createAuthenticationToken(@RequestBody JwtRequest authenticationRequest) throws Exception {
        calcService.authenticate(authenticationRequest.getUsername(), authenticationRequest.getPassword());
        final UserDetails userDetails = userDetailsService
                .loadUserByUsername(authenticationRequest.getUsername());
        final String token = jwtUtil.generateToken(userDetails);
        return ResponseEntity.ok(new JwtResponse(token));
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public ResponseEntity<?> saveUser(@RequestBody UserDTO user) throws Exception {
        return ResponseEntity.ok(userDetailsService.save(user));
    }



    /*@RequestMapping(method = RequestMethod.GET, value = "/users")
    public List<User> users(){
        return userDao.findAll();
    }*/

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
