package se.itmo.checkpointsbackend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import se.itmo.checkpointsbackend.entities.Entry;
@Repository
public interface EntryRepository extends JpaRepository<Entry,Long> {

}
