package se.seb.backend.repository;

import org.springframework.stereotype.Repository;
import se.seb.backend.domain.User;

import java.util.ArrayList;
import java.util.Optional;

@Repository
public class UserRepository {

    private ArrayList<User> users = new ArrayList<>();

    public UserRepository() {

        // Populating user list with hardcoded default values;
        User customer = new User("customer", "password", User.Role.USER);
        User product_manager = new User("product_manager", "password", User.Role.PRODUCT_MANAGER);

        users.add(customer);
        users.add(product_manager);
    }

    public Optional<User> findByUsername(String username) {
        return users.stream().filter(u -> u.getUsername().equalsIgnoreCase(username)).findFirst();
    }
}
