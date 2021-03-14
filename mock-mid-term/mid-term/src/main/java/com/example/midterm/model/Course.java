package com.example.midterm.model;


import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

import org.springframework.format.annotation.DateTimeFormat;





enum Discipline{
	SCIENCE,ENGINEERING,BUSINESS;
}



@Entity
public class Course{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	private String name;
	@Enumerated(EnumType.STRING)
	private Discipline disciplines;
	
	@Enumerated(EnumType.STRING)
	private PaidModel type;
	
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate dateOffered;
	private BigDecimal revenueGenerated; 
	
	@ManyToMany(mappedBy = "enrolledCourse")
	private Set<User> users;
	
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Discipline getDisciplines() {
		return disciplines;
	}
	public void setDisciplines(Discipline disciplines) {
		this.disciplines = disciplines;
	}
	public PaidModel getType() {
		return type;
	}
	public void setType(PaidModel type) {
		this.type = type;
	}
	public LocalDate getDateOffered() {
		return dateOffered;
	}
	public void setDateOffered(LocalDate dateOffered) {
		this.dateOffered = dateOffered;
	}
	public BigDecimal getRevenueGenerated() {
		return revenueGenerated;
	}
	public void setRevenueGenerated(BigDecimal revenueGenerated) {
		this.revenueGenerated = revenueGenerated;
	}
	@Override
	public String toString() {
		return "Course [id=" + id + ", name=" + name + ", disciplines=" + disciplines + ", type=" + type
				+ ", dateOffered=" + dateOffered + ", revenueGenerated=" + revenueGenerated + "]";
	}
}
