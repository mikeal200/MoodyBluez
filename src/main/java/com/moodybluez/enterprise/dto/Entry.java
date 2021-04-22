package com.moodybluez.enterprise.dto;

import lombok.Data;

import javax.persistence.*;
import java.sql.Date;
import java.util.Objects;

@Entity
public @Data
class Entry {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "entryId", nullable = false)
    private int entryId;

    @Basic
    @Column(name = "userId", nullable = false)
    private int userId;

    @Basic
    @Column(name = "moodId", nullable = false)
    private int moodId;

    @Basic
    @Column(name = "date", nullable = false, unique = false)
    private Date date;

    @Basic
    @Column(name = "description", nullable = true, length = 500)
    private String description;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Entry entry = (Entry) o;
        return entryId == entry.entryId && userId == entry.userId && moodId == entry.moodId && Objects.equals(date, entry.date) && Objects.equals(description, entry.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(entryId, userId, moodId, date, description);
    }
}
