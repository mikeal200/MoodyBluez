package com.moodybluez.enterprise.controller;

import com.moodybluez.enterprise.dto.User;
import com.moodybluez.enterprise.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class MoodyBluezController {

    @Autowired
    private IUserService userService;

    /**
     * Handles the root endpoint and returns index.html
     * @return returns index page
     */
    @GetMapping("/")
    public String index(Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (!(authentication instanceof AnonymousAuthenticationToken)) {
            String currentUserName = authentication.getName();
            return "index";
        }
        else {
            return showRegistrationForm(model);
        }
    }

    @GetMapping("/metric")
    public String metric() {
        return "metric";
    }

    @GetMapping("/aboutus")
    public String aboutus() {
        return "aboutus";
    }

    @GetMapping("/user/registration")
    public String showRegistrationForm(Model model) {
        User user = new User();
        model.addAttribute("user", user);

        return "signup_form";
    }

    @PostMapping("/process_register")
    public String processRegister(User user) throws Exception {
        User savedUser;

        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String encodedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(encodedPassword);

        try {
            savedUser = userService.save(user);
        } catch (Exception e) {
            return "error";
        }



        if(savedUser != null) {
            return "register_success";
        }
        else {
            return "error";
        }
    }
}