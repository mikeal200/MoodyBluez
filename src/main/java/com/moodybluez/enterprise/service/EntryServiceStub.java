package com.moodybluez.enterprise.service;

import com.moodybluez.enterprise.dto.Date;
import com.moodybluez.enterprise.dto.Entry;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

import static java.lang.Integer.parseInt;


@Component
public class EntryServiceStub implements IEntryService {

    Map<String, Entry> moodEntryMap = new HashMap<>();

    @Override
    public void saveEntry(Entry entry) {
        moodEntryMap.put(entry.date.getDate(), entry);
    }

    @Override
    public Entry fetchByDate(String date) {
        Entry entry = new Entry();
        entry.setMoodID(3);
        entry.setReasonDesc("I laid in bed all day.");

        Date dateObj = new Date();
        dateObj.setDate("2/22/2021");
        /*dateObj.setDayOfWeekID(1);
        dateObj.setDayOfWeekDesc(dayOfWeek.name());
        dateObj.setDayOfWeekID(dayOfWeek.getValue());*/

        moodEntryMap.put(date, entry);

        if(moodEntryMap.containsKey(date)){
            return moodEntryMap.get(date);
        }
        else {
            return null;
        }
    }

    @Override
    public Map<String, Entry> fetchAll() {
        return moodEntryMap;
    }
}
