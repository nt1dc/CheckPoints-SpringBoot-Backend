package se.itmo.checkpointsbackend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import se.itmo.checkpointsbackend.model.Role;

public interface RoleRepository extends JpaRepository<Role,Long> {
    Role findByName(String name);
}
