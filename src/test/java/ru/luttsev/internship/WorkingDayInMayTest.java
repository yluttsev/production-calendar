package ru.luttsev.internship;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.Month;

public class WorkingDayInMayTest {
    @Test
    public void isWorkingDay() {
        ProductionCalendar productionCalendar = new ProductionCalendar();
        LocalDate date = LocalDate.of(2024, Month.MAY, 20);
        Assertions.assertFalse(productionCalendar.isWeekend(date));
    }

    @Test
    public void isNonWorkingDay() {
        ProductionCalendar productionCalendar = new ProductionCalendar();
        LocalDate date = LocalDate.of(2024, Month.MAY, 10);
        Assertions.assertTrue(productionCalendar.isWeekend(date));
    }
}
