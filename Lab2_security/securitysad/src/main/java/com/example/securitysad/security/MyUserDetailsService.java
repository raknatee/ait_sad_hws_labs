package com.example.securitysad.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.securitysad.dao.UserJPADao;
import com.example.securitysad.model.User;

@Service
public class MyUserDetailsService implements UserDetailsService{
	
	@Autowired
	private UserJPADao userDao;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		User u = userDao.findByUsername(username);
		if(u==null) {
			throw new UsernameNotFoundException("User 404");
		}
		return new UserDetailsImplement(u);
	}

}
