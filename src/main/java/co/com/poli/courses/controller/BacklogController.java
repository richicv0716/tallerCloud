package co.com.poli.courses.controller;


import co.com.poli.courses.entities.Backlog;
import co.com.poli.courses.entities.Project;
import co.com.poli.courses.services.BacklogService;
import co.com.poli.courses.services.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/backlog")

//@RequiredArgsConstructor
public class BacklogController {

    @Autowired
    private BacklogService backlogService;

    @GetMapping
    List<Backlog> findAll() {
        return backlogService.findAll();
    }

    @RequestMapping(method = RequestMethod.POST, value = "")
    public ResponseEntity<Backlog> addBacklog(@Valid @RequestBody Backlog backlog) {
        return backlogService.save(backlog);
    }
}
