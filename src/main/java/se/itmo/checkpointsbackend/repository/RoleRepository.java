package se.itmo.checkpointsbackend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import se.itmo.checkpointsbackend.entities.Role;

public interface RoleRepository extends JpaRepository<Role,Long> {
    Role findByName(String name);
}
