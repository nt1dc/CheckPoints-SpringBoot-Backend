package se.itmo.checkpointsbackend.api;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import se.itmo.checkpointsbackend.dto.AuthReq;
import se.itmo.checkpointsbackend.dto.JwtResponse;
import se.itmo.checkpointsbackend.dto.RoleUserForm;
import se.itmo.checkpointsbackend.entities.Role;
import se.itmo.checkpointsbackend.entities.User;
import se.itmo.checkpointsbackend.exeptions.UserAlreadyExistException;
import se.itmo.checkpointsbackend.service.UserServiceImpl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.IOException;
import java.net.URI;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.http.HttpHeaders.AUTHORIZATION;
import static org.springframework.http.HttpStatus.FORBIDDEN;

@Slf4j
@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:3000")
public class UserController {
    private final UserServiceImpl userService;


    @PostMapping("/register")
    public ResponseEntity<?> register(@Valid @RequestBody AuthReq authReq, BindingResult bindResult, HttpServletResponse response) {
        if (authReq.getUsername() == null || authReq.getUsername().length() < 2 || authReq.getPassword() == null || authReq.getPassword().length() < 2) {
            log.error("Bad req");
            return ResponseEntity.badRequest().body("bad req");
        }
        try {
            userService.register(authReq);
            userService.addRoleToUser(authReq.getUsername(), "ROLE_USER");
        } catch (UserAlreadyExistException e) {
            response.addHeader("error", "already exist");
            return ResponseEntity.status(409).body(e.getMessage());
        } catch (Exception e) {
            log.error(e.getMessage());
        }
        return ResponseEntity.ok("registered");
    }


    @GetMapping("/allUsers")
    public ResponseEntity<List<User>> getUsers() {
        return ResponseEntity.ok().body(userService.getUsers());
    }


    @PostMapping("/user/save")
    public ResponseEntity<User> saveUser(@RequestBody User user) {
        return ResponseEntity.ok().body(userService.save(user));
    }

    @PostMapping("/role/save")
    public ResponseEntity<Role> saveRole(@RequestBody Role role) {
        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("api/role/save").toUriString());
        return ResponseEntity.created(uri).body(userService.saveRole(role));
    }


    @PostMapping("/role/addtouser")
    public ResponseEntity<?> addRoleToUser(@RequestBody RoleUserForm role) {
        userService.addRoleToUser(role.getUsername(), role.getRoleName());
        return ResponseEntity.ok().build();
    }


    @GetMapping("/token/refresh")
    public ResponseEntity<?> refreshToken(HttpServletRequest request, HttpServletResponse response) {
        log.info("refresh");
        String authorizationHeader = request.getHeader(AUTHORIZATION);
        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
            try {
                String refresh_token = authorizationHeader.substring("Bearer ".length());
                Algorithm algorithm = Algorithm.HMAC256("secret".getBytes(StandardCharsets.UTF_8));
                JWTVerifier verifier = JWT.require(algorithm).build();
                DecodedJWT decodedJWT = verifier.verify(refresh_token);
                String username = decodedJWT.getSubject();
                User user = userService.getUser(username);
                String access_token = JWT.create()
                        .withSubject(user.getUsername())
                        .withExpiresAt(new Date(System.currentTimeMillis() + 10 * 60 * 1000))
                        .withIssuer(request.getRequestURI())
                        .withClaim("roles", user.getRoles().stream().map(Role::getName).collect(Collectors.toList()))
                        .sign(algorithm);
                return ResponseEntity.ok().body(new JwtResponse(access_token, refresh_token));
            } catch (Exception e) {
                log.error("ERROR OF REFRESH IN: {} ", e.getMessage());
                response.setHeader("error", e.getMessage());
                try {
                    response.sendError(FORBIDDEN.value());
                } catch (IOException ex) {
                    log.error(ex.getMessage());
                }
                response.setStatus(FORBIDDEN.value());
                return ResponseEntity.badRequest().body(e.getMessage());
            }
        } else {
            log.error("refresh token is missing");
            throw new RuntimeException("refresh token is missing");
        }
    }
}
