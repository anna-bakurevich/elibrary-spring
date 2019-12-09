package com.jd2.elibrary.web.spring;

import com.jd2.elibrary.model.Role;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .authorizeRequests()
                .antMatchers("/index", "/login", "/registration").permitAll()
                .antMatchers("/customerPage").hasRole("CUSTOMER")
                .antMatchers("/librarianPage","/editBookCatalogue").hasRole("LIBRARIAN")
                .anyRequest().authenticated();
    }
}
