package se.itmo.checkpointsbackend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import se.itmo.checkpointsbackend.entities.Entry;
import se.itmo.checkpointsbackend.entities.User;

import java.util.List;

public interface EntryRepository extends JpaRepository<Entry,Long> {

}
