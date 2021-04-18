package com.moodybluez.enterprise.controller;

import com.moodybluez.enterprise.dto.User;
import com.moodybluez.enterprise.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserController {
    @Autowired
    private IUserService userService;

    @GetMapping("/user/{username}")
    User getByID(@PathVariable String username) {
        return userService.fetchByUsername(username);
    }

    @PutMapping("/user")
    User save(@RequestBody User user) throws Exception {
        return userService.save(user);
    }
}
