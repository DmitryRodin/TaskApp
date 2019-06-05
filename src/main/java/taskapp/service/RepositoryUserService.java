package taskapp.service;

import org.apache.commons.lang.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import taskapp.dao.UserRepository;
import taskapp.model.User;
import taskapp.model.snapshots.UserCreationRequest;
import taskapp.model.snapshots.UserSnapshot;
import taskapp.model.snapshots.UserUpdateRequest;

@Service
public class RepositoryUserService implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TaskService taskService;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public UserSnapshot Save(UserCreationRequest request) {
        User user = User.builder()
                .password(bCryptPasswordEncoder.encode(request.getPassword()))
                .roles(request.getRoles())
                .username(request.getUsername())
                .build();
        return toSnapshot(userRepository.save(user));

    }

    @Override
    public UserSnapshot update(UserUpdateRequest request, Long userId) {
        User user = userRepository.getOne(userId);
        user.setRoles(request.getRoles() == null ? user.getRoles() : request.getRoles());
        user.setUsername(request.getUsername() == null ? user.getUsername() : request.getUsername());
        user.setPassword(request.getPassword() == null ? user.getPassword() : bCryptPasswordEncoder.encode(request.getPassword()));
        return toSnapshot(userRepository.save(user));
    }

    @Override
    public UserSnapshot findByUsername(String username) {
        return toSnapshot(userRepository.findFirstByUsername(username));
    }

    @Override
    public UserSnapshot getById(Long id) {
        return toSnapshot(userRepository.getOne(id));
    }

    @Override
    public void delete(Long userId) {
        userRepository.deleteById(userId);
    }

    @Transactional
    @Override
    public String resetPassword(Long userId) {
        String encode = bCryptPasswordEncoder.encode(RandomStringUtils.random(8, true, true));
        getById(userId).setPassword(encode);

        return encode;
    }

    private UserSnapshot toSnapshot(User user) {
        return UserSnapshot.builder()
                .password(user.getPassword())
                .roles(user.getRoles())
                .username(user.getUsername())
                .tasks(taskService.findByUserId(user.getId()))
                .build();
    }


}
