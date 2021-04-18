package com.moodybluez.enterprise.dao;

import com.moodybluez.enterprise.dto.Entry;
import com.moodybluez.enterprise.service.CustomUserDetails;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
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
        Entry entry = new Entry();
        int userId = 0;

        try {
            d = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH).parse(date);
        } catch (ParseException e) {
            LOG.error("Failed to Parse Date, please check if the date is in correct format. Date : " + date, e);
        }

        List<Entry> entries = entryRepository.findByDate(d);

        if(entries.size() == 0) {
            entry = null;
        }
        else if(entries.size() == 1) {
            entry = entries.get(0);
        }
        else {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            if (!(authentication instanceof AnonymousAuthenticationToken)) {
                Object principal = authentication.getPrincipal();
                userId = ((CustomUserDetails) principal).getUserId();
            }
            for (Entry e : entries) {
                if(e.getUserId() == userId) {
                    entry = e;
                }
            }
        }

        return entry;
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
