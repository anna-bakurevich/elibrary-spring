package com.jd2.elibrary.web.controller;

import com.jd2.elibrary.model.Role;
import com.jd2.elibrary.model.User;
import com.jd2.elibrary.service.impl.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping
public class EditController {
    private static final Logger log = LoggerFactory.getLogger(EditController.class);

    private final UserService userService;

    public EditController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/edit")
    public String doGet() {
        return "edit";
    }

    @PostMapping("/edit")
    public String doPost(HttpServletRequest req, UsernamePasswordAuthenticationToken principal) {

        User user = (User) principal.getPrincipal();
        String firstName = req.getParameter("firstName");
        String lastName = req.getParameter("lastName");
        String phone = req.getParameter("phone");
        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setPhone(phone);
        userService.update(user, firstName, lastName, phone);
        log.info("user {} update", user.getId());

        if (user.getRole().equals(Role.LIBRARIAN)){
            return "redirect:/librarianPage";
        }
        return "redirect:/customerPage";
    }
}
