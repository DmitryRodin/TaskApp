package taskapp.model.snapshots;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import taskapp.model.TaskPriority;
import taskapp.model.TaskStatus;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@Builder
public class CreationTaskRequest {

    @NotBlank
    private String title;

    @NotBlank
    private String description;

    @NotNull
    @Enumerated(EnumType.STRING)
    private TaskPriority taskPriority;

    @NotNull
    @Enumerated(EnumType.STRING)
    private TaskStatus taskStatus;

    private Long userId;

}
