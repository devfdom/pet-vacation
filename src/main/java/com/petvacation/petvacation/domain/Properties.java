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
    private boolean book;
    private String renter;
    private int rating;
    private String description;
    @ManyToOne
    @JoinColumn(name="owner")
    private User user;

    @OneToMany(mappedBy = "properties")
    Set<Booking> booking;

    public Properties(Long id, String city, int bedrooms, int capacity, boolean pool, boolean garden, String photo, String available, boolean approved, boolean book, String renter, int rating, String description, User user, Set<Booking> booking) {
        this.id = id;
        this.city = city;
        this.bedrooms = bedrooms;
        this.capacity = capacity;
        this.pool = pool;
        this.garden = garden;
        this.photo = photo;
        this.available = available;
        this.approved = approved;
        this.book = book;
        this.renter = renter;
        this.rating = rating;
        this.description = description;
        this.user = user;
        this.booking = booking;
    }
}
