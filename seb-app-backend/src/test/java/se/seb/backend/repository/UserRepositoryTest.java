package se.seb.backend.repository;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.junit4.SpringRunner;
import se.seb.backend.domain.User;

import java.util.EnumSet;

@SpringBootTest
@RunWith(SpringRunner.class)
@ComponentScan(basePackages = {"se.seb.backend.repository"})
public class UserRepositoryTest {


    @Autowired
    private UserRepository userRepository;

    @Test
    public void whenFindByUsername_thenReturnUser() {
        User test_user = new User(
                "test_user",
                "$2a$10$0zn118c9MCbWcoU.B1GxaOcU6.JPjuQL8/dobcDFWzKu7COL65/I2",
                EnumSet.of(User.Role.ROLE_USER));

        userRepository.save(test_user);

        try {
            User returnedUser = userRepository.findByUsername(test_user.getUsername()).get();
            assertThat(returnedUser.getUsername()).isEqualTo(test_user.getUsername());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void whenExistsByUsername_thenReturnBoolean() {
        User test_user = new User(
                "test_user",
                "$2a$10$0zn118c9MCbWcoU.B1GxaOcU6.JPjuQL8/dobcDFWzKu7COL65/I2",
                EnumSet.of(User.Role.ROLE_USER));

        userRepository.save(test_user);

        try {
            assertThat(userRepository.existsByUsername(test_user.getUsername())).isTrue();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void whenSave_thenReturnSavedUser() {
        User test_user = new User(
                "test_user",
                "$2a$10$0zn118c9MCbWcoU.B1GxaOcU6.JPjuQL8/dobcDFWzKu7COL65/I2",
                EnumSet.of(User.Role.ROLE_USER));

        try {
            User returnedUser = userRepository.save(test_user);
            assertThat(returnedUser.getUsername()).isEqualTo(test_user.getUsername());
            assertThat(returnedUser.getPassword()).isEqualTo(test_user.getPassword());
            assertThat(returnedUser.getRoles()).isEqualTo(test_user.getRoles());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
