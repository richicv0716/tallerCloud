package co.com.poli.courses.services;

import co.com.poli.courses.entities.Backlog;
import co.com.poli.courses.entities.Project;
import co.com.poli.courses.entities.ProjectTask;
import co.com.poli.courses.repository.BacklogRepository;

import co.com.poli.courses.repository.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
//@RequiredArgsConstructor
public class BacklogServiceImpl implements BacklogService {


    @Autowired
    private BacklogRepository backlogRepository;
    private ProjectService projectService;

    @Override
    public List<Backlog> findAll() {
        return backlogRepository.findAll();
    }

    @Override
    public Optional<Backlog> findByID(Long id) {
        return backlogRepository.findById(id);
    }


    @Override
    public ResponseEntity<Backlog> save(Backlog backlog) {
        Map<String, String> errorResponse = new HashMap<>();
        for (Backlog p : findAll()) {
            if (backlog.getProjectIdentifier().equals(p.getProjectIdentifier())) {
                errorResponse.put("message", "Ya existe un backlog asociado al proyecto con ProjectIdentifier: " + p.getProjectIdentifier());
                errorResponse.put("status", HttpStatus.BAD_REQUEST.toString());
                return new ResponseEntity(errorResponse, HttpStatus.BAD_REQUEST);
            }

            if (backlog.getProjectIdentifier().equals("")) {
                errorResponse.put("message", "El ProjectIdentifier no puede estar vacio");
                errorResponse.put("status", HttpStatus.BAD_REQUEST.toString());
                return new ResponseEntity(errorResponse, HttpStatus.BAD_REQUEST);
            }

        }

        if (!backlog.getProjectIdentifier().equals(Long.toString(backlog.getProject().getId()))) {
            HttpHeaders headers = new HttpHeaders();
            errorResponse.put("message", "El ProjectIdentifier debe ser el mismo que tiene asociado el proyecto");
            errorResponse.put("status", HttpStatus.BAD_REQUEST.toString());
            return new ResponseEntity(errorResponse, HttpStatus.BAD_REQUEST);
        }


        backlogRepository.save(backlog);

        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/json; charset=UTF-8");
        headers.add("uri", "/backlog");
        ResponseEntity response = new ResponseEntity<Backlog>(backlog, headers, HttpStatus.CREATED);
        return response;
    }


}
