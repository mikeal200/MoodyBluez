package com.moodybluez.enterprise.controller;

import com.moodybluez.enterprise.dao.EntrySQLDAO;
import com.moodybluez.enterprise.dao.IEntryDAO;
import com.moodybluez.enterprise.dao.IMoodDAO;
import com.moodybluez.enterprise.dao.MoodSQLDAO;
import com.moodybluez.enterprise.dto.Entry;
import com.moodybluez.enterprise.dto.Mood;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
public class MoodController {
    @Autowired
    private MoodSQLDAO moodDAO;

    @GetMapping("/mood/{id}")
    Mood getByID(@PathVariable int id) {
        return moodDAO.fetchByMoodID(id);
    }

    @GetMapping("/mood")
    List<Mood> get() {
        List<Mood> ret = new ArrayList<>();
        Map<Integer, Mood> entities =  moodDAO.fetchAll();
        for(Mood mood:entities.values()){
            ret.add(mood);
        }
        return ret;
    }

    @PutMapping("/mood")
    Mood modify(@RequestBody Mood mood) {
        return moodDAO.saveEntry(mood);
    }
}
