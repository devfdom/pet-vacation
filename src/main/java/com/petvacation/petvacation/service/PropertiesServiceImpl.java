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

@Service @RequiredArgsConstructor
public class PropertiesServiceImpl {
    @Autowired
    private PropertiesRepository propertiesRepository;

    public PropertiesServiceImpl(PropertiesRepository propertiesRepository) {
    }

   @Override
    public List<Properties> findAll(){
        return (List<Properties>) propertiesRepository.findAll(Sort.by(Sort.Direction.DESC, "available"));
    }

    @Override
    public List<Properties> listAvailable(){

        return (List<Properties>) propertiesRepository.findByAvailableIsTrue();
    }

    @Override
    public void save (Properties properties){
        propertiesRepository.save(properties);
    }

    @Override
    public Properties findById(Long id){
        return propertiesRepository.findById(id).orElse(null);
    }

    @Override
    public void delete(Long id){
        propertiesRepository.deleteById(id);
    }

    @Override
    public List<User> listUser(){
        return null;
    }
}
