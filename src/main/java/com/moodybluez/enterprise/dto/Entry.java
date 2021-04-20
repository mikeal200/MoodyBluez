package com.moodybluez.enterprise.dto;

import lombok.Data;

import javax.persistence.*;
import java.sql.Date;
import java.util.Objects;

@Entity
public @Data

/**
 * getters and setters
 *
 * @param entryId the entryId is a unique identifier for each entry
 * @param userId the userId is a unique identifier for each user
 * @param moodId the moodId is a unique identifier for each mood
 * @param date the date is the date of the entry
 * @param description the description is the description provided by the user
 */
class Entry {
    private int entryId;
    private int userId;
    private int moodId;
    private Date date;
    private String description;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "entryId", nullable = false)

/**
 *
 * Gets the unique identifier for the entry.
 * @return entryId
 */
    public int getEntryId() {
        return entryId;
    }

    @Basic
    @Column(name = "userId", nullable = false)

/**
 * Gets the unique identifier for the user.
 * @return userId
 */
    public int getUserId() {
        return userId;
    }

    public void setUserId(int userID) {
        this.userId = userID;
    }

    @Basic
    @Column(name = "moodId", nullable = false)

/**
 * Gets the unique identifier for the mood.
 * @return moodId
 */
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

    @Basic
    @Column(name = "date", nullable = false, unique = false)

/**
 * Gets the date of the entry.
 * @return date
 */
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

    @Basic
    @Column(name = "description", nullable = true, length = 500)
/**
 * Gets the description of the entry.
 * @return description
 */
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
