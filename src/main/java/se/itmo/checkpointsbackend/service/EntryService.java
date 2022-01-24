package se.itmo.checkpointsbackend.service;


import lombok.Data;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import se.itmo.checkpointsbackend.model.AreaChecker;
import se.itmo.checkpointsbackend.entities.Entry;
import se.itmo.checkpointsbackend.repository.EntryRepository;
import se.itmo.checkpointsbackend.repository.RoleRepository;
import se.itmo.checkpointsbackend.repository.UserRepository;

import java.util.ArrayList;
import java.util.List;

@Data
@Service
@Transactional
public class EntryService  {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final EntryRepository entryRepository;
    private final AreaChecker areaChecker;


    public EntryService(UserRepository userRepository, RoleRepository roleRepository, EntryRepository entryRepository, AreaChecker areaChecker1) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.entryRepository = entryRepository;
        this.areaChecker = areaChecker1;
    }

    public void clear(String username) {
        userRepository.findByUsername(username).setEntries(new ArrayList<>());
        entryRepository.deleteAll(userRepository.findByUsername(username).getEntries());
    }

    public List<Entry> getEntries() {
        return entryRepository.findAll();
    }
}
