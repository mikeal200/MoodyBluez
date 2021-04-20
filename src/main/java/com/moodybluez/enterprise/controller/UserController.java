package com.moodybluez.enterprise.controller;

import com.moodybluez.enterprise.dto.User;
import com.moodybluez.enterprise.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserController {
    @Autowired
    private IUserService userService;

/**
 *
 * @param username
 * @return userService.username the specific username related to the user
 */
    @GetMapping("/user/{username}")
    User getByID(@PathVariable String username) {
        return userService.fetchByUsername(username);
    }

/**
 *
 * @param user the one using the app
 * @return userService.user the specific user
 * @throws Exception
 */
    @PutMapping("/user")
    User save(@RequestBody User user) throws Exception {
        return userService.save(user);
    }
}
