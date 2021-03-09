package com.example.midterm.Dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.example.midterm.model.Role;
import com.example.midterm.model.User;

@RepositoryRestResource(collectionResourceRel = "users",path="users")
public interface UserDao extends JpaRepository<User, Integer>{
	User findByUsername(String username);
	
	
	List<Role> getRolesByUsername(String username);
	
	
}
