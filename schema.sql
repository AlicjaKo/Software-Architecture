-- Optional schema file for initial DB setup (Postgres)
CREATE TABLE IF NOT EXISTS flashcard (
  id uuid PRIMARY KEY,
  question text,
  answer text,
  ease_factor double precision,
  interval_days integer,
  next_review timestamp,
  create_date timestamp
);

CREATE TABLE IF NOT EXISTS review_history (
  id uuid PRIMARY KEY,
  card_id uuid,
  reviewed_at timestamp,
  grade integer
);
