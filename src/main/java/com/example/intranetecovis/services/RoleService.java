package com.example.intranetecovis.services;

import com.example.intranetecovis.models.Nouvelle;
import com.example.intranetecovis.models.Role;
import com.example.intranetecovis.repositories.IRoleRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/***
 * Classe du Service des roles
 */
@Service
public class RoleService {
    // déclaration des variables de classe
    private final IRoleRepository roleRepository;

    /***
     * Constructeur de la classe RoleService
     * @param roleRepository
     */
    public RoleService(IRoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    /***
     * Trouve un role selon son id
     * @param id id du role à trouver
     * @return le Role
     */
    public Role findById(int id) {
        return roleRepository.findById(id).get();
    }
}
