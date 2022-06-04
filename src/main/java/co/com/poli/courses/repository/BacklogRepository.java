package co.com.poli.courses.repository;
import co.com.poli.courses.entities.Backlog;
import co.com.poli.courses.entities.Project;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface BacklogRepository  extends JpaRepository<Backlog,Long> {

    List<Backlog> findAll();
    Backlog save(Backlog backlog);
    Backlog findById(Backlog backlog);
}
