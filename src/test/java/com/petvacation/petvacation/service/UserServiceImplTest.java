package com.petvacation.petvacation.service;

import com.petvacation.petvacation.domain.Properties;
import com.petvacation.petvacation.domain.Role;
import com.petvacation.petvacation.domain.User;
import com.petvacation.petvacation.repository.PropertiesRepository;
import com.petvacation.petvacation.repository.RoleRepository;
import com.petvacation.petvacation.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.ArrayList;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.mockito.BDDMockito.given;
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
    void itCanLoadUserByUsername() {
        User user = new User(
                1L,
                "Eli",
                "eli@gmail.com",
                "eli",
                "frontend",
                new ArrayList<>()

        );
        Role role = new Role(1L, "ROLE_USER");
        user.getRoles().add(role);

        given(userRepository.findByUsername("eli")).willReturn(user);

        underTest.loadUserByUsername("eli");
        verify(userRepository).findByUsername("eli");
    }

    @Test
    void itThrowsAnExceptionWhenUserNotFound() {

        given(userRepository.findByUsername("eli")).willReturn(null);

        assertThatThrownBy(() -> underTest.loadUserByUsername("eli"))
                .isInstanceOf(UsernameNotFoundException.class)
                .hasMessageContaining("User not found in the database");

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
                new ArrayList<>()

        );
        Role role = new Role(1L, "ROLE_USER");

        given(userRepository.findByUsername("Francisco")).willReturn(user);
        given(roleRepository.findByName("ROLE_USER")).willReturn(role);

        // user.getRoles().add(role);
        underTest.addRoleToUser("Francisco", "ROLE_USER");
        verify(userRepository).findByUsername("Francisco");
        verify(roleRepository).findByName("ROLE_USER");
        //assertThat(user.getRoles()).toString().contains("ROLE_USER");
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

        underTest.deleteUserById(1L);
        verify(userRepository).deleteById(1L);

    }

    /*@Test
    void addOwnerToProperties() {
    }*/

  /*  @Test
    @Disabled
    void addOwnerToProperties() {
        User user = new User(
                1L,
                "Fran",
                "fran@gmail.com",
                "Francisco",
                "backend",
                new ArrayList<>()
        );
        Properties properties = new Properties(1L);

        user.getProperties().add(properties);
        given(propertiesRepository.findById(1L)).willReturn(Optional.of(properties));

        underTest.addOwnerToProperties(1L, 1L);
        verify(userRepository).findUserById(1L);

    }*/
/*
    @Test
    void itThrowsAnExceptionWhenPropertiesRuntimeExceptionIsNew(){
        given(userRepository.findUserById(1L)).willReturn(null);

        assertThatThrownBy(() -> underTest.addOwnerToProperties(1L, 1L)).isInstanceOf((RuntimeException.class)).hasMessageContaining("new");
    }*/

    /*  @Test
    void addOwnerProperties() {
    }

    @Test
    void addBooking() {
    }
*/
   /* @Test
    @Disabled
    void testAddBooking() {

         User user = new User();

        Properties properties = new Properties(1L);

        user.getProperties().add(properties);
        assertThat(user.getProperties()).toString().contains("booking");


    }
*/




}