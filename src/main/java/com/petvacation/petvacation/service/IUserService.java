package com.petvacation.petvacation.service;

import com.petvacation.petvacation.domain.Properties;
import com.petvacation.petvacation.domain.Role;
import com.petvacation.petvacation.domain.User;
import java.util.List;


public interface IUserService {
    User saveUser(User user);
    Role saveRole(Role role);
    void addRoleToUser(String username, String roleName);
    User getUser(String username);
    List<User> getUsers();
    User findById(Long id);
    void deleteUserById(Long id);

    void addOwnerToProperties(User owner, Long idProperties);

    void addOwnerToProperties(Long idUser, Long idProperties);

    void addOwnerProperties(User user, Long idProperties);

    void addBooking(User guest, Long idProperties);

    void addBooking(Long idUser, Long idProperties);

    public User findUserById(Long id);
}
