package co.com.poli.courses.services;

import co.com.poli.courses.entities.Project;
import co.com.poli.courses.entities.ProjectTask;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface ProjectTaskService {
    ResponseEntity<ProjectTask> save(ProjectTask projectTask);

    void deleteById(Long id);

    List<ProjectTask> findAll();

    ResponseEntity<List<ProjectTask>> tareas(String projectIdentifier);

    ResponseEntity<String> horas(String projectIdentifier);

    ResponseEntity<String> horasStatus(String projectIdentifier, String status);

    ResponseEntity<ProjectTask> delete2(String projectIdentifier, Long id);
}
