package com.moodybluez.enterprise;

import com.moodybluez.enterprise.dao.*;
import com.moodybluez.enterprise.dto.Entry;
import com.moodybluez.enterprise.dto.Mood;
import com.moodybluez.enterprise.dto.User;
import com.moodybluez.enterprise.service.IUserService;
import jdk.jshell.execution.Util;
import org.junit.jupiter.api.Test;

import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.sql.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.Map;

import static java.lang.Integer.parseInt;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.verify;

@SpringBootTest
class EnterpriseApplicationTests {

	@Autowired
	private MoodDAOStub moodDAO;
	@Autowired
	private EntryDAOStub entryDAO;
	@Autowired
	private UserDAOStub userDAO;

	private Mood mood = new Mood();
	private Entry entry = new Entry();
	private User user = new User();




	@Test
	void fetchMoodByID_returnsSadForID3() {
		givenMoodDataAreAvailable();
		whenMoodWithID3();
		thenReturnOneSadMoodForID3();
	}

	private void givenMoodDataAreAvailable() {
		mood.setDescription("Sad");
		mood.setMoodid(3);
		moodDAO.saveEntry(mood);
	}

	private void whenMoodWithID3() {
		mood = moodDAO.fetchByMoodID(3);
	}

	private void thenReturnOneSadMoodForID3() {
		String moodDescription = mood.getDescription();
		assertEquals("Sad", moodDescription);
	}

	@Test
	void saveMoodEntry() throws Exception {
		givenMoodDataAreAvailable();
		whenEntryIsCompleted();
		thenReturnMoodEntry();
	}

	private void whenEntryIsCompleted() {
		int moodID = 3;
		String reasonForMood = "I laid in bed all day.";
		LocalDate date = LocalDate.of(2021,2,21);

		entry.setDate(java.sql.Date.valueOf(date));
		entry.setMoodid(moodID);
		entry.setDescription(reasonForMood);
		entry.setEntityid(1);


		entryDAO.saveEntry(entry);
	}

	private void thenReturnMoodEntry() throws Exception {
		Map<Integer, Entry> moodEntries = entryDAO.fetchAll();
		boolean moodEntryPresent = false;
		for (Map.Entry mapElement : moodEntries.entrySet()) {
			int entryID = (int) mapElement.getKey();
			Entry entry = (Entry) mapElement.getValue();

			if (entry.getMoodid() == 3 && entry.getDescription().equals("I laid in bed all day.")
					&& entryID == 1) {
				moodEntryPresent = true;
				break;
			}
		}

		assertTrue(moodEntryPresent);
	}

	@Test
	void fetchEntryByDate_DateHasEntry() {
		givenMoodDataAreAvailable();
		whenEntryIsCompleted();
		whenDateIsClickedOnCalendar();
		thenReturnsEntryOnDate();
	}

	private void whenDateIsClickedOnCalendar() {
		entry = entryDAO.fetchByDate("2021-02-21");
	}

	private void thenReturnsEntryOnDate() {
		String reason = entry.getDescription();
		int mood = entry.getMoodid();
		assertEquals("I laid in bed all day.", reason);
		assertEquals(3, mood);
	}

	@Test
	void saveUser_validateUserIsSaved() throws Exception {
		givenUserDataAreAvailable();
		whenUserRegistersWithUniqueUsername();
		thenSaveUserAndReturnIt();
	}

	private void givenUserDataAreAvailable() throws Exception {
		user.setUserid(123);
		user.setUsername("123");
		user.setPassword("123");
		userDAO.saveEntry(user);
	}

	private void whenUserRegistersWithUniqueUsername() {
		user.setUsername("JustinHayward");
		user.setPassword("ravioli");
	}

	private void thenSaveUserAndReturnIt() throws Exception {
		User createdUser = userDAO.saveEntry(user);
		assertEquals(user, createdUser);
	}
}
