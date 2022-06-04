package co.com.poli.courses;


import co.com.poli.courses.entities.Backlog;
import co.com.poli.courses.entities.Project;
import co.com.poli.courses.entities.ProjectTask;
import co.com.poli.courses.repository.BacklogRepository;
import co.com.poli.courses.repository.ProjectRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

@DataJpaTest
public class ProjectRepositorySmokeTest {

    @Autowired
    private ProjectRepository projectRepository;

    @Test
    public void when_findAll_return_ListProject() {

        Project project = Project.builder()
                .projectName("Quadi")
                .projectIdentifier("1")
                .description("Test")
                .startDate("2022-06-04")
                .endDate("2022-06-04")
                .backlog(null)
                .build();
        projectRepository.save(project);
        List<Project> projects = projectRepository.findAll();
        Assertions.assertThat(projects.size()).isEqualTo(1);

    }

}