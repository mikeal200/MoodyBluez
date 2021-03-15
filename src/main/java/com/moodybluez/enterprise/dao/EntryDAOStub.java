package com.moodybluez.enterprise.dao;

import com.moodybluez.enterprise.dto.Entry;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class EntryDAOStub implements IEntryDAO{

    private Map<Integer, Entry> entries = new HashMap<>();

    @Override
    public boolean saveEntry(Entry entry) {
        entries.put(entry.getEntryID(), entry);
        if (entries.containsKey(entry.getEntryID())) return true;

        return false;
    }

    @Override
    public Entry fetchByWeekDay(int weekDayID) {
        List<Entry> entryList = new ArrayList<>(entries.values());
        Entry requiredEntry = new Entry();

        for (Entry entry : entryList) {
            if (entry.getWeekDayID() == weekDayID) {
                requiredEntry = entry;
            }
        }

        return requiredEntry;
    }

    @Override
    public List<Entry> fetchByMood(int moodID) {
        List<Entry> entryList = new ArrayList<>(entries.values());
        List<Entry> requiredEntries = new ArrayList<>();

        for (Entry entry : entryList) {
            if (entry.getMoodID() == moodID) {
                requiredEntries.add(entry);
            }
        }

        return requiredEntries;
    }

    @Override
    public Entry fetchByDate(String date) {
        List<Entry> entryList = new ArrayList<>(entries.values());
        Entry requiredEntry = new Entry();

        for (Entry entry : entryList) {
            if (entry.getDate().getDate().equals(date)) {
                requiredEntry = entry;
            }
        }

        return requiredEntry;
    }

    @Override
    public Map<Integer, Entry> fetchAll() {
        return entries;
    }
}
