package co.com.poli.courses.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class ProjectDto implements Serializable {
    private final Long id;
    private final String projectName;
    private final String projectIdentifier;
    private final String description;
    private final String startDate;
    private final String endDate;
    private final Long backlogId;
    private final String backlogProjectIdentifier;
}
