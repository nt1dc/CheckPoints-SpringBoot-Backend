package se.itmo.checkpointsbackend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import se.itmo.checkpointsbackend.entities.Entry;

public interface EntryRepository extends JpaRepository<Entry,Long> {

}
