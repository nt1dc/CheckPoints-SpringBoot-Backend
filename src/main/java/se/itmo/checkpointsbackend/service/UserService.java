package se.itmo.checkpointsbackend.service;

import se.itmo.checkpointsbackend.model.User;

import java.util.List;

public interface UserService {
    User register(User user);

    List<User> getAll();


    User findByUserName(String name);

    User findByID(Long id);

    void delete(Long id);
}
