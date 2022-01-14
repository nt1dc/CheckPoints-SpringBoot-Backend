package se.itmo.checkpointsbackend.service;

import se.itmo.checkpointsbackend.model.Entry;
import se.itmo.checkpointsbackend.model.Role;
import se.itmo.checkpointsbackend.model.User;

import java.util.List;

public interface UserService {
    User saveUser(User user);

    Role saveRole(Role role);

    void addRoleToUser(String username, String roleName);

    User getUser(String username);

    List<User> getUsers();

    List<Entry> getUserEntries(String username);

    void deleteEntriesOfUser(String username);
}
