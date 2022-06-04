package co.com.poli.courses.controller;

import co.com.poli.courses.entities.Backlog;
import co.com.poli.courses.entities.Project;
import co.com.poli.courses.entities.ProjectTask;
import co.com.poli.courses.services.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/project")
//@RequiredArgsConstructor
public class ProjectController {
    @Autowired
    ProjectService projectService;

    @GetMapping
    List<Project> findAll() {
        return projectService.findAll();
    }

    @RequestMapping(method = RequestMethod.POST, value = "")
    public ResponseEntity<Project> save(@Valid @RequestBody Project project) {
        return projectService.save(project);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/{id}")
    public ResponseEntity<List<ProjectTask>> tareas(@PathVariable("id") String id) {
        return projectService.tareas(id);
    }
}
