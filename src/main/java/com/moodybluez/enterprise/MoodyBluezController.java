package com.moodybluez.enterprise;

import com.moodybluez.enterprise.dto.User;
import com.moodybluez.enterprise.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;


@Controller
public class MoodyBluezController {

    @Autowired
    IUserService userService;

    /**
     * Handles the root endpoint and returns index.html
     * @return returns index page
     */
    @RequestMapping("/")
    public String index(User user) {
        return "index";
    }

    /**
     * Handles the register endpoint where users register
     * with a unique username and password
     * @param user user object that will be saved into database
     * @return returns index page
     */
    /*@RequestMapping("/register")
    public String saveUser(User user) {
        try {
            userService.save(user);
        } catch(Exception e) {
            e.printStackTrace();
            return "index";
        }
        return "index";
    }*/

    @GetMapping("/register")
    public String showRegistrationForm(Model model) {
        model.addAttribute("user", new User());

        return "index";
    }


    @PostMapping("/process_register")
    public String processRegister(User user) throws Exception {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String encodedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(encodedPassword);

        userService.save(user);

        return "index";
    }
}
