package com.example.project_orion.payload;

import com.example.project_orion.enums.Difficulty;
import com.example.project_orion.enums.Subject;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Filter {

    private Subject subject;

    private Difficulty difficulty;

    private String title;

    List<Long> tagList;
}
