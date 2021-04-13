package com.moodybluez.enterprise.dao;

import com.moodybluez.enterprise.dto.Entry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@Repository
@Profile({"dev", "default"})
public class EntrySQLDAO implements IEntryDAO{
    @Autowired
    private EntryRepository entryRepository;

    Logger LOG = LoggerFactory.getLogger(this.getClass());

    @Override
    public Entry save(Entry entry) {
        return entryRepository.save(entry);
    }

    @Override
    public List<Entry> fetchByMonth(int year, int month) {
        return entryRepository.findByMonth(year,month);
    }

    @Override
    public Entry fetchByDate(String date) {
        Date d = new Date();

        try {
            d = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH).parse(date);
        } catch (ParseException e) {
            LOG.error("Failed to Parse Date, please check if the date is in correct format. Date : " + date, e);
        }

        return entryRepository.findByDate(d);
    }

    @Override
    public List<Entry> fetchByMood(int moodId) {
        return entryRepository.findByMood(moodId);
    }

    @Override
    public Map<Integer, Entry> fetchAll() {
        Map<Integer, Entry> entities = new HashMap<>();
        entryRepository.findAll().forEach(entry -> {
            entities.put(entry.getEntryId(),entry);
        });
        return entities;
    }

    @Override
    public void delete(int entryID) {
        entryRepository.deleteById(entryID);
    }

    @Override
    public Entry fetchById(int id){
        return entryRepository.findById(id).get();
    }
}
