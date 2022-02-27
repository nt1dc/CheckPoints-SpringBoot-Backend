package se.itmo.checkpointsbackend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import se.itmo.checkpointsbackend.entities.User;
@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);
    void deleteUserByUsername(String username);
}
