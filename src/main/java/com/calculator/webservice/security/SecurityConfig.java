package com.calculator.webservice.security;

import com.calculator.webservice.calculatorapi.repository.UserDao;
import com.calculator.webservice.calculatorapi.service.JwtUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.BeanIds;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private UserDao userDao;

    @Autowired
    private JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;

    @Autowired
    private JwtRequestFilter jwtRequestFilter;

    @Autowired
    private JwtUserDetailsService jwtUserDetailsService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public SecurityConfig(UserDao userDao) {
        this.userDao = userDao;
    }

   /* @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        // configure AuthenticationManager so that it knows from where to load
        // user for matching credentials
        // Use BCryptPasswordEncoder
        auth.userDetailsService(jwtUserDetailsService).passwordEncoder(passwordEncoder);
    }*/

//    @Bean(name = BeanIds.AUTHENTICATION_MANAGER)

//    @Override
    @Bean
    public AuthenticationManager customAuthenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    /*@Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth
//                .authenticationProvider(daoAuthenticationProvider())
//                .parentAuthenticationManager(authenticationManagerBean())
                .userDetailsService(jwtUserDetailsService);
    }*/

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http.authorizeRequests()
                .antMatchers("/authenticate")
                .permitAll().anyRequest().authenticated().and()
                .cors().and()
                .httpBasic().and()
                .exceptionHandling()
//                .accessDeniedHandler(accessDeniedHandler)
                .authenticationEntryPoint(jwtAuthenticationEntryPoint).and()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);

        http.csrf().disable();

        http.addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);
       /* http
                //remove csrf and state in session because it is not needed in jwt
                .csrf().disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authorizeRequests()
                .antMatchers("/authenticate","/register").permitAll().anyRequest() .authenticated()
                //added jwt filters (1. authentication 2. authorization)
                *//*
                .addFilter(new JwtAuthenticationFilter(authenticationManager()))
                .addFilter(new JwtAuthorizationFilter(authenticationManager(),this.userRepo))
                .authorizeRequests()
                .antMatchers("/login").permitAll()
                .antMatchers("/users").permitAll()
                .antMatchers("/add/**").authenticated()
                .antMatchers("/sub/**").authenticated()
                .antMatchers("/mul/**").hasAnyRole("ADMIN","MANAGER")
                .antMatchers("/div/**").hasAnyRole("ADMIN","MANAGER")
                .antMatchers("/add/**").hasAnyAuthority("USER","ROLE_MANAGER","ROLE_ADMIN")
                .antMatchers("v2/mul/**").hasAnyAuthority("ROLE_MANAGER","ROLE_ADMIN")
                .antMatchers("v2/div/**").hasAnyAuthority("ROLE_MANAGER","ROLE_ADMIN")
                 *//*
                .and().exceptionHandling().authenticationEntryPoint(jwtAuthenticationEntryPoint)
                .and()
                .addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);*/
    }

    /*@Bean
    DaoAuthenticationProvider daoAuthenticationProvider() {
        DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
        daoAuthenticationProvider.setPasswordEncoder(passwordEncoder);
        daoAuthenticationProvider.setUserDetailsService(jwtUserDetailsService);

        return daoAuthenticationProvider;
    }*/
}
