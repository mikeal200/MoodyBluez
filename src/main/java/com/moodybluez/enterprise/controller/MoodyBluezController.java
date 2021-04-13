package com.moodybluez.enterprise.controller;

import com.moodybluez.enterprise.dto.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MoodyBluezController {

//    @Autowired
//    IUserService userService;

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

        return "index";
    }

    @GetMapping("/aboutus")
    public String aboutus() {
        return "aboutus";
    }


//    @PostMapping("/process_register")
//    public String processRegister(User user) throws Exception {
//        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
//        String encodedPassword = passwordEncoder.encode(user.getPassword());
//        user.setPassword(encodedPassword);
//
//        userService.save(user);
//
//        return "index";
//    }

}