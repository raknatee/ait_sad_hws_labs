package com.example.midterm.Controller;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.ModelAndView;

import com.example.midterm.Dao.AddressDao;
import com.example.midterm.Dao.EmployeeDao;
import com.example.midterm.Dao.UserDao;
import com.example.midterm.Service.EmployeeService;
import com.example.midterm.Service.MyUserDetailsService;
import com.example.midterm.model.Address;
import com.example.midterm.model.Employee;
import com.example.midterm.model.User;

import lombok.Getter;

@Controller
public class UserController {

	@Autowired
	private UserDao userDao;

	@Autowired
	private EmployeeDao empDao;

	@Autowired
	private MyUserDetailsService userService;

	@Autowired
	private EmployeeService empService;

	@RequestMapping(path = "/", method = RequestMethod.GET)
	public String root() {

		return "redirect:/who";

	}

	@RequestMapping(path = "/who", method = RequestMethod.GET)
	public String who() {

		String username = SecurityContextHolder.getContext().getAuthentication().getName();
		boolean isAdmin = userService.isAdminByUsername(username);
		
		if (isAdmin) {
			return "redirect:/admin";
		} else {
			return "redirect:/user";
		}
	}
	
	@RequestMapping(path = "/user", method = RequestMethod.GET)
	public ModelAndView userHome() {
		ModelAndView mv = new ModelAndView("/user/my_info.jsp");
		
		String username = SecurityContextHolder.getContext().getAuthentication().getName();
		User user = userDao.findByUsername(username);
		
		mv.addObject("user",user);
		return mv;
	}
	@Getter
	class BDO{
		private int id;
		@DateTimeFormat(pattern = "yyyy-MM-dd")
		private LocalDate birthday;
		public BDO(int id, LocalDate birthday) {
			super();
			this.id = id;
			this.birthday = birthday;
		}
		
	}
	@RequestMapping(path = "/user/edit_bdo", method = RequestMethod.POST)
	public String edit_bdo(BDO input) {
		
//		check authz
		String username = SecurityContextHolder.getContext().getAuthentication().getName();
		User user = userDao.findByUsername(username);
		
		if(user.getEmp().getId()==input.getId()) {
			
			user.getEmp().setBirthday(input.getBirthday());
			userDao.save(user);
		}else {
			throw new ResponseStatusException(
			          HttpStatus.BAD_REQUEST, "Unauthz");
		}
		
		
		return "redirect:/user";
	}
	
	@Autowired
	private AddressDao addressDao; 
	
	
	@RequestMapping(path = "/user/remove_address", method = RequestMethod.GET)
	public String remove_address(int id) {
		
//		TODO authz
		String username = SecurityContextHolder.getContext().getAuthentication().getName();
	
		if(addressDao.countAddressByUsername(username)>1) {
			
			addressDao.deleteById(id);
		}else {
			throw new ResponseStatusException(
			          HttpStatus.BAD_REQUEST, "address must be more than 1");
		}
		
		return "redirect:/user";
	}
	
	@RequestMapping(path = "/user/add_address", method = RequestMethod.POST)
	public String add_address(Address address) {
		
		String username = SecurityContextHolder.getContext().getAuthentication().getName();
		User user = userDao.findByUsername(username);
		user.getEmp().getAddresses().add(address);
		
		userDao.save(user);
		
		return "redirect:/user";
	}
	
}
