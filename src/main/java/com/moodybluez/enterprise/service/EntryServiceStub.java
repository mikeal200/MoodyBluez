package com.moodybluez.enterprise.service;

import com.moodybluez.enterprise.dto.Date;
import com.moodybluez.enterprise.dto.Entry;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

import static java.lang.Integer.parseInt;


@Component
public class EntryServiceStub implements IEntryService {

    Map<String, Entry> moodEntryMap = new HashMap<>();

    /**
     * Takes a user inputted entry, pulls the date of the entry (entry.date), and adds them to moodEntryMap where date is the key and entry is the value
     * @param entry The entry the user wants to save
     */
    @Override
    public void saveEntry(Entry entry) {
        if (moodEntryMap.containsKey(entry.date.getDate())) {
            throw new IllegalArgumentException("An entry already exists for that date!");
        } else {
            moodEntryMap.put(entry.date.getDate(), entry);
        }
    }

    /**
     * Retrieves the database entry that is paired with the date provided by the user.
     * @param date A String consisting of a date in MM/DD/YYYY format
     * @return The entry data that is paired with the date param within the moodEntryMap
     */
    @Override
    public Entry fetchByDate(String date) {
        Entry entry = new Entry();
        entry.setMoodID(3);
        entry.setReasonDesc("I laid in bed all day.");

        Date dateObj = new Date();
        dateObj.setDate("2/22/2021");

        if (moodEntryMap.containsKey(date)){
            throw new IllegalArgumentException("An entry already exists for that date!");
        } else {
            moodEntryMap.put(date, entry);
        }


        if(moodEntryMap.containsKey(date)){
            return moodEntryMap.get(date);
        }
        else {
            return null;
        }
    }

    /**
     * Returns all date/entry pairs within the database.
     * @return All key-value pairs within moodEntryMap
     */
    @Override
    public Map<String, Entry> fetchAll() {
        return moodEntryMap;
    }
}
