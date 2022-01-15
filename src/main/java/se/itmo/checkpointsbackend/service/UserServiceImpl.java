package se.itmo.checkpointsbackend.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import se.itmo.checkpointsbackend.POJO.req.AuthReq;
import se.itmo.checkpointsbackend.dto.EntryReqDto;
import se.itmo.checkpointsbackend.entities.Entry;
import se.itmo.checkpointsbackend.entities.Role;
import se.itmo.checkpointsbackend.entities.User;
import se.itmo.checkpointsbackend.exeprions.NotIncludedInTheRangeException;
import se.itmo.checkpointsbackend.model.AreaChecker;
import se.itmo.checkpointsbackend.repository.EntryRepository;
import se.itmo.checkpointsbackend.repository.RoleRepository;
import se.itmo.checkpointsbackend.repository.UserRepository;

import java.util.List;


@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final AreaChecker areaChecker;
    private final EntryRepository entryRepository;

    @Override
    public User save(User user) {
        return userRepository.save(user);
    }

    @Override
    public User register(AuthReq data) {
        User user = new User(data.getUsername(), data.getPassword());
        log.info("Saving new user {} to the db", user.getUsername());
        return userRepository.save(user);
    }

    @Override
    public void login(AuthReq authReq) {
    }

    @Override
    public Role saveRole(Role role) {
        log.info("Saving new role {} to the db", role.getName());
        return roleRepository.save(role);
    }


    @Override
    public void deleteUserWithName(String username) {
        userRepository.deleteById(userRepository.findByUsername(username).getId());
    }

    @Override
    public User getUser(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    public List<User> getUsers() {
        return userRepository.findAll();
    }

    @Override
    public void addRoleToUser(String username, String roleName) {
        log.info("Adding role: {} to user : {}", roleName, username);
        User user = userRepository.findByUsername(username);
        Role role = roleRepository.findByName(roleName);
        user.getRoles().add(role);
    }

    public void addEntryToUser(String username, EntryReqDto entryReqDto) throws NotIncludedInTheRangeException {
        User user = userRepository.findByUsername(username);
        Entry entry = areaChecker.checkEntry(entryReqDto);
        entryRepository.save(entry);
        user.getEntries().add(entry);
        userRepository.save(user);
        log.info(user.toString());
    }


}
