package co.com.poli.courses.entities;

import co.com.poli.courses.commons.EntityBase;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.util.Date;
import java.util.Objects;

@Getter
@Setter
@Entity
@Table(name = "projectTasks")
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProjectTask extends EntityBase {

    @NotEmpty(message = "El campo name no puede ser NULL")
    @Column(name = "name", nullable = false)
    private String name;
    @NotEmpty(message = "El campo summary no puede ser NULL")
    @Column(name = "summary", nullable = false)
    private String summary;
    @Column(name = "acceptanceCriteria")
    private String acceptanceCriteria;
    @Pattern(regexp = "^(Not Stared|in progress|completed|deleted)$", message = "invalid code")
    @Column(name = "status")
    private String status;
    @Min(1)
    @Max(5)
    @Column(name = "priority")
    private int priority;
    @Min(1)
    @Max(8)
    @Positive(message = "La cantidad debe ser mayor que 0")
    @Column(name = "hours")
    private double hours;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private String startDate;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private String endDate;
    @NotEmpty(message = "No se puede actualizar")
    @Column(name = "projectIdentifier", updatable = false)
    private String projectIdentifier;


    @JsonBackReference
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "backlog_id")
    @NotEmpty(message = "El campo Backlog no puede ser NULL")
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
