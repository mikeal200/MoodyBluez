package com.moodybluez.enterprise.dto;

import lombok.Data;

public @Data
class Entry {
    private int moodID;
    private String reasonDesc;
    public Date date;
}
