package com.petvacation.petvacation.domain;
import javax.persistence.*;

@Entity
@Table(name = "owner")
public class OwnerEntity {

    private static final long serialVersionUId = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String username;
    private String password;



}
