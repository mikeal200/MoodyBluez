package com.moodybluez.enterprise.dao;

import com.moodybluez.enterprise.dto.Entry;
import com.moodybluez.enterprise.dto.Mood;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Repository
@Profile({"dev", "default"})
public class MoodSQLDAO implements IMoodDAO {
    @Autowired
    MoodRepository moodRepository;

    public Mood save(Mood mood){
        return moodRepository.save(mood);
    }

    public Mood fetchByID(int moodID){
        return moodRepository.findById(moodID).get();
    }

    public Map<Integer, Mood> fetchAll(){
        Map<Integer, Mood> entities = new HashMap<>();
        moodRepository.findAll().forEach(entry -> {
            entities.put(entry.getMoodId(),entry);
        });
        return entities;
    }

    @Override
    public void delete(int moodID) {
        moodRepository.deleteById(moodID);
    }
}
