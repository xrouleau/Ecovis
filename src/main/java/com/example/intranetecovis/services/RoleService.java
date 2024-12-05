package com.example.intranetecovis.services;

import com.example.intranetecovis.models.Nouvelle;
import com.example.intranetecovis.models.Role;
import com.example.intranetecovis.repositories.IRoleRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RoleService {
    private final IRoleRepository roleRepository;

    public RoleService(IRoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    public List<Role> getAll() {
        return roleRepository.findAll();
    }

    public Role findById(int id) {
        return roleRepository.findById(id).get();
    }

    public Role findByName(String name) {
        return roleRepository.findRoleByName(name);
    }
}
