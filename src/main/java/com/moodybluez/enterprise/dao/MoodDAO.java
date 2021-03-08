package com.moodybluez.enterprise.dao;

import com.moodybluez.enterprise.dto.Mood;
import org.springframework.stereotype.Component;

import java.util.*;

/**
 * Class representing a particular mood
 */
@Component
public class MoodDAO implements IMoodDAO{

    private Map<Integer, Mood> moods = new HashMap<>();

    /**
     * Adds a Mood to the collection.  If an exception is raised, return false for custom
     * error trapping so the program doesn't crash.
     * @param mood representing a Mood object
     * @return boolean (true if successful)
     *
     */
    @Override
    public boolean createEntry(Mood mood) {
        try {
            moods.put(mood.getMoodID(), mood);
            return true;
        } catch( Exception e) {
            return false;
        }
    }

    @Override
    public Mood fetchByMood(String mood) {
        List<Mood> moodList = new ArrayList<>(moods.values());
        Mood matchedMood = new Mood();

        for (Mood moodDTO : moodList) {
            if (moodDTO.getMood().equals(mood)) {
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
