package com.example.midterm.Controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.example.midterm.Dao.CourseDao;
import com.example.midterm.Dao.UserDao;
import com.example.midterm.Service.CourseService;
import com.example.midterm.Service.MyUserDetails;
import com.example.midterm.Service.MyUserDetailsService;
import com.example.midterm.model.Course;
import com.example.midterm.model.PaidModel;
import com.example.midterm.model.Role;
import com.sun.el.stream.Stream;

@Controller
public class CourseController {

	@Autowired
	private CourseService courseService;
	
	@Autowired
	private MyUserDetailsService userService;

	@Autowired
	private CourseDao courseDao;

	@RequestMapping(path = "/course",method=RequestMethod.GET)
		public ModelAndView courseList() {
			ModelAndView mv = new ModelAndView("course_home.jsp");
			List<Course> all_courses = courseService.getAllCourse();
			
			
			String username = SecurityContextHolder.getContext().getAuthentication().getName();
			boolean isAdmin =  userService.isAdminByUsername(username);
			mv.addObject("isAdmin",isAdmin);
			if(isAdmin) {
				mv.addObject("list_course",all_courses);
			}else {
				List<Course> unenrolledCourse = userService.getUnenrolledCourse(username);
				List<Course> enrolledCourse = userService.getEnrolledCourse(username);
				
				mv.addObject("list_unenrolledCourse",unenrolledCourse);
				mv.addObject("list_enrolledCourse",enrolledCourse);
			}
			
			
			return mv;
		}
	
	@RequestMapping(path = "/admin/add_course",method=RequestMethod.GET)
	public ModelAndView addCourse() {
		ModelAndView mv = new ModelAndView("course_form.jsp");
		mv.addObject("form_action","/admin/add_course");
		return mv;
	}
	@RequestMapping(path = "/admin/add_course",method=RequestMethod.POST)
	public String addCourseSubmit(Course course) {
		courseDao.save(course);
		return "redirect:/course";
	}
	@RequestMapping(path = "/admin/edit_course",method=RequestMethod.GET)
	public ModelAndView editCourse(@RequestParam int id) {
		ModelAndView mv = new ModelAndView("course_form.jsp");
		Course course = courseDao.getOne(id);

		mv.addObject("form_action","/admin/edit_course?id"+id);
		mv.addObject("e",course);
		
		
		return mv;
	}
	@RequestMapping(path = "/admin/edit_course",method=RequestMethod.POST)
	public String editCourseSubmit(Course course) {
		courseDao.save(course);
		return "redirect:/course";
	}
}
