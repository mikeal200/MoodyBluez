package com.moodybluez.enterprise.dao;

import com.moodybluez.enterprise.dto.Mood;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class MoodDAOStub implements IMoodDAO{

    private Map<Integer, Mood> moods = new HashMap<>();

    @Override
    public boolean createEntry(Mood mood) {
        moods.put(mood.getMoodID(), mood);
        return moods.containsKey(mood.getMoodID());
    }

    @Override
    public Mood fetchByMood(String mood) {
        List<Mood> moodList = new ArrayList<>(moods.values());
        Mood matchedMood = new Mood();

        for (Mood moodDTO : moodList) {
            if (moodDTO.getDescription().equals(mood)) {
                matchedMood = moodDTO;
            }
        }

        return matchedMood;
    }

    @Override
    public Mood fetchByMoodID(int moodID) {
        List<Mood> moodList = new ArrayList<>(moods.values());
        Mood matchedMood = new Mood();

        for (Mood moodDTO : moodList) {
            if (moodDTO.getMoodID() == moodID) {
                matchedMood = moodDTO;
            }
        }

        return matchedMood;
    }

    @Override
    public Map<Integer, Mood> fetchAll() {
        return moods;
    }
}
