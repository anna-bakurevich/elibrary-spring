package com.jd2.elibrary.web.controller;

import com.jd2.elibrary.model.Role;
import com.jd2.elibrary.model.User;
import com.jd2.elibrary.service.impl.UserService;
import com.jd2.elibrary.web.Util;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.awt.*;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

@Controller
@RequestMapping
public class LoginController {
    private static final Logger log = LoggerFactory.getLogger(LoginController.class);

    private final UserService userService;

    public LoginController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/login")
    public String doGet() {
        Object authUserDetails = Util.getCurrentUserDetails();
        if (authUserDetails == "anonymousUser") {
            return "login";
        }
        return "redirect:/customerPage";
    }

    @PostMapping("/login")
    public String doPost(HttpServletRequest req) {
        String login = req.getParameter("login");
        String password = req.getParameter("password");
        User user = userService.login(login, password);
        if (user == null) {
            req.setAttribute("error", "login or password invalid");
            return "registration";
        }
        Authentication auth = new UsernamePasswordAuthenticationToken(user, null, getAuthorities(user.getRole()));
        SecurityContextHolder.getContext().setAuthentication(auth);
        log.info("user {} logged", user.getLogin());

        if (user.getRole().equals(Role.LIBRARIAN)) {
            return "redirect:/librarianPage";
        }
        return "redirect:/customerPage";
    }

    private List<GrantedAuthority> getAuthorities(Role role) {
        switch (role) {
            case CUSTOMER:
                return Arrays.asList((GrantedAuthority) () -> "ROLE_CUSTOMER");
            case LIBRARIAN:
                return Arrays.asList((GrantedAuthority) () -> "ROLE_LIBRARIAN");
            default:
                throw new RuntimeException("wrong role");
        }
    }
}

