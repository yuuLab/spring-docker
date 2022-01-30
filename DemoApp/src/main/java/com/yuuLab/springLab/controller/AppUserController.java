package com.yuuLab.springLab.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.yuuLab.springLab.controller.request.AppUserSaveRequest;
import com.yuuLab.springLab.controller.response.AppUserGetResponse;
import com.yuuLab.springLab.controller.response.AppUserSaveResponse;
import com.yuuLab.springLab.logic.AppUserInquiryLogic;
import com.yuuLab.springLab.logic.AppUserSaveLogic;
import com.yuuLab.springLab.logic.dto.input.AppUserSaveInput;
import com.yuuLab.springLab.logic.dto.output.AppUserInquiryOutput;

@RestController
public class AppUserController {
	
	private final AppUserInquiryLogic appUserInquiryLogic;
	
	private final AppUserSaveLogic appUserSaveLogic;
	
	@Autowired
	public AppUserController(AppUserInquiryLogic appUserInquiryLogic, AppUserSaveLogic appUserSaveLogic) {
		this.appUserInquiryLogic = appUserInquiryLogic;
		this.appUserSaveLogic = appUserSaveLogic;
	}
	
	
	@GetMapping(value="/user/{user_id:^[0-9]{1,20}$}")
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
	
	@PostMapping(value="/user")
	public AppUserSaveResponse saveAppUser(@RequestBody AppUserSaveRequest request ) {
		
		AppUserSaveInput input = AppUserSaveInput.builder()
				.firstName(request.getFirstName())
				.lastName(request.getLastName())
				.email(request.getEmail())
				.telNumber(request.getTelNumber())
				.build();
		
		String userId = this.appUserSaveLogic.saveAppUser(input);
		
		return AppUserSaveResponse.builder().userId(userId).build();
	}
}
