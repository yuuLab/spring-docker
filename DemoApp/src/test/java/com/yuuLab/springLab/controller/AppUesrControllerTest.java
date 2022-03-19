package com.yuuLab.springLab.controller;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.sql.SQLException;

import org.dbunit.Assertion;
import org.dbunit.dataset.ITable;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.event.annotation.AfterTestClass;
import org.springframework.test.context.event.annotation.BeforeTestClass;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import com.yuuLab.springLab.application.DemoAppApplication;
import com.yuuLab.springLab.db.testtool.DBTestWorker;

@SpringBootTest(classes=DemoAppApplication.class)
@AutoConfigureMockMvc
@Transactional
public class AppUesrControllerTest {
	
	@Autowired
	private MockMvc mockMvc;
	
	private Logger LOGGER = org.slf4j.LoggerFactory.getLogger(AppUesrControllerTest.class);
	
	private static DBTestWorker dbTester;
	
	@BeforeTestClass
	private void setUp() {
		try {
			LOGGER.info("start setup");
			// データベース準備
			 dbTester= new DBTestWorker("AppUesrControllerTest");
			 dbTester.setUpData();
		} catch (Exception e) {
			LOGGER.info("failed test setUp", e);
		}
	}
	
	@AfterTestClass
	private void tearDown() throws SQLException {
		dbTester.closeDbConn();
	}
	
	@Test
	public void 会員情報照会() throws Exception {
		String responseJson = this.mockMvc.perform(
			get("/user/1000000000000000"))
			.andExpect(status().isOk())
			.andReturn().getResponse().getContentAsString();
		
		String expectedResponseString = 
				"{\"user_id\":\"1000000000000000\",\"first_name\":\"test\",\"last_name\":\"user\",\"email\":\"test@test.com\",\"tel_number\":\"09012345678\"}";
		assertEquals(expectedResponseString, responseJson);
	}

	
	@Test
	public void 会員情報登録() throws Exception {
		String body = "{\"first_name\" : \"yuu\", \"last_name\" : \"lab\", \"email\" : \"springApi@yuulab.com\", \"mobile_number\" : \"123-4567-8910\", \"gender_code\" : \"0\"}";
		this.mockMvc.perform(
				post("/user")
				.contentType(MediaType.APPLICATION_JSON)
				.content(body))
				.andExpect(status().isOk())
				.andReturn().getResponse().getContentAsString();
		ITable expectedTable = dbTester.getExpectedData("app_user");
		ITable actualTable = dbTester.getActualData("app_user");
		Assertion.assertEquals(expectedTable, actualTable);
	}
}
