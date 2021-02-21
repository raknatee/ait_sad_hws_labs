# Lab 2 Spring Security

This lab, I followed document to create basic web application with Authentication and Authorization.

## Security 

SecurityConfig.java
```java
@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter{
	
	@Autowired
	private MyUserDetailsService uds;
	
	@Bean
	public BCryptPasswordEncoder bCryptPasswordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(uds).passwordEncoder(bCryptPasswordEncoder());
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {

		/*
		 * 		/h2-console, /login, /register  -> anyone
		 * 		/admin/**    -> ROLE_ADMIN
		 * 		/**           -> ROLE_USER, ROLE_ADMIN
		 * 		/premium/**   -> ROLE_ADMIN, ROLE_PREMIUM_USER
		 */
		http.csrf().disable().authorizeRequests()
		.antMatchers("/h2-console/**","/login","/register").permitAll()
		.antMatchers("/admin/**").hasRole("ADMIN")
		.antMatchers("/**").hasAnyRole("ADMIN","USER")
		.and()
		.formLogin()
			.loginPage("/login").permitAll()
			.defaultSuccessUrl("/home",true)
		.and()
		.logout().invalidateHttpSession(true)
			.clearAuthentication(true)
			.logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
			.logoutSuccessUrl("/logout-success").permitAll();
		
		http.headers().frameOptions().disable();	
	}
}
```
This class contains all path information for Authorization.

## H2 Configuration

MyUserDetailsService.java
```java
@Service
public class MyUserDetailsService implements UserDetailsService{
	
	@Autowired
	private UserJPADao userDao;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User u = userDao.findByUsername(username);
		if(u==null) {
			throw new UsernameNotFoundException("User 404");
		}
		return new UserDetailsImplement(u);
	}
}
```

UserDetailsImpl.java 
```java
public class UserDetailsImplement implements UserDetails{
	
	private User user;
	
	public UserDetailsImplement(User u) {
		this.user = u;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		Set<GrantedAuthority> ga = new HashSet<>();
		
		for(Role role:user.getRoles()) {
			ga.add(new SimpleGrantedAuthority(role.getName()));
		}
		return ga;
		
	}
    // ... get and set

    // ...
}

```

## Register, Login and Logout
UserService.java
```java
public interface UserService {
	void save(User user);
	User findByUsername(String username);
}
```

UserServiceImplement.java
```java
@Service
public class UserServiceImplement implements UserService{

	@Autowired
	private UserJPADao userdao;
	
	@Autowired
	private BCryptPasswordEncoder be;
	
	@Autowired
	private EmailService emailService;
	@Override
	public void save(User user) {
		System.out.println("here");
		String hashedPassword = be.encode(user.getPassword());
		user.setPassword(hashedPassword);
		user.setActive(true);
		userdao.save(user);
		
		SimpleMailMessage emailMsg = new SimpleMailMessage();
		emailMsg.setTo(user.getEmail());
		emailMsg.setText("You are registered!");
		emailMsg.setSubject("Registration successful!");
		emailMsg.setFrom("admin@random.asia");
		
		try {
			emailService.sendEmail(emailMsg);
			System.out.println("successful");
		}
		catch (MailException e) {
			System.out.println(e.getMessage());
		}
	}

	@Override
	public User findByUsername(String username) {
		return userdao.findByUsername(username);
	}

	
}
```

EmailService.java
```java
public interface EmailService {
	void sendEmail(SimpleMailMessage emailMsg);
}
```

EmailServiceImplement.java
```java
public class EmailServiceImplement implements EmailService{

	@Value("${spring.mail.host}")
	private String host;
	
	@Value("${spring.mail.port}")
	private int port;
	
	@Value("${spring.mail.username}")
	private String username;
	
	@Value("${spring.mail.password}")
	private String password;
	
	@Override
	public void sendEmail(SimpleMailMessage emailMsg) {
		JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
		mailSender.setHost(this.host);
		mailSender.setPort(this.port);
		mailSender.setUsername(this.username);
		mailSender.setPassword(this.password);
		
		mailSender.send(emailMsg);
	}
}
```

UserValidator.java
```java
public class UserValidator implements Validator {

	@Autowired
	private UserService userService;
		
	@Override
	public boolean supports(Class<?> clazz) {
		// TODO Auto-generated method stub
		return User.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		User user = (User) target;
		if(user.getUsername().length() <6 || user.getUsername().length() >32) {
			errors.rejectValue("username", "size.user.username");
		}
		if(userService.findByUsername(user.getUsername())!=null) {
			errors.rejectValue("username", "duplicate.user.username");
		}
		
		if(user.getPassword().length()<8 || user.getPassword().length()>32) {
			errors.rejectValue("password", "size.user.password");
		}
		if(!user.getPasswordConfirm().equals(user.getPassword())) {
			errors.rejectValue("passwordConfirm", "diff.user.passwordConfirm");
		}
		
	}

}
```

### Controller

UserController.java
```java

@Controller
public class UserController {
	@Autowired
	private UserService userService;
	
	@Autowired
	private UserValidator userV;
	
	@RequestMapping(path="/home")
	public ModelAndView userDashboard(Principal p) {
		ModelAndView mv = new ModelAndView("home.jsp");
		User u = (User) userService.findByUsername(p.getName());
		mv.addObject("user",u);
		
		for(Role role: u.getRoles()) {
			if(role.getName().equalsIgnoreCase("ROLE_ADMIN")) {
				// ...
			}
			if(role.getName().equalsIgnoreCase("ROLE_USER")) {
				// ...
			}
			if(role.getName().equalsIgnoreCase("ROLE_PREMIUM_USER")) {
				// ...
			}
		}
		return mv;
	}
	
	@RequestMapping(path="/register",method=RequestMethod.GET)
	public String register(Model model) {
		model.addAttribute("user", new User());
		return "register.jsp";
	}
	
	@RequestMapping(path = "/register",method=RequestMethod.POST)
	public String addUser(@Valid @ModelAttribute("user") User user, BindingResult bR) {
		userV.validate(user, bR);
		
		if(bR.hasErrors()) {
			return "register.jsp";
		}
		userService.save(user);
		return "login.jsp";
		
		
	}
	@RequestMapping(path="/login")
	public String login() {
		return "login.jsp";
	}
	@RequestMapping(path="/logout-success")
	public String logout() {
		return "logout.jsp";
	}
}

```