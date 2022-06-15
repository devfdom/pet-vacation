package com.petvacation.petvacation.service;

import com.petvacation.petvacation.domain.Properties;
import com.petvacation.petvacation.domain.Role;
import com.petvacation.petvacation.domain.User;
import com.petvacation.petvacation.repository.PropertiesRepository;
import com.petvacation.petvacation.repository.RoleRepository;
import com.petvacation.petvacation.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service @RequiredArgsConstructor @Transactional @Slf4j
public class UserServiceImpl implements IUserService, UserDetailsService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;
    private final PropertiesRepository propertiesRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username);
        if(user == null){
            log.error("User not found in the database");
            throw new UsernameNotFoundException("User not found in the database");
        } else{
            log.info("User found in the database: {}",username);
        }
        Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
        user.getRoles().forEach(role -> {
            authorities.add(new SimpleGrantedAuthority(role.getName()));
        });
        return new org.springframework.security.core.userdetails.User(user.getUsername(),user.getPassword(), authorities);
    }

    @Override
    public User saveUser(User user) {
        log.info("Saving new user {} to the database", user.getName());
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }

    @Override
    public Role saveRole(Role role) {
        log.info("Saving new role {} to the database", role.getName());
        return roleRepository.save(role);
    }

    @Override
    public void addRoleToUser(String username, String roleName) {
        log.info("Adding role {} to user {} ", roleName, username);
        User user = userRepository.findByUsername(username);
        Role role = roleRepository.findByName(roleName);
        user.getRoles().add(role);
    }

    @Override
    public User getUser(String username) {
        log.info("Fetching user {}",  username);
        return userRepository.findByUsername(username);
    }

    @Override
    public List<User> getUsers() {
        log.info("Fetching all users");
        return userRepository.findAll();
    }

    /*@Override
    public List<User> findAll() {
        log.info("Fetching all users");
        return userRepository.findAll();
    }*/

    /*@Override
    public User findByUsername(String username) {
        log.info("Finding users by username");
        return userRepository.findByUsername(username);
    }*/

    @Override
    public User findById(Long id) {
        return userRepository.findById(id).orElse(null);
    }

    @Override
    public void deleteUserById(Long id) {
        userRepository.deleteById(id);
    }

    @Override
    public void addOwnerToProperties(User owner, Long idProperties) {

    }

    @Override
    public void addOwnerToProperties(Long idUser, Long idProperties) {
        log.info("Adding owner {} to property {} ", idUser, idProperties);
        User user = userRepository.findUserById(idUser);
        Properties properties = propertiesRepository.findPropertyById(idProperties);
        user.getProperties().add(properties);
    }

    @Override
    public void addOwnerProperties(User user, Long idProperties) {

    }

    @Override
    public void addBooking(User guest, Long idProperties) {

    }

    @Override
    public void addBooking(Long idUser, Long idProperties) {
        log.info("Adding guest {} to property {} ", idUser, idProperties);
        User user = userRepository.findUserById(idUser);
        Properties properties = propertiesRepository.findPropertyById(idProperties);
        user.getProperties().add(properties);
    }

    @Override
    public User findUserById(Long id) {
        return null;
    }

    /*@Override
    public void addUserProperties(Long userId, Long propertiesId) {

    }*/



}
