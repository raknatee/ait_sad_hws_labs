package com.example.securitysad.service;

import com.example.securitysad.model.User;

public interface UserService {
	void save(User user);
	User findByUsername(String username);
}
