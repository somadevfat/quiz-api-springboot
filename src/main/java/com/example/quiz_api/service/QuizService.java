package com.example.quiz_api.service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.example.quiz_api.domain.Quiz;
import com.example.quiz_api.dto.QuizDto;
import com.example.quiz_api.repository.QuizRepository;

@Service
public class QuizService {

    private final QuizRepository quizRepository;

    public QuizService(QuizRepository quizRepository) {
        this.quizRepository = quizRepository;
    }

    public List<QuizDto> getAllQuizzes() {
        return quizRepository.findAll()
                .stream()
                .map(this::mapToDto)
                .collect(Collectors.toList());
    }

    public QuizDto getQuizById(Long id) {
        Quiz quiz = quizRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Quiz not found with id: " + id));
        return mapToDto(quiz);
    }

    private QuizDto mapToDto(Quiz quiz) {
        return new QuizDto(
                quiz.getId(),
                quiz.getQid(),
                quiz.getChapter(),
                quiz.getCategory(),
                quiz.getDifficulty(),
                quiz.getChoices(),
                quiz.getCode(),
                quiz.getQuestionText(),
                quiz.getExplanation(),
                quiz.getQuestionCategory()
        );
    }
}