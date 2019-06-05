package taskapp.model.snapshots;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import taskapp.model.TaskPriority;
import taskapp.model.TaskStatus;

@Data
@AllArgsConstructor
@Builder
public class TaskSnapshot {

    private String title;

    private String description;

    private TaskPriority taskPriority;

    private TaskStatus taskStatus;
}
