package com.jd2.elibrary.web.controller;

import com.jd2.elibrary.service.impl.UserService;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping
public class LogoutController {
    private final UserService userService;

    public LogoutController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/logout")
   public String doGet(HttpServletRequest req) {
        SecurityContextHolder.clearContext();
        try {
            req.logout();
        } catch (ServletException e) {
            throw new RuntimeException();
        }
        return "login";
    }
}
