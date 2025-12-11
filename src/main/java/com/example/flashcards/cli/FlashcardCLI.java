package com.example.flashcards.cli;

import com.example.flashcards.domain.Flashcard;
import com.example.flashcards.service.FlashcardService;
import com.example.flashcards.service.ReviewService;
import com.example.flashcards.service.SpacedRepetitionService;
import com.example.flashcards.util.SRSCalculator;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Scanner;

@Component
public class FlashcardCLI implements CommandLineRunner {
    private final FlashcardService flashcardService;
    private final ReviewService reviewService;
    public FlashcardCLI(FlashcardService flashcardService, ReviewService reviewService, SpacedRepetitionService srsService) {
        this.flashcardService = flashcardService;
        this.reviewService = reviewService;
    }

    @Override
    public void run(String... args) throws Exception {
        try (Scanner scanner = new Scanner(System.in)) {
            System.out.println("Flashcards CLI");
            while (true) {
                System.out.println();
                System.out.println("1) Create new card");
                System.out.println("2) Practice due cards");
                System.out.println("3) Practice all cards");
                System.out.println("4) Exit");
                System.out.print("Choose an option [1-4]: ");
                String choice = scanner.nextLine();
                if (choice == null) break;
                choice = choice.trim();
                if (choice.equals("1")) {
                    System.out.print("Question: ");
                    String question = scanner.nextLine().trim();
                    System.out.print("Answer: ");
                    String answer = scanner.nextLine().trim();
                    if (question.isEmpty() || answer.isEmpty()) {
                        System.out.println("Question and answer cannot be empty.");
                        continue;
                    }
                    var f = flashcardService.create(question, answer);
                    System.out.println("Created flashcard: " + f.getId());
                    continue;
                } else if (choice.equals("2")) {
                    var due = flashcardService.findDueCards();
                    if (due.isEmpty()) {
                        System.out.println("No cards due for practice.");
                        continue;
                    }
                    System.out.println("Practicing " + due.size() + " card(s)");
                    for (var card : due) {
                        System.out.println();
                        System.out.println("Question: " + card.getQuestion());
                        System.out.print("Your answer: ");
                        String userAnswer = scanner.nextLine();
                        boolean correct = false;
                        if (userAnswer != null) {
                            correct = userAnswer.trim().equalsIgnoreCase(card.getAnswer() == null ? "" : card.getAnswer().trim());
                        }
                        if (correct) {
                            System.out.println("Correct!");
                        } else {
                            System.out.println("Incorrect. Correct answer: " + card.getAnswer());
                        }
                        int grade = correct ? 5 : 2;
                        reviewService.review(card.getId(), grade);
                        var r = SRSCalculator.calculate(card.getEaseFactor(), card.getIntervalDays(), grade);
                        long days = java.time.Duration.between(LocalDateTime.now(), r.nextReview).toDays();
                        System.out.printf("Recorded review. Next review in %d day(s).\n", days);
                    }
                    continue;
                } else if (choice.equals("3")) {
                    var all = flashcardService.findAll();
                    if (all.isEmpty()) {
                        System.out.println("No cards available.");
                        continue;
                    }
                    System.out.println("Practicing all " + all.size() + " card(s)");
                    for (var card : all) {
                        System.out.println();
                        System.out.println("Question: " + card.getQuestion());
                        System.out.print("Your answer: ");
                        String userAnswer = scanner.nextLine();
                        boolean correct = false;
                        if (userAnswer != null) {
                            correct = userAnswer.trim().equalsIgnoreCase(card.getAnswer() == null ? "" : card.getAnswer().trim());
                        }
                        if (correct) {
                            System.out.println("Correct!");
                        } else {
                            System.out.println("Incorrect. Correct answer: " + card.getAnswer());
                        }
                        int grade = correct ? 5 : 2;
                        reviewService.review(card.getId(), grade);
                        var r = SRSCalculator.calculate(card.getEaseFactor(), card.getIntervalDays(), grade);
                        long days = java.time.Duration.between(LocalDateTime.now(), r.nextReview).toDays();
                        System.out.printf("Recorded review. Next review in %d day(s).\n", days);
                    }
                    continue;
                } else if (choice.equals("4")) {
                    break;
                } else {
                    System.out.println("Invalid choice. Please select 1, 2 or 3.");
                }
            }
        }
        System.out.println("Goodbye.");
    }
}
