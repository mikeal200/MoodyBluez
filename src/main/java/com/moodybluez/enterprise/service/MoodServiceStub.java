package com.moodybluez.enterprise.service;

import com.moodybluez.enterprise.dto.Mood;
import org.springframework.stereotype.Component;

@Component
public class MoodServiceStub implements IMoodService {
    /**
     *
     * @param id unique identifier for a mood
     * @return
     * fetches the mood by id
     */
    @Override
    public Mood fetchById(int id) {
        Mood mood = new Mood();
        mood.setMoodID(3);
        mood.setMoodDesc("Sad");
        return mood;
    }
}
