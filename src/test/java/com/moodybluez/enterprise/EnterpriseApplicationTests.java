package com.moodybluez.enterprise;

import com.moodybluez.enterprise.dao.IEntryDAO;
import com.moodybluez.enterprise.dao.IMoodDAO;
import com.moodybluez.enterprise.dao.IUserDAO;
import com.moodybluez.enterprise.dto.Date;
import com.moodybluez.enterprise.dto.Entry;
import com.moodybluez.enterprise.dto.Mood;
import com.moodybluez.enterprise.dto.User;
import com.moodybluez.enterprise.service.IUserService;
import com.moodybluez.enterprise.service.UserService;
import org.junit.jupiter.api.Test;

import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

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
	private IMoodDAO moodDAO;
	@Autowired
	private IEntryDAO entryDAO;

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
		mood.setMoodID(3);
		moodDAO.createEntry(mood);
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

		entry.setMoodID(moodID);
		entry.setDescription(reasonForMood);
		entry.setDate(new Date());
		entry.setEntryID(1);
		entry.setWeekDayID(dayOfWeek.getValue());
		entry.getDate().setDate(entryDate);
		entry.getDate().setWeekDayID(dayOfWeek.getValue());
		entry.getDate().setWeekDay(dayOfWeek.name());

		verify(entryDAO, atLeastOnce()).saveEntry(entry);
	}

	private void thenReturnMoodEntry() throws Exception {
		Map<Integer, Entry> moodEntries = entryDAO.fetchAll();
		boolean moodEntryPresent = false;
		for (Map.Entry mapElement : moodEntries.entrySet()) {
			int entryID = (int) mapElement.getKey();
			Entry entry = (Entry) mapElement.getValue();

			if (entry.getMoodID() == 3 && entry.getDescription().equals("I laid in bed all day.")
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
		int mood = entry.getMoodID();
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
		user.setUserName("JustinHayward");
		user.setPassword("ravioli");
	}

	private void thenSaveUserAndReturnIt() throws Exception {
		User createdUser = userService.save(user);
		assertEquals(user, createdUser);
		verify(userDAO, atLeastOnce()).save(user);
	}
}