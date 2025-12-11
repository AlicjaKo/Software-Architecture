package com.example.flashcards.service;

import com.example.flashcards.domain.Flashcard;
import com.example.flashcards.util.SRSCalculator;
import org.springframework.stereotype.Service;

@Service
public class SpacedRepetitionService {

    public Flashcard applyReview(Flashcard card, int grade) {
        SRSCalculator.Result r = SRSCalculator.calculate(card.getEaseFactor(), card.getIntervalDays(), grade);
        card.setEaseFactor(r.easeFactor);
        card.setIntervalDays(r.intervalDays);
        card.setNextReview(r.nextReview);
        return card;
    }
}
