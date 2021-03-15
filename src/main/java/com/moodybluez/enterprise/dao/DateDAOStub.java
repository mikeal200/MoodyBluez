package com.moodybluez.enterprise.dao;

import com.moodybluez.enterprise.dto.Date;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class DateDAOStub implements IDateDAO{

    private Map<Integer, Date> dates = new HashMap<>();

    @Override
    public boolean createEntry(Date date) {
        dates.put(date.getDateID(), date);
        return dates.ContainsKey(date.getDateID())
    }

    @Override
    public Date fetchByWeekDay(int weekDayID) {
        List<Date> dateList = new ArrayList<>(dates.values());
        Date requiredDate = new Date();

        for (Date date : dateList) {
            if (date.getWeekDayID() == weekDayID) {
                requiredDate = date;
            }
        }

        return requiredDate;
    }

    @Override
    public Date fetchByDate(String date) {
        List<Date> dateList = new ArrayList<>(dates.values());
        Date requiredDate = new Date();

        for (Date dateDTO : dateList) {
            if (dateDTO.getDate().equals(date)) {
                requiredDate = dateDTO;
            }
        }

        return requiredDate;
    }
}
