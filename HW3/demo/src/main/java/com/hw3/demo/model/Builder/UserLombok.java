package com.hw3.demo.model.Builder;

import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.Builder;
import lombok.Data;

@Entity
@Builder
@Data
public class UserLombok {
	@Id
	private int uid;
	private String name;
	private String nationality;
	private String email;
}
