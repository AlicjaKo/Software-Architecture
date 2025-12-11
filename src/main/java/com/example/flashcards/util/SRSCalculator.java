package com.example.flashcards.util;

import java.time.LocalDateTime;

public class SRSCalculator {
    public static class Result {
        public final double easeFactor;
        public final int intervalDays;
        public final LocalDateTime nextReview;

        public Result(double easeFactor, int intervalDays, LocalDateTime nextReview) {
            this.easeFactor = easeFactor;
            this.intervalDays = intervalDays;
            this.nextReview = nextReview;
        }
    }

    public static Result calculate(double currentEf, int currentInterval, int grade) {
        double ef = currentEf;
        int interval;
        if (grade < 3) {
            interval = 1;
        } else {
            interval = (int) Math.max(1, Math.round(currentInterval * ef));
        }

        ef = ef + (0.1 - (5 - grade) * (0.08 + (5 - grade) * 0.02));
        if (ef < 1.3) ef = 1.3;

        LocalDateTime next = LocalDateTime.now().plusDays(interval);
        return new Result(ef, interval, next);
    }
}
