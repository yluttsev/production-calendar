package ru.luttsev.internship;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.time.Month;
import java.time.ZoneId;
import java.time.ZonedDateTime;

public class WorkingTimeTest {
    @Test
    public void isWorkingTimeInMoscowZone() {
        ProductionCalendar productionCalendar = new ProductionCalendar();
        ZonedDateTime workingDateTime = ZonedDateTime.of(
                LocalDateTime.of(2024, Month.MAY, 20, 15, 0),
                ZoneId.of("Europe/Moscow")
        );
        Assertions.assertFalse(productionCalendar.isNonWorkingTime(workingDateTime));
    }

    @Test
    public void isNonWorkingTimeInMoscowZone() {
        ProductionCalendar productionCalendar = new ProductionCalendar();
        ZonedDateTime nonWorkingDateTime = ZonedDateTime.of(
                LocalDateTime.of(2024, Month.MAY, 8, 12, 0),
                ZoneId.of("Europe/Moscow")
        );
        Assertions.assertFalse(productionCalendar.isNonWorkingTime(nonWorkingDateTime));
    }

    @Test
    public void isWorkingTimeInCustomZone() {
        ProductionCalendar productionCalendar = new ProductionCalendar();
        ZonedDateTime workingDateTime = ZonedDateTime.of(
                LocalDateTime.of(2024, Month.MAY, 20, 17, 0),
                ZoneId.of("Asia/Novosibirsk")
        );
        Assertions.assertFalse(productionCalendar.isNonWorkingTime(workingDateTime));
    }

    @Test
    public void isNonWorkingTimeInCustomZone() {
        ProductionCalendar productionCalendar = new ProductionCalendar();
        ZonedDateTime nonWorkingDateTime = ZonedDateTime.of(
                LocalDateTime.of(2024, Month.MAY, 20, 9, 0),
                ZoneId.of("Asia/Novosibirsk")
        );
        Assertions.assertTrue(productionCalendar.isNonWorkingTime(nonWorkingDateTime));
    }
}
