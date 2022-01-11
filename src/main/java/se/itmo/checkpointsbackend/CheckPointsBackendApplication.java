package se.itmo.checkpointsbackend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.persistence.EntityManagerFactory;
import javax.swing.text.html.parser.Entity;

@SpringBootApplication
public class CheckPointsBackendApplication {

    public static void main(String[] args) {
        SpringApplication.run(CheckPointsBackendApplication.class, args);
    }

}
