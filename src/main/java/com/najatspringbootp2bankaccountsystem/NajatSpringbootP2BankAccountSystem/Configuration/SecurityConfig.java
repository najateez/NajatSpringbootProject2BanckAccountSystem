package com.najatspringbootp2bankaccountsystem.NajatSpringbootP2BankAccountSystem.Configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
             //   .anyRequest().authenticated()
                .anyRequest().permitAll() // Allow access to all requests without authentication
                .and()
                .httpBasic()
                .and()
                .csrf().disable();
    }

    /*  for Security MisConfiguration:
    In controllere there is no annotation @PreAuthorize("hasRole('USER')") and @PreAuthorize("hasRole('ADMin')"),
    because using these annotations is a best practice to enforce access control based on user (roles).
     */
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication()
                .withUser("najateez")
                .password(passwordEncoder().encode("najateez@123"))
                .roles("ADMIN") // Assigning the 'ADMIN' role to the user 'najateez'
                .and()
                .withUser("admin")
                .password(passwordEncoder().encode("admin@123"))
                .roles("USER"); // Assigning the 'USER' role to the user 'admin'
    }

    //configured a password encoder, (Security configuration).
    @Bean
    public PasswordEncoder passwordEncoder() {

        return new BCryptPasswordEncoder();
    }


}
