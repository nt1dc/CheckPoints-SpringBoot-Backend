package se.itmo.checkpointsbackend.api;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import se.itmo.checkpointsbackend.model.Entry;
import se.itmo.checkpointsbackend.model.Role;
import se.itmo.checkpointsbackend.model.User;
import se.itmo.checkpointsbackend.service.UserService;

import java.security.Principal;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @GetMapping("/users")
    public ResponseEntity<List<User>> getUsers() {
        return ResponseEntity.ok().body(userService.getUsers());
    }

    @PostMapping("/user/save")
    public ResponseEntity<User> saveUser(@RequestBody User user) {
        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("api/user/save").toUriString());
        return ResponseEntity.created(uri).body(userService.saveUser(user));
    }

    @PostMapping("/role/save")
    public ResponseEntity<Role> saveRole(@RequestBody Role role) {
        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("api/role/save").toUriString());
        return ResponseEntity.created(uri).body(userService.saveRole(role));
    }

    @PostMapping("/role/save")
    public ResponseEntity<?> addRole(@RequestBody RoleToUserForm r) {
        userService.addRoleToUser(r.getUsername(), r.getRoleName());
        return ResponseEntity.ok().build();
    }


    @GetMapping("/myEntries")
    public ResponseEntity<List<Entry>> getUserEntries(@RequestBody User user) {
        return ResponseEntity.ok().body(userService.getUserEntries(user.getUsername()));
    }

    @PostMapping("/clear")
    public void deleteEntriesOfUser(Principal principal) {
        userService.deleteEntriesOfUser(principal.getName());
        ResponseEntity.ok().body("");
    }
}

@Data
class RoleToUserForm {
    private String username;
    private String roleName;
}