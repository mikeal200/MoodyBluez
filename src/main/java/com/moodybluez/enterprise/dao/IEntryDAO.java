package com.moodybluez.enterprise.dao;

import com.moodybluez.enterprise.dto.Entry;

import java.util.List;
import java.util.Map;

public interface IEntryDAO {
/**
 * Creates a new Entry database record from a Entry object.
 *
 * Returns null if an error occurs or returns with entry if entry is saved in database
 *
 * @param entry Entry object representing a entry to be created
 * @return newly created Entry object
 */
    Entry save(Entry entry);

/**
 * Allows the user to enter their entry for the day
 *
 * @param year the year which the entry was entered
 * @param month the month which the entry was entered
 *
 */
    List<Entry> fetchByMonth(int year, int month);

    Entry fetchByDate(String date);

    List<Entry> fetchByMood(int moodId);

    Entry fetchById(int id);

    Map<Integer, Entry> fetchAll();

    void delete(int entryId);
}
