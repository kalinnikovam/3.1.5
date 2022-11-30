package com.example.firstspringbootsecurityapp.service.imps;


import com.example.firstspringbootsecurityapp.models.Role;
import com.example.firstspringbootsecurityapp.repository.RoleRepository;
import com.example.firstspringbootsecurityapp.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class RoleServiceImp implements RoleService {

    private final RoleRepository repository;

    @Autowired
    public RoleServiceImp(RoleRepository repository) {
        this.repository = repository;
    }

    @Override
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public List<Role> getAllRoles() {
        return repository.findAll();
    }
}


