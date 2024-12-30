package com.example.project_orion.service;

import com.example.project_orion.models.Question;
import com.example.project_orion.payload.QuestionDTO;
import jakarta.validation.Valid;

import java.util.List;

public interface QuestionService {
    List<Question> getAllQuestions();

    QuestionDTO createQuestion(@Valid QuestionDTO questionDTO);
}
