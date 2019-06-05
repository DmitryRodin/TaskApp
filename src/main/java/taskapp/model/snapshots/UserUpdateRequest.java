package taskapp.model.snapshots;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import taskapp.model.Role;

@AllArgsConstructor
@Data
@Builder
public class UserUpdateRequest {

    private String username;

    private String password;

    private String confirmPassword;

    private Role roles;

}
