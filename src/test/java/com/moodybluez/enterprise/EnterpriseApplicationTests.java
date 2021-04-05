package com.moodybluez.enterprise;

import com.moodybluez.enterprise.dao.*;
import com.moodybluez.enterprise.dto.Entry;
import com.moodybluez.enterprise.dto.Mood;
import com.moodybluez.enterprise.dto.User;
import com.moodybluez.enterprise.service.IMoodService;
import com.moodybluez.enterprise.service.IUserService;
import com.moodybluez.enterprise.service.MoodService;
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

	private IMoodService moodService;
	private Mood mood = new Mood();

	@MockBean
	private IMoodDAO moodDAO;

	@MockBean
	private IEntryDAO entryDAO;


	private Entry entry = new Entry();


	private IUserService userService;
	private User user = new User();

	@MockBean
	private IUserDAO userDAO;

	@Test
	void fetchMoodByID_returnsSadForID3() {
		givenMoodDataAreAvailable();
		whenMoodWithID3();
		whenSearchMoodWithID3();
		thenReturnOneSadMoodForID3();
	}

	private void whenSearchMoodWithID3() {
		mood = moodService.fetchByID(3);
	}

	private void givenMoodDataAreAvailable() {
		Mockito.when(moodDAO.saveEntry(mood)).thenReturn(mood);
		moodService = new MoodService(moodDAO);
	}

	private void whenMoodWithID3() {
		Mood mood = new Mood();
		mood.setDescription("Sad");
		mood.setMoodID(3);
		//moodDAO.saveEntry(mood);

		Mockito.when(moodDAO.fetchByID(3)).thenReturn(mood);
	}

	private void thenReturnOneSadMoodForID3() {
		String moodDescription = mood.getDescription();
		assertEquals("Sad", moodDescription);
	}

	@Test
	void saveMoodEntry() throws Exception {
		givenMoodDataAreAvailable();
		whenEntryIsCompleted();
		thenCreateNewMoodEntryAndReturnIt();
	}

	private void whenEntryIsCompleted() {
		int moodID = 3;
		String reasonForMood = "I laid in bed all day.";
		LocalDate date = LocalDate.of(2021,2,21);

		entry.setDate(java.sql.Date.valueOf(date));
		entry.setMoodid(moodID);
		entry.setDescription(reasonForMood);
		entry.setEntityid(1);
	}

	private void thenCreateNewMoodEntryAndReturnIt() throws Exception {
		Mood moodEntry = moodService.saveEntry(mood);
		assertEquals(mood, moodEntry);
		verify(moodDAO, atLeastOnce()).saveEntry(mood);
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
		userDAO.save(user);
	}

	private void whenUserRegistersWithUniqueUsername() {
		user.setUsername("JustinHayward");
		user.setPassword("ravioli");
	}

	private void thenSaveUserAndReturnIt() throws Exception {
		User createdUser = userDAO.save(user);
		assertEquals(user, createdUser);
	}
}
