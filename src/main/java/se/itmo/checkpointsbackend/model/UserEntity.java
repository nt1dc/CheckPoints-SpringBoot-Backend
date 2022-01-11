package se.itmo.checkpointsbackend.model;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "users")
@Data
public class UserEntity extends BaseEntity {
    @Column(name = "login")
    private String login;
    @Column(name = "password")
    private String password;


    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "user_roles",joinColumns = {@JoinColumn(name = "user_id",referencedColumnName = "id")},inverseJoinColumns = {@JoinColumn(name = "role_id",referencedColumnName = "id")})
    private List<Role> roles;

    @ManyToMany
    private List<EntryEntity> entries;

}
