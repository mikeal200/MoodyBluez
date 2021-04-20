package com.moodybluez.enterprise.controller;

import com.moodybluez.enterprise.dto.Mood;
import com.moodybluez.enterprise.service.IMoodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
public class MoodController {

    @Autowired
    private IMoodService moodService;

/**
 *
 * @param id the unique identifier for the mood
 * @return moodService.id the specific id related to the mood
 */
    @GetMapping("/mood/{id}")
    Mood getById(@PathVariable int id) {
        return moodService.fetchById(id);
    }

    @GetMapping("/mood")
    List<Mood> get() {
        Map<Integer, Mood> entities =  moodService.fetchAll();
        return new ArrayList<>(entities.values());
    }

    @PutMapping("/mood")
    Mood modify(@RequestBody Mood mood) {
        return moodService.save(mood);
    }
}