package com.calculator.webservice.calculatorapi.service;

import com.calculator.webservice.calculatorapi.model.User;
import com.calculator.webservice.calculatorapi.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.Arrays;
import java.util.List;

@Service
public class DbInit implements CommandLineRunner {

    private UserRepo userRepo;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public DbInit(PasswordEncoder passwordEncoder, UserRepo userRepo){
        this.passwordEncoder = passwordEncoder;
        this.userRepo = userRepo;
    }


    @Override
    public void run(String... args) {

        User pari = new User("pari", passwordEncoder.encode("pari123"), "USER", "");
        User admin = new User("admin", passwordEncoder.encode("admin123"), "ADMIN", "add,sub");
        User manager = new User("manager", passwordEncoder.encode("manager123"), "MANAGER", "mul,div");

        List<User> users = Arrays.asList(pari,admin,manager);
        userRepo.saveAll(users);

    }
}
