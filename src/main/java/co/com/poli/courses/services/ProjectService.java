package co.com.poli.courses.services;


import co.com.poli.courses.entities.Project;
import co.com.poli.courses.entities.ProjectTask;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Optional;

public interface ProjectService {

    List<Project> findAll();

    Optional<Project> findByID(Long id);

    ResponseEntity<Project> save(Project project);

    ResponseEntity<List<ProjectTask>> tareas(String projectIdentifier);


}
