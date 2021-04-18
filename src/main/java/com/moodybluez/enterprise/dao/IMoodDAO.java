package com.moodybluez.enterprise.dao;

import com.moodybluez.enterprise.dto.Mood;

import java.util.Map;
/**
 * @author
 */

/**
 * Allows the user to enter information about their mood
 *
 * @param moodId a number that helps identify each mood
 *
 */
public interface IMoodDAO {

    Mood save(Mood mood);

    Mood fetchByID(int moodId);

    Map<Integer, Mood> fetchAll();

    void delete(int moodId);
}

