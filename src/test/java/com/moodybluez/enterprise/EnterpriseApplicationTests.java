package com.moodybluez.enterprise;

import com.moodybluez.enterprise.dto.Date;
import com.moodybluez.enterprise.dto.Entry;
import com.moodybluez.enterprise.dto.Mood;
import com.moodybluez.enterprise.service.IEntryService;
import com.moodybluez.enterprise.service.IMoodService;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;

import static java.lang.Integer.parseInt;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
class EnterpriseApplicationTests {

	@Autowired
	private IMoodService moodService;
	@Autowired
	private IEntryService entryService;

	private Mood mood;
	private Entry entry;
	private Date date;

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

		Entry entry = new Entry();
		entry.setMoodID(moodID);
		entry.setReasonDesc(reasonForMood);
		entry.setDate(new Date());
		entry.date.setDate(entryDate);
		entry.date.setDayOfWeekID(dayOfWeek.getValue());
		entry.date.setDayOfWeekDesc(dayOfWeek.name());
		entry.date.setDayOfWeekID(dayOfWeek.getValue());

		entryService.saveEntry(entry);
	}

	private void thenReturnMoodEntry() throws Exception {
		Map<String, Entry> moodEntries = entryService.fetchAll();
		boolean moodEntryPresent = false;
		for (Map.Entry mapElement : moodEntries.entrySet()) {
			String date = (String) mapElement.getKey();
			Entry entry = (Entry) mapElement.getValue();

			if (entry.getMoodID() == 3 && entry.getReasonDesc() == "I laid in bed all day."
					&& date == "2/22/2021") {
				moodEntryPresent = true;
				break;
			}
		}

		assertTrue(moodEntryPresent);
	}

	@Test
	void fetchEntryByDate_DateHasEntry() {
		givenMoodDataAreAvailable();
		whenDateIsClickedOnCalendar();
		thenReturnsEntryOnDate();
	}

	private void whenDateIsClickedOnCalendar() {
		entry = entryService.fetchByDate("2/22/2021");
	}

	private void thenReturnsEntryOnDate() {
		String reason = entry.getReasonDesc();
		int mood = entry.getMoodID();
		assertEquals("I laid in bed all day.", reason);
		assertEquals(3, mood);
	}
}
