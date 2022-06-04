package co.com.poli.courses.entities;

import co.com.poli.courses.commons.EntityBase;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@Getter
@Setter
@Entity
@Table(name = "projects")
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Project extends EntityBase {

    @NotBlank(message = "El campo  projectName no puede ser NULL")
    @Column(name = "projectName", unique = true, nullable = false)
    private String projectName;
    @NotEmpty(message = "El campo projectIdentifier no puede ser NULL")
    @Column(name = "projectIdentifier", unique = true, nullable = false, updatable = false)
    @Size(min = 5, max = 7, message = "El campo projectIdentifier debe tener entre 5 y 7 caracteres")
    private String projectIdentifier;
    @NotEmpty(message = "El campo description no puede ser NULL")
    @NotBlank(message = "El campo description no puede ser NULL")
    @Column(name = "description", nullable = false)
    private String description;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private String startDate;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private String endDate;

    @JsonManagedReference
    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.PERSIST, mappedBy = "project")
    private Backlog backlog;

    @Override
    public boolean equals(Object o) {
        return super.equals(o);
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }
}
