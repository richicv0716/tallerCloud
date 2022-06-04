package co.com.poli.courses.entities;

import co.com.poli.courses.commons.EntityBase;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import java.util.List;
import java.util.Objects;


@Getter
@Setter
@Entity
@Table(name = "backlogs")
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Backlog extends EntityBase {

    @Column(name = "projectIdentifier", nullable = false)
    private String projectIdentifier;

    @JsonBackReference
    @OneToOne(fetch = FetchType.EAGER)
    @NotEmpty(message = "El campo projectIdentifier no puede ser NULL")
    private Project project;

    @OneToMany(mappedBy = "backlog", fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private List<ProjectTask> projectTasks;

    @Override
    public boolean equals(Object o) {
        return super.equals(o);
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }
}
