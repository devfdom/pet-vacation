package com.petvacation.petvacation.domain;

import javax.persistence.*;

import java.time.LocalDateTime;

import static javax.persistence.GenerationType.AUTO;

@Entity
public class Booking {
    @Id
    Long id;

    @ManyToOne
    @JoinColumn (name="user_id")
    User user;

    @ManyToOne
    @JoinColumn (name = "properties_id")
    Properties properties;

    LocalDateTime checkin;
    LocalDateTime checkout;


}
