package com.example.securitysad.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.securitysad.model.Role;

public interface RoleJPADao extends JpaRepository<Role, Integer>{

}
