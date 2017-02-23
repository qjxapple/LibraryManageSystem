package com.library.biz;

import java.util.List;

import com.library.entity.*;

public interface RecordBiz {
	public List<Record2> queryUserRecords(String uname);// 查看指定用户的图书租还记录

	public List<Record2> queryBookRecords(String bname);// 查看指定书籍的租还记录

	public List<Record2> queryHasReturnedRecords(String uname);// 查看指定用户的已经归还记录

	public List<Record2> queryNoReturnedRecords(String uname);// 查看指定用户的未归还记录

	public List<Record2> queryAllRecords();// 查看所有租还记录
	
	public List<Record2> queryUserAllRecords(int uid);//查询用户所有租阅记录
}
