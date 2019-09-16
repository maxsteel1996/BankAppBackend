package com.myapp.myapp.model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
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
	@Enumerated(EnumType.STRING)
	Role role;
  @OneToOne(fetch = FetchType.EAGER,cascade = CascadeType.ALL)
  Branch branch;

}
