package com.petvacation.petvacation.service;

import com.petvacation.petvacation.domain.User;

import javax.validation.Valid;
import java.util.List;
import java.util.Properties;

public interface IPropertiesService {
    public List<Properties> findAll();
    public List<Properties> listAvailable();
    public void save(Properties properties);
    public Properties findById(Long id);
    public void delete (Long id);
    List<User> listUser();
}
