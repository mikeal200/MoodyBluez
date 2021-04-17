package com.moodybluez.enterprise.controller;

import com.moodybluez.enterprise.dao.UserRepository;
import com.moodybluez.enterprise.dto.User;
import com.moodybluez.enterprise.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class MoodyBluezController {

    @Autowired
    private UserRepository userRepo;

    /**
     * Handles the root endpoint and returns index.html
     * @return returns index page
     */
    @GetMapping("/")
    public String index() {
        return "index";
    }

    @GetMapping("/metric")
    public String metric() {
        return "metric";
    }

    @GetMapping("/register")
    public String showRegistrationForm(Model model) {
        model.addAttribute("user", new User());

        return "signup_form";
    }

    @GetMapping("/aboutus")
    public String aboutus() {
        return "aboutus";
    }


    @PostMapping("/process_register")
    public String processRegister(User user) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String encodedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(encodedPassword);

        userRepo.save(user);

        return "register_success";
    }

}