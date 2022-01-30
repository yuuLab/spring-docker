package com.yuuLab.springLab.logic.dto.input;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class AppUserSaveInput {
	
	private String firstName;
	
	private String lastName;
	
	private String email;
	
	private String telNumber;


}
