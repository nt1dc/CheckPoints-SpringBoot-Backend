package se.itmo.checkpointsbackend.repository;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import se.itmo.checkpointsbackend.entities.Role;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@DataJpaTest
class RoleRepositoryTest {


    @Autowired
    private RoleRepository underTest;

    @AfterEach
    void tearDown() {
        underTest.deleteAll();
    }

    @Test
    void findByName() {
        Role role = new Role("ROLE_ADMIN");
        underTest.save(role);
        Role res = underTest.findByName("ROLE_ADMIN");
        assertThat(role).isEqualTo(res);
    }
}