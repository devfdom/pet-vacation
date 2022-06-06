package com.petvacation.petvacation.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Set;
import static javax.persistence.GenerationType.AUTO;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Properties {
    @Id
    @GeneratedValue(strategy = AUTO)
    private Long id;
    private String city;
    private int bedrooms;
    private int capacity;
    private boolean pool;
    private boolean garden;
    private String photo;
    private String available;
    private boolean approved;
    private int user_id;
    private boolean book;
    private String renter;
    private int rating;

    @OneToMany(mappedBy = "properties")
    Set<Booking> booking;
}
