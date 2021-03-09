package com.example.midterm.Dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.midterm.model.Address;
public interface AddressDao extends JpaRepository<Address, Integer>{

	@Query(value="  select count(*) from (employee as e join employee_addresses as f on e.user_id =f.employees_user_id) as t  join user as u on u.id=t.user_id where username = ?1",nativeQuery=true)
	public int countAddressByUsername(String Username);
}