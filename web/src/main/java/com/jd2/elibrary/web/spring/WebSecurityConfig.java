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
                //для всех пользователей
                .antMatchers("/index", "/login", "/registration").permitAll()
                //для клиентов
                .antMatchers("/customerPage").hasRole("CUSTOMER")
                //для библиотекарей
                .antMatchers("/librarianPage","/editBookCatalogue").hasRole("LIBRARIAN")
                //все остальное для всех аутентифицированных пользователей
                .anyRequest().authenticated();
    }
}
