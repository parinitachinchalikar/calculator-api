package com.calculator.webservice.calculatorapi.service;

import com.calculator.webservice.calculatorapi.model.User;
import com.calculator.webservice.calculatorapi.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserPrincipalDetailService implements UserDetailsService {

    @Autowired
    private UserRepo userRepo;

    /*
    public UserPrincipalDetailService(UserRepo userRepo) {
        this.userRepo = userRepo;
    }
     */


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepo.findByUsername(username);
        UserPrincipal userPrincipal = new UserPrincipal(user);
        return userPrincipal;
    }
}
