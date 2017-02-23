package com.library.dao.implement;

import java.util.ArrayList;
import java.util.List;

import com.library.dao.UserDao;
import com.library.entity.User;

public class UserDaoImp extends BaseDao implements UserDao {

	@Override
	public boolean addUser(User user) {
		String sql = "insert into users (uname,upass,type,integration) values(?,?,?,?)";
		List<Object> params = new ArrayList<Object>();
		params.add(user.getUname());
		params.add(user.getUpass());
		params.add(user.getType());
		params.add(user.getIntegration());

		return this.operateUpdate(sql, params);
	}

	@Override
	public boolean deleteUser(int id) {
		String sql = "delete from users where id=?";
		List<Object> params = new ArrayList<Object>();
		params.add(id);
		return this.operateUpdate(sql, params);
	}

	@Override
	public boolean updateUser(User user) {
		String sql = "update users set uname=?,upass=?,type=?,integration=? where id=?";
		List<Object> params = new ArrayList<Object>();
		params.add(user.getUname());
		params.add(user.getUpass());
		params.add(user.getType());
		params.add(user.getIntegration());
		params.add(user.getId());
		return this.operateUpdate(sql, params);
	}

	@Override
	public User queryUser(User user) {
		String sql = "select id,uname,upass,type,integration from users where binary uname=? and binary upass =? and type=?";
		List<Object> params = new ArrayList<Object>();
		List<User> userList = null;
		params.add(user.getUname());
		params.add(user.getUpass());
		params.add(user.getType());
		try {
			userList = this.operateQuery(sql, params, User.class);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if (userList.size() > 0) {
			return userList.get(0);
		}
		return null;
	}

	@Override
	public List<User> queryAllUsers() {
		String sql = "select id,uname,upass,type,integration from users where type = 0";
		List<User> userList = null;
		try {
			userList = this.operateQuery(sql, null, User.class);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return userList;
	}

	@Override
	public User queryUserById(int uid) {

		String sql = "select id,uname,upass,type,integration from users where id=?";
		List<Object> params = new ArrayList<Object>();
		List<User> userList = null;
		params.add(uid);
		try {
			userList = this.operateQuery(sql, params, User.class);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if (userList.size() > 0) {
			return userList.get(0);
		}
		return null;
	}

}
