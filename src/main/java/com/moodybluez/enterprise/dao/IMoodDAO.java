package com.moodybluez.enterprise.dao;

import com.moodybluez.enterprise.dto.Mood;

import java.util.Map;

public interface IMoodDAO {

    /**
     *
     * @param mood mood that will be saved
     * @return returns mood that was saved in database
     */
    Mood save(Mood mood);

    /**
     *
     * @param moodId id of mood that will be fetched
     * @return returns mood object
     */
    Mood fetchByID(int moodId);

    /**
     *
     * @return returns a map that includes all 6 moods
     */
    Map<Integer, Mood> fetchAll();

    /**
     *
     * @param moodId id of mood that will be deleted
     */
    void delete(int moodId);
}

