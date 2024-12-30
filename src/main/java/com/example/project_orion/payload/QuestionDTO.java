package com.example.project_orion.payload;

import com.example.project_orion.enums.Difficulty;
import com.example.project_orion.enums.Subject;
import com.example.project_orion.models.Option;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class QuestionDTO {
    private String title;
    private String description;
    private Subject subject;
    private Difficulty difficulty;
    private List<Option> options;
    private Long correctOptionId;
}
