package com.petvacation.petvacation.controller;


import com.petvacation.petvacation.domain.Properties;
import com.petvacation.petvacation.domain.User;
import com.petvacation.petvacation.repository.PropertiesRepository;
import com.petvacation.petvacation.repository.UserRepository;
import com.petvacation.petvacation.service.IUserService;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

/*

@RestController
@RequestMapping("/api/bookings")
@RequiredArgsConstructor
public class BookingsController {
    private final IUserService userService;
    private final PropertiesRepository propertiesRepository;
    private final UserRepository userRepository;

   */
/* @PostMapping("/user/properties")
    public ResponseEntity<?> addUserBooking(@RequestBody UserBookingsForm form){
        userService.addUserProperties(form.getUserId(),form.getPropertiesId());
        return ResponseEntity.ok().build();
    }
*//*


    */
/*
    @PutMapping("api/user/{id}/book")
    public String userAddBooking(Authentication auth, @PathVariable("id") Long idProperties){
        Properties properties= propertiesRepository.findById(idProperties).orElseThrow(UserNotFoundException::new);

        String username = auth.getName();
        User user = userService.findByUsername(username);

        if (user.getProperties().contains(properties)){
            System.out.println("Properties Duplicated!");
        }else{
            .properties.setBook().getSigned()+1;
            propertiesRepository.save();
            System.out.println("Signed Up for the " + .getSigned());
            user.getProperties().add();
            userRepository.save(user);
        }

        return "redirect:/views/users/index";
    }



    @GetMapping("/userRemoveBooking/{id}")
    public String userRemoveBooking(Authentication auth, @PathVariable ("id") Long idEvent, @RequestParam(value = "renter") String renter){
        Properties properties = propertiesRepository.findById(idEvent);

        String username = auth.getName();
        User user = userService.findByUsername(username);

        if (user.getProperties().contains(properties)){
            properties.setRenter(renter);
            propertiesRepository.save(properties);
            System.out.println("Event Removed");
            user.getBook().remove(properties);
            userRepository.save(user);
        }
        else{
            System.out.println("Event not find");
        }

        return "redirect:/views/users/index";
    }
    *//*


}
@Data
class UserBookingsForm{
    private Long userId;
    private Long propertiesId;
}
*/

