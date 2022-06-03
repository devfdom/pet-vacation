package com.petvacation.petvacation.controller;

import com.petvacation.petvacation.domain.Role;
import com.petvacation.petvacation.domain.User;
import com.petvacation.petvacation.service.IUserService;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class UserController {
    private final IUserService iUserService;

    @GetMapping("/users")
    public ResponseEntity<List<User>>getUsers(){
        return ResponseEntity.ok().body(iUserService.getUsers());
    }

    @PostMapping("/user/save")
    public ResponseEntity<User>saveUser(@RequestBody User user){
        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/user/save").toUriString());
        return ResponseEntity.created(uri).body(iUserService.saveUser(user));
    }

    @PostMapping("/role/save")
    public ResponseEntity<Role>saveRole(@RequestBody Role role){
        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/role/save").toUriString());
        return ResponseEntity.created(uri).body(iUserService.saveRole(role));
    }

    @PostMapping("/role/addtouser")
    public ResponseEntity<?>addRoleToUser(@RequestBody RoleToUserForm form){
        iUserService.addRoleToUser(form.getUsername(),form.getRoleName());
        return ResponseEntity.ok().build();
    }


}

    @Data
    class RoleToUserForm{
        private String username;
        private String roleName;

    }

