package com.example.quiz_api.domain;

import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.CollectionTable;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Lob;
import lombok.Data;

@Data
@Entity
@JsonIgnoreProperties(ignoreUnknown = true)
public class Quiz {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String qid;

    private String chapter;
    private String category;
    private String difficulty;

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "quiz_choices", joinColumns = @JoinColumn(name = "quiz_id"))
    @Column(name = "choice", length=1024)
    private List<String> choices;

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "quiz_answers", joinColumns = @JoinColumn(name = "quiz_id"))
    @Column(name = "answer_index")
    private List<Integer> answer;

    @Lob
    @Column(columnDefinition = "TEXT")
    private String code;

    @Lob
    @Column(columnDefinition = "TEXT")
    private String questionText;

    @Lob
    @Column(columnDefinition = "TEXT")
    private String explanation;

    private String questionCategory;


    @JsonProperty("qid")
    private void unpackQidFromNestedObject(Map<String, String> qidMap) {
        this.qid = qidMap.get("en-US");
    }

    @JsonProperty("chapter")
    private void unpackChapterFromNestedObject(Map<String, String> chapterMap) {
        this.chapter = chapterMap.get("en-US");
    }

    @JsonProperty("category")
    private void unpackCategoryFromNestedObject(Map<String, String> categoryMap) {
        this.category = categoryMap.get("en-US");
    }

    @JsonProperty("difficulty")
    private void unpackDifficultyFromNestedObject(Map<String, String> difficultyMap) {
        this.difficulty = difficultyMap.get("en-US");
    }

    @JsonProperty("choices")
    private void unpackChoicesFromNestedObject(Map<String, List<String>> choicesMap) {
        this.choices = choicesMap.get("en-US");
    }

    @JsonProperty("answer")
    private void unpackAnswerFromNestedObject(Map<String, List<Integer>> answerMap) {
        this.answer = answerMap.get("en-US");
    }

    @JsonProperty("code")
    private void unpackCodeFromNestedObject(Map<String, String> codeMap) {
        this.code = codeMap.get("en-US");
    }

    @JsonProperty("questionText")
    private void unpackQuestionTextFromNestedObject(Map<String, String> questionTextMap) {
        this.questionText = questionTextMap.get("en-US");
    }

    @JsonProperty("explanation")
    private void unpackExplanationFromNestedObject(Map<String, String> explanationMap) {
        this.explanation = explanationMap.get("en-US");
    }

    @JsonProperty("questionCategory")
    private void unpackQuestionCategoryFromNestedObject(Map<String, String> questionCategoryMap) {
        this.questionCategory = questionCategoryMap.get("en-US");
    }
}