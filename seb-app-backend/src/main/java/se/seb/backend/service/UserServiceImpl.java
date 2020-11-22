package se.seb.backend.service;

import org.springframework.stereotype.Service;
import se.seb.backend.domain.User;
import se.seb.backend.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User findByUsername(String username) {
        return userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User was not found with username: " + username));
    }
}
