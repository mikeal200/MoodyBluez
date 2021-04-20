package com.moodybluez.enterprise.dto;

import lombok.Data;

import javax.persistence.*;
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

/**
 *
 * Gets the unique identifier for the entry.
 * @return entryId
 */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "entryId", nullable = false)
    public int getEntryId() {
        return entryId;
    }

/**
 * Gets the unique identifier for the user.
 * @return userId
 */
    @Basic
    @Column(name = "userId", nullable = false)
    public int getUserId() {
        return userId;
    }

    public void setUserId(int userID) {
        this.userId = userID;
    }

/**
 * Gets the unique identifier for the mood.
 * @return moodId
 */
    @Basic
    @Column(name = "moodId", nullable = false)
    public int getMoodId() {
        return moodId;
    }

/**
 * Sets the unique identifier for the mood.
 *
 */
    public void setMoodId(int moodId) {
        this.moodId = moodId;
    }

/**
 * Gets the date of the entry.
 * @return date
 */
    @Basic
    @Column(name = "date", nullable = false, unique = false)
    public Date getDate() {
        return date;
    }

/**
 * Sets the unique identifier for the date.
 *
 */
    public void setDate(Date date) {
        this.date = date;
    }

/**
 * Gets the description of the entry.
 * @return description
 */
    @Basic
    @Column(name = "description", nullable = true, length = 500)
    public String getDescription() {
        return description;
    }

/**
 * Sets the unique identifier for the description.
 *
 */
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
