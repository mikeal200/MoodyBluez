package com.moodybluez.enterprise.service;

import com.moodybluez.enterprise.dao.IMoodDAO;
import com.moodybluez.enterprise.dto.Mood;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;

import java.util.Map;

public class MoodService implements IMoodService {

    public MoodService() {

    }

    public MoodService(IMoodDAO moodDAO) {

        this.moodDAO = moodDAO;
    }

    @Autowired
    private IMoodDAO moodDAO;

    @Override
    @CacheEvict(value="mood", key="#id")
    public void delete(int moodID) throws Exception {
        moodDAO.delete(moodID);
    }

    @Override
    @Cacheable(value="mood", key="#id")
    public Mood fetchByID(int moodID) {
        Mood foundMood = moodDAO.fetchByID(moodID);
        return foundMood;
    }

    @Override
    public Mood saveEntry(Mood mood) {
        return moodDAO.saveEntry(mood);
    }

    @Override
    public Map<Integer, Mood> fetchAll() {
        return moodDAO.fetchAll();
    }
}
