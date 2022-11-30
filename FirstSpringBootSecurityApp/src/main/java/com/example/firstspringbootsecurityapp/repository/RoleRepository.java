package com.example.firstspringbootsecurityapp.repository;

import com.example.firstspringbootsecurityapp.models.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
}