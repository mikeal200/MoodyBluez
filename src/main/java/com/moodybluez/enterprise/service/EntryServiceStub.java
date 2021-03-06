package com.moodybluez.enterprise.service;

import com.moodybluez.enterprise.dto.Date;
import com.moodybluez.enterprise.dto.Entry;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

import static java.lang.Integer.parseInt;


@Component
public class EntryServiceStub implements IEntryService {

    Map<String, Entry> moodEntries = new HashMap<>();

    @Override
    public void saveEntry(Entry entry) {
        moodEntries.put(entry.getDate().getDate(), entry);
    }

    @Override
    public Entry fetchByDate(String date) {
        Date dateObj = new Date();
        dateObj.setDate("2/22/2021");

        Entry entry = new Entry();
        entry.setMoodID(3);
        entry.setDescription("I laid in bed all day.");
        entry.setDate(dateObj);

        moodEntries.put(date, entry);

        if(moodEntries.containsKey(date)){
            return moodEntries.get(date);
        }
        else {
            return null;
        }
    }

    @Override
    public Map<String, Entry> fetchAll() {
        return moodEntries;
    }
}
