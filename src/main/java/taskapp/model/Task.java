package taskapp.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import taskapp.model.snapshots.TaskSnapshot;

import javax.persistence.*;

import static javax.persistence.EnumType.*;

@Entity
@Data
@AllArgsConstructor
@Builder
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String title;

    private String description;

    @Enumerated(STRING)
    private TaskPriority taskPriority;

    @Enumerated(STRING)
    private TaskStatus taskStatus;

    private Long userId;

    public TaskSnapshot toTaskSnapshot (Task task){
        return TaskSnapshot.builder()
                .description(task.description)
                .taskPriority(task.taskPriority)
                .taskStatus(task.taskStatus)
                .title(task.title)
                .build();
    }
}
