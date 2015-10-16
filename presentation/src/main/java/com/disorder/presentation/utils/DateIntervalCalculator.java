package com.disorder.presentation.utils;


import org.threeten.bp.LocalDateTime;

public interface DateIntervalCalculator {

    int getDays(LocalDateTime start, LocalDateTime end);
}
