package raknatee.lab1.demo.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Min;
import javax.validation.constraints.Max;
enum GenderType{
	Male,Female;
}

@Entity
@Table(name="employee")
public class Employee {
	
	@Id
	@Column(name = "id")
	private int id;
	@Column(name = "full_name")
	private String fullName;
	@Enumerated(EnumType.STRING)
	
	@Column(name = "gender")
	private GenderType gender;	
	
	@Column(name = "address")
	private String address;
	
	@Column(name = "salary")
	private double salary;
	@Override
	public String toString() {
		return "Employee [id=" + id + ", fullName=" + fullName + ", gender=" + gender + ", address=" + address
				+ ", salary=" + salary + ", value=" + value + ", positionLevel=" + positionLevel + "]";
	}

	@Column(name = "value")
	private double value;

	@Min(1)
	@Max(4)
	@Column(name = "position_level")
	private int positionLevel;
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public GenderType getGender() {
		return gender;
	}

	public void setGender(GenderType gender) {
		this.gender = gender;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public double getSalary() {
		return salary;
	}

	public void setSalary(double salary) {
		this.salary = salary;
	}

	public double getValue() {
		return value;
	}

	public void setValue(double value) {
		this.value = value;
	}

	public int getPositionLevel() {
		return positionLevel;
	}

	public void setPositionLevel(int positionLevel) {
		this.positionLevel = positionLevel;
	}
	
	
	
}
