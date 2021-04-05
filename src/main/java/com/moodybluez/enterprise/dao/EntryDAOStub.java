package com.moodybluez.enterprise.dao;

import com.moodybluez.enterprise.dto.Entry;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import java.text.SimpleDateFormat;
import java.util.*;

@Repository
@Profile("test")
public class EntryDAOStub implements IEntryDAO{

    private Map<Integer, Entry> entries = new HashMap<>();

    @Override
    public Entry save(Entry entry) {
        entries.put(entry.getEntryID(), entry);
        return entries.get(entry.getEntryID());
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
    public List<Entry> fetchByMood(int moodID) {
        List<Entry> entryList = new ArrayList<>();
        for (Entry entry : entries.values()){
            if(entry.getEntryID()==moodID){
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
    public void delete(int entryID) {
        entries.remove(entryID);
    }

    @Override
    public Entry fetchByID(int id){
        return entries.get(id);
    }
}
