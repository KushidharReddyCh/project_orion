package com.example.project_orion.models;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Submission{

    @NonNull
    private Long questionId;

    @NonNull
    private Long optionId;
}
