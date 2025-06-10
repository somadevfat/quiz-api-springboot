package com.example.quiz_api.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.example.quiz_api.domain.Quiz;
import com.example.quiz_api.dto.QuizDto;
import com.example.quiz_api.repository.QuizRepository;

@ExtendWith(MockitoExtension.class)
class QuizServiceTest {

    @Mock
    private QuizRepository quizRepository;

    @InjectMocks
    private QuizService quizService;

    private Quiz testQuiz1;
    private Quiz testQuiz2;

    @BeforeEach
    void setUp() {
        testQuiz1 = new Quiz();
        testQuiz1.setId(1L);
        testQuiz1.setQid("1-1");
        testQuiz1.setChapter("1章");
        testQuiz1.setCategory("パッケージの使用");
        testQuiz1.setDifficulty("初級");
        testQuiz1.setQuestionText("Javaのパッケージ機能に関する記述として、適切なものを3つ選択しなさい。");
        testQuiz1.setChoices(Arrays.asList("A. クラス名の衝突を避ける", "B. ドメイン名を逆順に"));
        testQuiz1.setCode("");
        testQuiz1.setExplanation("パッケージは名前空間を提供します。");
        testQuiz1.setQuestionCategory("パッケージの役割");

        testQuiz2 = new Quiz();
        testQuiz2.setId(2L);
        testQuiz2.setQid("1-2");
        testQuiz2.setChapter("1章");
        testQuiz2.setCategory("パッケージの使用");
        testQuiz2.setDifficulty("中級");
        testQuiz2.setQuestionText("パッケージのインポートに関する記述として正しいものを選択しなさい。");
        testQuiz2.setChoices(Arrays.asList("A. import文は必須", "B. import文は任意"));
        testQuiz2.setCode("");
        testQuiz2.setExplanation("import文は任意です。");
        testQuiz2.setQuestionCategory("インポート");
    }

    @Test
    void getAllQuizzes_shouldReturnAllQuizzes() {
        // Given
        List<Quiz> expectedQuizzes = Arrays.asList(testQuiz1, testQuiz2);
        when(quizRepository.findAll()).thenReturn(expectedQuizzes);

        // When
        List<QuizDto> actualQuizzes = quizService.getAllQuizzes();

        // Then
        assertEquals(2, actualQuizzes.size());
        assertEquals("1-1", actualQuizzes.get(0).qid());
        assertEquals("パッケージの使用", actualQuizzes.get(0).category());
        assertEquals("1-2", actualQuizzes.get(1).qid());
    }

    @Test
    void getAllQuizzes_shouldReturnEmptyListWhenNoQuizzes() {
        // Given
        when(quizRepository.findAll()).thenReturn(Arrays.asList());

        // When
        List<QuizDto> actualQuizzes = quizService.getAllQuizzes();

        // Then
        assertEquals(0, actualQuizzes.size());
    }

    @Test
    void getQuizById_shouldReturnQuizDto_whenQuizExists() {
        // Given
        when(quizRepository.findById(1L)).thenReturn(Optional.of(testQuiz1));

        // When
        QuizDto actualQuiz = quizService.getQuizById(1L);

        // Then
        assertNotNull(actualQuiz);
        assertEquals(1L, actualQuiz.id());
        assertEquals("1-1", actualQuiz.qid());
        assertEquals("パッケージの使用", actualQuiz.category());
        assertEquals("初級", actualQuiz.difficulty());
    }

    @Test
    void getQuizById_shouldThrowNoSuchElementException_whenQuizNotExists() {
        // Given
        when(quizRepository.findById(999L)).thenReturn(Optional.empty());

        // When & Then
        NoSuchElementException exception = assertThrows(
            NoSuchElementException.class, 
            () -> quizService.getQuizById(999L)
        );
        assertEquals("Quiz not found with id: 999", exception.getMessage());
    }
}