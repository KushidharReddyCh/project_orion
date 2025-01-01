package com.example.project_orion.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Table(name = "options")
public class Option {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long optionId;

    private String text;

    @ManyToOne
    @JoinColumn(name = "question_id")
    @JsonIgnore
    private Question question;

    /*
       In bidirectional relationships, JSON serialization can cause infinite recursion
       (e.g., Question -> Options -> Question -> ...).
       Adding @JsonIgnore to the child entity's parent reference (e.g., Option.question)
       breaks this loop by ignoring the parent during serialization.
    */


}
