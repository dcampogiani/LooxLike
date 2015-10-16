package com.disorder.presentation.utils;

import org.junit.Before;
import org.junit.Test;
import org.threeten.bp.LocalDateTime;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class DateIntervalCalculatorImplTest {

    private DateIntervalCalculatorImpl subjectUnderTest;

    @Before
    public void setUp() throws Exception {
        subjectUnderTest = new DateIntervalCalculatorImpl();
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldFailsIfStartIsAfterEnd() {
        LocalDateTime start = LocalDateTime.of(2015, 10, 16, 15, 55);
        LocalDateTime end = LocalDateTime.of(2015, 10, 15, 17, 50);
        subjectUnderTest.getDays(start, end);
    }

    @Test
    public void shouldBeOneDay() {
        LocalDateTime start = LocalDateTime.of(2015, 10, 16, 15, 55);
        LocalDateTime end = LocalDateTime.of(2015, 10, 17, 17, 50);
        long expected = 1;
        long result = subjectUnderTest.getDays(start, end);
        assertThat(result, is(expected));
    }

    @Test
    public void shouldBeOneMonth() {
        LocalDateTime start = LocalDateTime.of(2015, 10, 16, 15, 55);
        LocalDateTime end = LocalDateTime.of(2015, 11, 16, 17, 50);
        long expected = 31;
        long result = subjectUnderTest.getDays(start, end);
        assertThat(result, is(expected));
    }

    @Test
    public void shouldBeOneYear() {
        LocalDateTime start = LocalDateTime.of(2015, 10, 16, 15, 55);
        LocalDateTime end = LocalDateTime.of(2016, 10, 16, 17, 50);
        long expected = 366;
        long result = subjectUnderTest.getDays(start, end);
        assertThat(result, is(expected));
    }
}