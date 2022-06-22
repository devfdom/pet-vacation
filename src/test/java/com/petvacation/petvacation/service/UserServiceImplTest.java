package com.petvacation.petvacation.service;

import com.petvacation.petvacation.domain.Booking;
import com.petvacation.petvacation.domain.Role;
import com.petvacation.petvacation.domain.User;
import com.petvacation.petvacation.repository.PropertiesRepository;
import com.petvacation.petvacation.repository.RoleRepository;
import com.petvacation.petvacation.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.ArrayList;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.verify;


@ExtendWith(MockitoExtension.class)
class UserServiceImplTest {

    @Mock
    private UserRepository userRepository;
    @Mock
    private  RoleRepository roleRepository;
    @Mock
    private  PasswordEncoder passwordEncoder;
    @Mock
    private  PropertiesRepository propertiesRepository;


    private UserServiceImpl underTest;


    @BeforeEach
    void setUp() {
        underTest = new UserServiceImpl(userRepository, roleRepository, passwordEncoder, propertiesRepository);
    }

    @Test
    void loadUserByUsername() {
        User user = new User(
                null,
                "María",
                "maria@gmail.com",
                "maría",
                "frontend",
                null

        );
    }

    @Test
    void saveUser() {
        User user = new User(
                1L,
                "Glaucia",
                "glaucia@gmail.com",
                "Glaucia",
                "bootcamp",
                null
        );
        underTest.saveUser(user);

        ArgumentCaptor<User> userArgumentCaptor = ArgumentCaptor.forClass(User.class);
        verify(userRepository).save(userArgumentCaptor.capture());

        User capturedUser = userArgumentCaptor.getValue();
        assertThat(capturedUser).isEqualTo(user);

    }

    @Test
    void saveRole() {
        Role role = new Role(
                1L,
                "role_user"
        );
        underTest.saveRole(role);
        verify(roleRepository).save(role);
    }

    @Test
    void addRoleToUser() {
        User user = new User(
               1L,
               "Fran",
               "fran@gmail.com",
               "Francisco",
               "backend",
                null
        );
        Role role = new Role(
                1L,
                "role_user"
        );
        user.getRoles().add(role);
        assertThat(user.getRoles()).toString().contains("ROLE_USER");
    }

    @Test
    void getUser() {
        underTest.getUser("username");
        verify(userRepository).findByUsername("username");

    }

    @Test
    void getUsers() {
        underTest.getUsers();
        verify(userRepository).findAll();
    }

    @Test
    void findById() {
        User user = new User(
                1L,
                "Glaucia",
                "glaucia@gmail.com",
                "Glaucia",
                "bootcamp",
                null
        );
        underTest.saveUser(user);
        underTest.findById(1L);
        verify(userRepository).findById(1L);

    }

    @Test
    void deleteUserById() {
        /*User user = new User(
                1L,
                "Glaucia",
                "glaucia@gmail.com",
                "Glaucia",
                "bootcamp",
                 null

        )*/;
        underTest.deleteUserById(1L);
        verify(userRepository).deleteById(1L);

    }

    @Test
    void addOwnerToProperties() {
    }

    @Test
    void testAddOwnerToProperties() {
    }


   /* @Test
    void testAddBooking() {
        Booking booking = new Booking(


        );
        underTest.addBooking("Lupe",1L);

        ArgumentCaptor<Booking> bookingArgumentCaptor = ArgumentCaptor.forClass(Booking.class);
        verify(propertiesRepository).save(bookingArgumentCaptor.capture());

        Booking capturedBooking = bookingArgumentCaptor.getValue();

        assertThat(capturedBooking).isEqualTo(booking);

    }
*/

}