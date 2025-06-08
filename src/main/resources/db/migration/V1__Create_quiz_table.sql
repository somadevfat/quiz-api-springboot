-- Create quiz table
CREATE TABLE IF NOT EXISTS quiz (
    id BIGSERIAL PRIMARY KEY,
    qid VARCHAR(255) UNIQUE,
    chapter VARCHAR(255),
    category VARCHAR(255),
    difficulty VARCHAR(255),
    code TEXT,
    question_text TEXT,
    explanation TEXT,
    question_category VARCHAR(255)
);

-- Create quiz_choices table
CREATE TABLE IF NOT EXISTS quiz_choices (
    quiz_id BIGINT NOT NULL,
    choice VARCHAR(1024),
    FOREIGN KEY (quiz_id) REFERENCES quiz(id) ON DELETE CASCADE
);

-- Create quiz_answers table  
CREATE TABLE IF NOT EXISTS quiz_answers (
    quiz_id BIGINT NOT NULL,
    answer_index INTEGER,
    FOREIGN KEY (quiz_id) REFERENCES quiz(id) ON DELETE CASCADE
);

-- Create indexes for better performance
CREATE INDEX IF NOT EXISTS idx_quiz_qid ON quiz(qid);
CREATE INDEX IF NOT EXISTS idx_quiz_category ON quiz(category);
CREATE INDEX IF NOT EXISTS idx_quiz_difficulty ON quiz(difficulty);