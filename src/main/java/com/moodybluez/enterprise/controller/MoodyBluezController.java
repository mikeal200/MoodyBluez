package com.moodybluez.enterprise.controller;

import com.moodybluez.enterprise.dto.User;
import com.moodybluez.enterprise.service.CustomUserDetails;
import com.moodybluez.enterprise.service.IUserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class MoodyBluezController {

    @Autowired
    private IUserService userService;

    Logger log = LoggerFactory.getLogger(this.getClass());

    /**
     * Handles the root endpoint and returns index.html
     * @return returns index page
     */
    @GetMapping("/")
    public String index(Model model) {
        int userId = 0;
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (!(authentication instanceof AnonymousAuthenticationToken)) {
            Object principal = authentication.getPrincipal();
            userId = ((CustomUserDetails)principal).getUserId();
            String userName = ((CustomUserDetails)principal).getUsername();
            log.info("Logged in as: " + userName);
            return "index";
        }
        else {
            return showRegistrationForm(model);
        }
    }

    @RequestMapping(value = "/userId", method = RequestMethod.GET)
    @ResponseBody
    public int currentUserId() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (!(authentication instanceof AnonymousAuthenticationToken)) {
            Object principal = authentication.getPrincipal();
            return ((CustomUserDetails)principal).getUserId();
        }
        else {
            return 0;
        }
    }

    @GetMapping("/login")
    public String login() {
        log.info("Login");
        return "login";
    }

    @GetMapping("/metric")
    public String metric() {
        log.info("Metrics");
        return "metric";
    }

    @GetMapping("/user/registration")
    public String showRegistrationForm(Model model) {
        User user = new User();
        model.addAttribute("user", user);
        log.info("User Registration");
        return "signup_form";
    }

    @PostMapping("/process_register")
    public String processRegister(User user) {
        log.debug("Registering new user.");
        User savedUser;

        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String encodedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(encodedPassword);
        
        try {
            savedUser = userService.save(user);
            log.info("New user " + user.getUsername() + " was registered.");
        } catch (Exception e) {
            log.error("Unable to register new user, user already exists.");
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