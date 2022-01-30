package com.yuuLab.springLab.logic;

import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yuuLab.springLab.logic.dto.output.AppUserInquiryOutput;
import com.yuuLab.springLab.repository.dao.AppUserDao;
import com.yuuLab.springLab.repository.entity.AppUser;

@Transactional
@Service
public class AppUserInquiryLogic {

	
	protected final AppUserDao appUserDao;
	
	@Autowired
	public AppUserInquiryLogic(AppUserDao appUserDao) {
		this.appUserDao = appUserDao;
	}
	
	public AppUserInquiryOutput getAppUserInfo(String userId) {
		AppUser user = this.appUserDao.selectById(userId);
		if (Objects.isNull(user)) {
			user = new AppUser();
		}
		return AppUserInquiryOutput.builder()
				.userId(user.userId)
				.firstName(user.firstName)
				.lastName(user.lastName)
				.email(user.email)
				.telNumber(user.telNumber)
				.build();
	}
	
}
