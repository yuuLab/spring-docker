package com.yuuLab.springLab.controller.response;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;

@Getter
@Builder
@EqualsAndHashCode
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class AppUserGetResponse {
	
	private String userId;
	
	private String firstName;
	
	private String lastName;
	
	private String email;
	
	private String telNumber;

}
