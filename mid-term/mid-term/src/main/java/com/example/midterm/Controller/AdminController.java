package com.example.midterm.Controller;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.example.midterm.Dao.EmployeeDao;
import com.example.midterm.Dao.UserDao;
import com.example.midterm.Service.EmployeeService;
import com.example.midterm.model.Address;
import com.example.midterm.model.Employee;
import com.example.midterm.model.Level;
import com.example.midterm.model.Role;
import com.example.midterm.model.User;

import lombok.Getter;
import lombok.Setter;

@Controller
public class AdminController {

	@Autowired
	private UserDao userDao;
	
	@Autowired
	private EmployeeDao empDao;
	
	@Autowired
	private EmployeeService empService;
	
	@RequestMapping(path = "/admin",method=RequestMethod.GET)
	public ModelAndView adminHome() {

			ModelAndView mv = new ModelAndView("admin_home.jsp");
		
			List<Employee> employees = empService.getAll();
			
			mv.addObject("employees",employees);
			
			return mv;
		
	}
	
	@RequestMapping(path = "/admin/add_employee",method=RequestMethod.GET)
	public ModelAndView add_employee() {

		ModelAndView mv = new ModelAndView("/admin/add_form.jsp");
			
		
			return mv;
		
	}
	
	@Getter
	@Setter
	class ObjAdd{
		private String username;
		private String password;
		private String name;
		private Level level;
		@DateTimeFormat(pattern = "yyyy-MM-dd")
		private LocalDate birthday;
		private BigDecimal baseSalary;
	
		private String city;
		private String street;
		private String houseNo;
		private String zipcode;
		public ObjAdd(String username, String password, String name, Level level, LocalDate birthday,
				BigDecimal baseSalary, String city, String street, String houseNo, String zipcode) {
			super();
			this.username = username;
			this.password = password;
			this.name = name;
			this.level = level;
			this.birthday = birthday;
			this.baseSalary = baseSalary;
			this.city = city;
			this.street = street;
			this.houseNo = houseNo;
			this.zipcode = zipcode;
		}
		
		
	}
	
	
	@RequestMapping(path = "/admin/add_employee",method=RequestMethod.POST)
	public String add_employee_submit(ObjAdd input) {

		
		Address a1 = new Address();
		a1.setCity(input.getCity());
		a1.setStreet(input.getStreet());
		a1.setHouseNo(input.getHouseNo());
		a1.setZipcode(input.getZipcode());
		List<Address> listAddress = new ArrayList<>();
		listAddress.add(a1);
		
		
		Employee emp = new Employee();
		emp.setName(input.getName());
		emp.setLevel(input.getLevel());
		emp.setBirthday(input.getBirthday());
		emp.setBaseSalary(input.getBaseSalary());
		emp.setAddresses(listAddress);
	
		Set<Role> setRoleUser1 = new HashSet<Role>();
		
		Role r = new Role();
		r.setName("ROLE_USER");
		setRoleUser1.add(r);
		User user = new User();
		user.setUsername(input.getUsername());
		
		
		String hashpassword = new BCryptPasswordEncoder().encode(input.getPassword());
		user.setPassword(hashpassword);
		user.setRoles(setRoleUser1);
		user.setEmp(emp);
		emp.setUser(user);
		
		userDao.save(user);
			
		
			return "redirect:/admin";
		
	}
	
	@RequestMapping(path = "/admin/edit_employee",method=RequestMethod.GET)
	public ModelAndView edit_employee(@RequestParam int id) {

			ModelAndView mv = new ModelAndView("/admin/employee_form.jsp");
		
			Employee emp = empDao.findById(id);
			
			mv.addObject("e",emp);
			mv.addObject("form_action","/admin/edit_employee");
		
			return mv;
		
	}
	
	@Getter
	@Setter
	class MyObj{
		private int id;
		private Level level;
		private String name;
		private BigDecimal baseSalary;
		public MyObj(int id, Level level, String name, BigDecimal baseSalary) {
			super();
			this.id = id;
			this.level = level;
			this.name = name;
			this.baseSalary = baseSalary;
		}
		
	}
	@RequestMapping(path = "/admin/edit_employee",method=RequestMethod.POST)
	public String edit_employee_submit(MyObj e) {

			Employee emp = empDao.findById(e.id);
			
			emp.setLevel(e.getLevel());
			emp.setName(e.getName());
			emp.setBaseSalary(e.getBaseSalary());
			
//			System.out.println("save "+ e.getId());
			empDao.save(emp);
			
		
			return "redirect:/admin";
		
	}
	
	@RequestMapping(path = "/admin/test_user",method=RequestMethod.GET)
	public String addUser1() {
		
		Address a1 = new Address();
		a1.setCity("BKK");
		a1.setStreet("SS");
		a1.setHouseNo("192");
		a1.setZipcode("12120");
		List<Address> listAddress = new ArrayList<>();
		listAddress.add(a1);
		
		Employee empUser1 = new Employee();
		empUser1.setName("Employee1");
		empUser1.setLevel(Level.C2);
		empUser1.setBirthday(LocalDate.parse("2017-06-15"));
		empUser1.setBaseSalary(new BigDecimal(100));
		empUser1.setAddresses(listAddress);
		Set<Role> setRoleUser1 = new HashSet<Role>();
		
		Role r = new Role();
		r.setName("ROLE_USER");
		setRoleUser1.add(r);
		User user1 = new User();
		user1.setUsername("user1");
		user1.setPassword("$2a$10$Go1kUBzjXe2B9m/RvgnTbOF6lLq/UmO6OYGWnjae/0fia5p31P4vi");
		user1.setRoles(setRoleUser1);
		user1.setEmp(empUser1);
		empUser1.setUser(user1);
		
		userDao.save(user1);
		return "redirect:/admin";
	}
}
