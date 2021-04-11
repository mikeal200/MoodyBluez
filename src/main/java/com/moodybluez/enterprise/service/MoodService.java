package com.moodybluez.enterprise.service;

import com.moodybluez.enterprise.dao.IMoodDAO;
import com.moodybluez.enterprise.dto.Mood;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class MoodService implements IMoodService {

    @Autowired
    private IMoodDAO moodDAO;

    public MoodService() {

    }

    public MoodService(IMoodDAO moodDAO) {

        this.moodDAO = moodDAO;
    }

    @Override
    @CacheEvict(value="mood", key="#id")
    public void delete(int moodId) throws Exception {
        moodDAO.delete(moodId);
    }

    @Override
    @Cacheable(value="mood", key="#id")
    public Mood fetchById(int moodId) {
        Mood foundMood = moodDAO.fetchByID(moodId);
        return foundMood;
    }

    @Override
    public Mood save(Mood mood) {
        return moodDAO.save(mood);
    }

    @Override
    public Map<Integer, Mood> fetchAll() {
        return moodDAO.fetchAll();
    }
}
