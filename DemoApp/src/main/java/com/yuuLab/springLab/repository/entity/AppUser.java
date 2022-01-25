package com.yuuLab.springLab.repository.entity;

import org.seasar.doma.Column;
import org.seasar.doma.Entity;
import org.seasar.doma.Id;
import org.seasar.doma.Table;

@Table(name = "APP_USER")
@Entity
public class AppUser {
	
	@Id
	@Column(name="user_id")
	public String userId;
	
	@Column(name="first_name")
	public String firstName;
	
	@Column(name="last_name")
	public String lastName;
	
	@Column(name="email")
	public String email;
	
	@Column(name="tel_number")
	public String telNumber;
}
