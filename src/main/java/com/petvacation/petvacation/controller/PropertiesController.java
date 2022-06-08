package com.petvacation.petvacation.controller;

import com.petvacation.petvacation.domain.Properties;
import com.petvacation.petvacation.repository.PropertiesRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import javax.validation.Valid;
import java.util.List;


@RestController
@RequestMapping("/properties")
@RequiredArgsConstructor
public class PropertiesController {

    private PropertiesRepository propertiesRepository;

    PropertiesController(PropertiesRepository propertiesRepository){
        this.propertiesRepository = propertiesRepository;
    }

    @GetMapping("/properties")
    public List<Properties> findAllProperties() {
        return propertiesRepository.findAll(); }
    //ok
    @GetMapping("/properties/{id}")
    public Properties findPropertiesById(@PathVariable("id") Long id) throws ChangeSetPersister.NotFoundException {
        return propertiesRepository.findById(id).orElseThrow(ChangeSetPersister.NotFoundException::new);
    }
    //ok
    @PostMapping("/properties/create")
    public Properties createNewProperties(@RequestBody @Valid Properties properties){
        /*Properties properties = new Properties();
        model.addAttribute("title", "Form: New Properties");
        model.addAttribute("properties", properties);*/
        return this.propertiesRepository.save(properties);
    }

    /*@PutMapping("/properties/edit")
    public String editProperties(@Valid @ModelAttribute Properties properties, BindingResult result,
                                 Model model, RedirectAttributes attribute){

        if (result.hasErrors()){
            model.addAttribute("title", "Form: New Properties");
            model.addAttribute("properties", properties);
            System.out.println("Errors with the form");

            return "/views/admin/frmCreate";
        }

        propertiesRepository.save(properties);
        System.out.println("Successfully saved!");
        attribute.addFlashAttribute("success","Successfully saved");
        return "redirect:/views/admin/";
    }*/
    //ok
    @PutMapping("/properties")
    public Properties updatePropertiesById(@PathVariable("id") Long idProperties, @RequestBody @Valid Properties properties) {
        return propertiesRepository.save(properties);
    }

    @DeleteMapping("/properties/delete/{id}")
    public Properties deletePropertiesById (@PathVariable("id") Long idProperties, RedirectAttributes attribute) throws ChangeSetPersister.NotFoundException {
        Properties properties = propertiesRepository.findById(idProperties).orElseThrow(ChangeSetPersister.NotFoundException::new);
        propertiesRepository.deleteById(idProperties);
        return properties;

    }

    /**Find a property by Id and marks it as rented, also register the name of who rented it*/
    @PutMapping("/properties/{id}/book")
    public Properties updatePropertiesRented(@PathVariable("id") Long idUser, @RequestParam (value = "idUser") String renter) throws ChangeSetPersister.NotFoundException {
        Properties properties = propertiesRepository.findById(idUser).orElseThrow(ChangeSetPersister.NotFoundException::new);
        properties.setRenter(renter);
        properties.setBook(true);
        return propertiesRepository.save(properties);
    }

    /** It marks a property as avaiable when remove the name of renter*/
    @PutMapping("/properties/{id}/return")
    public Properties clearPropertiesRented(@PathVariable Long id) throws ChangeSetPersister.NotFoundException {
        Properties properties = propertiesRepository.findById(id).orElseThrow(ChangeSetPersister.NotFoundException::new);
        properties.setRenter(null);
        properties.setBook(false);
        return propertiesRepository.save(properties);
    }

    /**Attributes a score to properties*/
    @PutMapping("/properties/{id}/rating")
    public Properties upDateRatingPropertiesById(@PathVariable Long id, @RequestBody Properties properties) throws ChangeSetPersister.NotFoundException {
        Properties propertiesToEdit = propertiesRepository.findById(id).orElseThrow(ChangeSetPersister.NotFoundException::new);
        int newRating = properties.getRating();
        if(newRating >=0 && newRating <=5){
            propertiesToEdit.setRating(newRating);
        } else {
            propertiesToEdit.setRating(0);
        }
        return propertiesRepository.save(propertiesToEdit);
    }


}
