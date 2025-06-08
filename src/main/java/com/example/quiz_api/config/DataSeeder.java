package com.example.quiz_api.config;

import java.io.InputStream;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.example.quiz_api.domain.Quiz;
import com.example.quiz_api.repository.QuizRepository;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

@Component
public class DataSeeder implements CommandLineRunner {

    private static final Logger logger = LoggerFactory.getLogger(DataSeeder.class);
    private final QuizRepository quizRepository;
    private final ObjectMapper objectMapper;

    public DataSeeder(QuizRepository quizRepository, ObjectMapper objectMapper) {
        this.quizRepository = quizRepository;
        this.objectMapper = objectMapper;
    }

    @Override
    public void run(String... args) {
        if (quizRepository.count() == 0) {
            try (InputStream inputStream = TypeReference.class.getResourceAsStream("/clean_questions.json")) {
                if (inputStream == null) {
                    logger.error("clean_questions.json not found!");
                    return;
                }
                List<Quiz> quizzes = objectMapper.readValue(inputStream, new TypeReference<List<Quiz>>() {});
                quizRepository.saveAll(quizzes);
                logger.info("{} quizzes have been saved to the database.", quizzes.size());
            } catch (Exception e) {
                logger.error("An error occurred while seeding the database.", e);
            }
        } else {
            logger.info("Database already contains data. Skipping seeding.");
        }
    }
}