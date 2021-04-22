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
        log.debug("Accessed Index Endpoint");

        try {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            if (!(authentication instanceof AnonymousAuthenticationToken)) {
                Object principal = authentication.getPrincipal();
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

    /**
     * Handles /userId endpoint, this endpoint will try to get an authentication token
     * for the current user and with that token it will grab the userId of that user
     * which can be used for various things in the application. If it doesnt find the token
     * then an exception will be caught.
     * @return returns the userId of the current user
     */
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

    /**
     * Handles the /login endpoint, this endpoint will be hit when the user tries to enter
     * the login page. This endpoint will return the login page and if its unsuccessful it
     * will return the error page.
     * @return returns a string that will show either the index.html page or error.html page
     */
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

    /**
     * Handles the /metric endpoint, this endpoint will be hit when the user tries to access
     * the metrics page. If successful the metrics page will be brought up and if not the
     * error page will be returned.
     * @return returns a string that will show either the metric.html page or error.html page
     */
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

    /**
     * handles the /user/registration endpoint, this endpoint will be hit when the user tries
     * to access the registration page. A new user will be created on this endpoint that is null
     * the user will be added to the model to be filled with data from the html page.
     * @param model model that is linked to the html page via thymeleaf
     * @return returns a string that will show either the signup_form.html page or error.html page
     */
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

    /**
     * Handles the /process_register endpoint, this endpoint will be hit when the user tries
     * successfully registers. This page encrypts the password that user provided and this
     * password is then set in the User object. This User object is then saved to the database.
     * @param user User object that is created and saved to the database
     * @return returns a string that will show either the register_success.html page or error.html page
     * register_success.html will be shown if the User returned from the database is not null
     */
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