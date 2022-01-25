package com.yuuLab.springLab.logic.dto.output;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class AppUserInquiryOutput {

	private String userId;
	
	private String firstName;
	
	private String lastName;
	
	private String email;
	
	private String telNumber;
}
