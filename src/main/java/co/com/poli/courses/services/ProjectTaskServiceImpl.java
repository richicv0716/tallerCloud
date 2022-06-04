package co.com.poli.courses.services;

import co.com.poli.courses.entities.Project;
import co.com.poli.courses.entities.ProjectTask;
import co.com.poli.courses.repository.ProjectTaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class ProjectTaskServiceImpl implements ProjectTaskService {

    @Autowired
    private ProjectTaskRepository projectTaskRepository;

    @Override
    public ResponseEntity<ProjectTask> save(ProjectTask projectTask) {
        Map<String, String> errorResponse = new HashMap<>();
        for (ProjectTask p : findAll()) {
            if (p.getProjectIdentifier().equals(projectTask.getProjectIdentifier()) && projectTask.getName().equals(p.getName())) {
                errorResponse.put("message", "Ya existe una tarea con nombre: " + p.getName() + " en el proyecto con ProjectIdentifier: " + p.getProjectIdentifier());
                errorResponse.put("status", HttpStatus.BAD_REQUEST.toString());
                return new ResponseEntity(errorResponse, HttpStatus.BAD_REQUEST);
            }
            if (p.getProjectIdentifier().equals("")) {
                errorResponse.put("message", "El ProjectIdentifier no puede estar vacio");
                errorResponse.put("status", HttpStatus.BAD_REQUEST.toString());

                return new ResponseEntity(errorResponse, HttpStatus.BAD_REQUEST);
            }

        }

        if (!projectTask.getProjectIdentifier().equals(Long.toString(projectTask.getId()))) {
            errorResponse.put("message", "El ProjectIdentifier debe ser el mismo que tiene asociado el backlog");
            errorResponse.put("status", HttpStatus.BAD_REQUEST.toString());
            return new ResponseEntity(errorResponse, HttpStatus.BAD_REQUEST);
        }
        projectTaskRepository.save(projectTask);
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/json; charset=UTF-8");
        headers.add("uri", "/task");
        ResponseEntity response = new ResponseEntity<ProjectTask>(projectTask, headers, HttpStatus.CREATED);
        return response;
    }

    @Override
    public void deleteById(Long id) {
        projectTaskRepository.deleteById(id);
    }

    @Override
    public List<ProjectTask> findAll() {
        return projectTaskRepository.findAll();
    }


    public ResponseEntity<String> horas(String projectIdentifier) {
        double horas = 0;
        for (ProjectTask p : findAll()) {
            if (p.getProjectIdentifier().equals(projectIdentifier) && !p.getStatus().equals("deleted")) {
                horas += p.getHours();
            }
        }
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/json; charset=UTF-8");
        headers.add("uri", "/task/hours/project");
        ResponseEntity response = new ResponseEntity<String>(Double.toString(horas), headers, HttpStatus.OK);
        return response;
    }

    ;

    public ResponseEntity<String> horasStatus(String projectIdentifier, String status) {
        double horas = 0;
        for (ProjectTask p : findAll()) {
            if (p.getProjectIdentifier().equals(projectIdentifier) && !p.getStatus().equals("deleted") && p.getStatus().equals(status)) {
                horas += p.getHours();
            }
        }
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/json; charset=UTF-8");
        headers.add("uri", "/task/hours/project/status");
        ResponseEntity response = new ResponseEntity<String>(Double.toString(horas), headers, HttpStatus.OK);
        return response;
    }

    ;

    public ResponseEntity<List<ProjectTask>> tareas(String projectIdentifier) {
        List<ProjectTask> projectTaskList = new ArrayList<ProjectTask>();
        for (ProjectTask p : findAll()) {
            if (projectIdentifier.equals(p.getProjectIdentifier())) {
                projectTaskList.add(p);
            }
        }
        if (!projectTaskList.isEmpty()) {
            HttpHeaders headers = new HttpHeaders();
            headers.add("Content-Type", "application/json; charset=UTF-8");
            headers.add("uri", "/taks/project/" + projectIdentifier);
            ResponseEntity response = new ResponseEntity<List<ProjectTask>>(projectTaskList, headers, HttpStatus.OK);
            return response;
        } else {
            Map<String, String> errorResponse = new HashMap<>();
            errorResponse.put("message", "No existen tareas para ese proyecto");
            errorResponse.put("status", HttpStatus.NOT_FOUND.toString());
            return new ResponseEntity(errorResponse, HttpStatus.NOT_FOUND);
        }

    }

    ;

    public Optional<ProjectTask> findByID(Long id) {
        return projectTaskRepository.findById(id);
    }

    ;

    public ResponseEntity<ProjectTask> delete2(String projectIdentifier, Long id) {
        Optional<ProjectTask> optionalProjectTask = findByID(id);
        if (optionalProjectTask.isPresent()) {
            ProjectTask projectTask = optionalProjectTask.get();
            deleteById(id);
            projectTask.setStatus("deleted");
            save(projectTask);

            HttpHeaders headers = new HttpHeaders();
            headers.add("Content-Type", "application/json; charset=UTF-8");
            headers.add("uri", "/project");
            ResponseEntity response = new ResponseEntity<ProjectTask>(projectTask, headers, HttpStatus.OK);
            return response;
        } else {
            Map<String, String> errorResponse = new HashMap<>();
            errorResponse.put("message", "No existe ese proyecto");
            errorResponse.put("status", HttpStatus.NOT_FOUND.toString());
            return new ResponseEntity(errorResponse, HttpStatus.NOT_FOUND);
        }
    }

    ;


}
