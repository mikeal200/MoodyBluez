package com.moodybluez.enterprise.dto;

import lombok.Data;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.sql.Date;
import java.util.Objects;

@Entity
public @Data
class Entry {
    private int entryId;
    private int userId;
    private int moodId;
    private Date date;
    private String description;

    @Id
    @Column(name = "entryId", nullable = false)
    public int getEntryId() {
        return entryId;
    }

    @Basic
    @Column(name = "userId", nullable = false)
    public int getUserId() {
        return userId;
    }

    public void setUserId(int userID) {
        this.userId = userID;
    }

    @Basic
    @Column(name = "moodID", nullable = false)
    public int getMoodID() {
        return moodId;
    }

    public void setMoodID(int moodID) {
        this.moodId = moodID;
    }

    @Basic
    @Column(name = "date", nullable = false)
    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @Basic
    @Column(name = "description", nullable = true, length = 500)
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

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
