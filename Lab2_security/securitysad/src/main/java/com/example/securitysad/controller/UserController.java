package com.example.securitysad.controller;

import java.security.Principal;

import org.apache.catalina.Role;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.example.securitysad.security.UserValidator;
import com.example.securitysad.service.UserService;

@Controller
public class UserController {
	@Autowired
	private UserService userService;
	
	@Autowired
	private UserValidator userV;
	
	@RequestMapping(path="/home")
	public ModelAndView userDashboard(Principal p) {
		ModelAndView mv = new ModelAndView("home.jsp");
		User u = (User) userService.findByUsername(p.getName());
		mv.addObject("user",u);
		
		for(Role role: u.getRoles()) {
			if(role.getName().equalsIgnoreCase("ROLE_ADMIN")) {
				// ...
			}
			if(role.getName().equalsIgnoreCase("ROLE_USER")) {
				// ...
			}
			if(role.getName().equalsIgnoreCase("ROLE_PREMIUM_USER")) {
				// ...
			}
		}
		return mv;
	}
	
	@RequestMapping(path="/register",method=RequestMethod.GET)
	public String register(Model model) {
		model.addAttribute("user", new User());
		return "register.jsp";
	}
	
	@RequestMapping(path = "/register",method=RequestMethod.POST)
	public String addUser(@Valid @ModelAttribute("user") User user, BindingResult bR) {
		userV.validate(user, bR);
		
		if(bR.hasErrors()) {
			return "register.jsp";
		}
		userService.save(user);
		return "login.jsp";
		
		
	}
	@RequestMapping(path="/login")
	public String login() {
		return "login.jsp";
	}
	@RequestMapping(path="/logout-success")
	public String logout() {
		return "logout.jsp";
	}
}
