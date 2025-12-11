package com.example.flashcards.events;

import com.example.flashcards.domain.Flashcard;
import com.example.flashcards.service.FlashcardService;
import com.example.flashcards.service.SpacedRepetitionService;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class CardReviewedListener {
    private final SpacedRepetitionService srs;
    private final FlashcardService flashcardService;

    public CardReviewedListener(SpacedRepetitionService srs, FlashcardService flashcardService) {
        this.srs = srs;
        this.flashcardService = flashcardService;
    }

    @Async
    @EventListener
    public void handle(CardReviewedEvent event) {
        Optional<Flashcard> opt = flashcardService.findById(event.getCardId());
        if (opt.isEmpty()) return;
        Flashcard card = opt.get();
        srs.applyReview(card, event.getGrade());
        flashcardService.save(card);
    }
}
