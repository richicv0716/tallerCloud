package co.com.poli.courses.services;

import co.com.poli.courses.entities.Backlog;
import co.com.poli.courses.entities.Project;
import co.com.poli.courses.entities.ProjectTask;
import co.com.poli.courses.repository.ProjectRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProjectServiceImpl implements ProjectService {

    private final ProjectRepository projectRepository;

    @Override
    public ResponseEntity<Project> save(Project project) {
        Map<String, String> errorResponse = new HashMap<>();
        for (Project p : findAll()) {
            if (project.getProjectIdentifier().equals(p.getProjectIdentifier())) {
                errorResponse.put("message", "Ya existe un proyecto con ProjectIdentifier: " + p.getProjectIdentifier());
                errorResponse.put("status", HttpStatus.BAD_REQUEST.toString());

                return new ResponseEntity(errorResponse, HttpStatus.BAD_REQUEST);
            }
            if (project.getProjectIdentifier().equals("")) {
                errorResponse.put("message", "El ProjectIdentifier no puede estar vacio");
                errorResponse.put("status", HttpStatus.BAD_REQUEST.toString());

                return new ResponseEntity(errorResponse, HttpStatus.BAD_REQUEST);
            }

        }
        projectRepository.save(project);
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/json; charset=UTF-8");
        headers.add("uri", "/project");
        ResponseEntity response = new ResponseEntity<Project>(project, headers, HttpStatus.CREATED);
        return response;
    }

    @Override
    public List<Project> findAll() {
        return projectRepository.findAll();
    }

    @Override
    public Optional<Project> findByID(Long id) {
        return projectRepository.findById(id);
    }


    public ResponseEntity<List<ProjectTask>> tareas(String projectIdentifier) {
        for (Project p : findAll()) {
            if (projectIdentifier.equals(p.getProjectIdentifier())) {

                HttpHeaders headers = new HttpHeaders();
                headers.add("Content-Type", "application/json; charset=UTF-8");
                headers.add("uri", "/project");
                ResponseEntity response = new ResponseEntity<List<ProjectTask>>(p.getBacklog().getProjectTasks(), headers, HttpStatus.OK);
                return response;

            }
        }
        Map<String, String> errorResponse = new HashMap<>();
        errorResponse.put("message", "No existen tareas para ese proyecto");
        errorResponse.put("status", HttpStatus.NOT_FOUND.toString());
        return new ResponseEntity(errorResponse, HttpStatus.NOT_FOUND);
    }

    ;
}
