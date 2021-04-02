package com.moodybluez.enterprise.dto;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Objects;

@Entity
public class Mood {
    private int moodid;
    private String description;

    @Id
    @Column(name = "moodid", nullable = false)
    public int getMoodid() {
        return moodid;
    }

    public void setMoodid(int moodid) {
        this.moodid = moodid;
    }

    @Basic
    @Column(name = "description", nullable = false, length = 15)
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
        Mood mood = (Mood) o;
        return moodid == mood.moodid && Objects.equals(description, mood.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(moodid, description);
    }
}
