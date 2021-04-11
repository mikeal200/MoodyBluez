package com.moodybluez.enterprise.service;

import com.moodybluez.enterprise.dto.Entry;

import java.sql.Date;
import java.util.List;
import java.util.Map;

public interface IEntryService {

    Map<Integer, Entry> fetchAll();

    Entry save(Entry entry) throws Exception;

    Entry fetchByDate(String date);

    void delete(int entryId) throws Exception;

    Entry fetchById(int id);

    List<Entry> fetchByMonth(int year, int month);

    List<Entry> fetchByMood(int moodId);
}