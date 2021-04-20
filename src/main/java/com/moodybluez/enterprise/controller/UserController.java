package com.moodybluez.enterprise.controller;

import com.moodybluez.enterprise.dto.User;
import com.moodybluez.enterprise.service.IUserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserController {

    @Autowired
    private IUserService userService;

    Logger log = LoggerFactory.getLogger(this.getClass());

    @GetMapping("/user/{username}")
    User getByID(@PathVariable String username) {
        log.debug("Getting User Endpoint");

        try {
            return userService.fetchByUsername(username);
        } catch (Exception e) {
            log.error("/user/username Failed ", e);
            return new User();
        }
    }

    @PutMapping("/user")
    User save(@RequestBody User user) {
        log.debug("Saving User Endpoint");

        try {
            return userService.save(user);
        } catch (Exception e) {
            log.error("/user Failed ", e);
            return new User();
        }
    }
}
