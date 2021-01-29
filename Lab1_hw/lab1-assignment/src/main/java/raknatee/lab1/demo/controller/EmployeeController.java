package raknatee.lab1.demo.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import raknatee.lab1.demo.dao.EmployeeDAO;
import raknatee.lab1.demo.model.Employee;


@Controller
public class EmployeeController {

	@Autowired
	EmployeeDAO dao;
	
	@RequestMapping(path = "/", method = RequestMethod.GET)
	public ModelAndView homeFunc() {
		ModelAndView mv = new ModelAndView("home.jsp");
		mv.addObject("employeeData",dao.findAllByOrderByNetDes());
		return mv;
	}
	@RequestMapping(path = "/employee/delete", method = RequestMethod.GET)
	public String employeeDelFunc(@RequestParam int uid) {
		dao.deleteById(uid);
		return "redirect:/";
	}
	
	
	@RequestMapping(path = "/employee/form", method = RequestMethod.GET)
	public ModelAndView employeeFormFunc(@RequestParam int uid) {
		Employee e = dao.getOne(uid);
		ModelAndView mv = new ModelAndView("employee_form.jsp");
		mv.addObject("e",e);
		return mv;
	}
	
	@RequestMapping(path = "/employee", method = RequestMethod.POST)
//	@ResponseBody
	public String employeePostFunc(Employee e) {
//		return e.toString();
		dao.save(e);
		return "redirect:/";
	}

	
}
