package com.moodybluez.enterprise.service;

import com.moodybluez.enterprise.dto.Entry;
import org.springframework.stereotype.Component;
import java.util.ArrayList;
import java.util.List;


@Component
public class EntryServiceStub implements IEntryService {

    List<Entry> allMoodEntries = new ArrayList<Entry>();

    @Override
    public void saveEntry(Entry entry) {
        allMoodEntries.add(entry);
    }

    @Override
    public List<Entry> fetchAll() {
        return allMoodEntries;
    }
}
