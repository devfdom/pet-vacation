package com.petvacation.petvacation.service;

import com.petvacation.petvacation.domain.Properties;
import com.petvacation.petvacation.domain.User;

import java.util.List;


public interface IPropertiesService {
    List<Properties> findAll();
    //public List<Properties> listAvailable();
    Properties save(Properties properties);
    Properties update(Properties properties);
    Properties findPropertyById(Long id);
    void delete (Long id);
    List<User> listUser();
    List<Properties> getProperty(Properties properties);
    List<Properties> getPropertiesOwner(User user);
    List<Properties> findPropertyByCity(String city);
}
