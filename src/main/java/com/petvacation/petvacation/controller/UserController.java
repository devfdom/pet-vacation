package com.petvacation.petvacation.controller;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.petvacation.petvacation.domain.Role;
import com.petvacation.petvacation.domain.User;
import com.petvacation.petvacation.service.IPropertiesService;
import com.petvacation.petvacation.service.IUserService;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import org.springframework.security.core.Authentication;
import org.springframework.ui.Model;
import org.springframework.util.MimeTypeUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URI;
import java.util.*;
import java.util.stream.Collectors;

import static org.springframework.http.HttpHeaders.AUTHORIZATION;
import static org.springframework.http.HttpStatus.FORBIDDEN;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class UserController {
    private final IUserService userService;
    private final IPropertiesService iPropertiesService;

    @GetMapping("/users")
    public ResponseEntity<List<User>>getUsers(){
        return ResponseEntity.ok().body(userService.getUsers());
    }

    @GetMapping("/")
    public String listUsers(Model model){
        List<User> listOfUsers = userService.findAll();
        model.addAttribute("users", "List of Users");
        model.addAttribute("users", listOfUsers);
        return "/views/admin/list";
    }
    @GetMapping("/create")
    public String createANewUser (Model model) {

        User user = new User();
        /*List<Users> listUsers= usersService.listUsers();*/

        model.addAttribute("title", "Form: New User");
        model.addAttribute("user", user);
        /* model.addAttribute("users", listUsers);*/

        return "/views/user/frmUser";
    }

    @PostMapping("/user/save")
    public ResponseEntity<User>saveUser(@RequestBody User user){
        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/user/save").toUriString());
        return ResponseEntity.created(uri).body(userService.saveUser(user));
    }

    @PostMapping("/role/save")
    public ResponseEntity<Role>saveRole(@RequestBody Role role){
        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/role/save").toUriString());
        return ResponseEntity.created(uri).body(userService.saveRole(role));
    }

    @PostMapping("/role/addtouser")
    public ResponseEntity<?>addRoleToUser(@RequestBody RoleToUserForm form){
        userService.addRoleToUser(form.getUsername(),form.getRoleName());
        return ResponseEntity.ok().build();
    }

    @GetMapping("/token/refresh")
    public void refreshToken(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String authorizationHeader = request.getHeader(AUTHORIZATION);
        if(authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
            try{
                String refresh_token = authorizationHeader.substring("Bearer ".length());
                Algorithm algorithm = Algorithm.HMAC256("secret".getBytes());
                JWTVerifier verifier = JWT.require(algorithm).build();
                DecodedJWT decodedJWT = verifier.verify(refresh_token);
                String username = decodedJWT.getSubject();
                User user = userService.getUser(username);
                String access_token = JWT.create()
                        .withSubject(user.getUsername())
                        .withExpiresAt(new Date(System.currentTimeMillis()+10*60*1000 ))
                        .withIssuer(request.getRequestURI().toString())
                        .withClaim("roles",user.getRoles().stream().map(Role::getName).collect(Collectors.toList()))
                        .sign(algorithm);
                Map<String, String> tokens = new HashMap<>();
                tokens.put("access_token", access_token);
                tokens.put("refresh_token", refresh_token);
                response.setContentType(MediaType.APPLICATION_JSON_VALUE);
                new ObjectMapper().writeValue(response.getOutputStream(), tokens);
            }catch (Exception exception){
                response.setHeader("errror", exception.getMessage());
                response.setStatus(FORBIDDEN.value());
                //response.sendError(FORBIDDEN.value());
                Map<String, String> error = new HashMap<>();
                error.put("error_message", exception.getMessage());
                response.setContentType(MimeTypeUtils.APPLICATION_JSON_VALUE);
                new ObjectMapper().writeValue(response.getOutputStream(), error);
            }

        } else {
            throw new RuntimeException("Refresh token is missing");
        }
    }
    @GetMapping("/user/edit/{id}")
    public String editUser (@PathVariable("id") Long idUsers, Model model,
                            RedirectAttributes attribute){

        User user = null;

        if(idUsers > 0) {
            user = userService.findById(idUsers);

            if(user == null){
                System.out.println("Error: The indicated Id doesn't exist!");
                attribute.addFlashAttribute("error","Attention: The indicated Id doesn't exist!");
                return "redirect:/views/user/index";
            }
        }else {
            System.out.println("Error:Errors with the Id!");
            attribute.addFlashAttribute("error","Attention: Errors with the Id");
            return "redirect:/views/user/index";
        }


        model.addAttribute("title", "Form: Edit User");
        model.addAttribute("users", user);


        return "/views/user/frmUsers";
    }
    @GetMapping("/user/delete/{id}")
    public String delete (@PathVariable("id") Long idUser, RedirectAttributes attribute){
        User user = null;

        if(idUser > 0) {
            user = userService.findById(idUser);

            if(user == null){
                System.out.println("Error:The indicated Id doesn't exist!");
                attribute.addFlashAttribute("error","Attention: The indicated Id doesn't exist!");
                return "redirect:/views/user/index";
            }
        }else {
            System.out.println("Error: Error with the Id");
            attribute.addFlashAttribute("error","Attention: error with the Id!");
            return "redirect:/views/user/index";
        }

        userService.delete(idUser);
        System.out.println("Successfully deleted!");
        attribute.addFlashAttribute("warning","Successfully deleted!");

        return "redirect:/views/user/index";
    }

    /*<!--@GetMapping("/userAddBooking/{id}")
    public String userAddBooking(Authentication auth, @PathVariable("id") Long idProperties){
        Properties  properties= iPropertiesService.findById(idProperties);

        String username = auth.getName();
        User user = userService.findByUsername(username);

        if (user.getProperties().contains(properties)){
            System.out.println("Duplicated Porperties!");
        }
        else{
            .setSigned(.getSigned()+1);
            propertiesRepository.save();
            System.out.println("Signed Up for the " + .getSigned());
            user.getEvents().add();
            userRepository.save(user);
        }

        return "redirect:/views/users/index";
    }
    @GetMapping("/userRemoveBooking/{id}")
    public String userRemoveBooking(Authentication auth, @PathVariable ("id") Long idEvent){
        Properties properties = iPropertiesService.findById(idEvent);

        String username = auth.getName();
        User user = userService.findByUsername(username);

        if (user.getEvents().contains(properties)){
            properties.setSigned(properties.getSigned()-1);
            eventsRepository.save(properties);
            System.out.println("Event Removed");
            user.getEvents().remove(properties);
            usersRepository.save(user);
        }
        else{
            System.out.println("Event not find");
        }

        return "redirect:/views/users/index";
    }-->*/

}

@Data
class RoleToUserForm{
    private String username;
    private String roleName;
}

