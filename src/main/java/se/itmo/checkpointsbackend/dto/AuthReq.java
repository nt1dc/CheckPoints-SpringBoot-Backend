package se.itmo.checkpointsbackend.dto;

import lombok.Data;
@Data
public class AuthReq {
    private String username;
    private String password;
}
