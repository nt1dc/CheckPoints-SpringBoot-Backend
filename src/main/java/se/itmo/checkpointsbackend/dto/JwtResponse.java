package se.itmo.checkpointsbackend.dto;


import lombok.Data;

@Data
public class JwtResponse {
    String access_token;
    String refresh_token;

    public JwtResponse(String access_token, String refresh_token) {
        this.access_token = access_token;
        this.refresh_token = refresh_token;
    }
}
