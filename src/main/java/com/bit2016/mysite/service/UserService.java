package com.bit2016.mysite.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bit2016.mysite.exception.UserDaoException;
import com.bit2016.mysite.repository.UserDao;
import com.bit2016.mysite.vo.UserVo;

@Service
public class UserService {

	@Autowired
	private UserDao userDao;

	public void join(UserVo vo) {
		System.out.println("UserService join 입장");
		userDao.insert(vo);
	}

	public UserVo login(String email, String password) {
		System.out.println("UserService login 입장");
		UserVo userVo = null;

			userVo = userDao.get(email, password);
			
		return userVo;
	}

	public UserVo getUser(Long no) {
		System.out.println("UserService getUser 입장");
		return userDao.get(no);
	}

	public void updateUser(UserVo vo) {
		System.out.println("UserService updateUser 입장");
		userDao.update(vo);
	}
}
