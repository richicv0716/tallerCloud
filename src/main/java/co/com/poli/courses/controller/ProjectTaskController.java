package co.com.poli.courses.controller;

import co.com.poli.courses.entities.Project;
import co.com.poli.courses.entities.ProjectTask;
import co.com.poli.courses.services.ProjectTaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/task")
//@RequiredArgsConstructor
public class ProjectTaskController {

    @Autowired
    ProjectTaskService projectTaskService;

    @GetMapping
    List<ProjectTask> findAll() {
        return projectTaskService.findAll();
    }

    @RequestMapping(method = RequestMethod.POST, value = "")
    public ResponseEntity<ProjectTask> save(@Valid @RequestBody ProjectTask projectTask) {
        return projectTaskService.save(projectTask);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/hours/project/{id}")
    public ResponseEntity<String> horas(@PathVariable("id") String id) {
        return projectTaskService.horas(id);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/project/{id}")
    public ResponseEntity<List<ProjectTask>> tareas(@PathVariable("id") String id) {
        return projectTaskService.tareas(id);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/hours/project/{id}/{status}")
    public ResponseEntity<String> horasStatus(@PathVariable("id") String id, @PathVariable("status") String status) {
        return projectTaskService.horasStatus(id, status);
    }

    @RequestMapping(method = RequestMethod.PATCH, value = "/{idtask}/{id}")
    public ResponseEntity<ProjectTask> delete2(@PathVariable("id") String id, @PathVariable("idtask") long idTask) {
        return projectTaskService.delete2(id, idTask);
    }
}
