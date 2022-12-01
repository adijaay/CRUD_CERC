// package com.example.controller;
 
// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.context.annotation.Bean;
// import org.springframework.context.annotation.Configuration;
// import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
// import org.springframework.security.config.annotation.web.builders.HttpSecurity;
// import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
// import org.springframework.security.web.SecurityFilterChain;
 
// /**
//  * Spring Web MVC Security Java Config Demo Project
//  * Configures authentication and authorization for the application.
//  *
//  * @author www.codejava.net
//  *
//  */
// @Configuration
// @EnableWebSecurity
// public class Securitycontroller {
     
//     @Autowired
//     public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
//         auth
//             .inMemoryAuthentication()
//                 .withUser("admin").password("nimda").roles("ADMIN");
//     }
     
//     @Bean
//     protected SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
   
//       http.authorizeRequests()
//         .antMatchers("/indexdetail").permitAll()
//         .antMatchers("/index").access("hasRole('ADMIN')")
//         .and().formLogin();
       
//       http.csrf().disable();
//       return http.build();
//     }   
// }