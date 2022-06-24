package com.petvacation.petvacation.service;

import com.petvacation.petvacation.domain.Properties;
import com.petvacation.petvacation.domain.User;

import java.util.List;


public interface IPropertiesService {
    public List<Properties> findAll();
    //public List<Properties> listAvailable();
    public Properties save(Properties properties);

    Properties update(Properties properties);

    public Properties findPropertyById(Long id);
    public void delete (Long id);

    List<User> listUser();

    /* List<User> listUser();*/
    List<Properties> getProperty(Properties properties);
/*
    List<Properties> getPropertiesOwner(User user);
*/
    List<Properties> findPropertyByCity(String city);
}
