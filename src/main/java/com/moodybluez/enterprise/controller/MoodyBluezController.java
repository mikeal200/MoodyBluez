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
import org.hibernate.jdbc.Expectations;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


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
        log.debug("Accessed Index Endpoint");
        try {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            if (!(authentication instanceof AnonymousAuthenticationToken)) {
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
        } catch (Exception e) {
            log.error("Failed Index", e);
            return "error";
        }
    }

    @RequestMapping(value = "/userId", method = RequestMethod.GET)
    @ResponseBody
    public int currentUserId() {

        log.debug("/userID Endpoint Hit");
        try {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            if (!(authentication instanceof AnonymousAuthenticationToken)) {
                Object principal = authentication.getPrincipal();
                return ((CustomUserDetails)principal).getUserId();
            }
            else {
                return 0;
            }
        } catch (Exception e) {
            log.error("/userID Failed", e);
            return -1;
        }
    }

    @GetMapping("/login")
    public String login() {

        log.debug("Accessed Login Endpoint");
        try {
            return "login";
        } catch (Exception e) {
            log.error("Login Failed ", e);
            return "error";
        }
        
    }

    @GetMapping("/metric")
    public String metric() {

        log.debug("Accessed Metrics Endpoint");
        try {
            return "metric";
        } catch (Exception e) {
            log.error("Failed Metrics ", e);
            return "error";
        }
        
    }

    @GetMapping("/user/registration")
    public String showRegistrationForm(Model model) {

        log.debug("User Registration");
        User user = new User();
        try {
            model.addAttribute("user", user);
            return "signup_form";
        } catch (Exception e) {
            log.error("Failed Signup", e);
            return "error";
        }
    }

    @PostMapping("/process_register")
    public String processRegister(User user) {
        log.debug("Registering new user.");
        User savedUser;
        String result = "";

        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String encodedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(encodedPassword);
        
        try {
            savedUser = userService.save(user);
            log.info("New user " + user.getUsername() + " was registered.");
            if(savedUser != null)
            {
                result = "register_success";
            }
            return result;
        } catch (Exception e) {
            log.error("Unable to register new user, user already exists." , e);
            return "error";
        }
    }
}