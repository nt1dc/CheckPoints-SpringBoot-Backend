package se.itmo.checkpointsbackend.service;


import lombok.Data;
import org.springframework.stereotype.Service;
import se.itmo.checkpointsbackend.model.AreaChecker;
import se.itmo.checkpointsbackend.POJO.req.CheckEntryReq;
import se.itmo.checkpointsbackend.entities.Entry;
import se.itmo.checkpointsbackend.repository.RoleRepository;
import se.itmo.checkpointsbackend.repository.UserRepository;

import java.util.ArrayList;
import java.util.List;

@Data
@Service
public class EntriesService {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final AreaChecker areaChecker;

    public EntriesService(UserRepository userRepository, RoleRepository roleRepository, AreaChecker areaChecker) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.areaChecker = areaChecker;
    }

    public void clear(String username) {
        userRepository.findByUsername(username).setEntries(new ArrayList<>());
    }

    public void add(String username, CheckEntryReq entryReq) {
//        userRepository.findByUsername(username).getEntries().add(areaChecker.checkEntry(entryReq));
    }

     public  List<Entry> getEntries(String username) {
        return userRepository.findByUsername(username).getEntries();
    }

}
