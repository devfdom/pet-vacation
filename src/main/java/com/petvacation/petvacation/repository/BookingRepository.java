package com.petvacation.petvacation.repository;

import com.petvacation.petvacation.domain.Booking;
import com.petvacation.petvacation.domain.User;
import org.hibernate.Session;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookingRepository extends JpaRepository<Booking, Long> {
    //Session getCurrentSession();
    //public User findByGuest(User guest);
    //public User findByOwner(User owner);

    //public User getOwner(User owner);
}
