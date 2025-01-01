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

    @NotNull
    @Size(min = 4, max = 4, message = "must be exactly 4")
    private List<Option> options;

    @NotNull // use jakartha NotNull not lombok one
    @Size(min = 1, message = "at-least 1 tag needs to present")
    private Set<Tag> tagList;

    @NotNull
    @Min(1) @Max(4)
    private Integer correctOptionId;

}
