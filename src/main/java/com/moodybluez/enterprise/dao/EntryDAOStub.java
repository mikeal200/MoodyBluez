package com.moodybluez.enterprise.dao;

import com.moodybluez.enterprise.dto.Entry;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import java.text.SimpleDateFormat;
import java.util.*;

@Repository
@Profile("test")

/**
 *
 * @param entry
 * @return the details of the entry
 */
public class EntryDAOStub implements IEntryDAO{

    private Map<Integer, Entry> entries = new HashMap<>();

    @Override
    public Entry save(Entry entry) {
        entries.put(entry.getEntryId(), entry);
        return entries.get(entry.getEntryId());
    }

    @Override
    public List<Entry> fetchByMonth(int year, int month) {
        List<Entry> entryList = new ArrayList<>();
        for (Entry entry : entries.values()){
            if(entry.getDate().getYear()==year&&entry.getDate().getMonth()==month){
                entryList.add(entry);
            }
        }

        return entryList;
    }

    @Override
    public Entry fetchByDate(String date) {
        Date datef = new Date();
        try{
            datef = new SimpleDateFormat("yyyy-MM-dd").parse(date);
        }
        catch (Exception e){
            return null;
        }

        Entry requiredEntry = new Entry();
        for (Entry entry : entries.values()) {
            if (entry.getDate().equals(date)) {
                requiredEntry = entry;
                return requiredEntry;
            }
        }

        return null;
    }

    @Override
    public List<Entry> fetchByMood(int moodId) {
        List<Entry> entryList = new ArrayList<>();
        for (Entry entry : entries.values()){
            if(entry.getEntryId()==moodId){
                entryList.add(entry);
            }
        }
        return entryList;
    }



    @Override
    public Map<Integer, Entry> fetchAll() {
        return entries;
    }

    @Override
    public void delete(int entryId) {
        entries.remove(entryId);
    }

    @Override
    public Entry fetchById(int id){
        return entries.get(id);
    }
}
