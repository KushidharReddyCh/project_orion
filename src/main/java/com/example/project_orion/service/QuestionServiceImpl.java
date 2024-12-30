package com.example.project_orion.service;

import com.example.project_orion.models.Answer;
import com.example.project_orion.models.Option;
import com.example.project_orion.models.Question;
import com.example.project_orion.models.Tag;
import com.example.project_orion.payload.QuestionDTO;
import com.example.project_orion.repository.QuestionRepository;
import com.example.project_orion.repository.TagRepository;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;

import java.util.*;

@Service
public class QuestionServiceImpl implements QuestionService{

    @Autowired
    private QuestionRepository questionRepository;

    @Autowired
    private TagRepository tagRepository;

    @Autowired
    private ModelMapper modelMapper;

    private final List<Question> questionList = new ArrayList<>();

    @Override
    public List<Question> getAllQuestions() {
        return questionRepository.findAll();
    }

    @Override
    public QuestionDTO createQuestion(QuestionDTO questionDTO) {

        Question question = Question.builder()
                .title(questionDTO.getTitle())
                .description(questionDTO.getDescription())
                .author(questionDTO.getAuthor())
                .isActive(questionDTO.isActive())
                .subject(questionDTO.getSubject())
                .difficulty(questionDTO.getDifficulty())
                .options(questionDTO.getOptions())
                .tagList(questionDTO.getTagList())
                .build();

        if (question.getOptions() != null) {
            for (Option option : question.getOptions()) {
                option.setQuestion(question);
            }
        }

        if (question.getTagList() != null) {
            Set<Tag> tags = new HashSet<>();
            for (Tag tag : question.getTagList()) {
                Tag existingTag = tagRepository.findByText(tag.getText());
                // Save new tag if it doesn't exist
                tags.add(Objects.requireNonNullElseGet(existingTag, () -> tagRepository.save(tag)));
            }
            question.setTagList(tags);
        }

        Question savedQuestion = questionRepository.save(question);

        List<Option> options = savedQuestion.getOptions();
        Long correctOptionId = options.get(questionDTO.getCorrectOptionId() - 1).getOptionId();

        // Create an Answer and set the correct option
        Answer answer = new Answer();
        answer.setCorrectOptionId(correctOptionId);
        savedQuestion.setAnswer(answer);

        Question updateQuestion = questionRepository.save(savedQuestion);
        return modelMapper.map(updateQuestion, QuestionDTO.class);
    }
}


        /*
        *   Note on Bidirectional Relationships in JPA:
             In a bidirectional relationship, Spring JPA does not automatically
             update the association on both sides. For example, when persisting
             a Question with its Options, the "question" field in the Option entity
             must be explicitly set. This ensures that the foreign key (QUESTION_ID)
             in the options table is populated.

             Reason: JPA does not infer the relationship on the inverse (child) side
             unless explicitly mapped in the code. Without setting the relationship
             on both sides, the child entity (Option) won't know about the parent
             entity (Question), resulting in a null foreign key.

             Example fix:
             Before saving the Question, ensure each Option has its "question" field set:
          */