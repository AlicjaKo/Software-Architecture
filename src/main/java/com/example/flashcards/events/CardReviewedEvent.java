package com.example.flashcards.events;

import java.util.UUID;

public class CardReviewedEvent {
    private final UUID cardId;
    private final int grade;

    public CardReviewedEvent(UUID cardId, int grade) {
        this.cardId = cardId;
        this.grade = grade;
    }

    public UUID getCardId() { return cardId; }
    public int getGrade() { return grade; }
}
