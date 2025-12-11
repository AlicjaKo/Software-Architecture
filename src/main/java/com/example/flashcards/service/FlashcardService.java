package com.example.flashcards.service;

import com.example.flashcards.domain.Flashcard;
import com.example.flashcards.repository.FlashcardRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class FlashcardService {
    private final FlashcardRepository repository;

    public FlashcardService(FlashcardRepository repository) {
        this.repository = repository;
    }

    public Flashcard create(String question, String answer) {
        Flashcard f = new Flashcard();
        f.setQuestion(question);
        f.setAnswer(answer);
        return repository.save(f);
    }

    public List<Flashcard> findAll() { return repository.findAll(); }

    public List<Flashcard> findDueCards() {
        return repository.findByNextReviewBefore(LocalDateTime.now().plusSeconds(1));
    }

    public Optional<Flashcard> findById(UUID id) { return repository.findById(id); }

    public Flashcard save(Flashcard card) { return repository.save(card); }

    public void delete(UUID id) { repository.deleteById(id); }
}
