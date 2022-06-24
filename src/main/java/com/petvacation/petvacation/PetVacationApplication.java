package com.petvacation.petvacation;

import com.petvacation.petvacation.domain.Role;

import com.petvacation.petvacation.domain.User;
import com.petvacation.petvacation.service.IUserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.ArrayList;


@SpringBootApplication
public class PetVacationApplication {

    public static void main(String[] args) {
        SpringApplication.run(PetVacationApplication.class, args);
    }

    @Bean
    PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

   /* @Bean
    CommandLineRunner run(IUserService iUserService) {
        return args -> {
            iUserService.saveRole(new Role(null, "ROLE_USER"));
            iUserService.saveRole(new Role(null, "ROLE_MANAGER"));
            iUserService.saveRole(new Role(null, "ROLE_OWNER"));
            iUserService.saveRole(new Role(null, "ROLE_ADMIN"));
            iUserService.saveRole(new Role(null, "ROLE_SUPER_ADMIN"));
            iUserService.saveRole(new Role(null, "ROLE_CARETAKER"));

            iUserService.saveUser((new User(null, "Glaucia Mesquita", "glauciamesquitagmm@hotmail.com", "Glauciagmm","123456",  new ArrayList<>())));
            iUserService.saveUser((new User(null, "Lupe Flores", "lupe@hotmail.com", "Lupe","78912", new ArrayList<>())));
            iUserService.saveUser((new User(null, "Patricia Muino", "patri@hotmail.com", "Patri","34567", new ArrayList<>())));
            iUserService.saveUser((new User(null, "Eli Ildeva", "eli@hotmail.com", "Eli","56894", new ArrayList<>())));
            iUserService.saveUser((new User(null, "Francisco Dominguez", "fran@hotmail.com","Fran","99999",  new ArrayList<>())));

            iUserService.addRoleToUser("Glauciagmm","ROLE_ADMIN");
            iUserService.addRoleToUser("Glauciagmm","ROLE_USER");
            iUserService.addRoleToUser("Glauciagmm","ROLE_OWNER");
            iUserService.addRoleToUser("Glauciagmm","ROLE_SUPER_ADMIN");
            iUserService.addRoleToUser("Eli","ROLE_USER");
            iUserService.addRoleToUser("Lupe","ROLE_ADMIN");
            iUserService.addRoleToUser("Fran","ROLE_CARETAKER");
            iUserService.addRoleToUser("Patri","ROLE_OWNER");
        };
    }*/
}
