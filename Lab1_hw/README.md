# Lab 1

This lab, I create basic web app by Spring Boot with H2 database.

## Screenshots

![](https://github.com/raknatee/ait_sad_hws_labs/blob/master/Lab1_hw/screenshots/home.PNG)

***

![](https://github.com/raknatee/ait_sad_hws_labs/blob/master/Lab1_hw/screenshots/form.PNG)

## Code

### add more dependencies

#### pom.xml
```xml
    <!-- For Employee Model validation (such as Min Max func) -->
    <dependency>
        <groupId>javax.validation</groupId>
        <artifactId>validation-api</artifactId>
        <version>2.0.1.Final</version>
    </dependency>

    <!-- For loop over JSP -->
	<!-- https://mvnrepository.com/artifact/jstl/jstl -->
	<dependency>
			<groupId>jstl</groupId>
			<artifactId>jstl</artifactId>
			<version>1.2</version>
	</dependency>
```

### Create Enum for gender of employee and Min Max for Position Level
#### Employee.java
```java
import javax.validation.constraints.Min;
import javax.validation.constraints.Max;

enum GenderType{
	Male,Female;
}

@Entity
@Table(name="employee")
public class Employee {

	...

	@Enumerated(EnumType.STRING)
	@Column(name = "gender")
	private GenderType gender;	

    ...

    @Min(1)
	@Max(4)
	@Column(name = "position_level")
	private int positionLevel;

    ...

}
```

### Create Home and Delete employee

#### EmployeeController.java
```java
@Controller
public class EmployeeController {

	@Autowired
	EmployeeDAO dao;
	
	@RequestMapping(path = "/", method = RequestMethod.GET)
	public ModelAndView homeFunc() {
		ModelAndView mv = new ModelAndView("home.jsp");
		mv.addObject("employeeData",dao.findAllByOrderByNetDes());
		return mv;
	}
	@RequestMapping(path = "/employee/delete", method = RequestMethod.GET)
	public String employeeDelFunc(@RequestParam int uid) {
		dao.deleteById(uid);
		return "redirect:/";
	}
}
```

#### EmployeeDAO.java
```java
public interface EmployeeDAO extends JpaRepository<Employee, Integer>{
	
	@Query("from Employee order by (value-salary) Desc")
	List<Employee> findAllByOrderByNetDes();
}
```

#### home.jsp 

1. add
```html
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
```
2. html
```html
<div class=main_grid>
		<c:forEach items="${employeeData}" var="e">
			<div class="container box_shadow ${ (e.value-e.salary>0)?"positive_net_value":"minus_net_value"}">
				<p><c:out value="${e.id}"/> | <c:out value="${e.fullName}"/></p>
				<p>Gender: <c:out value="${e.gender}"/></p>
				<p>Address: <c:out value="${e.address}"/></p>
				<p>Salary: <c:out value="${e.salary}"/> | Value: <c:out value="${e.value}"/></p> 
				<p>Net: <c:out value="${e.value-e.salary }"/>
				<p>Position Level: <c:out value="${e.positionLevel}"/></p>
				<a href="/employee/form?uid=${e.id}"><img class="my_icon1 edit_icon" alt="edit" src="icon/edit.png"></a>
				<a href="/employee/delete?uid=${e.id} "><img class="my_icon1 del_icon" alt="edit" src="icon/delete.png"></a>
			</div>
		</c:forEach>
</div>
```

### Create Form POST method 

employee_form.jsp
```html
<form class="main_form box_shadow" action="/employee" method="post">
		<div class="uid_box">
			<p>ID:${e.id}</p>
			<input type="hidden" name="id" value="${e.id}">
		</div>
		<div class="info_box">
			<label for="fullName"><p class="title">Full Name</p></label> <input
				type="text" name="fullName" id="fullName" value="${e.fullName}">
		</div>
		
        ...
</form>

```

EmployeeController.java
```java
    // for getting the form 
	@RequestMapping(path = "/employee/form", method = RequestMethod.GET)
	public ModelAndView employeeFormFunc(@RequestParam int uid) {
		Employee e = dao.getOne(uid);
		ModelAndView mv = new ModelAndView("employee_form.jsp");
		mv.addObject("e",e);
		return mv;
	}
	
    // for summit
	@RequestMapping(path = "/employee", method = RequestMethod.POST)
	public String employeePostFunc(Employee e) {
		dao.save(e);
		return "redirect:/";
	}

```

