package com.petvacation.petvacation.service;

import com.petvacation.petvacation.domain.Properties;
import com.petvacation.petvacation.domain.User;

import javax.validation.Valid;
import java.util.List;


public interface IPropertiesService {
    public List<Properties> findAllProperties();
    public List<Properties> listAvailable();
    public void save(Properties properties);
    public Properties findPropertyById(Long id);
    public void delete (Long id);
    List<User> listUser();
}
