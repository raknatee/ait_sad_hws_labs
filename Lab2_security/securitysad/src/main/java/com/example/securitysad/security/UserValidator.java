package com.example.securitysad.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.example.securitysad.model.User;
import com.example.securitysad.service.UserService;

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
