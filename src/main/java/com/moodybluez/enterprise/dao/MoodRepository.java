package com.moodybluez.enterprise.dao;

import com.moodybluez.enterprise.dto.Entry;
import com.moodybluez.enterprise.dto.Mood;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface MoodRepository extends CrudRepository<Mood, Integer> {

}
