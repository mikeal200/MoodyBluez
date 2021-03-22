package com.moodybluez.enterprise.service;

import com.moodybluez.enterprise.dto.Mood;

public interface IMoodService {
    /**
     * fetch a mood with a given ID
     * @param id unique identifier for a mood
     * @return the matching mood, or null if no matches found
     */
    Mood fetchById(int id);
}