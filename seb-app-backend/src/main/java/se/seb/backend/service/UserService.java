package se.seb.backend.service;

import org.springframework.stereotype.Service;
import se.seb.backend.domain.User;

@Service
public interface UserService {

    User findByUsername(String username);
}
