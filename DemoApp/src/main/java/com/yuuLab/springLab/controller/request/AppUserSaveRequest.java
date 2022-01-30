package com.yuuLab.springLab.controller.request;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class AppUserSaveRequest {

	private String firstName;
	
	private String lastName;
	
	private String email;
	
	private String telNumber;
}
