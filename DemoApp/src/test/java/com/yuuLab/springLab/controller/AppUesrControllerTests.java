package com.yuuLab.springLab.controller;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.event.annotation.BeforeTestClass;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import com.yuuLab.springLab.application.DemoAppApplication;
import com.yuuLab.springLab.db.testtool.DBTestWorker;

@SpringBootTest(classes=DemoAppApplication.class)
@AutoConfigureMockMvc
@Transactional
public class AppUesrControllerTests {
	
	@Autowired
	private MockMvc mockMvc;
	
	private Logger LOGGER = org.slf4j.LoggerFactory.getLogger(AppUesrControllerTests.class);
	
	@BeforeTestClass
	private void setUp() {
		try {
			LOGGER.info("start setup");
			// データベース準備
			DBTestWorker dbTestWorker= new DBTestWorker("AppUserControllerTests");
			dbTestWorker.setUpData();
		} catch (Exception e) {
			LOGGER.info("failed test setUp", e);
		}
	}
	
	@Test
	public void testGetAppUser() throws Exception {
		String responseJson = this.mockMvc.perform(
			get("/user/1000000000000000"))
			.andExpect(status().isOk())
			.andReturn().getResponse().getContentAsString();
		
		String expectedResponseString = 
				"{\"user_id\":\"1000000000000000\",\"first_name\":\"test\",\"last_name\":\"user\",\"email\":\"test@test.com\",\"tel_number\":\"09012345678\"}";
		assertEquals(expectedResponseString, responseJson);
	}

}
