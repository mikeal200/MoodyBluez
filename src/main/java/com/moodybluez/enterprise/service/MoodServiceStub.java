package com.moodybluez.enterprise.service;

import com.moodybluez.enterprise.dto.Mood;
import org.springframework.stereotype.Component;

@Component
public class MoodServiceStub implements IMoodService {
    @Override
    public Mood fetchById(int id) {
        Mood mood = new Mood();
        mood.setMoodID(3);
        mood.setMood("Sad");
        return mood;
    }
}
