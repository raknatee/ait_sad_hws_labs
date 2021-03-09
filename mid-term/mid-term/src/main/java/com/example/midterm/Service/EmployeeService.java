package com.example.midterm.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.midterm.Dao.EmployeeDao;
import com.example.midterm.model.Employee;

@Service
public class EmployeeService {
	@Autowired
	private EmployeeDao empDao;
	
	public List<Employee> getAll(){
		return empDao.findAll();
	}

}
