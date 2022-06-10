package com.petvacation.petvacation.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Set;

import static javax.persistence.GenerationType.AUTO;

@Entity @Data @NoArgsConstructor @AllArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = AUTO)
    private Long id;
    private String name;
    private String email;
    private String username;
    private String password;

    @ManyToMany(fetch = FetchType.EAGER)
    private Collection <Role> roles = new ArrayList<>();

    @OneToMany(mappedBy = "user")
    Set<Booking> booking;

    @OneToMany(mappedBy = "user")
    Set<Properties> properties;

    public User(Long id, String name, String email, String username, String password, Collection<Role> roles) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.username = username;
        this.password = password;
        this.roles = roles;
    }


}
