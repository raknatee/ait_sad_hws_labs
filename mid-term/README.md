# Mid-Term

## Screenshot

### User Profile

![alt text](https://raw.githubusercontent.com/raknatee/ait_sad_hws_labs/master/mid-term/image/user1.PNG)

### Admin Profile

![alt text](https://raw.githubusercontent.com/raknatee/ait_sad_hws_labs/master/mid-term/image/admin1.PNG)

### Admin add new User

![alt text](https://raw.githubusercontent.com/raknatee/ait_sad_hws_labs/master/mid-term/image/admin_add_user.PNG)

### Admin edit User information

![alt text](https://raw.githubusercontent.com/raknatee/ait_sad_hws_labs/master/mid-term/image/add_edit_user.PNG)

## Model

### Address

```java
@Entity
@Getter
@Setter

public class Address {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Column(nullable = false)
	@NotBlank(message = "This field is requried.")
	private String city;

	@Column(nullable = false)
	@NotBlank(message = "This field is requried.")
	private String street;

	@Column(nullable = false)
	@NotBlank(message = "This field is requried.")
	private String houseNo;

	@Column(nullable = false)
	@NotBlank(message = "This field is requried.")
	private String zipcode;

	@ManyToMany(mappedBy = "addresses")
	private Set<Employee> employees;

}

```

### Employee
```java
@Entity
@Getter
@Setter
public class Employee {

	@Id
	private int id;

	@OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval = true)
	@JoinColumn(name = "user_id", referencedColumnName = "id")
	@JsonIgnore
	@MapsId
	private User user;

	@Column(nullable = false)
	@NotBlank(message = "This field is requried.")
	private String name;

	private Level level;

	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate birthday;

	private BigDecimal baseSalary;

	@ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JsonBackReference
	private List<Address> addresses;

	public BigDecimal getSalary() {
		if (level == Level.C1) {
			return this.baseSalary.add(new BigDecimal(200));
		}
		if (level == Level.C2) {
			return this.baseSalary.add(new BigDecimal(500));
		}
		if (level == Level.C3) {
			return this.baseSalary.add(new BigDecimal(1000));
		}

		return new BigDecimal(0);
	}

}
```

### Level
```java
public enum Level {
	C1,C2,C3;
}
```

### Role
```java
@Entity
@Data
@Table(name="role")
@NoArgsConstructor
public class Role {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	private String name;
	
	@ManyToMany(mappedBy = "roles")
	private Set<User> users;
	
	public boolean equals(String role) {
		return this.name.equals(role);
	}

}
```
### User
```java
@Entity
@Getter
@Setter
@NoArgsConstructor

public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Column(nullable = false)
	@NotBlank(message = "This field is requried.")
	private String username;

	@NotBlank(message = "This field is required.")
	@Column(nullable = false)
	private String password;

	@ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JsonBackReference
	private Set<Role> roles;

	@OneToOne(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
	private Employee emp;

	public boolean hasRole(String role) {
		for (Role r : this.roles) {
			if (r.equals(role)) {
				return true;
			}
		}
		return false;
	}

}
```
## Security Config

```java
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable()
		.authorizeRequests()
			.antMatchers("/admin/**").hasAnyRole("ADMIN")
			.antMatchers("/user/**").hasAnyRole("USER")
			.antMatchers("/h2-console/**","/login").permitAll()

			.antMatchers("/**").hasAnyRole("ADMIN","USER")
		.and()
		.formLogin()
			.loginPage("/login").permitAll()
			.defaultSuccessUrl("/who",true)
			;
		http.headers().frameOptions().disable();
	}
```

## User Controller

```java
@RequestMapping(path = "/user", method = RequestMethod.GET)
	public ModelAndView userHome() {
		ModelAndView mv = new ModelAndView("/user/my_info.jsp");

		String username = SecurityContextHolder.getContext().getAuthentication().getName();
		User user = userDao.findByUsername(username);

		mv.addObject("user",user);
		return mv;
	}
	@Getter
	class BDO{
		private int id;
		@DateTimeFormat(pattern = "yyyy-MM-dd")
		private LocalDate birthday;
		public BDO(int id, LocalDate birthday) {
			super();
			this.id = id;
			this.birthday = birthday;
		}

	}
	@RequestMapping(path = "/user/edit_bdo", method = RequestMethod.POST)
	public String edit_bdo(BDO input) {

//		check authz
		String username = SecurityContextHolder.getContext().getAuthentication().getName();
		User user = userDao.findByUsername(username);

		if(user.getEmp().getId()==input.getId()) {

			user.getEmp().setBirthday(input.getBirthday());
			userDao.save(user);
		}else {
			throw new ResponseStatusException(
			          HttpStatus.BAD_REQUEST, "Unauthz");
		}


		return "redirect:/user";
	}

	@Autowired
	private AddressDao addressDao;


	@RequestMapping(path = "/user/remove_address", method = RequestMethod.GET)
	public String remove_address(int id) {

//		TODO authz
		String username = SecurityContextHolder.getContext().getAuthentication().getName();

		if(addressDao.countAddressByUsername(username)>1) {

			addressDao.deleteById(id);
		}else {
			throw new ResponseStatusException(
			          HttpStatus.BAD_REQUEST, "address must be more than 1");
		}

		return "redirect:/user";
	}

	@RequestMapping(path = "/user/add_address", method = RequestMethod.POST)
	public String add_address(Address address) {

		String username = SecurityContextHolder.getContext().getAuthentication().getName();
		User user = userDao.findByUsername(username);
		user.getEmp().getAddresses().add(address);

		userDao.save(user);

		return "redirect:/user";
	}
```

## Admin Controller

```java
@RequestMapping(path = "/admin",method=RequestMethod.GET)
	public ModelAndView adminHome() {

			ModelAndView mv = new ModelAndView("admin_home.jsp");

			List<Employee> employees = empService.getAll();

			mv.addObject("employees",employees);

			return mv;

	}

	@RequestMapping(path = "/admin/add_employee",method=RequestMethod.GET)
	public ModelAndView add_employee() {

		ModelAndView mv = new ModelAndView("/admin/add_form.jsp");


			return mv;

	}

	@Getter
	@Setter
	class ObjAdd{
		private String username;
		private String password;
		private String name;
		private Level level;
		@DateTimeFormat(pattern = "yyyy-MM-dd")
		private LocalDate birthday;
		private BigDecimal baseSalary;

		private String city;
		private String street;
		private String houseNo;
		private String zipcode;
		public ObjAdd(String username, String password, String name, Level level, LocalDate birthday,
				BigDecimal baseSalary, String city, String street, String houseNo, String zipcode) {
			super();
			this.username = username;
			this.password = password;
			this.name = name;
			this.level = level;
			this.birthday = birthday;
			this.baseSalary = baseSalary;
			this.city = city;
			this.street = street;
			this.houseNo = houseNo;
			this.zipcode = zipcode;
		}


	}


	@RequestMapping(path = "/admin/add_employee",method=RequestMethod.POST)
	public String add_employee_submit(ObjAdd input) {


		Address a1 = new Address();
		a1.setCity(input.getCity());
		a1.setStreet(input.getStreet());
		a1.setHouseNo(input.getHouseNo());
		a1.setZipcode(input.getZipcode());
		List<Address> listAddress = new ArrayList<>();
		listAddress.add(a1);


		Employee emp = new Employee();
		emp.setName(input.getName());
		emp.setLevel(input.getLevel());
		emp.setBirthday(input.getBirthday());
		emp.setBaseSalary(input.getBaseSalary());
		emp.setAddresses(listAddress);

		Set<Role> setRoleUser1 = new HashSet<Role>();

		Role r = new Role();
		r.setName("ROLE_USER");
		setRoleUser1.add(r);
		User user = new User();
		user.setUsername(input.getUsername());


		String hashpassword = new BCryptPasswordEncoder().encode(input.getPassword());
		user.setPassword(hashpassword);
		user.setRoles(setRoleUser1);
		user.setEmp(emp);
		emp.setUser(user);

		userDao.save(user);


			return "redirect:/admin";

	}

	@RequestMapping(path = "/admin/edit_employee",method=RequestMethod.GET)
	public ModelAndView edit_employee(@RequestParam int id) {

			ModelAndView mv = new ModelAndView("/admin/employee_form.jsp");

			Employee emp = empDao.findById(id);

			mv.addObject("e",emp);
			mv.addObject("form_action","/admin/edit_employee");

			return mv;

	}

	@Getter
	@Setter
	class MyObj{
		private int id;
		private Level level;
		private String name;
		private BigDecimal baseSalary;
		public MyObj(int id, Level level, String name, BigDecimal baseSalary) {
			super();
			this.id = id;
			this.level = level;
			this.name = name;
			this.baseSalary = baseSalary;
		}

	}
	@RequestMapping(path = "/admin/edit_employee",method=RequestMethod.POST)
	public String edit_employee_submit(MyObj e) {

			Employee emp = empDao.findById(e.id);

			emp.setLevel(e.getLevel());
			emp.setName(e.getName());
			emp.setBaseSalary(e.getBaseSalary());

//			System.out.println("save "+ e.getId());
			empDao.save(emp);


			return "redirect:/admin";

	}

```
