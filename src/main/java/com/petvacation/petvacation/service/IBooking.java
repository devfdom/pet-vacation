package com.petvacation.petvacation.service;

import com.petvacation.petvacation.domain.Booking;
import com.petvacation.petvacation.domain.Properties;
import com.petvacation.petvacation.domain.User;

import java.util.List;

public interface IBooking {

    //User saveUser(User guest);
   // User getUser(User guest);

    //User saveOwner(User owner);
   // User getOwner(User owner);
    //Properties saveProperties(Long idProperties);
    Properties getProperties(Long idProperties);

    void addBooking(User guest, Long idProperties);
    List<Booking> getBookingByUserId(User idUser);

    public List<Booking> getBookingByPropertyId(Properties idProperty);

    void addBooking(Long idUser, Long idProperties);

    public List<Booking> getAllBookings();
    List<Booking> getCheckin(Booking checkin);
    List<Booking> getCheckout(Booking checkout);
    Booking saveBooking (Booking booking);
    void deleteBooking(Long id);
    Booking getBookingById(Long id);

    Object getBooking();

    Booking findAll();
}
