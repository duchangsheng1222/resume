package com.resume.service.impl;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.resume.dao.UserDao;
import com.resume.dto.UserInfo;
import com.resume.po.UserInfoPo;
import com.resume.service.UserService;
import com.resume.service.exception.UserException;
import com.resume.service.exception.UserException.UserExceptionType;
import com.resume.util.BeanUtil;

@Service
public class UserServiceImpl implements UserService{

	@Autowired
	private UserDao userDao;
	
	@Override
	public UserInfo queryByUsername(String username) {
		UserInfoPo userInfoPo = userDao.queryByUsername(username);
		UserInfo userInfo = BeanUtil.createCopy(userInfoPo, UserInfo.class);
		if(null != userInfo && null != userInfoPo){
			userInfo.setEnabled("0".equals(userInfoPo.getLocked()));
			return userInfo;
		}
		return null;
	}

	@Override
	@Transactional
	public void register(String email, String password,String role) throws UserException{
		UserInfoPo user = userDao.queryByUsername(email);
		if(null != user){
			throw new UserException(UserExceptionType.USER_EXISTS);
		}
		user = new UserInfoPo();
		user.setEmail(email);
		user.setPassword(password);
		user.setLocked("0");
		userDao.insertUser(user);
		
		
	}

	@Override
	public void resetPassword(String email, String password) {
		userDao.resetPasswordByEmail(password, email);
	}

}
