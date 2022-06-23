package com.petvacation.petvacation.service;

import com.petvacation.petvacation.domain.Properties;
import com.petvacation.petvacation.domain.User;

import java.util.List;


public interface IPropertiesService {
    List<Properties> findAll();
    //public List<Properties> listAvailable();
<<<<<<< HEAD
    Properties save(Properties properties);
    Properties update(Properties properties);
    Properties findPropertyById(Long id);
    void delete (Long id);
    List<User> listUser();
=======
    public Properties save(Properties properties);
    public Properties findPropertyById(Long id);
    public void delete (Long id);
   /* List<User> listUser();*/
>>>>>>> aa6ba40a32443249377c6e16849dda8ddcef2d50
    List<Properties> getProperty(Properties properties);
/*
    List<Properties> getPropertiesOwner(User user);
*/
    List<Properties> findPropertyByCity(String city);
}
