package com.example.project_orion.controller;

import com.example.project_orion.models.Question;
import com.example.project_orion.payload.QuestionDTO;
import com.example.project_orion.service.QuestionService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class QuestionController {

    @Autowired
    private QuestionService questionService;

    @GetMapping("/public/questions")
    public List<Question> getAllQuestions(){
        return questionService.getAllQuestions();
    }

    @PostMapping("/admin/questions")
    public ResponseEntity<QuestionDTO> createQuestion(@Valid @RequestBody QuestionDTO questionDTO){
        QuestionDTO savedQuestionDTO = questionService.createQuestion(questionDTO);
        return new ResponseEntity<>(savedQuestionDTO, HttpStatus.OK);
    }

}
