package com.example.midterm.Dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.midterm.model.Role;

public interface RoleDao extends JpaRepository<Role, Integer>{

	
}
