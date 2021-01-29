package raknatee.lab1.demo.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import raknatee.lab1.demo.model.Employee;


public interface EmployeeDAO extends JpaRepository<Employee, Integer>{
	
	@Query("from Employee order by (value-salary) Desc")
	List<Employee> findAllByOrderByNetDes();
}
