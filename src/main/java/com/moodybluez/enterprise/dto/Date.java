package com.moodybluez.enterprise.dto;

import lombok.Data;

public @Data
class Date {
    private String date;
    private int dayOfWeekID;
    private String dayOfWeekDesc;
    private Entry entry;
}
