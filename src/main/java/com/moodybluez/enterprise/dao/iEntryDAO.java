package com.moodybluez.enterprise.dao;

import com.moodybluez.enterprise.dto.Entry;

import java.util.List;
import java.util.Map;

public interface IEntryDAO {

    boolean saveEntry(Entry entry);

    Entry fetchByWeekDay(int weekDayID);

    List<Entry> fetchByMood(int moodID);

    Entry fetchByDate(String date);

    Map<Integer, Entry> fetchAll();
}
