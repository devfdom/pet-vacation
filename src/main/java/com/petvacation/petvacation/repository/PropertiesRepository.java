package com.petvacation.petvacation.repository;

import com.petvacation.petvacation.domain.Properties;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PropertiesRepository extends JpaRepository<Properties, Long> {
        //public List<Properties> findByAvailableIsTrue();
        Properties findPropertyById(Long properties);

        List<Properties> findPropertyByCity(String city);
}
