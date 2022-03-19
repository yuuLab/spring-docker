package com.yuuLab.springLab.logic;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.yuuLab.springLab.logic.dto.output.AppUserInquiryOutput;
import com.yuuLab.springLab.repository.dao.AppUserDao;
import com.yuuLab.springLab.repository.entity.AppUser;


@ExtendWith(SpringExtension.class)
public class AppUserInquiryLogicTest {

	@InjectMocks
	private AppUserInquiryLogic logic;
	
	// DaoクラスをMockとする。実際にDBから取得しない。
	@Mock
	private AppUserDao appUserDao;
	
	@Test
	public void testGetAppUserInfo() throws Exception {
		String testId = "1000000000";
		
		// Mockとしたクラスのメソッドについて、testIdを引数に実行した場合に返却される値を指定する。
		AppUser mockUser = getMockUserData();
		when(appUserDao.selectById(testId)).thenReturn(mockUser);
		
		AppUserInquiryOutput result = this.logic.getAppUserInfo(testId);
		AppUserInquiryOutput expected = AppUserInquiryOutput.builder()
				.userId(testId)
				.firstName(mockUser.firstName)
				.lastName(mockUser.lastName)
				.email(mockUser.email)
				.telNumber(mockUser.telNumber)
				.build();
		assertTrue(expected.equals(result));
	}
	
	
	/**
	 * @return テスト用AppUserオブジェクト
	 */
	private static AppUser getMockUserData() {
		AppUser user = new AppUser();
		user.userId = "1000000000";
		user.firstName = "firstName";
		user.lastName = "lastName";
		user.email = "test@test";
		user.telNumber = "09011112222";
		return user;
	}
}
