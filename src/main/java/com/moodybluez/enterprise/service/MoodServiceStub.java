package com.moodybluez.enterprise.service;

import com.moodybluez.enterprise.dto.Mood;
import org.springframework.stereotype.Component;

@Component
public class MoodServiceStub implements IMoodService {

    /**
     * Returns a mood based on the id provided by the user (Currently just generates a new mood and returns it)
     * @param id unique identifier for a mood
     * @return The mood retrieved using the provided id
     */
    @Override
    public Mood fetchById(int id) {
        Mood mood = new Mood();
        mood.setMoodID(3);
        mood.setMoodDesc("Sad");
        return mood;
    }
}
