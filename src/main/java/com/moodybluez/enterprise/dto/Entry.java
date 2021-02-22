package com.moodybluez.enterprise.dto;

import lombok.Data;

public @Data
class Entry {
    private int dayOfWeekID;
    private int moodID;
    private String reasonDesc;
    private Date date;
}
