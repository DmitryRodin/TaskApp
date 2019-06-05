package taskapp.model.snapshots;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class EditTaskRequest {

    private Long taskId;

    private String title;

    private String description;

}
