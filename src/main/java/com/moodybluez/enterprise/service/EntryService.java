package com.moodybluez.enterprise.service;

import com.moodybluez.enterprise.dao.IEntryDAO;
import com.moodybluez.enterprise.dto.Entry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
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
    public Entry fetchById(int id) {
        return entryDAO.fetchById(id);
    }

    @Override
    public Entry save(Entry entry) throws Exception {
        return entryDAO.save(entry);
    }

    @Override
    public Entry fetchByDate(String date) {
        return entryDAO.fetchByDate(date);
    }

    public List<Entry> fetchByMonth(int year, int month) {return entryDAO.fetchByMonth(year, month);}

    public List<Entry> fetchByMood(int moodId) {return entryDAO.fetchByMood(moodId);}
}
