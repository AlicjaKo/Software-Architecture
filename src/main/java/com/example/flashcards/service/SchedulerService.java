package com.example.flashcards.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class SchedulerService {
    private static final Logger log = LoggerFactory.getLogger(SchedulerService.class);

    private final FlashcardService flashcardService;

    public SchedulerService(FlashcardService flashcardService) {
        this.flashcardService = flashcardService;
    }

    @Scheduled(cron = "0 * * * * *") // every minute at second 0
    public void checkDue() {
        int due = flashcardService.findDueCards().size();
        // Only log when there are cards due to reduce noise
        if (due > 0) {
            log.info("Scheduler: {} card(s) due for review", due);
        }
    }
}
