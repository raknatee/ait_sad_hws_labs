package com.example.finalexam.Dao;

import java.util.List;

import javax.persistence.LockModeType;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.finalexam.model.Product;

public interface ProductDAO extends JpaRepository<Product, Integer>{
	
	List<Product> findAll(); 
	
	@Lock(LockModeType.PESSIMISTIC_WRITE)
	@Query("select p from Product p where id= :id")
	Product findProductForPessimistic(@Param("id") int id);
	
	@Lock(LockModeType.OPTIMISTIC_FORCE_INCREMENT)
	@Query("select p from Product p where id= :id")
	Product findProductForOptimistic(@Param("id") int id);

}
