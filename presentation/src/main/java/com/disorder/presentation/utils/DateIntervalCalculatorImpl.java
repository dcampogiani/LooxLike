package com.disorder.presentation.utils;

import org.threeten.bp.LocalDateTime;
import org.threeten.bp.temporal.ChronoUnit;

import javax.inject.Inject;

public class DateIntervalCalculatorImpl implements DateIntervalCalculator {

    @Inject
    public DateIntervalCalculatorImpl() {
    }

    @Override
    public int getDays(LocalDateTime start, LocalDateTime end) {

        if (start.isAfter(end))
            throw new IllegalArgumentException();

        return (int) ChronoUnit.DAYS.between(start, end);
    }
}
