package com.codecool.PTA.repository;

import com.codecool.PTA.model.role.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

public interface RoleRepository extends JpaRepository<Role, Long> {

    public Role findByRole(String role);

}
