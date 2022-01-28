package com.calculator.webservice.calculatorapi.repository;

import com.calculator.webservice.calculatorapi.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserDao extends JpaRepository <User, Integer>{

    UserDao findByUsername(String username);

}
