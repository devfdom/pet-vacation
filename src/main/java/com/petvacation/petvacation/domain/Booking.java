package com.petvacation.petvacation.domain;

import javax.persistence.*;
import java.util.Date;


@Entity
public class Booking {
    @Id
    Long id;
    private Date checkin;
    private Date checkout;


    @ManyToOne
    @JoinColumn (name="user_id")
    User guest;

    @ManyToOne
    @JoinColumn (name = "properties_id")
    Properties properties;

   /* LocalDateTime checkin;
    LocalDateTime checkout;*/

}
