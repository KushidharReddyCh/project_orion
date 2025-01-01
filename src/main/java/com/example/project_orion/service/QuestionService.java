package com.example.project_orion.service;

import com.example.project_orion.payload.QuestionDTO;
import com.example.project_orion.payload.QuestionResponse;
import jakarta.validation.Valid;

public interface QuestionService {
    QuestionResponse getAllQuestions(Integer pageNumber, Integer pageSize, String sortBy, String sortOrder);

    QuestionDTO createQuestion(@Valid QuestionDTO questionDTO);

    QuestionDTO getQuestionById(Long questionId);

    QuestionDTO updateQuestion(Long questionId, QuestionDTO questionDTO);

    QuestionDTO deleteQuestion(@Valid Long questionId);
}
