package se.itmo.checkpointsbackend.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AuthReq {
    @Size(min = 2,max = 30)
    @NotNull
    @NotEmpty
    private String username;
    @Size(min = 2,max = 30)
    @NotNull
    @NotEmpty
    private String password;
}
