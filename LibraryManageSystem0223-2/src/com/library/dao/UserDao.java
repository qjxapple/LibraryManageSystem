package com.library.dao;

import java.util.List;

import com.library.entity.User;

public interface UserDao {
	public boolean addUser(User user);// 增加用户

	public boolean deleteUser(int id);// 删除用户

	public boolean updateUser(User user);// 修改用户信息

	public User queryUser(User user);// 查询用户

	public List<User> queryAllUsers();// 查询所有用户

	public User queryUserById(int uid);// 查询指定用户
}
