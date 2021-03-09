package com.example.midterm.Controller;



import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;


@Controller
public class HomeController {
	@RequestMapping(path = "/login",method=RequestMethod.GET)
	public String login() {
		return "login.jsp";
	}
}
