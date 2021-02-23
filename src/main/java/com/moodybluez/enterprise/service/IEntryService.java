package com.moodybluez.enterprise.service;

import com.moodybluez.enterprise.dto.Entry;

import java.util.Map;

public interface IEntryService {

    Map<String, Entry> fetchAll();

    void saveEntry(Entry entry);

    Entry fetchByDate(String date);


}
