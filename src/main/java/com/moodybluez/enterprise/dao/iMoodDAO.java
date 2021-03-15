package com.moodybluez.enterprise.iMoodDAO;

interface iMoodDAO {
    Mood save(Mood mood) throws Exception;

    List<Mood> fetchAll();

    Mood fetch(int MoodId);

    void delete(int MoodId);

    List<Mood> fetchMoodsByMoodId(int moodId);

}