package com.devhub.backend.dto;

import com.devhub.backend.entity.Task;

import java.time.LocalDateTime;

public record TaskResponse(
        Long id,
        String title,
        String description,
        String status,
        Long projectId,
        LocalDateTime createdAt
) {

    public static TaskResponse fromEntity(Task task) {
        return new TaskResponse(
                task.getId(),
                task.getTitle(),
                task.getDescription(),
                task.getStatus(),
                task.getProject().getId(),
                task.getCreatedAt()
        );
    }
}
