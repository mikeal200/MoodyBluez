package com.moodybluez.enterprise.dao;

import com.moodybluez.enterprise.dto.Entry;

import java.util.List;
import java.util.Map;

/**
 * @author
 */

/**
 * @param year the year which the entry was entered
 * @param month the month which the entry was entered
 *
 */
public interface IEntryDAO {

    Entry save(Entry entry);

    List<Entry> fetchByMonth(int year, int month);

    Entry fetchByDate(String date);

    List<Entry> fetchByMood(int moodId);

    Entry fetchById(int id);

    Map<Integer, Entry> fetchAll();

    void delete(int entryId);
}
