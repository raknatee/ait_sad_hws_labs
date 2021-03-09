package com.example.hibernate;

import java.util.concurrent.TimeUnit;

import org.junit.jupiter.api.Test;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ConfigurableApplicationContext;

import com.example.hibernate.model.LeaveType;
import com.example.hibernate.service.TestService;

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
