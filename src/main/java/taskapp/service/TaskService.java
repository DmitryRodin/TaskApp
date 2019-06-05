package taskapp.service;

import org.springframework.transaction.annotation.Transactional;
import taskapp.model.snapshots.CreationTaskRequest;
import taskapp.model.Task;
import taskapp.model.snapshots.EditTaskRequest;
import taskapp.model.snapshots.FindTaskRequest;

import java.util.List;
import java.util.Set;

public interface TaskService {

    List<Task> find(FindTaskRequest request);

    Set<Task> findByUserId(Long userId);

    Long create (CreationTaskRequest request);

    void assignTask(Long taskId, Long userId);

    @Transactional
    void edit(EditTaskRequest request);

    void delete(Long taskId);
}
