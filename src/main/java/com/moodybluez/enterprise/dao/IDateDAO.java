package com.moodybluez.enterprise.dao;

import com.moodybluez.enterprise.dto.Date;

public interface IDateDAO {

    boolean createEntry(Date date);

    Date fetchByWeekDay(int weekDayID);

    Date fetchByDate(String date);

}
