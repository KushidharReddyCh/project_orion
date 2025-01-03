package com.example.project_orion.controller;

import com.example.project_orion.config.AppConstants;
import com.example.project_orion.payload.QuestionDTO;
import com.example.project_orion.payload.QuestionResponse;
import com.example.project_orion.service.QuestionService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class QuestionController {

    @Autowired
    private QuestionService questionService;

    @GetMapping("/public/questions")
    public ResponseEntity<QuestionResponse> getAllQuestions(
            @RequestParam(name = "pageNumber", defaultValue = AppConstants.PAGE_NUMBER, required = false) Integer pageNumber,
            @RequestParam(name = "pageSize", defaultValue = AppConstants.PAGE_SIZE, required = false) Integer pageSize,
            @RequestParam(name = "sortBy", defaultValue = AppConstants.SORT_QUESTIONS_BY, required = false) String sortBy,
            @RequestParam(name = "sortOrder", defaultValue = AppConstants.SORT_DIR, required = false) String sortOrder
    ){
        QuestionResponse questionResponse = questionService.getAllQuestions(pageNumber, pageSize, sortBy, sortOrder);
        return new ResponseEntity<>(questionResponse, HttpStatus.OK);
    }

    @PostMapping("/admin/questions")
    public ResponseEntity<QuestionDTO> createQuestion(@Valid @RequestBody QuestionDTO questionDTO){
        QuestionDTO savedQuestionDTO = questionService.createQuestion(questionDTO);
        return new ResponseEntity<>(savedQuestionDTO, HttpStatus.CREATED);
    }

    @GetMapping("/public/question/{questionId}")
    public ResponseEntity<QuestionDTO> getQuestionById(@PathVariable Long questionId){
        QuestionDTO questionDTO = questionService.getQuestionById(questionId);
        return new ResponseEntity<>(questionDTO, HttpStatus.OK);
    }

    @PutMapping("/admin/question/{questionId}")
    public ResponseEntity<QuestionDTO> updateCategory(@RequestBody QuestionDTO questionDTO, @PathVariable Long questionId){
        QuestionDTO savedQuestionDTO = questionService.updateQuestion(questionId, questionDTO);
        return new ResponseEntity<>(savedQuestionDTO, HttpStatus.OK);
    }

    @DeleteMapping("/admin/question/{questionId}")
    public ResponseEntity<QuestionDTO> deleteCategory(@PathVariable Long questionId){
        QuestionDTO savedQuestionDTO = questionService.deleteQuestion(questionId);
        return new ResponseEntity<>(savedQuestionDTO, HttpStatus.OK);
    }

}
