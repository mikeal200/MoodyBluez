package com.moodybluez.enterprise.dao;

import com.moodybluez.enterprise.dto.Mood;

import java.util.Map;

public interface IMoodDAO {

    boolean createEntry(Mood mood);

    Mood fetchByMood(String mood);

    Map<Integer, Mood> fetchAll();
}
