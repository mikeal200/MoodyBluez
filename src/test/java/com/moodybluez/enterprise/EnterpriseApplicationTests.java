package com.moodybluez.enterprise;

import com.moodybluez.enterprise.dao.IEntryDAO;
import com.moodybluez.enterprise.dao.IMoodDAO;
import com.moodybluez.enterprise.dao.IUserDAO;
import com.moodybluez.enterprise.dto.Entry;
import com.moodybluez.enterprise.dto.Mood;
import com.moodybluez.enterprise.dto.User;
import com.moodybluez.enterprise.service.*;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.verify;

@SpringBootTest
class EnterpriseApplicationTests {

	private IMoodService moodService;
	private Mood mood = new Mood();

	@MockBean
	private IMoodDAO moodDAO;

	private IEntryService entryService;
	private Entry entry = new Entry();

	@MockBean
	private IEntryDAO entryDAO;

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
		mood = moodService.fetchById(3);
	}

	private void givenMoodDataAreAvailable() {
		Mockito.when(moodDAO.save(mood)).thenReturn(mood);
		moodService = new MoodService(moodDAO);
	}

	private void whenMoodWithID3() {
		Mood mood = new Mood();
		mood.setDescription("Sad");
		mood.setMoodId(3);

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

	private void whenSearchEntryWithSpecificDate() {
		LocalDate date = LocalDate.of(2021,2,21);
		entry = entryService.fetchByDate(java.sql.Date.valueOf(date).toString());
	}

	private void whenEntryIsCompleted() {
		Entry dateEntry = new Entry();
		LocalDate date = LocalDate.of(2021,2,21);

		dateEntry.setDate(java.sql.Date.valueOf(date));
		dateEntry.setMoodId(3);
		dateEntry.setDescription("I laid in bed all day.");
		dateEntry.setEntryId(1);

		Mockito.when(entryDAO.fetchByDate(java.sql.Date.valueOf(date).toString())).thenReturn(dateEntry);
	}

	private void thenCreateNewMoodEntryAndReturnIt() throws Exception {
		Mood moodEntry = moodService.save(mood);
		assertEquals(mood, moodEntry);
		verify(moodDAO, atLeastOnce()).save(mood);
	}

	@Test
	void fetchEntryByDate_DateHasEntry() {
		givenEntryDataAreAvailable();
		whenEntryIsCompleted();
		whenDateIsClickedOnCalendar();
		thenReturnsEntryOnDate();
	}

	private void givenEntryDataAreAvailable() {
		Mockito.when(entryDAO.save(entry)).thenReturn(entry);
		entryService = new EntryService(entryDAO);
	}

	private void whenDateIsClickedOnCalendar() {
		String date = "2021-02-21";
		entry = entryService.fetchByDate(date);
	}

	private void thenReturnsEntryOnDate() {
		String reason = entry.getDescription();
		int mood = entry.getMoodId();
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
		Mockito.when(userDAO.save(user)).thenReturn(user);
		userService = new UserService(userDAO);
	}

	private void whenUserRegistersWithUniqueUsername() {
		user.setUsername("JustinHayward");
		user.setPassword("ravioli");
	}

	private void thenSaveUserAndReturnIt() throws Exception {
		User createdUser = userService.save(user);
		assertEquals(user, createdUser);
		verify(userDAO, atLeastOnce()).save(user);
	}
}
