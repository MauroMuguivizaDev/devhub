package com.devhub.backend.dto;

import com.devhub.backend.entity.Project;

import java.time.LocalDateTime;

public record ProjectResponse(
        Long id,
        String name,
        String description,
        String status,
        Long userId,
        LocalDateTime createdAt
) {

    public static ProjectResponse fromEntity(Project project) {
        return new ProjectResponse(
                project.getId(),
                project.getName(),
                project.getDescription(),
                project.getStatus(),
                project.getUser().getId(),
                project.getCreatedAt()
        );
    }
}
