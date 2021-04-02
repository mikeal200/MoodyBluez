package com.moodybluez.enterprise.dao;

import com.moodybluez.enterprise.dto.Entry;

import java.util.List;
import java.util.Map;

public interface IEntryDAO {

    Entry saveEntry(Entry entry);

    List<Entry> fetchByMonth(int year, int month);

    Entry fetchByDate(String date);

    List<Entry> fetchByMood(int moodID);

    Entry fetchByID(int id);

    Map<Integer, Entry> fetchAll();
}
