/*package com.moodybluez.enterprise.controller;

import com.moodybluez.enterprise.dao.IMoodDAO;
import com.moodybluez.enterprise.dao.IUserDAO;
import com.moodybluez.enterprise.dao.MoodSQLDAO;
import com.moodybluez.enterprise.dao.UserSQLDAO;
import com.moodybluez.enterprise.dto.Mood;
import com.moodybluez.enterprise.dto.User;
import com.moodybluez.enterprise.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class UserController {
    @Autowired
    private UserSQLDAO userDAO;

    @GetMapping("/user/{username}")
    User getByID(@PathVariable String username) {
        return userDAO.fetchByUsername(username);
    }

    @PutMapping("/user")
    User modify(@RequestBody User user) {
        return userDAO.save(user);
    }
}
*/