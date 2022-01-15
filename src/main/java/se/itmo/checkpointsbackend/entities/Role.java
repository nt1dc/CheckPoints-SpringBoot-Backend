package se.itmo.checkpointsbackend.entities;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "roles")
@Data
@NoArgsConstructor
public class Role extends BaseEntity{
    @Column(name = "name")
    private  String name;

    public Role(String role_user) {
        this.name=role_user;
    }
}
