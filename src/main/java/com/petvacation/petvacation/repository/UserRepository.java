package com.petvacation.petvacation.repository;

import com.petvacation.petvacation.domain.Properties;
import com.petvacation.petvacation.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository <User, Long> {
    public User findByUsername(String username);
    //public User findByGuest(User guest);
    User findUserById(Long id);

    // public User findByOwner(User owner);
}
