package com.petvacation.petvacation.controller;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.petvacation.petvacation.domain.Properties;
import com.petvacation.petvacation.domain.Role;
import com.petvacation.petvacation.domain.User;
import com.petvacation.petvacation.repository.PropertiesRepository;
import com.petvacation.petvacation.repository.UserRepository;
import com.petvacation.petvacation.service.IUserService;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.ui.Model;
import org.springframework.util.MimeTypeUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URI;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static org.springframework.http.HttpHeaders.AUTHORIZATION;
import static org.springframework.http.HttpStatus.FORBIDDEN;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class UserController {
    private final IUserService userService;
    private final PropertiesRepository propertiesRepository;
    private final UserRepository userRepository;

    @GetMapping("/user")
    public ResponseEntity<List<User>>getUsers(){
        return ResponseEntity.ok().body(userService.getUsers());
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

    @GetMapping("/user/admin")
    public String listUsers(Model model){
        List<User> listOfUsers = userRepository.findAll();
        model.addAttribute("users", "List of Users");
        model.addAttribute("users", listOfUsers);
        return "/user/admin/list";
    }

    @GetMapping("/user/register")
    public String createNewAccount (Model model) {

        User user = new User();
        model.addAttribute("title", "Form: New User");
        model.addAttribute("user", user);
        return "/api/user/frmRegister";
    }

    //creo que está duplicada pero una llama al service y la otra al repositorio
    @PostMapping("/user/save/")
    public User addUser(@RequestBody User user) {
        return userRepository.save(user);
    }

    @GetMapping("/user/{id}")
    public User findUserById(@PathVariable Long id) {
        return userRepository.findById(id).orElseThrow(UserNotFoundException::new);
    }

    @GetMapping("/user/edit/{id}")
    public String editUser (@PathVariable("id") Long idUsers, Model model,
                            RedirectAttributes attribute){
        User user = null;

        if(idUsers > 0) {
            user =  userRepository.findById(idUsers).orElseThrow(UserNotFoundException::new);

            if(user == null){
                System.out.println("Error: The indicated Id doesn't exist!");
                attribute.addFlashAttribute("error","Attention: The indicated Id doesn't exist!");
                return "redirect:/api/user/index";
            }
        }else {
            System.out.println("Error:Errors with the Id!");
            attribute.addFlashAttribute("error","Attention: Errors with the Id");
            return "redirect:/api/user/index";
        }
        model.addAttribute("title", "Form: Edit User");
        model.addAttribute("user", user);

        return "/api/user/frmRegister";
    }

    @DeleteMapping("/api/user/delete/{id}")
    public User delete (@PathVariable("id") Long id){
       User user = userRepository.findById(id).orElseThrow(UserNotFoundException::new);
        userRepository.delete(user);
        System.out.println("Successfully deleted!");
        return user;
    }

    @PostMapping("/user/properties")
    public ResponseEntity<?> addUserProperties(@RequestBody OwnerPropertiesForm form){
        userService.addUserProperties(form.getUserId(),form.getPropertiesId());
        return ResponseEntity.ok().build();
    }
}

@Data
class RoleToUserForm{
    private String username;
    private String roleName;
}

@Data
class OwnerPropertiesForm{
    private Long userId;
    private Long propertiesId;
}

