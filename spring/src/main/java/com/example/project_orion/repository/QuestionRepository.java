package com.example.project_orion.repository;

import com.example.project_orion.models.Question;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QuestionRepository extends JpaRepository<Question, Long> {
    Question findByTitle(@NotBlank @Size(min = 3, max = 50, message = "must contain at-least 3 characters & at-max 25 characters") String title);
}
