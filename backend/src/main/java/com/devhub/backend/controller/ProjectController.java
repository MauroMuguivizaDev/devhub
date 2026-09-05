package com.devhub.backend.controller;

import com.devhub.backend.dto.ProjectRequest;
import com.devhub.backend.dto.ProjectResponse;
import com.devhub.backend.entity.Project;
import com.devhub.backend.service.ProjectService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/projects")
public class ProjectController {

    private final ProjectService projectService;

    public ProjectController(ProjectService projectService) {
        this.projectService = projectService;
    }

    @GetMapping
    public ResponseEntity<List<ProjectResponse>> findAll() {
        List<ProjectResponse> projects = projectService.findAll()
                .stream()
                .map(ProjectResponse::fromEntity)
                .toList();

        return ResponseEntity.ok(projects);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProjectResponse> findById(@PathVariable Long id) {
        return ResponseEntity.ok(
                ProjectResponse.fromEntity(
                        projectService.findById(id)
                )
        );
    }

    @PostMapping
    public ResponseEntity<ProjectResponse> create(
            @Valid @RequestBody ProjectRequest request) {

        Project project = new Project();
        project.setName(request.name());
        project.setDescription(request.description());

        if (request.status() != null && !request.status().isBlank()) {
            project.setStatus(request.status());
        }

        Project createdProject =
                projectService.create(project, request.userId());

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(ProjectResponse.fromEntity(createdProject));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProjectResponse> update(
            @PathVariable Long id,
            @Valid @RequestBody ProjectRequest request) {

        Project project = new Project();
        project.setName(request.name());
        project.setDescription(request.description());

        if (request.status() != null && !request.status().isBlank()) {
            project.setStatus(request.status());
        }

        return ResponseEntity.ok(
                ProjectResponse.fromEntity(
                        projectService.update(id, project)
                )
        );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        projectService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
