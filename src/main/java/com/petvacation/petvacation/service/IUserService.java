package com.petvacation.petvacation.service;

import com.petvacation.petvacation.domain.Role;
import com.petvacation.petvacation.domain.User;
import java.util.List;


public interface IUserService {
    User saveUser(User user);
    Role saveRole(Role role);
    void addRoleToUser(String username, String roleName);
    User getUser(String username);
    List<User> getUsers();

}
