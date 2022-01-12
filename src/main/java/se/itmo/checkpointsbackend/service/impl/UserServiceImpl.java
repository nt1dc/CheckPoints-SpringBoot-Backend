package se.itmo.checkpointsbackend.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import se.itmo.checkpointsbackend.model.Role;
import se.itmo.checkpointsbackend.model.Status;
import se.itmo.checkpointsbackend.model.User;
import se.itmo.checkpointsbackend.repository.RoleRepository;
import se.itmo.checkpointsbackend.repository.UserRepository;
import se.itmo.checkpointsbackend.service.UserService;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class UserServiceImpl implements UserService {


    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    public UserServiceImpl(UserRepository userRepository, RoleRepository roleRepository, BCryptPasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public User register(User user) {
        Role roleUser = roleRepository.findByName("ROLE_USER");
        List<Role> userRoles =new ArrayList<>();
        userRoles.add(roleUser);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRoles(userRoles);
        user.setStatus(Status.ACTIVE);
        User registeredUser= userRepository.save(user);
        log.info("IN register - user: {} successfully registered  ",registeredUser);
        return registeredUser;
    }

    @Override
    public List<User> getAll() {
        List<User> result = userRepository.findAll();
        log.info("IN get ALL - {} Users found",result.size());
        return result;
    }

    @Override
    public User findByUserName(String name) {
        return null;
    }

    @Override
    public User findByID(Long id) {
        return null;
    }

    @Override
    public void delete(Long id) {

    }
}
