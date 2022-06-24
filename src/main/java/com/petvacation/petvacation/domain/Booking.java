package com.petvacation.petvacation.domain;

import javax.persistence.*;
import java.util.Date;


@Entity
public class Booking {
    @Id
    Long id;
    private Date checkin;
    private Date checkout;
    private int totalPrice;


    @ManyToOne
    @JoinColumn (name="user_id")
    User guest;

    @ManyToOne
    @JoinColumn (name = "properties_id")
    Properties properties;

    public void addBooking(User guest){
        guest.add(guest);
        guest.getProperties().add(properties);
    }
    public void removeBooking(User guest) {
        guest.remove(guest);
        guest.getProperties().remove(properties);
    }
   /* LocalDateTime checkin;
    LocalDateTime checkout;*/

}
