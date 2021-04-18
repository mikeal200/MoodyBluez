package com.moodybluez.enterprise.dao;

import com.moodybluez.enterprise.dto.Mood;
import org.springframework.data.repository.CrudRepository;

public interface MoodRepository extends CrudRepository<Mood, Integer> {
}
