package com.moodybluez.enterprise.controller;

import com.moodybluez.enterprise.dto.Mood;
import com.moodybluez.enterprise.service.IMoodService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
public class MoodController {

    @Autowired
    private IMoodService moodService;

    Logger log = LoggerFactory.getLogger(this.getClass());

    /**
     *
     * @param id id that corresponds to a certain mood within the databse
     * @return
     */
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

    /**
     *
     * @return returns a list of all moods from the database
     */
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

    /**
     *
     * @param mood mood that will be saved to the database
     * @return returns a single mood object that was saved to the database
     */
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