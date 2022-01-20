package com.calculator.webservice.security;

import com.calculator.webservice.calculatorapi.repository.UserRepo;
import com.calculator.webservice.calculatorapi.service.UserPrincipalDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private UserRepo userRepo;

    @Autowired
    private UserPrincipalDetailService userPrincipalDetailService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public SecurityConfig(UserRepo userRepo) {
        this.userRepo = userRepo;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) {
        auth.authenticationProvider(daoAuthenticationProvider());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .addFilter(new JwtAuthenticationFilter(authenticationManager()))
                .addFilter(new JwtAuthorizationFilter(authenticationManager(),this.userRepo))
                .authorizeRequests()
                .antMatchers("/add/**").permitAll()
                .antMatchers("/sub/**").authenticated()
                .antMatchers("/mul/**").hasAnyRole("ADMIN","MANAGER")
                .antMatchers("/div/**").hasAnyRole("ADMIN","MANAGER")
                .antMatchers("/add/**").hasAnyAuthority("USER","ROLE_MANAGER","ROLE_ADMIN")
                .antMatchers("v2/mul/**").hasAnyAuthority("ROLE_MANAGER","ROLE_ADMIN")
                .antMatchers("v2/div/**").hasAnyAuthority("ROLE_MANAGER","ROLE_ADMIN")
                .antMatchers("/users").hasRole("ROLE_ADMIN")
                .and()
                .httpBasic();
    }

    @Bean
    DaoAuthenticationProvider daoAuthenticationProvider() {
        DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
        daoAuthenticationProvider.setPasswordEncoder(passwordEncoder);
        daoAuthenticationProvider.setUserDetailsService(userPrincipalDetailService);

        return daoAuthenticationProvider;
    }

    /*
    @Bean
    PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
     */
}
