package com.library.dao;

import java.util.List;

import com.library.entity.Record;
import com.library.entity.Record2;

public interface RecordDao {
	public Record queryRecordById(int rid);// 通过指定的id查看记录

	public boolean saveRecord(Record record);// 保存record

	public boolean updateRecord(Record record);// 更新record记录

	public List<Record2> queryAllRecords();// 查询所有书籍借阅记录

	public List<Record> findAllRecords();// 查询所有书籍借阅记录，返回的是record集合对象
	
	public List<Record2> queryUserAllRecords(int uid);//查询本人所有已经借阅记录

	public List<Record2> queryRecordByUname(String uname);// 查询指定用户名节借书记录

	public List<Record2> queryRecordByBname(String bname);// 查询指定书名的借还记录

	public Record queryReocrdByBookId(int bid);// 通过指定的book 查找书籍

	public List<Record2> queryUserRecordByReturnTime(boolean flag, String uname);// 查询用户的归还记录，归还/未归还

}
