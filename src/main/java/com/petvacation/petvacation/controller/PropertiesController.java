package com.petvacation.petvacation.controller;

import com.petvacation.petvacation.domain.Properties;
import com.petvacation.petvacation.repository.PropertiesRepository;
import com.petvacation.petvacation.service.IPropertiesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;


@RestController
@RequestMapping("/petvacation")
@CrossOrigin(origins="http://localhost:4200/")
public class PropertiesController {


    private final IPropertiesService propertiesService;

    @Autowired
    public PropertiesController(IPropertiesService propertiesService){
        this.propertiesService = propertiesService;
    }
    //private IPropertiesService propertiesService;

    @GetMapping("/properties")
    public List<Properties> findAllProperties() {
        return propertiesService.findAll(); }

    @PostMapping("/properties/create")
    public Properties createNewEvent (@RequestBody @Valid Properties properties){
        return propertiesService.save(properties);
    }

    //ok
    @GetMapping("/properties/{id}")
    public Properties findPropertiesById(@PathVariable("id") Long idProperties)  {
        return propertiesService.findPropertyById(idProperties);
    }

    @PutMapping("properties/edit/{id}")
    public Properties updateProperty (@PathVariable("id") Long idProperties, @RequestBody @Valid Properties properties){
        return propertiesService.save(properties);
    }

    @DeleteMapping("/properties/{id}")
    public ResponseEntity<Boolean> deleteProperty (@PathVariable("id") Long id){
        propertiesService.delete(id);
        return new ResponseEntity<Boolean>(Boolean.TRUE, HttpStatus.OK);
    }



    /*@PostMapping("/save")
    public String saveProperties(@Valid @ModelAttribute Properties properties, BindingResult result,
                            Model model, RedirectAttributes attribute){

        if (result.hasErrors()){
            model.addAttribute("title", "Form: New Property");
            model.addAttribute("events", properties);
            System.out.println("Errors with the form");

            return "/ruta/Angular";
        }

        propertiesService.save(properties);
        System.out.println("Successfully saved!");
        attribute.addFlashAttribute("success","Successfully saved");
        return "redirect:/properties";
    }*/

   /* @GetMapping("/properties/edit")
    public String editProperties(@Valid @ModelAttribute Properties properties, BindingResult result,
                                 Model model, RedirectAttributes attribute){

        if (result.hasErrors()){
            model.addAttribute("title", "Form: New Properties");
            model.addAttribute("properties", properties);
            System.out.println("Errors with the form");

            return "/ruta/angular";
        }
        propertiesService.save(properties);
        System.out.println("Successfully saved!");
        attribute.addFlashAttribute("success","Successfully saved");
        return "redirect:/properties";
    }

    @PutMapping("/properties")
    public Properties updatePropertiesById(@PathVariable("id") Long idProperties, @RequestBody @Valid Properties properties) {
        propertiesService.save(properties);
        return properties;
    }*/

    /*@GetMapping("/delete/{id}")
    public String deleteEvent (@PathVariable("id") Long idProperties, RedirectAttributes attribute){
        Properties properties = null;

        if(idProperties > 0) {
            properties = propertiesService.findPropertyById(idProperties);

            if(properties == null){
                System.out.println("Error: The ID doesn't not exist!");
                attribute.addFlashAttribute("error","Attention: The ID doesn't exist!");
                return "redirect:/properties";
            }
        }else {
            System.out.println("Error: Error with the indicated ID!");
            attribute.addFlashAttribute("error","Attention: Error with the indicated ID!");
            return "redirect:/properties";
        }

        propertiesService.delete(idProperties);
        System.out.println("Successfully Removed!");
        attribute.addFlashAttribute("warning","Successfully Removed!");

        return "redirect:/properties";
    }*/

}




/*@DeleteMapping("/properties/delete/{id}")
    public Properties deletePropertiesById (@PathVariable("id") Long idProperties, RedirectAttributes attribute) throws ChangeSetPersister.NotFoundException {
        Properties properties = properties.findById(idProperties).orElseThrow(ChangeSetPersister.NotFoundException::new);
        properties.deleteById(idProperties);
        return properties;

    }

    *//**Find a property by Id and marks it as rented, also register the name of who rented it*//*
    @PutMapping("/properties/{id}/book")
    public Properties updatePropertiesRented(@PathVariable("id") Long idUser, @RequestParam (value = "idUser") String renter) throws ChangeSetPersister.NotFoundException {
        Properties properties = propertiesRepository.findById(idUser).orElseThrow(ChangeSetPersister.NotFoundException::new);
        properties.setRenter(renter);
        properties.setBook(true);
        return propertiesRepository.save(properties);
    }

    *//** It marks a property as avaiable when remove the name of renter*//*
    @PutMapping("/properties/{id}/return")
    public Properties clearPropertiesRented(@PathVariable Long id) throws ChangeSetPersister.NotFoundException {
        Properties properties = propertiesRepository.findById(id).orElseThrow(ChangeSetPersister.NotFoundException::new);
        properties.setRenter(null);
        properties.setBook(false);
        return propertiesRepository.save(properties);
    }

    *//**Attributes a score to properties*//*
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
    }*/