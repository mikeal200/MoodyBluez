package com.moodybluez.enterprise.service;

import com.moodybluez.enterprise.dao.IEntryDAO;
import com.moodybluez.enterprise.dto.Date;
import com.moodybluez.enterprise.dto.Entry;
import org.springframework.stereotype.Component;

import java.util.Map;



@Component
public class EntryServiceStub implements IEntryService {

    private IEntryDAO entryDAO;

    @Override
    public boolean saveEntry(Entry entry) throws Exception{
        return entryDAO.saveEntry(entry);
    }

    @Override
    public Entry fetchByDate(String date) {
        Entry entry = new Entry();
        entry.setMoodID(3);
        entry.setDescription("I laid in bed all day.");
        Date dateObj = new Date();
        dateObj.setDate("2/22/2021");

        return entry;
    }

    @Override
    public Iterable<Entry> fetchAll() {
        return null;
    }
}