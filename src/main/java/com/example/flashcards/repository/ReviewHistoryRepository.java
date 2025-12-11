package com.example.flashcards.repository;

import com.example.flashcards.domain.ReviewHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface ReviewHistoryRepository extends JpaRepository<ReviewHistory, UUID> {
    List<ReviewHistory> findByCardId(UUID cardId);
}
