package com.moodybluez.enterprise.controller;

import com.moodybluez.enterprise.dto.Mood;
import com.moodybluez.enterprise.service.IMoodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
public class MoodController {

    @Autowired
    private IMoodService moodService;

    Logger log = LoggerFactory.getLogger(this.getClass());

    @GetMapping("/mood/{id}")
    Mood getById(@PathVariable int id) {
        log.debug("/mood/id Endpoint");
        try {
            return moodService.fetchById(id);
        } catch (Exception e) {
            log.error("/mood/id Failed ", e);
            return null;
        }
        
    }

    @GetMapping("/mood")
    List<Mood> get() {
        log.debug("/mood Endpoint");
        try {
            Map<Integer, Mood> entities =  moodService.fetchAll();
        return new ArrayList<>(entities.values());
        } catch (Exception e) {
            log.error("/mood Failed ", e);
            return null;
        }
        
    }

    @PutMapping("/mood")
    Mood modify(@RequestBody Mood mood) {
        log.debug("/mood Endpoint");
        try {
            return moodService.save(mood);
        } catch (Exception e) {
            log.error("/mood Failed ", e);
            return null;
        }
        
    }
}