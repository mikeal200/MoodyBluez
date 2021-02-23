package com.moodybluez.enterprise.service;

import com.moodybluez.enterprise.dto.Entry;

import java.util.List;

public interface IEntryService {
    void saveEntry(Entry entry);

    List<Entry> fetchAll();
}
