package se.itmo.checkpointsbackend.api;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import se.itmo.checkpointsbackend.POJO.req.AuthReq;
import se.itmo.checkpointsbackend.entities.Role;
import se.itmo.checkpointsbackend.entities.User;
import se.itmo.checkpointsbackend.service.UserService;

import java.security.Principal;

import java.net.URI;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;


    @PostMapping("/register")
    public ResponseEntity<User> saveUser(@RequestBody AuthReq user) {
        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("api/user/save").toUriString());
        return ResponseEntity.created(uri).body(userService.register(user));
    }



    @GetMapping("zxc")
    public ResponseEntity<String> zxc(){
        return ResponseEntity.ok().body("zxc");
    }



    @PostMapping("/login")
    public ResponseEntity<?> login() {
        return null;
    }


    @PostMapping("/role/save")
    public ResponseEntity<Role> saveRole(@RequestBody Role role) {
        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("api/role/save").toUriString());
        return ResponseEntity.created(uri).body(userService.saveRole(role));
    }


    @PostMapping("/deleteMe")
    public void delete(Principal principal) {
        userService.deleteUserWithName(principal.getName());
    }
}
