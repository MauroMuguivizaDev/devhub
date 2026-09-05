package com.devhub.backend.repository;

import com.devhub.backend.entity.Project;
import com.devhub.backend.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProjectRepository extends JpaRepository<Project, Long> {

    List<Project> findByUser(User user);

    List<Project> findByStatus(String status);
}
