package com.myapp.myapp.model;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.OneToOne;

import lombok.Data;

@Entity
@Inheritance
@Data
public class User {

	@Id
	String userName;
	String name;
	String phone;
	String password;
	Role role;
  @OneToOne(fetch = FetchType.EAGER)
  Branch branch;

}
