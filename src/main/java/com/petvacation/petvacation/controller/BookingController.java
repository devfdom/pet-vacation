package com.petvacation.petvacation.controller;

import com.petvacation.petvacation.domain.Booking;
import com.petvacation.petvacation.domain.User;
import com.petvacation.petvacation.service.BookingService;
import com.petvacation.petvacation.service.IPropertiesService;
import com.petvacation.petvacation.service.IUserService;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/petvacation")
@RequiredArgsConstructor
@CrossOrigin(origins="http://localhost:4200/")
public class BookingController {
    private final BookingService bookingService;
    private final IUserService userService;
    private final IPropertiesService propertiesService;

    @GetMapping("/booking")
    public Booking getBooking(){
        return bookingService.findAll();
    }

    @PostMapping("/booking/create")
    public Booking createNewBooking (@RequestBody @Valid Booking booking){
        return bookingService.saveBooking(booking);
    }

    //ok
    @GetMapping("/booking/{id}")
    public Booking findBookingById(@PathVariable("id") Long idBooking)  {
        return bookingService.getBookingById(idBooking);
    }

    @DeleteMapping("/booking/delete/{id}")
    public ResponseEntity<Boolean> deleteBookingById (@PathVariable("id") Long idBooking){
        bookingService.deleteBookingById(idBooking);
        return new ResponseEntity<Boolean>(Boolean.TRUE, HttpStatus.OK);
    }
    @PostMapping("/booking/addtouser")
    public ResponseEntity<?>addBookingToUser(@RequestBody BookingToUserForm form){
        User user = new User();
        user =userService.findById(form.getIdUser());
        userService.addBooking(user,form.getIdProperties());
        return ResponseEntity.ok().build();
    }
}

@Data
class BookingToUserForm{
    private Long idUser;
    private Long idProperties;
}


