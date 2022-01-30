package com.yuuLab.springLab.repository.dao;

import org.seasar.doma.Dao;
import org.seasar.doma.Insert;
import org.seasar.doma.Select;
import org.seasar.doma.boot.ConfigAutowireable;

import com.yuuLab.springLab.repository.entity.AppUser;

@ConfigAutowireable
@Dao
public interface AppUserDao {
	
	@Select
    public AppUser selectById(String userId);
	
	@Insert
	public int insert(AppUser entity);

}
