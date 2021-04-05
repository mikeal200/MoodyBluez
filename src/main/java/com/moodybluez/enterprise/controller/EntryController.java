package com.moodybluez.enterprise.controller;

import com.moodybluez.enterprise.dto.Entry;
import com.moodybluez.enterprise.service.IEntryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
public class EntryController {

    @Autowired
    private IEntryService entryService;

    @GetMapping("/entry/{id}")
    public Entry getById(@PathVariable int id) {
        return entryService.fetchById(id);
    }

    @GetMapping("entry/{year}/{month}")
    public Map<Integer, Entry> getByMonth(@PathVariable int year, @PathVariable int month) {
        List<Entry> entries = entryService.fetchByMonth(year, month);
        Map<Integer, Entry> ret = new HashMap<>();
        for(Entry entry: entries){
            ret.put(entry.getDate().getDay(),entry);
        }
        return ret;
    }

    @GetMapping("entry/{year}/{month}/{day}")
    Entry getByMonth(@PathVariable Integer year, @PathVariable Integer month, @PathVariable Integer day) {
        Entry entry = entryService.fetchByDate(year.toString()+"-"+month.toString()+"-"+day.toString());
        return entry;
    }

    @GetMapping("entry/mood/{id}")
    List<Entry> getByMood(@PathVariable int id) {
        return entryService.fetchByMood(id);
    }

    @GetMapping("entry/metric/{id}")
    List<Integer> getMetricByMood(@PathVariable int id) {
        List<Integer> ret = Arrays.asList(0,0,0,0,0,0,0);
        List<Entry> entities = entryService.fetchByMood(id);

        for(Entry entry:entities){
            int weekday = entry.getDate().getDay();
            ret.set(weekday, ret.get(weekday)+1);
        }

        return ret;
    }

    @PostMapping(path="entry", consumes = "application/json", produces = "application/json")
    public Entry modify(@RequestBody @DateTimeFormat(pattern = "yyyy-MM-dd") Entry entry) throws Exception {
        return entryService.save(entry);
    }
}
