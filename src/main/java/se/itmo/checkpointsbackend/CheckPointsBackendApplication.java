package se.itmo.checkpointsbackend;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import se.itmo.checkpointsbackend.dto.EntryReqDto;
import se.itmo.checkpointsbackend.entities.Role;
import se.itmo.checkpointsbackend.entities.User;
import se.itmo.checkpointsbackend.service.EntryService;
import se.itmo.checkpointsbackend.service.UserServiceImpl;

@SpringBootApplication
public class CheckPointsBackendApplication {
    public static void main(String[] args) {
        SpringApplication.run(CheckPointsBackendApplication.class, args);
    }


    @Bean
    CommandLineRunner runner(UserServiceImpl userService, EntryService entriesService) {
        return args -> {
            userService.saveRole(new Role("ROLE_USER"));
            userService.saveRole(new Role("ROLE_ADMIN"));
            userService.save(new User("username", "password"));
            userService.addRoleToUser("username", "ROLE_USER");
            userService.addEntryToUser("username", new EntryReqDto(1, 2, 3));
        };
    }
}
