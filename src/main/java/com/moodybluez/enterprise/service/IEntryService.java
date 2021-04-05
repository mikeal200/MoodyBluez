package com.moodybluez.enterprise.service;

import com.moodybluez.enterprise.dto.Entry;

import java.sql.Date;
import java.util.List;
import java.util.Map;

public interface IEntryService {

    Map<Integer, Entry> fetchAll();

    Entry save(Entry entry) throws Exception;

    Entry fetchByDate(String date);

    void delete(int entryID) throws Exception;

    Entry fetchByID(int id);

    List<Entry> fetchByMonth(int year, int month);

    List<Entry> fetchByMood(int moodID);
}