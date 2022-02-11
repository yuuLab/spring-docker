package com.yuuLab.springLab.logic.dto.output;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;

@Getter
@Builder
@EqualsAndHashCode
public class AppUserInquiryOutput {

	private String userId;
	
	private String firstName;
	
	private String lastName;
	
	private String email;
	
	private String telNumber;
}
