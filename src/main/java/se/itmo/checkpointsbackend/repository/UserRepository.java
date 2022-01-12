package se.itmo.checkpointsbackend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import se.itmo.checkpointsbackend.model.User;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByUserName(String name);
}
