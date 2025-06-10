package com.example.quiz_api.dto;

import java.util.List;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "クイズ情報")
public record QuizDto(
    @Schema(description = "クイズID", example = "1")
    Long id,
    
    @Schema(description = "クイズ識別子", example = "1-1")
    String qid,
    
    @Schema(description = "章", example = "1章")
    String chapter,
    
    @Schema(description = "カテゴリ", example = "パッケージの使用")
    String category,
    
    @Schema(description = "難易度", example = "初級")
    String difficulty,
    
    @Schema(description = "選択肢一覧")
    List<String> choices,
    
    @Schema(description = "コード例", example = "public class Example {}")
    String code,
    
    @Schema(description = "問題文")
    String questionText,
    
    @Schema(description = "解説")
    String explanation,
    
    @Schema(description = "問題カテゴリ", example = "パッケージの役割")
    String questionCategory
) {}