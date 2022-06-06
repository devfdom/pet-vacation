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

    @GetMapping("/properties/{id}")
    public Properties findPropertiesById(@PathVariable Long id) throws ChangeSetPersister.NotFoundException {
        return propertiesRepository.findById(id).orElseThrow(ChangeSetPersister.NotFoundException::new);
    }

    @GetMapping("/properties/create")
    public String createNewProperties(Model model){

        Properties properties = new Properties();

        model.addAttribute("title", "Form: New Properties");
        model.addAttribute("properties", properties);

        return "/views/admin/frmCreate";
    }

    @PostMapping("/properties/save")
    public String saveProperties(@Valid @ModelAttribute Properties properties, BindingResult result,
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
    }

    @PutMapping("/properties")
    public Properties updatePropertiesById(@RequestBody Properties properties) {
        return propertiesRepository.save(properties);
    }

    @DeleteMapping("/properties/delete/{id}")
    public Properties deletePropertiesById (@PathVariable("id") Long idProperties, RedirectAttributes attribute) throws ChangeSetPersister.NotFoundException {
        Properties properties = propertiesRepository.findById(idProperties).orElseThrow(ChangeSetPersister.NotFoundException::new);
        propertiesRepository.deleteById(idProperties);
        return properties;

    }

    /**Encuentra el piso por su Id, lo marca como alquilado de acuerdo con el id {id}, además,
     *  registra el nombre de quien lo ha alquilado*/
    @PutMapping("/properties/{id}/book")
    public Properties updatePropertiesRented(@PathVariable Long id, @RequestParam (value = "renter") String renter) throws ChangeSetPersister.NotFoundException {
        Properties properties = propertiesRepository.findById(id).orElseThrow(ChangeSetPersister.NotFoundException::new);
        properties.setRenter(renter);
        properties.setBook(true);
        return propertiesRepository.save(properties);
    }

    /** Marca un piso como disponible cuando borrando el nombre de quien la ha alquilado */
    @PutMapping("/properties/{id}/return")
    public Properties clearPropertiesRented(@PathVariable Long id) throws ChangeSetPersister.NotFoundException {
        Properties properties = propertiesRepository.findById(id).orElseThrow(ChangeSetPersister.NotFoundException::new);
        properties.setRenter(null);
        properties.setBook(false);
        return propertiesRepository.save(properties);
    }

    /**Atribuye un Score a las pisos*/
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
