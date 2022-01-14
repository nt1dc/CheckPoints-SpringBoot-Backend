package se.itmo.checkpointsbackend.POJO.req;

import lombok.Data;

import java.util.List;

@Data
public class AuthReq {
    private String name;
    private String username;
    private String password;
    private List<String> roles;
}
