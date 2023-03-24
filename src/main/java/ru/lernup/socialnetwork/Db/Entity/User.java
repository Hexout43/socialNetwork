package ru.lernup.socialnetwork.Db.Entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;
@Setter
@Getter
@Entity
@Table(name = "user_")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "login")
    private String login;
    @Column(name = "password")
    private String password;
    @ManyToMany(mappedBy = "users",fetch = FetchType.EAGER,cascade = CascadeType.ALL)
    private Set<Role> roles;
    @OneToOne(mappedBy = "user",fetch = FetchType.LAZY,cascade =CascadeType.ALL)
    private Person person;

}
