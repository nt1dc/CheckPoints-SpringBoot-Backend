package se.itmo.checkpointsbackend.service;

import se.itmo.checkpointsbackend.POJO.req.AuthReq;
import se.itmo.checkpointsbackend.entities.Role;
import se.itmo.checkpointsbackend.entities.User;

public interface UserService {

    User register(AuthReq authReq);

    void login(AuthReq authReq);

    Role saveRole(Role role);

    void addRoleToUser(String username, String roleName);


    void deleteUserWithName(String username);
}
