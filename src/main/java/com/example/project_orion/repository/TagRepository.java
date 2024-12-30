package com.example.project_orion.repository;

import com.example.project_orion.models.Tag;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TagRepository extends JpaRepository<Tag, Long> {
    Tag findByText(String text);
}
