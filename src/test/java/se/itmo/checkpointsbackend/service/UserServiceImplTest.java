package se.itmo.checkpointsbackend.service;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import se.itmo.checkpointsbackend.entities.User;
import se.itmo.checkpointsbackend.model.AreaChecker;
import se.itmo.checkpointsbackend.repository.EntryRepository;
import se.itmo.checkpointsbackend.repository.RoleRepository;
import se.itmo.checkpointsbackend.repository.UserRepository;

import static org.mockito.Mockito.verify;


@ExtendWith(MockitoExtension.class)
class UserServiceImplTest {
    @Autowired
    UserService underTest;
    BCryptPasswordEncoder bCryptPasswordEncoder;
    AreaChecker areaChecker = new AreaChecker();
    EntryRepository entryRepository;
    @Mock
    UserRepository userRepository;
    RoleRepository roleRepository;
    AutoCloseable autoCloseable;

    @BeforeEach
    void setUp() {
        autoCloseable = MockitoAnnotations.openMocks(this);
        underTest = new UserServiceImpl(userRepository, roleRepository, areaChecker, entryRepository, bCryptPasswordEncoder);
    }

    @AfterEach
    void tearDown() throws Exception {
        autoCloseable.close();
    }

    @Test
    void getAllUsers() {
        underTest.getAllUsers();
        verify(userRepository).findAll();
    }

    @Test
    void save() {

    }

    @Test
    void getUser() {
        String usernameTest ="username";
        underTest.getUser(usernameTest);
        verify(userRepository).findByUsername(usernameTest);
    }

    @Test
    void deleteUserWithName() {
        String usernameTest ="username";
        underTest.deleteUserWithName(usernameTest);
        verify(userRepository).deleteUserByUsername(usernameTest);
    }
}