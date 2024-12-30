package com.example.project_orion.service;

import com.example.project_orion.models.Question;
import jakarta.validation.Valid;

import java.util.List;

public interface QuestionService {
    List<Question> getAllQuestions();

    Question createQuestion(@Valid Question question);
}
