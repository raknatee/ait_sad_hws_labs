package com.example.midterm.Service;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.midterm.Dao.CourseDao;
import com.example.midterm.model.Course;

@Service
public class CourseService{

	@Autowired
	private CourseDao courseDao;
	
	public List<Course> getAllCourse(){
		return courseDao.findAll();
	}
}
