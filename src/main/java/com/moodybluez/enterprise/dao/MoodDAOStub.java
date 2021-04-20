package com.moodybluez.enterprise.dao;

import com.moodybluez.enterprise.dto.Mood;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
@Profile("test")
public class MoodDAOStub implements IMoodDAO {

    private Map<Integer, Mood> moods = new HashMap<>();


/**
 *
 * @param mood correlates to the mood identified by the user
 * @return moods the various modds the user has inputted
 */
    @Override
    public Mood save(Mood mood) {
        moods.put(mood.getMoodId(), mood);
        return moods.get(mood.getMoodId());
    }

/**
 *
 * @param moodId correlates to a mood in the database
 * @return matchedMood the mood that correlates to the specific identifier passed in by moodId
 */
    @Override
    public Mood fetchByID(int moodId) {
        List<Mood> moodList = new ArrayList<>(moods.values());
        Mood matchedMood = new Mood();

        for (Mood moodDTO : moodList) {
            if (moodDTO.getMoodId() == moodId) {
                matchedMood = moodDTO;
            }
        }

        return matchedMood;
    }

    @Override
    public Map<Integer, Mood> fetchAll() {
        return moods;
    }

    @Override
    public void delete(int moodId) {
        moods.remove(moodId);
    }
}
