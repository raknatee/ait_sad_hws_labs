package com.example.finalexam.Service;

import javax.transaction.Transactional;

import org.hibernate.dialect.lock.PessimisticEntityLockException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import com.example.finalexam.Dao.ProductDAO;
import com.example.finalexam.model.Product;

@Service
public class ProductService {

	@Autowired
	ProductDAO pDAO;
	
	@Transactional
	@Async
	public void buyPessimistic(int id, int n){
		Product p = null;
		try {
			p = pDAO.findProductForPessimistic(id);
			
		}catch(Exception e) {
			System.err.println("Pessimistic LOCKED");
		}
		
		p.decreaseStock(n);
		pDAO.save(p);
		
	}
	
	@Transactional
	@Async
	public void buyOptimistic(int id, int n){
		Product p = null;
		try {
			p = pDAO.findProductForOptimistic(id);
			
		}catch(Exception e) {
			System.err.println("Optimistic LOCKED");
		}
		
		p.decreaseStock(n);
		pDAO.save(p);
		
	}
	
	
}
