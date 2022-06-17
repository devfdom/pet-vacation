package com.petvacation.petvacation.service;

import com.petvacation.petvacation.domain.Booking;
import com.petvacation.petvacation.domain.Properties;
import com.petvacation.petvacation.domain.User;

import com.petvacation.petvacation.repository.BookingRepository;
import com.petvacation.petvacation.repository.PropertiesRepository;
import com.petvacation.petvacation.repository.UserRepository;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;
import java.util.*;


@Service
@Transactional

public class BookingService implements IBooking{

        private final BookingRepository bookingRepository;
        private final UserRepository userRepository;
        private final PropertiesRepository propertiesRepository;


        public BookingService(BookingRepository bookingRepository, UserRepository userRepository, PropertiesRepository propertiesRepository) {
            this.bookingRepository = bookingRepository;
            this.userRepository = userRepository;
            this.propertiesRepository = propertiesRepository;
        }

        @Override
        public void addBooking (Long idUser, Long idProperties) {
            User user = userRepository.findUserById(idUser);
            Properties properties = propertiesRepository.findPropertyById(idProperties);
            user.getProperties().add(properties);
        }

        @Override
        public List<Booking> getAllBookings(){
            return bookingRepository.findAll();
        }


        @Override
        public Booking saveBooking (Booking booking){
            return bookingRepository.save(booking);
        }


        /*@Override
        public User getUser(User guest) {
            return bookingRepository.findByGuest(guest);
        }*/


       /* @Override
        public User getOwner(User owner) {
            return bookingRepository.getOwner(owner);
        }*/

        @Override
        public Properties getProperties(Long id) {
            return propertiesRepository.findById(id).orElse(null);
        }

    @Override
    public void addBooking(User guest, Long idProperties) {
            Booking booking = new Booking();
            booking.addBooking(guest);
            bookingRepository.save(booking);
    }

    @Override
    public void deleteBooking(User guest, Long idProperties) {
        Booking booking = new Booking();
        booking.removeBooking(guest);
        bookingRepository.delete(booking);
    }


    @Override
        public List<Booking> getBookingByUserId(User id) {
            return bookingRepository.findAll();
        }

        @Override
        public List<Booking> getBookingByPropertyId(Properties id) {
            return bookingRepository.findAll();
        }

        @Override
        public List<Booking> getCheckin(Booking checkin) {
            return bookingRepository.findAll();
        }

        @Override
        public List<Booking> getCheckout(Booking checkout) {
            return bookingRepository.findAll();
        }

        /*@Override
        public void deleteBookingById(Long id) {
            bookingRepository.deleteById(id);
        }*/

        @Override
        public Booking getBookingById(Long id) {
            return bookingRepository.findById(id).orElse(null);
        }

        @Override
        public Object getBooking() {
            return bookingRepository.findAll();
        }

        @Override
        public Booking findAll() {
            return (Booking) bookingRepository.findAll();
        }


    }
