package com.petvacation.petvacation.service;

import com.petvacation.petvacation.domain.Properties;
import com.petvacation.petvacation.repository.PropertiesRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class PropertiesServiceImplTest {

    @Mock
    private PropertiesRepository propertiesRepository;

    private PropertiesServiceImpl underTest;

    @BeforeEach
    void setUp() {
        underTest = new PropertiesServiceImpl(propertiesRepository);

    }

    @Test
    void findAll() {
        underTest.findAll();
        verify(propertiesRepository).findAll();
    }

    @Test
    void listAvailable() {
        underTest.findAll();
        verify(propertiesRepository).findAll();

    }

    @Test
    void save() {
        Properties properties = new Properties(
                1L,
                "city",
                4,
                5,
                true,
                true,
                "photo",
                "description",
                250,
                "owner",
                "booking",
                "guest"
        );

        underTest.save(properties);

        ArgumentCaptor<Properties> propertiesArgumentCaptor = ArgumentCaptor.forClass(Properties.class);

        verify(propertiesRepository).save(propertiesArgumentCaptor.capture());

        Properties capturedProperties = propertiesArgumentCaptor.getValue();
        assertThat(capturedProperties).isEqualTo(properties);

    }

    @Test
    void update(){
        Properties properties = new Properties(
                1L,
                "city",
                4,
                5,
                true,
                true,
                "photo",
                "description",
                250,
                "owner",
                "booking",
                "guest"
        );
        underTest.update(properties);
        verify(propertiesRepository).save(properties);
    }

    @Test
    void findPropertyById() {
        Properties properties = new Properties(
                1L,
                "city",
                4,
                5,
                true,
                true,
                "photo",
                "description",
                250,
                "owner",
                "booking",
                "guest"

        );
        given(propertiesRepository.findById(properties.getId())).willReturn(Optional.of(properties));
        underTest.findPropertyById(properties.getId());
        verify(propertiesRepository).findById(properties.getId());


    }

    /*@Test
    void itThrowsAnExceptionWhenPropertiesNotFound() {

        given(propertiesRepository.findById(1L)).willReturn(null);

        assertThatThrownBy(() -> underTest.findPropertyById(1L))
                .isInstanceOf(PropertiesNotFoundException.class)
                .hasMessageContaining("Properties not found in the database");

    }*/

    @Test
    void delete() {
        Properties properties = new Properties(
                1L,
                "city",
                4,
                5,
                true,
                true,
                "photo",
                "description",
                250,
                "owner",
                "booking",
                "guest"
        );
        underTest.delete(1L);
        verify(propertiesRepository).deleteById(1L);
    }

   /* @Test
    void listUser() {

        underTest.get(properties);
        verify(propertiesRepository).findAll();*/

   /* @Test
    void getProperty() {

        underTest.getProperty(properties);
        verify(propertiesRepository).findAll();
    }*/

   @Test
    void findPropertyByCity() {
        Properties properties = new Properties(

                "city",
                4,
                5,
                true,
                true,
                "photo",
                "description",
                150

        );
       underTest.findPropertyByCity("city");
       verify(propertiesRepository).findPropertyByCity("city");
   }


    }

