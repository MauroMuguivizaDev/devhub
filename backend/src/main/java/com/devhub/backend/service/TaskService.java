package com.devhub.backend.service;

import com.devhub.backend.entity.Project;
import com.devhub.backend.entity.Task;
import com.devhub.backend.repository.ProjectRepository;
import com.devhub.backend.repository.TaskRepository;
import org.springframework.stereotype.Service;
import com.devhub.backend.exception.ResourceNotFoundException;
import com.devhub.backend.exception.BusinessException;

import java.util.List;

@Service
public class TaskService {

    private final TaskRepository taskRepository;
    private final ProjectRepository projectRepository;

    public TaskService(
            TaskRepository taskRepository,
            ProjectRepository projectRepository) {
        this.taskRepository = taskRepository;
        this.projectRepository = projectRepository;
    }

    public List<Task> findAll() {
        return taskRepository.findAll();
    }

    public Task findById(Long id) {
        return taskRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Tarefa não encontrada"));
    }

    public Task create(Task task, Long projectId) {
        Project project = projectRepository.findById(projectId)
                .orElseThrow(() -> new ResourceNotFoundException("Projeto não encontrado"));

        task.setProject(project);

        return taskRepository.save(task);
    }

    public Task update(Long id, Task updatedTask) {
        Task task = findById(id);

        task.setTitle(updatedTask.getTitle());
        task.setDescription(updatedTask.getDescription());
        task.setStatus(updatedTask.getStatus());

        return taskRepository.save(task);
    }

    public void delete(Long id) {
        Task task = findById(id);
        taskRepository.delete(task);
    }
}
