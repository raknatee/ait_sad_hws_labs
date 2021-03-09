package com.example.midterm.Service;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.midterm.Dao.CourseDao;
import com.example.midterm.Dao.UserDao;
import com.example.midterm.model.Course;
import com.example.midterm.model.PaidModel;
import com.example.midterm.model.Role;
import com.example.midterm.model.User;

@Service
public class MyUserDetailsService implements UserDetailsService{

	@Autowired
	private UserDao userDao;
	@Autowired
	private CourseDao courseDao;
	
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		User user = userDao.findByUsername(username);
		if(user == null) {
			throw new UsernameNotFoundException("User 404");
		}
		return new MyUserDetails(user);
	}
	
	public boolean isAdminByUsername(String username) {
		User user = userDao.findByUsername(username);
		return user.hasRole("ROLE_ADMIN");
	}
	
	public Set<Role> getRolesByUsername(String username){
	    User user = userDao.findByUsername(username);
		return user.getRoles();
	}
	
	public List<Course> getUnenrolledCourse(String username){
		User user = userDao.findByUsername(username);
		
		PaidModel type = user.getCourseType();
		List<Course> enrolled_courses;
		if(type==PaidModel.PAID) {
			enrolled_courses = courseDao.findUnenrolledCourse(username);
		}else {
			enrolled_courses = courseDao.findUnenrolledCourse(username,PaidModel.FREE);
		}
		return enrolled_courses;
	}
	public List<Course> getEnrolledCourse(String username){
		List<Course> c = courseDao.findEnrolledCourse(username);
		return c;
	}

}
