package se.seb.backend.repository;

import org.springframework.stereotype.Repository;
import se.seb.backend.domain.User;

import java.util.ArrayList;
import java.util.EnumSet;
import java.util.Optional;

@Repository
public class UserRepository {

    private ArrayList<User> users = new ArrayList<>();

    public UserRepository() {

        // Populating user list with hardcoded default values;
        // Password is just an encoded value of "password"
        User customer = new User("customer", "$2a$10$0zn118c9MCbWcoU.B1GxaOcU6.JPjuQL8/dobcDFWzKu7COL65/I2", EnumSet.of(User.Role.USER));
        User product_manager = new User("product_manager", "$2a$10$0zn118c9MCbWcoU.B1GxaOcU6.JPjuQL8/dobcDFWzKu7COL65/I2", EnumSet.of(User.Role.USER, User.Role.PRODUCT_MANAGER));

        users.add(customer);
        users.add(product_manager);
    }

    public Optional<User> findByUsername(String username) {
        return users.stream().filter(u -> u.getUsername().equalsIgnoreCase(username)).findFirst();
    }
}
