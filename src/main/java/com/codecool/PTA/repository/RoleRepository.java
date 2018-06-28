package com.codecool.PTA.repository;

import com.codecool.PTA.model.role.Role;
import org.springframework.data.repository.CrudRepository;

public interface RoleRepository extends CrudRepository {

    public Role findByRole(String role);
}
