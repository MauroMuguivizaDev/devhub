package com.devhub.backend.controller;

import com.devhub.backend.dto.TaskRequest;
import com.devhub.backend.dto.TaskResponse;
import com.devhub.backend.entity.Task;
import com.devhub.backend.service.TaskService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tasks")
public class TaskController {

    private final TaskService taskService;

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @GetMapping
    public ResponseEntity<List<TaskResponse>> findAll() {
        List<TaskResponse> tasks = taskService.findAll()
                .stream()
                .map(TaskResponse::fromEntity)
                .toList();

        return ResponseEntity.ok(tasks);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TaskResponse> findById(@PathVariable Long id) {
        return ResponseEntity.ok(
                TaskResponse.fromEntity(
                        taskService.findById(id)
                )
        );
    }

    @PostMapping
    public ResponseEntity<TaskResponse> create(
            @Valid @RequestBody TaskRequest request) {

        Task task = new Task();
        task.setTitle(request.title());
        task.setDescription(request.description());

        if (request.status() != null && !request.status().isBlank()) {
            task.setStatus(request.status());
        }

        Task createdTask =
                taskService.create(task, request.projectId());

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(TaskResponse.fromEntity(createdTask));
    }

    @PutMapping("/{id}")
    public ResponseEntity<TaskResponse> update(
            @PathVariable Long id,
            @Valid @RequestBody TaskRequest request) {

        Task task = new Task();
        task.setTitle(request.title());
        task.setDescription(request.description());

        if (request.status() != null && !request.status().isBlank()) {
            task.setStatus(request.status());
        }

        return ResponseEntity.ok(
                TaskResponse.fromEntity(
                        taskService.update(id, task)
                )
        );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        taskService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
