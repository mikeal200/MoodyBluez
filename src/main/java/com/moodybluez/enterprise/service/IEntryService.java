package com.moodybluez.enterprise.service;

import com.moodybluez.enterprise.dto.Entry;

import java.sql.Date;
import java.util.Map;

public interface IEntryService {

    Map<Integer, Entry> fetchAll();

    Entry save(Entry entry) throws Exception;

    Entry fetchByDate(String date);

    void delete(int entryID) throws Exception;
}