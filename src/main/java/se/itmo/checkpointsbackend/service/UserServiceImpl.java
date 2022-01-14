package se.itmo.checkpointsbackend.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import se.itmo.checkpointsbackend.POJO.req.AuthReq;
import se.itmo.checkpointsbackend.entities.Role;
import se.itmo.checkpointsbackend.entities.User;
import se.itmo.checkpointsbackend.repository.RoleRepository;
import se.itmo.checkpointsbackend.repository.UserRepository;


@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;

    @Override
    public User register(AuthReq data) {
        User user = new User(data.getName(), data.getUsername(), data.getPassword());
        log.info("Saving new user {} to the db", user.getName());
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
    public void addRoleToUser(String username, String roleName) {
        log.info("Adding role: {} to user : {}", roleName, username);
        User user = userRepository.findByUsername(username);
        Role role = roleRepository.findByName(roleName);
        user.getRoles().add(role);
    }

    @Override
    public void deleteUserWithName(String username) {
        userRepository.deleteById(userRepository.findByUsername(username).getId());
    }


}
