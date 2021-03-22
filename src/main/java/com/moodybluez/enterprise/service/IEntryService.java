package com.moodybluez.enterprise.service;

import com.moodybluez.enterprise.dto.Entry;

import java.util.Map;

public interface IEntryService {

    Iterable<Entry> fetchAll();

    boolean saveEntry(Entry entry) throws Exception;

    Entry fetchByDate(String date);


}