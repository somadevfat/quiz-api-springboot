package com.example.quiz_api.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.quiz_api.dto.QuizDto;
import com.example.quiz_api.service.QuizService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

import java.util.List;

@RestController
@RequestMapping("/api/v1/quizzes")
@Tag(name = "Quiz", description = "クイズ管理API")
public class QuizController {

    private final QuizService quizService;

    public QuizController(QuizService quizService) {
        this.quizService = quizService;
    }

    @GetMapping
    @Operation(summary = "クイズ一覧取得", description = "すべてのクイズを取得します")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "クイズ一覧取得成功"),
        @ApiResponse(responseCode = "403", description = "認証が必要です")
    })
    public List<QuizDto> getQuizzes() {
        return quizService.getAllQuizzes();
    }

    @GetMapping("/{id}")
    @Operation(summary = "クイズ詳細取得", description = "指定されたIDのクイズを取得します")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "クイズ詳細取得成功"),
        @ApiResponse(responseCode = "404", description = "クイズが見つかりません"),
        @ApiResponse(responseCode = "403", description = "認証が必要です")
    })
    public QuizDto getQuizById(
        @Parameter(description = "クイズID", required = true, example = "1")
        @PathVariable Long id
    ) {
        return quizService.getQuizById(id);
    }
}