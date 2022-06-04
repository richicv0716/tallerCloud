package co.com.poli.courses.repository;


import co.com.poli.courses.entities.Project;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProjectRepository  extends JpaRepository<Project,Long> {
    @EntityGraph(attributePaths = { "backlog" })
    List<Project> findAll();
    Project save(Project project);
    Project findById(Project project);

}
