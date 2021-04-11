package com.moodybluez.enterprise.dao;

import com.moodybluez.enterprise.dto.Mood;

import java.util.Map;

public interface IMoodDAO {

    Mood save(Mood mood);

    Mood fetchByID(int moodId);

    Map<Integer, Mood> fetchAll();

    void delete(int moodId);
}

