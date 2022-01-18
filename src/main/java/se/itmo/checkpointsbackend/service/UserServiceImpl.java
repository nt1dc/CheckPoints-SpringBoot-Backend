package se.itmo.checkpointsbackend.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import se.itmo.checkpointsbackend.dto.AuthReq;
import se.itmo.checkpointsbackend.dto.EntryReqDto;
import se.itmo.checkpointsbackend.entities.Entry;
import se.itmo.checkpointsbackend.entities.Role;
import se.itmo.checkpointsbackend.entities.User;
import se.itmo.checkpointsbackend.exeprions.NotIncludedInTheRangeException;
import se.itmo.checkpointsbackend.exeprions.UserAlreadyExistException;
import se.itmo.checkpointsbackend.model.AreaChecker;
import se.itmo.checkpointsbackend.repository.EntryRepository;
import se.itmo.checkpointsbackend.repository.RoleRepository;
import se.itmo.checkpointsbackend.repository.UserRepository;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;


@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class UserServiceImpl implements UserService, UserDetailsService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final AreaChecker areaChecker;
    private final EntryRepository entryRepository;
    private final PasswordEncoder passwordEncoder;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username);
        if (user == null) {
            log.error("user with name {} not found in DB", username);
            throw new UsernameNotFoundException("user  not found in DB");
        } else {
            log.info("User {} found in DB", username);
        }
        Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
        user.getRoles().forEach(role -> {
            authorities.add(new SimpleGrantedAuthority(role.getName()));
        });
        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), authorities);
    }


    @Override
    public User save(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }

    @Override
    public User register(AuthReq newUser) throws UserAlreadyExistException {
        if (userRepository.findByUsername(newUser.getUsername())!=null){
            log.error("User {} already exist",newUser.getUsername());
            throw new UserAlreadyExistException("User already exist");
        }
        newUser.setPassword(passwordEncoder.encode(newUser.getPassword()));
        User user = new User(newUser.getUsername(), newUser.getPassword());
        log.info("Saving new user {} to the db", user.getUsername());
        return userRepository.save(user);
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

    @Override
    public Entry addEntryToUser(String username, EntryReqDto entryReqDto) throws NotIncludedInTheRangeException {
        User user = userRepository.findByUsername(username);
        Entry entry = areaChecker.checkEntry(entryReqDto);
        entryRepository.save(entry);
        user.getEntries().add(entry);
        return entry;
    }

}
