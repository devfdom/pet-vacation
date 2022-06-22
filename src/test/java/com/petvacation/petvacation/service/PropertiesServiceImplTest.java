package com.petvacation.petvacation.service;

import com.petvacation.petvacation.domain.Properties;
import com.petvacation.petvacation.repository.PropertiesRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
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
        underTest.listAvailable();
        verify(propertiesRepository).findByAvailableIsTrue();

    }

    @Test
    void save() {
        Properties properties = new Properties(
                1,
                3,
                true,
                true,
                "photo",
                "true",
                "my house",
                250,
                "Seville"
        );

        underTest.save(properties);

        ArgumentCaptor<Properties> propertiesArgumentCaptor = ArgumentCaptor.forClass(Properties.class);

        verify(propertiesRepository).save(propertiesArgumentCaptor.capture());

        Properties capturedProperties = propertiesArgumentCaptor.getValue();
        assertThat(capturedProperties).isEqualTo(properties);

    }

    @Test
    void findPropertyById() {
        Properties propertiesId = new Properties(
                1L,
                1,
                3,
                true,
                true,
                "photo",
                "available",
                true,
                "descr",
                250,
                "owner",
                "",
                "city",
                "user"

        );
        underTest.findPropertyById(propertiesId.getId());

        verify(propertiesRepository).findById(1L);
    }

    @Test
    void delete() {
        Properties propertiesDelete = new Properties(
                1L,
                1,
                3,
                true,
                true,
                "photo",
                "true",
                "my house",
                250,
                "Seville"
        );
        underTest.delete(propertiesDelete.getId());
        verify(propertiesRepository).deleteById(1L);
    }

   /* @Test
    void getProperty() {
        underTest.getProperty(list<properties>);
        verify(propertiesRepository).findAll();
    }*/

    @Test
    void findPropertyByCity() {
        Properties properties = new Properties(
                1L,
                4,
                5,
                true,
                true,
                "photo",
                "available",
                true,
                "description",
                150,
                "owner",
                null,
                "city",
                "user"

        );
        underTest.findPropertyByCity("city");
        verify(propertiesRepository).findPropertyByCity("city");
    }


}
