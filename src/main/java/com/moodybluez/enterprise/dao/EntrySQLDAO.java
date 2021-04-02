package com.moodybluez.enterprise.dao;

import com.moodybluez.enterprise.dto.Entry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class EntrySQLDAO implements IEntryDAO{
    @Autowired
    private EntryRepository entryRepository;

    @Override
    public Entry saveEntry(Entry entry) {
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
            entities.put(entry.getEntityid(),entry);
        });
        return entities;
    }

    @Override
    public Entry fetchByID(int id){
        return entryRepository.findById(id).get();
    }
}
