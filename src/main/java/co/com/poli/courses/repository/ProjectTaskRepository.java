package co.com.poli.courses.repository;


import co.com.poli.courses.entities.ProjectTask;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProjectTaskRepository extends JpaRepository<ProjectTask,Long> {

    ProjectTask save(ProjectTask projectTask);
    void deleteById(Long id);
    List<ProjectTask> findAll();
}
