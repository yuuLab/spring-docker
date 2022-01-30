package com.yuuLab.springLab.logic;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yuuLab.springLab.logic.dto.input.AppUserSaveInput;
import com.yuuLab.springLab.repository.dao.AppUserDao;
import com.yuuLab.springLab.repository.dao.numbering.UserIdGenerator;
import com.yuuLab.springLab.repository.entity.AppUser;

@Transactional
@Service
public class AppUserSaveLogic {

	protected final AppUserDao appUserDao;
	
	@Autowired
	public AppUserSaveLogic(AppUserDao appUserDao) {
		this.appUserDao = appUserDao;
	}
	
	/**
	 * 
	 * @return userId
	 */
	public String saveAppUser(AppUserSaveInput input) {
		AppUser insertData = this.createEntity(input);
		this.appUserDao.insert(insertData);
		return insertData.userId;
	}
	
	
	private AppUser createEntity(AppUserSaveInput input) {
		AppUser user = new AppUser();
		user.userId = UserIdGenerator.numberUserId();
		user.firstName = input.getFirstName();
		user.lastName = input.getLastName();
		user.email = input.getEmail();
		user.telNumber = input.getTelNumber();
		return user;
	}
}
