package com.example.project_orion.payload;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AnswerResponse {
    private Long questionId;
    private Long correctOptionId;
}
