package se.itmo.checkpointsbackend.service;

import se.itmo.checkpointsbackend.dto.AuthReq;
import se.itmo.checkpointsbackend.dto.EntryReqDto;
import se.itmo.checkpointsbackend.entities.Role;
import se.itmo.checkpointsbackend.entities.User;
import se.itmo.checkpointsbackend.exeprions.NotIncludedInTheRangeException;

import java.util.List;

public interface UserService {

    User save(User user);

    User register(AuthReq authReq);

    void login(AuthReq authReq);

    Role saveRole(Role role);

    void addRoleToUser(String username, String roleName);

    void deleteUserWithName(String username);

    User getUser(String username);

    List<User> getUsers();

    void addEntryToUser(String username, EntryReqDto entryReqDto) throws NotIncludedInTheRangeException;

}
