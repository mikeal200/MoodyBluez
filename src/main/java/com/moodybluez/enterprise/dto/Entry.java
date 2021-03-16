package com.moodybluez.enterprise.dto;

import lombok.Data;

public @Data
class Entry {
    private int entryID;
    private int moodID;
    private int weekDayID;
    private String description;
    private Date date;

}
