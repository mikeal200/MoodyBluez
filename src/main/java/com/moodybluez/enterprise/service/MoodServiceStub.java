package com.moodybluez.enterprise.service;

import com.moodybluez.enterprise.dto.Mood;
import org.springframework.stereotype.Component;

@Component
public class MooodServiceStub implements IMoodService {
    @Override
    public Mood fetchById(int id) {
        Mood mood = new Mood();
        mood.setMoodID(3);
        mood.setMoodDesc("Sad");
        return mood;
    }
}
