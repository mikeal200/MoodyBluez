package com.moodybluez.enterprise.controller;

import com.moodybluez.enterprise.dto.User;
import com.moodybluez.enterprise.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RestController
public class UserController {
    @Autowired
    private IUserService userService;

    Logger log = LoggerFactory.getLogger(this.getClass());

    @GetMapping("/user/{username}")
    User getByID(@PathVariable String username) {
        log.debug("Getting User");
        return userService.fetchByUsername(username);
    }

    @PutMapping("/user")
    User save(@RequestBody User user) throws Exception {
        log.info("Saving User");
        return userService.save(user);
    }
}
