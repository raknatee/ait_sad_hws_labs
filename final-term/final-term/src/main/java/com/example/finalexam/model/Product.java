package com.example.finalexam.model;

import java.math.BigDecimal;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Version;

import lombok.Data;
import lombok.ToString;

@Entity
@Data
public class Product {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	private String name;
	
	@Version
	private Long version;
	
	@ManyToOne(fetch = FetchType.EAGER)
	private Company company;
	
	@ManyToOne(fetch = FetchType.EAGER)
	private Category category;
	
	private BigDecimal price;
	private int stock;

	
	class StockException extends RuntimeException{
		private static final long serialVersionUID = 1L;

		public String toString() {
			return "stock must be >= 0";
		}
	}
	public void decreaseStock(int n) {
		if(this.stock-n >=0) {
			this.stock-=n;
		}else {
			throw new StockException();
		}
		
	}
	public String toString() {
		return this.id + " " + this.stock;
	}
	
}
