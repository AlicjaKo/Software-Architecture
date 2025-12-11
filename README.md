**Flashcards — Spring Boot Demo**

- **Project:** Flashcards spaced-repetition demo implemented with Spring Boot.
- **Location:** `.` (root of repository).

**What It Includes**
- **Spring Boot:** application entry in `src/main/java/com/example/flashcards/FlashcardsApplication.java`.
- **Persistence:** JPA entities `Flashcard`, `ReviewHistory` and Spring Data repositories.
- **Database:** PostgreSQL configured in `src/main/resources/application.yml` and a `postgres:16` service in `docker-compose.yml`.
- **Async & Events:** in-process event model (`CardReviewedEvent`, `CardReviewedListener`) with `@EnableAsync`.
- **Scheduling:** `SchedulerService` uses `@Scheduled` to check due cards.
- **CLI Demo:** a simple interactive CLI is available in `src/main/java/com/example/flashcards/cli/FlashcardCLI.java` (runs at app startup).
- **SRS logic:** present in `SpacedRepetitionService` and `SRSCalculator` utilities.

**Minimum Requirements Mapping**
- **Spring Boot:** implemented.
- **Distributed Database:** implemented (Postgres via Docker Compose).
- **Asynchronization / Event handling:** implemented (Spring async + events).
- **Scheduling / Background tasks:** implemented.

**Prerequisites**
- Java 17
- Maven
- Docker & Docker Compose (or Docker Desktop)

**Run locally (without Docker for the app)**
1. Start only the database:
```bash
docker compose up -d db
```
2. Build and run the Spring Boot app locally:
```bash
mvn -DskipTests package
mvn spring-boot:run
```
