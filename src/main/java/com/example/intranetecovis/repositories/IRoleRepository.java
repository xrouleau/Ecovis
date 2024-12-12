package com.example.intranetecovis.repositories;

import com.example.intranetecovis.models.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IRoleRepository  extends JpaRepository<Role, Integer> {
}
