package com.moodybluez.enterprise;

import com.moodybluez.enterprise.dao.*;
import com.moodybluez.enterprise.dto.Entry;
import com.moodybluez.enterprise.dto.Mood;
import com.moodybluez.enterprise.dto.User;
import com.moodybluez.enterprise.service.IUserService;
import org.junit.jupiter.api.Test;

import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.sql.Date;
import java.time.DayOfWeek;
import java.time.LocalDate;
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

	private Mood mood = new Mood();
	private Entry entry = new Entry();


	private IUserService userService;
	private User user = new User();

	@MockBean
	private IUserDAO userDAO;

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
		String entryDate = "2/22/2021";

		String[] dateSplit = entryDate.split("/");
		int month = parseInt(dateSplit[0]);
		int day = parseInt(dateSplit[1]);
		int year = parseInt(dateSplit[2]);

		LocalDate localDate
				= LocalDate.of(year, month, day);

		DayOfWeek dayOfWeek = DayOfWeek.from(localDate);

		entry.setEntityid(moodID);
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
		entry = entryDAO.fetchByDate("2/22/2021");
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
		Mockito.when(userDAO.saveEntry(user)).thenReturn(user);
	}

	private void whenUserRegistersWithUniqueUsername() {
		user.setUsername("JustinHayward");
		user.setPassword("ravioli");
	}

	private void thenSaveUserAndReturnIt() throws Exception {
		User createdUser = userService.save(user);
		assertEquals(user, createdUser);
		verify(userDAO, atLeastOnce()).saveEntry(user);
	}
}