package com.moodybluez.enterprise.iEntryDAO;

interface iEntryDAO {
    Entry save(Entry entry) throws Exception;

    List<Entry> fetchAll();

    Entry fetch(int DayOfWeekId);

    void delete(int DayOfWeekId);

    List<Entry> fetchEntriesbyDayOfWeekId(int DayOfWeekId);

}