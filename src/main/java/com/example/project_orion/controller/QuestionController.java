package com.example.project_orion.controller;

import com.example.project_orion.models.Question;
import com.example.project_orion.service.QuestionService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class QuestionController {

    @Autowired
    private QuestionService questionService;

    @GetMapping("/public/questions")
    public List<Question> getAllQuestions(){
        List<Question> questionList = questionService.getAllQuestions();
        return questionList;
    }

    @PostMapping("/admin/questions")
    public Question createQuestion(@Valid @RequestBody Question question){
        return questionService.createQuestion(question);
    }
}
