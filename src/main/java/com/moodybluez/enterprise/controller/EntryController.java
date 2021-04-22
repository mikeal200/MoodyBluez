package com.moodybluez.enterprise.controller;

import com.moodybluez.enterprise.dto.Entry;
import com.moodybluez.enterprise.service.IEntryService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
public class EntryController {

    @Autowired
    private IEntryService entryService;

    Logger log = LoggerFactory.getLogger(this.getClass());

    /**
     *
     * @param id id of the entry in the database
     * @return returns a single entry that is linked to the var id in the database
     */
    @GetMapping("/entry/{id}")
    public Entry getById(@PathVariable int id) {
        return entryService.fetchById(id);
    }

    /**
     *
     * @param year grabs the year that is requested
     * @param month grabs the month that is requested
     * @return returns a Map that has all entries from a given month of a certain year
     */
    @GetMapping("entry/{year}/{month}")
    public Map<Integer, Entry> getByMonth(@PathVariable int year, @PathVariable int month) {
        List<Entry> entries = entryService.fetchByMonth(year, month);
        Map<Integer, Entry> ret = new HashMap<>();
        for(Entry entry: entries){
            Calendar calendarInstance = Calendar.getInstance();
            calendarInstance.setTime(entry.getDate());
            int dayOfWeek = calendarInstance.get(Calendar.DAY_OF_WEEK);
            ret.put(dayOfWeek,entry);
        }
        return ret;
    }

    /**
     *
     * @param year grabs the year that is requested
     * @param month grabs the month that is requested
     * @param day grabs the day that is requested
     * @return returns a single entry that is on this specific date by a user
     */
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

    /**
     *
     * @param id this is the id of the mood in the database
     * @return returns a list of all entries that have this mood id
     */
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

    /**
     *
     * @param id this is the id of the mood in the database
     * @return returns a list of integers, each integer represents the
     * amount of entries that have been saved by a user that have a certain
     * mood and on a certain day
     */
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

    /**
     *
     * @param entry this is the entry that will be modified/saved into the database
     * @return returns a single entry, this was the entry that was saved into the database
     */
    @PutMapping(path="entry", consumes = "application/json", produces = "application/json")
    public Entry modify(@RequestBody @DateTimeFormat(pattern = "yyyy-MM-dd") Entry entry) {
        log.debug("entry Endpoint");

        try {
            return entryService.save(entry);
        } catch (Exception e) {
            log.error("Entry Failed ", e);
            return null;
        }
    }

    /**
     *
     * @param id this is the id that corresponds to the entry that will be deleted
     */
    @DeleteMapping("/entry/{id}")
    public void delete(@PathVariable int id) {
        log.debug("/deleteEntry/{id} endpoint hit");

        try {
            entryService.delete(id);
            log.info("Entry " + id + " deleted");
        }
        catch(Exception e) {
            log.error("Entry couldn't be deleted ", e);
        }
    }
}


