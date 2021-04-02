package com.moodybluez.enterprise.dao;

import com.moodybluez.enterprise.dto.Entry;
import com.moodybluez.enterprise.dto.Mood;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class MoodSQLDAO implements IMoodDAO{
    @Autowired
    MoodRepository moodRepository;

    public Mood saveEntry(Mood mood){
        return moodRepository.save(mood);
    }

    public Mood fetchByMoodID(int moodID){
        return moodRepository.findById(moodID).get();
    }

    public Map<Integer, Mood> fetchAll(){
        Map<Integer, Mood> entities = new HashMap<>();
        moodRepository.findAll().forEach(entry -> {
            entities.put(entry.getMoodid(),entry);
        });
        return entities;
    }
}
