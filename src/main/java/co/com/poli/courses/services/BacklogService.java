package co.com.poli.courses.services;

import co.com.poli.courses.entities.Backlog;
import co.com.poli.courses.entities.Project;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface BacklogService {

    List<Backlog> findAll();
    Optional<Backlog> findByID(Long id);
    ResponseEntity<Backlog> save(Backlog backlog);

}
