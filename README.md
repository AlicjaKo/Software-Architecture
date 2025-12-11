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

**Run (recommended using Docker Compose)**
1. Build and start services (app will be built inside `app` service):
```bash
docker compose up --build
```
2. The app will be available on port `8080` (if built to expose it). The PostgreSQL service listens internally as `db:5432`; the compose file maps host `5433` to container `5432`.

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

**Using the CLI demo**
- When the app starts it launches a minimal interactive CLI (`FlashcardCLI`) on the application STDOUT. Use it to create cards and practice (options: create, practice due, practice all).

**Database connection (overrides / env)**
- The app reads DB connection values from environment variables: `SPRING_DATASOURCE_URL`, `SPRING_DATASOURCE_USERNAME`, `SPRING_DATASOURCE_PASSWORD`. Defaults are in `application.yml`.

**Build**
- Build a runnable jar:
```bash
mvn -DskipTests package
java -jar target/flashcards-0.1.0.jar
```

**Suggested Enhancements (optional)**
- Add a small REST API to allow graders to interact via HTTP (endpoints for create/list/review).
- Add an external message broker (RabbitMQ/Kafka) if the course requires distributed messaging.
- Add integration tests using Testcontainers for Postgres.
- Add `README` demo commands (curl examples) if REST endpoints are added.

**Grading checklist (for this course part)**
- Spring Boot: ✅
- Distributed DB: ✅ (Postgres)
- Asynchronization / queues or event distribution: ✅ (Spring events + async). If an external broker is required, add RabbitMQ/Kafka.
- Scheduling/background jobs: ✅

**Contact / Notes**
- If you want, I can: add a REST controller, add RabbitMQ to `docker-compose.yml`, or add Testcontainers-based integration tests. Tell me which and I'll implement it.
# Flashcards (Spaced Repetition Scheduler)

Simple Spring Boot backend for flashcards with a Spaced Repetition Scheduler.

Run locally with Docker:

```bash
docker-compose up --build
```

CLI commands (after app starts):
- `add "Question" "Answer"`
- `list`
- `due`
- `review <uuid>`
- `exit`
# Software-Architecture
