package com.moodybluez.enterprise.service;

import com.moodybluez.enterprise.dao.IEntryDAO;
import com.moodybluez.enterprise.dto.Entry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;

import java.sql.Date;
import java.util.Map;

public class EntryService implements IEntryService {

    @Autowired
    private IEntryDAO entryDAO;

    public EntryService() {

    }

    public EntryService(IEntryDAO entryDAO) {
        this.entryDAO = entryDAO;
    }

    @Override
    @Cacheable(value="mood", key="#id")
    public Map<Integer, Entry> fetchAll() {
        return entryDAO.fetchAll();
    }

    @Override
    @CacheEvict(value="mood", key="#id")
    public void delete(int entryID) throws Exception {
        entryDAO.delete(entryID);
    }

    @Override
    public Entry save(Entry entry) throws Exception {
        return entryDAO.save(entry);
    }

    @Override
    public Entry fetchByDate(String date) {
        return entryDAO.fetchByDate(date);
    }
}
