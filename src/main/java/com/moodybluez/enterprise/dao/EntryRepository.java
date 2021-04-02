package com.moodybluez.enterprise.dao;

import com.moodybluez.enterprise.dto.Entry;
import com.moodybluez.enterprise.dto.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.Date;
import java.util.List;

public interface EntryRepository extends CrudRepository<Entry, Integer> {
    @Query("SELECT e from Entry e where YEAR(e.date)=?1 and MONTH(e.date)=?2")
    List<Entry> findByMonth(int year, int month);

    @Query("SELECT e from Entry e where e.date = ?1")
    Entry findByDate(Date date);

    @Query("SELECT e from Entry e where e.moodid = ?1")
    List<Entry> findByMood(int moodID);
}
