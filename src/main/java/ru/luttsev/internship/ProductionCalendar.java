package ru.luttsev.internship;

import java.time.*;
import java.util.ArrayList;
import java.util.List;

public class ProductionCalendar {
    private final List<LocalDate> weekends = new ArrayList<>();
    private final LocalTime startWorkingDay = LocalTime.of(9, 0);
    private final LocalTime endWorkingDay = LocalTime.of(18, 0);

    public ProductionCalendar() {
        initWeekends();
        removeDayOffsets();
        addHolidays();
    }

    public boolean isWeekend(LocalDate date) {
        return this.weekends.contains(date);
    }

    public boolean isNonWorkingTime(ZonedDateTime dateTime) {
        LocalDateTime moscowDateTime = dateTime
                .withZoneSameInstant(ZoneId.of("Europe/Moscow"))
                .toLocalDateTime();
        if (weekends.contains(moscowDateTime.toLocalDate()))
            return true;
        LocalTime moscowTime = moscowDateTime.toLocalTime();
        return !(moscowTime.isAfter(startWorkingDay) && moscowTime.isBefore(endWorkingDay));
    }

    private void initWeekends() {
        LocalDate startDate = LocalDate.of(2024, Month.JANUARY, 6);
        LocalDate endDate = LocalDate.of(2024, Month.DECEMBER, 29);

        for (LocalDate currentDate = startDate; currentDate.isBefore(endDate); currentDate = currentDate.plusDays(7)) {
            this.weekends.addAll(List.of(currentDate, currentDate.plusDays(1)));
        }
    }

    private void removeDayOffsets() {
        this.weekends.removeAll(
                List.of(LocalDate.of(2024, Month.APRIL, 27),
                        LocalDate.of(2024, Month.NOVEMBER, 2),
                        LocalDate.of(2024, Month.DECEMBER, 28))
        );
    }

    private void addHolidays() {
        List<Integer> holidayIntervals = List.of(1, 1, 1, 1, 3, 46, 14, 52, 1, 1, 8, 1, 33, 145, 56, 1);
        LocalDate startDate = LocalDate.of(2024, Month.JANUARY, 1);
        for (Integer interval : holidayIntervals) {
            this.weekends.add(startDate);
            startDate = startDate.plusDays(interval);
        }
    }
}
