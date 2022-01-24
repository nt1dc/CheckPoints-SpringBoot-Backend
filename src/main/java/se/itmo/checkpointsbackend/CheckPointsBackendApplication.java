package se.itmo.checkpointsbackend;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import se.itmo.checkpointsbackend.dto.EntryReqDto;
import se.itmo.checkpointsbackend.entities.Role;
import se.itmo.checkpointsbackend.entities.User;
import se.itmo.checkpointsbackend.service.UserServiceImpl;

@SpringBootApplication
public class CheckPointsBackendApplication {
    public static void main(String[] args) {
        SpringApplication.run(CheckPointsBackendApplication.class, args);
    }


    @Bean
    CommandLineRunner runner(UserServiceImpl userService) {
        return args -> {
            userService.saveRole(new Role("ROLE_USER"));
            userService.saveRole(new Role("ROLE_ADMIN"));
            userService.save(new User("username", "password"));
            userService.save(new User("test", "test"));
            userService.addRoleToUser("username", "ROLE_ADMIN");
            userService.addRoleToUser("test", "ROLE_ADMIN");
            userService.addEntryToUser("username", new EntryReqDto(1, 2, 3));
            userService.addEntryToUser("username", new EntryReqDto(1, 2, 3));
            userService.addEntryToUser("username", new EntryReqDto(1, 2, 3));
        };
    }

    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
