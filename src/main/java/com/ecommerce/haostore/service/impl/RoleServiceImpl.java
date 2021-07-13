package com.ecommerce.haostore.service.impl;

import com.ecommerce.haostore.model.Role;
import com.ecommerce.haostore.repository.RoleRepository;
import com.ecommerce.haostore.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class RoleServiceImpl implements RoleService {
    @Autowired
    RoleRepository roleRepository;

    public List<Role> getAllRole() {
        return roleRepository.findAll();
    }

    public Optional<Role> findRoleById(int id){
        return roleRepository.findById(id);
    }

}
