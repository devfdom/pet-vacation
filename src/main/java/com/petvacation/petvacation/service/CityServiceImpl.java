package com.petvacation.petvacation.service;


import com.petvacation.petvacation.domain.City;
import com.petvacation.petvacation.repository.CityRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class CityServiceImpl implements ICityService {

    private SessionFactory sessionFactory;
    private CityRepository cityRepository;

    public CityServiceImpl(CityRepository cityRepository) {
        this.cityRepository = cityRepository;
    }


    @Override
    public void createCity(String name) {
        Session session = sessionFactory.getCurrentSession();
        City city = new City();
        city.setName(name);
        session.save(city);
    }

    @Override
    public List<City> getAllCities() {
        return cityRepository.findAll();
    }

    /*@Override
    public List<City> getAllCities() {
        return cityRepository.findAll();
    }*/

    @Override
    public City getCityById(Integer id) {
        Session currentSession = sessionFactory.getCurrentSession();
        return (City) currentSession.get(City.class, id);
    }

}
