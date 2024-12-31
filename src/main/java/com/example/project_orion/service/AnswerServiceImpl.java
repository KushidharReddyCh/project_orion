package com.example.project_orion.service;
import com.example.project_orion.enums.SubmissionStatus;
import com.example.project_orion.exceptions.APIException;
import com.example.project_orion.models.Question;
import com.example.project_orion.models.Submission;
import com.example.project_orion.payload.AnswerResponse;
import com.example.project_orion.payload.ValidationResponse;
import com.example.project_orion.repository.QuestionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class AnswerServiceImpl implements AnswerService{

    @Autowired
    private QuestionRepository questionRepository;

    @Override
    public AnswerResponse getAnswer(Long questionId) {
        Question question = questionRepository.findById(questionId)
                .orElseThrow(() -> new APIException("Question with id " + questionId + " not found!"));

        Long correctOptionId = question.getAnswer() != null ? question.getAnswer().getCorrectOptionId() : null;

        return AnswerResponse.builder()
                                .questionId(questionId)
                                .correctOptionId(correctOptionId)
                                .build();
    }

    @Override
    public ValidationResponse validateAnswer(Submission submission) {

        Long questionId = submission.getQuestionId();
        Long submittedOptionId = submission.getOptionId();
        Question question = questionRepository.findById(questionId)
                .orElseThrow(() -> new APIException("Question with id " + questionId + " not found!"));

        Long correctOptionId = question.getAnswer() != null ? question.getAnswer().getCorrectOptionId() : null;
        if(correctOptionId == null){
            return new ValidationResponse(SubmissionStatus.ANSWER_DOES_NOT_EXIST);
        } else if (Objects.equals(correctOptionId, submittedOptionId)) {
            return new ValidationResponse(SubmissionStatus.CORRECT);
        }else{
            return new ValidationResponse(SubmissionStatus.INCORRECT);
        }
    }
}
