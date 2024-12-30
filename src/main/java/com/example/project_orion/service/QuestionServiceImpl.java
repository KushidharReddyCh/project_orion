package com.example.project_orion.service;

import com.example.project_orion.models.Option;
import com.example.project_orion.models.Question;
import com.example.project_orion.models.Tag;
import com.example.project_orion.repository.QuestionRepository;
import com.example.project_orion.repository.TagRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class QuestionServiceImpl implements QuestionService{

    @Autowired
    private QuestionRepository questionRepository;

    @Autowired
    private TagRepository tagRepository;

    private final List<Question> questionList = new ArrayList<>();

    @Override
    public List<Question> getAllQuestions() {
        return questionRepository.findAll();
    }

    @Override
    public Question createQuestion(Question question) {

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


        if (question.getOptions() != null) {
            for (Option option : question.getOptions()) {
                option.setQuestion(question);
            }
        }


        if (question.getTagList() != null) {
            Set<Tag> tags = new HashSet<>();
            for (Tag tag : question.getTagList()) {
                Tag existingTag = tagRepository.findByText(tag.getText());

                if (existingTag != null) {
                    tags.add(existingTag);
                } else {
                    tags.add(tagRepository.save(tag));  // Save new tag if it doesn't exist
                }
            }
            question.setTagList(tags); // Set tags for the question
        }

        return questionRepository.save(question);
    }

}
