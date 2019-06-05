package taskapp.model.snapshots;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import taskapp.model.Role;
import taskapp.model.Task;

import java.util.Set;

@AllArgsConstructor
@Builder
@Data
public class UserSnapshot {

    private String username;

    private String password;

    private String confirmPassword;

    private Role roles;

    private Set<Task> tasks;

}
