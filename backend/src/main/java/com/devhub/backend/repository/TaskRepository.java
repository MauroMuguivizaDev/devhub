package com.devhub.backend.repository;

import com.devhub.backend.entity.Project;
import com.devhub.backend.entity.Task;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TaskRepository extends JpaRepository<Task, Long> {

    List<Task> findByProject(Project project);

    List<Task> findByStatus(String status);
}
