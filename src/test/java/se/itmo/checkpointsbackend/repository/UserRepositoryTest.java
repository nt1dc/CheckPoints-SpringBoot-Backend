package se.itmo.checkpointsbackend.repository;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import se.itmo.checkpointsbackend.entities.User;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@DataJpaTest
class UserRepositoryTest {
    @Autowired
    UserRepository underTest;

    @AfterEach
    void tearDown(){
        underTest.deleteAll();
    }
    @Test
    void findByUsername() {
        String username = "username";
        User expect = new User(username, "password");
        underTest.save(expect);
        User res = underTest.findByUsername(username);
        assertThat(expect).isEqualTo(res);
    }
}