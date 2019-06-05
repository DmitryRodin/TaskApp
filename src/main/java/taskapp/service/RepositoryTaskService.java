package taskapp.service;

import lombok.AllArgsConstructor;
import lombok.Builder;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import taskapp.dao.TaskRepository;
import taskapp.model.*;
import taskapp.model.snapshots.CreationTaskRequest;
import taskapp.model.snapshots.EditTaskRequest;
import taskapp.model.snapshots.FindTaskRequest;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static java.util.Objects.nonNull;
import static org.springframework.data.jpa.domain.Specification.where;

@Service
@Builder
@AllArgsConstructor
public class RepositoryTaskService implements TaskService {

    private final TaskRepository taskRepository;

    private final UserService userService;

    @Override
    public List<Task> find(FindTaskRequest request) {

        return taskRepository.findAll(where(containsTaskPriority(request.getTaskPriority())
                .or(containsTaskStatus(request.getTaskStatus()))
                .or(titleContains(request.getQuery()))));
    }

    @Override
    public Set<Task> findByUserId(Long userId){
        return new HashSet<>(taskRepository.findAllByUserId(userId));
    }

    @Override
    public Long create(CreationTaskRequest request) {

        Task task = Task.builder()
                .description(request.getDescription())
                .taskPriority(request.getTaskPriority())
                .taskStatus(request.getTaskStatus())
                .title(request.getTitle())
                .userId(request.getUserId())
                .build();

         return taskRepository.save(task).getId();
    }

    @Transactional
    @Override
    public void assignTask(Long taskId, Long userId){
        taskRepository.getOne(taskId).setUserId(userId);
    }

    @Transactional
    @Override
    public void edit(EditTaskRequest request){
        Task task = taskRepository.getOne(request.getTaskId());

        task.setTitle(nonNull(request.getTitle()) ? request.getTitle() : task.getTitle());
        task.setDescription(nonNull(request.getDescription()) ? request.getDescription() : task.getDescription());
    }

    @Override
    public void delete(Long taskId){
        taskRepository.deleteById(taskId);
    }

    static Specification<Task> titleContains(String text) {
        return (taskRoot, cq, cb) -> cb.or(
                cb.like(taskRoot.get("title"), "%" + text + "%"),
                cb.like(taskRoot.get("description"), "%" + text + "%")
        );
    }

    static Specification<Task> containsTaskPriority(TaskPriority priority) {
        return (task, cq, cb) -> cb.equal(task.get("taskPriority"), priority);
    }

    static Specification<Task> containsTaskStatus(TaskStatus status) {
        return (task, cq, cb) -> cb.equal(task.get("taskStatus"), status);
    }


}
