package com.moodybluez.enterprise;

import com.moodybluez.enterprise.dto.User;
import com.moodybluez.enterprise.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MoodyBluezController {

    @Autowired
    IUserService userService;

    /**
     * Handles the root endpoint and returns index.html
     * @return returns index page
     */
    @RequestMapping("/")
    public String index() {
        return "index";
    }

    /**
     * Handles the register endpoint where users register
     * with a unique username and password
     * @param user user object that will be saved into database
     * @return returns index page
     */
    @PostMapping("/register")
    public String saveUser(User user) {
        try {
            userService.save(user);
        } catch(Exception e) {
            e.printStackTrace();
            return "index";
        }
        return "index";
    }
}
