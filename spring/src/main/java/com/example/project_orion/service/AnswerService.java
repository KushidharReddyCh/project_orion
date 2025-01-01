package com.example.project_orion.service;

import com.example.project_orion.models.Submission;
import com.example.project_orion.payload.AnswerResponse;
import com.example.project_orion.payload.ValidationResponse;

public interface AnswerService {
    AnswerResponse getAnswer(Long questionId);

    ValidationResponse validateAnswer(Submission submission);
}

