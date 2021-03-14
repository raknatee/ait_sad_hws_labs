package com.example.midterm.model;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.MapsId;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotBlank;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Employee {
	
	
	
	

	@Id
	private int id;
	
	@OneToOne(fetch = FetchType.EAGER,cascade = CascadeType.ALL,orphanRemoval = true)
	@JoinColumn(name="user_id",referencedColumnName = "id")
	@JsonIgnore
	@MapsId
	private User user;
	
	@Column(nullable = false)
	@NotBlank(message = "This field is requried.")
	private String name;

	
	
//	@Column(nullable = false)
//	@NotBlank(message = "This field is requried.")
	private Level level;
	
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate birthday;
	
	private BigDecimal baseSalary; 
	
	@ManyToMany(fetch = FetchType.EAGER,cascade=CascadeType.ALL)
	@JsonBackReference
	private List<Address> addresses;
	
	public BigDecimal getSalary() {
		if(level == Level.C1) {
			return this.baseSalary.add(new BigDecimal(200));
		}
		if(level == Level.C2) {
			return this.baseSalary.add(new BigDecimal(500));
		}
		if(level == Level.C3) {
			return this.baseSalary.add(new BigDecimal(1000));
		}
		
		return new BigDecimal(0);
	}
	

}
