package com.moodybluez.enterprise.dao;

import com.moodybluez.enterprise.dto.Entry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import java.text.SimpleDateFormat;
import java.util.*;

@Repository
@Profile({"dev", "default"})
public class EntrySQLDAO implements IEntryDAO{
    @Autowired
    private EntryRepository entryRepository;

    @Override
    public Entry save(Entry entry) {
//        if(entryRepository.findById(entry.getEntityid()))
        return entryRepository.save(entry);
    }

    @Override
    public List<Entry> fetchByMonth(int year, int month) {
        List<Entry> t= entryRepository.findByMonth(year,month);
        return t;
    }

    @Override
    public Entry fetchByDate(String date) {
        Date d = new Date();
        try {
            d = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH).parse(date);
        }
        catch (Exception e){

        }
        return entryRepository.findByDate(d);
    }

    @Override
    public List<Entry> fetchByMood(int moodID) {
        return entryRepository.findByMood(moodID);
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
