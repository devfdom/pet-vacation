package com.petvacation.petvacation.service;

import com.petvacation.petvacation.domain.City;

import java.util.List;

public interface ICityService {
    void createCity(String name);

    List<City> getAllCities();

    City getCityById(Integer id);
}
