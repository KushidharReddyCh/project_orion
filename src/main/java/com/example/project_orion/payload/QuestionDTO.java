package com.example.project_orion.payload;

import com.example.project_orion.enums.Difficulty;
import com.example.project_orion.enums.Status;
import com.example.project_orion.enums.Subject;
import com.example.project_orion.models.Option;
import com.example.project_orion.models.Tag;
import jakarta.validation.constraints.*;
import lombok.*;

import java.util.List;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class QuestionDTO {

    private Long questionId;

    @NotBlank
    @Size(min = 3, max = 50, message = "must contain at-least 3 characters & at-max 25 characters")
    private String title;

    @NotBlank
    @Size(min = 6, max = 1000, message = "must contain at-least 6 characters & at-max 1000 characters")
    private String description;

    private String author;

    @NotNull
    private Status status;

    @NotNull(message = "Subject must not be null")
    private Subject subject;

    @NotNull(message = "Difficulty must not be null")
    private Difficulty difficulty;

    //TODO: is options mandatory or not?
    private List<Option> options;

    private Set<Tag> tagList;

    //TODO: is correctOptionId mandatory or not?
    @Min(1) @Max(4)
    private Integer correctOptionId;

}
