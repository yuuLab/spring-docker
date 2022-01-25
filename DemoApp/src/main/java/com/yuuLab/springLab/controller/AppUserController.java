package com.yuuLab.springLab.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.yuuLab.springLab.controller.response.AppUserGetResponse;
import com.yuuLab.springLab.logic.AppUserInquiryLogic;
import com.yuuLab.springLab.logic.dto.output.AppUserInquiryOutput;

@RestController
public class AppUserController {
	
	@Autowired
	AppUserInquiryLogic appUserInquiryLogic;
	
	
	@GetMapping(value="/user/{user_id:^[0-9]{1,10}$}")
	public AppUserGetResponse getAppUser(@PathVariable("user_id") String userId) { 
		

		AppUserInquiryOutput output = this.appUserInquiryLogic.getAppUserInfo(userId);
		
		return AppUserGetResponse.builder()
				.userId(output.getUserId())
				.firstName(output.getFirstName())
				.lastName(output.getLastName())
				.email(output.getEmail())
				.telNumber(output.getTelNumber())
				.build();
	}
}
