package com.moodybluez.enterprise.controller;

import com.moodybluez.enterprise.dto.Entry;
import com.moodybluez.enterprise.service.IEntryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;

@RestController
public class EntryController {

    @Autowired
    private IEntryService entryService;

    Logger log = LoggerFactory.getLogger(this.getClass());

    @GetMapping("/entry/{id}")
    public Entry getById(@PathVariable int id) {
        log.debug("/entry/id Endpoint");
        try {
            return entryService.fetchById(id);
        } catch (Exception e) {
            log.error("/entry/id Failed", e);
            return null;
        }
        
    }

    @GetMapping("entry/{year}/{month}")
    public Map<Integer, Entry> getByMonth(@PathVariable int year, @PathVariable int month) {
        log.debug("entry/year/month Endpoint");
        try {
            List<Entry> entries = entryService.fetchByMonth(year, month);
            Map<Integer, Entry> ret = new HashMap<>();
            for(Entry entry: entries){
                Calendar calendarInstance = Calendar.getInstance();
                calendarInstance.setTime(entry.getDate());
                int dayOfWeek = calendarInstance.get(Calendar.DAY_OF_WEEK);
                ret.put(dayOfWeek,entry);
            }
        return ret;
        } catch (Exception e) {
            log.error("/entry/year/month Failed ", e);
            return null;
        }
        
    }

    @GetMapping("entry/{year}/{month}/{day}")
    Entry getByMonth(@PathVariable Integer year, @PathVariable Integer month, @PathVariable Integer day) {
        log.debug("entry/year/month/day Endpoint");
        try {
            return entryService.fetchByDate(year.toString()+"-"+month.toString()+"-"+day.toString());
        } catch (Exception e) {
            log.error("entry/year/month/day Failed ", e);
            return null;
        }
        
    }

    @GetMapping("entry/mood/{id}")
    List<Entry> getByMood(@PathVariable int id) {
        log.debug("entry/mood/id Endpoint");
        try {
            return entryService.fetchByMood(id);
        } catch (Exception e) {
            log.error("entry/mood/id Failed ", e);
            return null;
        }
        
    }

    @GetMapping("entry/metric/{id}")
    List<Integer> getMetricByMood(@PathVariable int id) {
        log.debug("entry/metric/id");
        try {
            List<Integer> ret = Arrays.asList(0,0,0,0,0,0,0);
            List<Entry> entities = entryService.fetchByMood(id);

        for(Entry entry:entities){
            Calendar calendarInstance = Calendar.getInstance();
            calendarInstance.setTime(entry.getDate());
             int weekday = calendarInstance.get(Calendar.DAY_OF_WEEK) - 1;
            ret.set(weekday, ret.get(weekday) + 1);
        }

        return ret;
        } catch (Exception e) {
            log.error("entry/metric/id Failed ", e);
            return null;
        }
        
    }

    @PutMapping(path="entry", consumes = "application/json", produces = "application/json")
    public Entry modify(@RequestBody @DateTimeFormat(pattern = "yyyy-MM-dd") Entry entry) throws Exception {
        log.debug("entry Endpoint");
        try {
            return entryService.save(entry);
        } catch (Exception e) {
            log.error("Entry Failed ", e);
            return null;
        }
        
    }
}


