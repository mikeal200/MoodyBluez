package com.moodybluez.enterprise;

import com.moodybluez.enterprise.dto.Mood;
import com.moodybluez.enterprise.service.IMoodService;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class EnterpriseApplicationTests {

	@Autowired
	private IMoodService moodService;
	private Mood mood;

	@Test
	void contextLoads() {
	}

	@Test
	void fetchMoodByID_returnsSadForID3() {
		givenMoodDataAreAvailable();
		whenMoodWithID3();
		thenReturnOneSadMoodForID3();
	}

	private void givenMoodDataAreAvailable() {
	}

	private void whenMoodWithID3() {
		mood = moodService.fetchById(3);
	}

	private void thenReturnOneSadMoodForID3() {
		String moodDescription = mood.getMoodDesc();
		assertEquals("Sad", moodDescription);
	}
}
