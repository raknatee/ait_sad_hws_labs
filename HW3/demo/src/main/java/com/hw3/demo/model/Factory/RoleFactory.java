package com.hw3.demo.model.Factory;

import org.springframework.stereotype.Component;

@Component
public class RoleFactory {
	public Role createRole(String role) {
		switch(role) {
		case "Admin":
			return new AdminRole();
		case "Member":
			return new MemberRole();
		default:
			throw new UnsupportedOperationException("Unsupported role");
		}
	}
}
