package com.petvacation.petvacation.repository;

import com.petvacation.petvacation.domain.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository <Role, Long> {
    public Role findByName(String name);
}
