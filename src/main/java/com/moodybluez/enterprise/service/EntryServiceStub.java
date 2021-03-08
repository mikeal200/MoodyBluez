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

    @Override
    public void saveEntry(Entry entry) {
        moodEntryMap.put(entry.date.getDate(), entry);
    }

    /**
     *
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

        moodEntryMap.put(date, entry);

        if(moodEntryMap.containsKey(date)){
            return moodEntryMap.get(date);
        }
        else {
            return null;
        }
    }

    /**
     *
     * @return All key-value pairs within moodEntryMap
     */
    @Override
    public Map<String, Entry> fetchAll() {
        return moodEntryMap;
    }
}
