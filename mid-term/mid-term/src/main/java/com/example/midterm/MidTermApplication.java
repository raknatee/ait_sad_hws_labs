package com.example.midterm;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import com.example.midterm.Dao.UserDao;
import com.example.midterm.Service.TestService;
import com.example.midterm.model.Address;
import com.example.midterm.model.Employee;
import com.example.midterm.model.Level;
import com.example.midterm.model.Role;
import com.example.midterm.model.User;



@SpringBootApplication
public class MidTermApplication {
	
//	@Autowired
//	private TestService ts;

	public static void main(String[] args) {
		SpringApplication.run(MidTermApplication.class, args);
		
		
	}
	


}
