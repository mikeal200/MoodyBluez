package com.moodybluez.enterprise.dto;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.sql.Date;
import java.util.Objects;

@Entity
public class Entry {
    private int entityid;
    private int userid;
    private int moodid;
    private Date date;
    private String description;

    @Id
    @Column(name = "entityid", nullable = false)
    public int getEntityid() {
        return entityid;
    }

    public void setEntityid(int entityid) {
        this.entityid = entityid;
    }

    @Basic
    @Column(name = "userid", nullable = false)
    public int getUserid() {
        return userid;
    }

    public void setUserid(int userid) {
        this.userid = userid;
    }

    @Basic
    @Column(name = "moodid", nullable = false)
    public int getMoodid() {
        return moodid;
    }

    public void setMoodid(int moodid) {
        this.moodid = moodid;
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
        return entityid == entry.entityid && userid == entry.userid && moodid == entry.moodid && Objects.equals(date, entry.date) && Objects.equals(description, entry.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(entityid, userid, moodid, date, description);
    }
}
