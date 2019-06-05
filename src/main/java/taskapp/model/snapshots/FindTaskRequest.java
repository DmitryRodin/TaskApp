package taskapp.model.snapshots;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import taskapp.model.TaskPriority;
import taskapp.model.TaskStatus;

@Data
@AllArgsConstructor
@Builder
public class FindTaskRequest {

    private String query;

    private Long userId;

    private TaskStatus taskStatus;

    private TaskPriority taskPriority;
}
