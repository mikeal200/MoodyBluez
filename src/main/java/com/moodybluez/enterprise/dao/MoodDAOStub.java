package com.moodybluez.enterprise.dao;

import com.moodybluez.enterprise.dto.Mood;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class MoodDAOStub implements IMoodDAO{

    private Map<Integer, Mood> moods = new HashMap<>();

    @Override
    public Mood saveEntry(Mood mood) {
        moods.put(mood.getMoodid(), mood);
        return moods.get(mood.getMoodid());
    }

    @Override
    public Mood fetchByMoodID(int moodID) {
        List<Mood> moodList = new ArrayList<>(moods.values());
        Mood matchedMood = new Mood();

        for (Mood moodDTO : moodList) {
            if (moodDTO.getMoodid() == moodID) {
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
