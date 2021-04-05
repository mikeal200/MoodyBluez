package com.moodybluez.enterprise.service;

import com.moodybluez.enterprise.dto.Mood;

import java.util.Map;

public interface IMoodService {

    void delete(int moodID) throws Exception;

    Mood fetchById(int moodID);

    Mood save(Mood mood);

    Map<Integer, Mood> fetchAll();
}