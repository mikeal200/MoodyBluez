package com.moodybluez.enterprise.dto;

import lombok.Data;

import javax.persistence.*;
import java.util.Objects;

@Entity
public @Data
class Mood {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "moodId", nullable = false)
    private int moodId;

    @Basic
    @Column(name = "description", nullable = false, length = 15)
    private String description;


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Mood mood = (Mood) o;
        return moodId == mood.moodId && Objects.equals(description, mood.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(moodId, description);
    }
}
