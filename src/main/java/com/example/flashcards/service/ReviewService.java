package com.example.flashcards.service;

import com.example.flashcards.domain.ReviewHistory;
import com.example.flashcards.repository.ReviewHistoryRepository;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class ReviewService {
    private final ReviewHistoryRepository historyRepository;
    private final ApplicationEventPublisher publisher;

    public ReviewService(ReviewHistoryRepository historyRepository, ApplicationEventPublisher publisher) {
        this.historyRepository = historyRepository;
        this.publisher = publisher;
    }

    public ReviewHistory review(UUID cardId, int grade) {
        ReviewHistory h = new ReviewHistory(cardId, grade);
        ReviewHistory saved = historyRepository.save(h);
        publisher.publishEvent(new com.example.flashcards.events.CardReviewedEvent(cardId, grade));
        return saved;
    }
}
