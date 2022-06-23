package com.petvacation.petvacation.service;

import com.petvacation.petvacation.domain.Properties;
import com.petvacation.petvacation.domain.User;
import com.petvacation.petvacation.repository.PropertiesRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;
import java.util.List;

@Service @RequiredArgsConstructor @Transactional @Slf4j
public class PropertiesServiceImpl implements IPropertiesService {


    private final PropertiesRepository propertiesRepository;

    /*public PropertiesServiceImpl(PropertiesRepository propertiesRepository) {
        this.propertiesRepository = propertiesRepository;
    }*/

   @Override
    public List<Properties> findAll(){
        return propertiesRepository.findAll();
    }

    /*@Override
    public List<Properties> listAvailable(){
        return (List<Properties>) propertiesRepository.findByAvailableIsTrue();
    }
*/
    @Override
    public Properties save (Properties properties){
        return propertiesRepository.save(properties);

    }

    @Override
    public Properties findPropertyById(Long id){
        return propertiesRepository.findById(id).orElse(null);
    }

    @Override
    public void delete(Long id){
        propertiesRepository.deleteById(id);
    }

    /*@Override
    public List<User> listUser(){
        return null;
    }*/
    @Override
    public List<Properties> getProperty(Properties properties) {
        return (List<Properties>) properties;
    }

    /*@Override
    public List<Properties> getPropertiesOwner(User user) {
        return null;
    }
*/
    @Override
    public List<Properties> findPropertyByCity(String city) {
        System.out.println(city);
        return propertiesRepository.findPropertyByCity(city);
    }

}
