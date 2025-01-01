package com.example.project_orion.payload;

import com.example.project_orion.enums.SubmissionStatus;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ValidationResponse {
    private SubmissionStatus submissionStatus;
}
