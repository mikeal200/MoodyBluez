package com.moodybluez.enterprise.dao;

import com.moodybluez.enterprise.dto.Entry;

import java.util.List;
import java.util.Map;

public interface IEntryDAO {

    /**
     *
     * @param entry entry object that will be saved
     * @return returns single entry object that was just saved
     */
    Entry save(Entry entry);

    /**
     *
     * @param year year of the date that will be fetched
     * @param month month of the date that will be fetched
     * @return returns a list of entries that are in the month of that year
     */
    List<Entry> fetchByMonth(int year, int month);

    /**
     *
     * @param date date that will be fetched
     * @return returns entry on that date that was passed
     * for a specific user
     */
    Entry fetchByDate(String date);

    /**
     *
     * @param moodId id of mood in database
     * @return returns list of entries that include that moodId
     */
    List<Entry> fetchByMood(int moodId);

    /**
     *
     * @param id id of the entry
     * @return returns a single entry object that has a certain id
     */
    Entry fetchById(int id);

    /**
     *
     * @return returns map that has every entry in the database in it
     */
    Map<Integer, Entry> fetchAll();

    /**
     *
     * @param entryId id of entry that will be deleted
     */
    void delete(int entryId);
}
