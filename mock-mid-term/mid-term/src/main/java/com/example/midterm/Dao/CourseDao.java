package com.example.midterm.Dao;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.midterm.model.Course;
import com.example.midterm.model.PaidModel;
public interface CourseDao extends JpaRepository<Course,Integer> {
//	@Query("from Course")
//	List<Course> getCourseNames();
	List<Course> findByType(PaidModel type);
	
	
	
	
	@Query(value = "select c.* from \r\n"
			+ "(select * from user as a join user_enrolled_course as b on a.id = b.users_id  where a.username=?1 ) as table1 right join course as c on table1.ENROLLED_COURSE_ID  = c.id where table1.id is null",nativeQuery = true)
	List<Course> findUnenrolledCourse(String username);
	
	@Query(value = "select c.* from \r\n"
			+ "(select * from user as a join user_enrolled_course as b on a.id = b.users_id  where a.username=?1 ) as table1 join course as c on table1.ENROLLED_COURSE_ID  = c.id",nativeQuery = true)
	List<Course> findEnrolledCourse(String username);
	
	@Query(value = "select c.* from \r\n"
			+ "(select * from user as a join user_enrolled_course as b on a.id = b.users_id  where a.username=?1 ) as table1 right join course as c on table1.ENROLLED_COURSE_ID  = c.id where table1.id is null and c.type=?2",nativeQuery = true)
	List<Course> findUnenrolledCourse(String username,PaidModel type);
	

}
