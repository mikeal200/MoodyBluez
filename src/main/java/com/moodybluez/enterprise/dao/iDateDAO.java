package com.moodybluez.enterprise.iDateDAO;

interface iDateDAO {
    Date save(Date date) throws Exception;
    List<Date> fetchAll();

    Date fetch(string date);

    void delete(string date);

    List<Date> fetchDatesByDayOfWeekId(int dayofweekId)
}