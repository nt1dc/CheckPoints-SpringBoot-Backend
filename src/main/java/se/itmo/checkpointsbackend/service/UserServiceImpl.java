package se.itmo.checkpointsbackend.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import se.itmo.checkpointsbackend.model.Entry;
import se.itmo.checkpointsbackend.model.Role;
import se.itmo.checkpointsbackend.model.User;
import se.itmo.checkpointsbackend.repository.RoleRepository;
import se.itmo.checkpointsbackend.repository.UserRepository;

import java.util.ArrayList;
import java.util.List;


@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;

    @Override
    public User saveUser(User user) {
        log.info("Saving new user {} to the db", user.getName());
        return userRepository.save(user);
    }

    @Override
    public Role saveRole(Role role) {
        log.info("Saving new role {} to the db", role.getName());
        return roleRepository.save(role);
    }

    @Override
    public void addRoleToUser(String username, String roleName) {
        log.info("Adding role: {} to user : {}", roleName, username);
        User user = userRepository.findByUsername(username);
        Role role = roleRepository.findByName(roleName);
        user.getRoles().add(role);
    }

    @Override
    public User getUser(String username) {
        log.info("Getting user with username: {}", username);
        return userRepository.findByUsername(username);
    }

    @Override
    public List<User> getUsers() {
        log.info("Getting all users from DB");
        return userRepository.findAll();
    }

    @Override
    public List<Entry> getUserEntries(String username) {
        log.info("Getting Entries of user with username {}",username);
        return userRepository.findByUsername(username).getEntries();
    }

    @Override
    public void deleteEntriesOfUser(String username) {
        log.info("Deleting entries of user with username: {} ", username);
        userRepository.findByUsername(username).setEntries(new ArrayList<>());

    }

}
