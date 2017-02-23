package com.library.biz.imp;

import java.util.List;

import com.library.biz.UserBiz;
import com.library.dao.UserDao;
import com.library.dao.implement.UserDaoImp;
import com.library.entity.User;

public class UserBizImp implements UserBiz {
	private UserDao userDao = null;

	public UserBizImp() {
		userDao = new UserDaoImp();
	}

	@Override
	public User login(User user) {

		return userDao.queryUser(user);
	}

	@Override
	public int registUser(User user) {
		if (userDao.queryUser(user) != null) {
			return 1;// 用户名已经存在
		} else {
			// 注册用户不存在，可以注册
			boolean res = userDao.addUser(user);
			if (res)
				return 2;// 注册成功
			else
				return 3;// 注册失败
		}
	}

	@Override
	public boolean addUser(User user) {
		// TODO Auto-generated method stub
		return userDao.addUser(user);
	}

	@Override
	public boolean modifyUser(User user) {
		// TODO Auto-generated method stub
		return userDao.updateUser(user);
	}

	@Override
	public boolean deleteUser(int uid) {
		// TODO Auto-generated method stub
		return userDao.deleteUser(uid);
	}

	@Override
	public List<User> queryAllUsers() {
		// TODO Auto-generated method stub
		return userDao.queryAllUsers();
	}

	@Override
	public User queryUserById(int uid) {
		// TODO Auto-generated method stub
		return userDao.queryUserById(uid);
	}

}
