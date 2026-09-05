package com.devhub.backend.service;

import com.devhub.backend.entity.Project;
import com.devhub.backend.entity.User;
import com.devhub.backend.repository.ProjectRepository;
import com.devhub.backend.repository.UserRepository;
import org.springframework.stereotype.Service;
import com.devhub.backend.exception.ResourceNotFoundException;
import com.devhub.backend.exception.BusinessException;

import java.util.List;

@Service
public class ProjectService {

    private final ProjectRepository projectRepository;
    private final UserRepository userRepository;

    public ProjectService(
            ProjectRepository projectRepository,
            UserRepository userRepository) {
        this.projectRepository = projectRepository;
        this.userRepository = userRepository;
    }

    public List<Project> findAll() {
        return projectRepository.findAll();
    }

    public Project findById(Long id) {
        return projectRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Projeto não encontrado"));
    }

    public Project create(Project project, Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("Utilizador não encontrado"));

        project.setUser(user);

        return projectRepository.save(project);
    }

    public Project update(Long id, Project updatedProject) {
        Project project = findById(id);

        project.setName(updatedProject.getName());
        project.setDescription(updatedProject.getDescription());
        project.setStatus(updatedProject.getStatus());

        return projectRepository.save(project);
    }

    public void delete(Long id) {
        Project project = findById(id);
        projectRepository.delete(project);
    }
}
