# Lab 3

## A
set TTL to 20 second

resources/ehcache.xml
```xml
<defaultCache maxEntriesLocalHeap="10000" eternal="false"
		timeToIdleSeconds="120" timeToLiveSeconds="20" diskSpoolBufferSizeMB="30"
		maxEntriesLocalDisk="10000000" diskExpiryThreadIntervalSeconds="120"
		memoryStoreEvictionPolicy="LRU" statistics="true">
		<persistence strategy="localTempSwap" />
	</defaultCache>
```

## B

User.java
```java
@OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
	@JoinColumn(name = "employee_id", referencedColumnName = "id")
	@JsonIgnore
	@MapsId 
	private Employee emp;
```

Employee.java
```java
@OneToOne(mappedBy = "emp", cascade = CascadeType.ALL, orphanRemoval = true)
	private User user;
```

data.sql
```sql
insert into employee_info (employee_age, fname, lname, mname) values (20, 'Chaklam', 'Silpasuwanchai', ' ');
insert into employee_info (employee_age, fname, lname, mname) values (69, 'John', 'Cahill', ' ');
insert into employee_info (employee_age, fname, lname, mname) values (69, 'peter', 'x', ' ');
insert into user (username, password, active, role,employee_id) values ('chaklam', '$2y$12$2LA4/IzwsfoF.SFtdxwJIus48N6JwFzTdMwlrc9lXRHnA9EOBU7AS', true, 'ROLE_ADMIN',1);
insert into user (username, password,  active, role,employee_id) values ('john', '$2y$10$uwslZFS.czXR6VE7XzyBTuH.xGtdDmBBnioj2J/KZqJr0cHDEr3fa', true, 'ROLE_USER',2);
insert into user (username, password,  active, role,employee_id) values ('peter', '$2y$10$uwslZFS.czXR6VE7XzyBTuH.xGtdDmBBnioj2J/KZqJr0cHDEr3fa', true, 'ROLE_USER',3);
insert into benefit(title) values ('Benefit Free Water');
insert into benefit(title) values ('Benefit Free Coffee');
insert into employee_info_benefits  values (1, 1);
insert into employee_info_benefits  values (1, 2);
insert into employee_info_benefits  values (2, 1);
insert into address  values ('Bangkok', '30/6', 'Ramindra', '10220', 1);
insert into address  values ('Bangkok', '30/7', 'Victory Monument', '12220', 2);
```

## C

let say, we have User and Post (one-to-many) 

cascade.REMOVE: when user has been deleted, a post hasn't been deleted immediately. It means we can move the post to another user

orphanRemoval=true: when user has been deleted, a post has been deleted immediately.

## D

nothing because if we don't specify FetchType.LAZY, it would use FetchType.LAZY as default.

## E

It could not execute SQL statement.

## F

Because @Transactional let Spring creates a proxy so we need to submit our code by passing this proxy. On the other hand, if we didn't use @Transactional, it would be error and Spring would not support a Transaction feature in H2.

## G

HibernateApplicationTests.java
```java
@SpringBootTest
class HibernateApplicationTests {

	private static ConfigurableApplicationContext applicationContext = SpringApplication
			.run(HibernateApplication.class);
	private static TestService ts = applicationContext.getBean(TestService.class);

	@Test
	void contextLoads() {
		System.out.println("----Testing Fetch---");
		HibernateApplicationTests.ts.testFetch(1);

	}

	@Test
	void testCache() throws InterruptedException {
		System.out.println("----Testing Cache---");
		TimeUnit.SECONDS.sleep(10); // make sure cache is cleared
		System.out.println("----Not loaded, require query---");
		HibernateApplicationTests.ts.testCache();
		System.out.println("----Already loaded---");
		HibernateApplicationTests.ts.testCache();
		System.out.println("----Already loaded---");
		HibernateApplicationTests.ts.testCache();
		TimeUnit.SECONDS.sleep(10);
		System.out.println("----Not loaded, require query---");
		HibernateApplicationTests.ts.testCache();
		// testing cascade persist
		System.out.println("----Testing Cascade Persist---");
		HibernateApplicationTests.ts.testCascadePersist(1);
		System.out.println("----Try log in to H2 and try john with pwd of 1234.  See what has been persisted---");

		// testing cascade remove
		System.out.println("----Testing Cascade Remove---");
		HibernateApplicationTests.ts.testCascadeRemove(1);
		System.out.println("----Try log in to H2 and try john with pwd of 1234.  See what has been deleted---");

		// testing inheritances
		System.out.println("----Testing Inheritances ---");
		System.out.println("----Adding Sick Leave for employee with emp_user_id 1---");
		HibernateApplicationTests.ts.testCreateLeave(2, LeaveType.SICK);
		System.out.println("----Adding Annual Leave for employee with emp_user_id 1---");
		HibernateApplicationTests.ts.testCreateLeave(2, LeaveType.ANNUAL);
		System.out.println(
				"----Try log in to H2 and try john with pwd of 1234.  See what has been added in LEAVE table---");
	}

}
```