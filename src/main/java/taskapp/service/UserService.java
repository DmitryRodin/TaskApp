package taskapp.service;


import taskapp.model.snapshots.UserCreationRequest;
import taskapp.model.snapshots.UserSnapshot;
import taskapp.model.snapshots.UserUpdateRequest;


public interface UserService {
    UserSnapshot Save(UserCreationRequest request);

    UserSnapshot update(UserUpdateRequest request, Long userId);

    UserSnapshot findByUsername(String username);

    UserSnapshot getById(Long id);

    void delete(Long userId);

    String resetPassword(Long userId);
}
