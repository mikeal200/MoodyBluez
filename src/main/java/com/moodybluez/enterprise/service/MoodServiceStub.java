package com.moodybluez.enterprise.service;

import com.moodybluez.enterprise.dto.Mood;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class MoodServiceStub implements IMoodService {

    @Override
    public void delete(int moodID) throws Exception {

    }

    @Override
    public Mood fetchByID(int moodID) {
        Mood mood = new Mood();
        mood.setMoodID(3);
        mood.setDescription("Sad");
        return mood;
    }

    @Override
    public Mood saveEntry(Mood mood) {
        return null;
    }

    @Override
    public Map<Integer, Mood> fetchAll() {
        return null;
    }
}