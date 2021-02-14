package com.example.securitysad.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.securitysad.dao.UserJPADao;
import com.example.securitysad.model.User;

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
			// TODO: handle exception
			System.out.println(e.getMessage());
		}
	}

	@Override
	public User findByUsername(String username) {
		// TODO Auto-generated method stub
		return userdao.findByUsername(username);
	}

	
}
