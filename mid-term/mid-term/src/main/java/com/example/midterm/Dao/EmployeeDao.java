package com.example.midterm.Dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.midterm.model.Employee;

public interface EmployeeDao extends JpaRepository<Employee, Integer>{

	Employee findById(int id);
}
