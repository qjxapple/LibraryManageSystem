package com.library.dao.implement;

import java.util.ArrayList;
import java.util.List;

import com.library.dao.RecordDao;
import com.library.entity.Record;
import com.library.entity.Record2;

public class RecordDaoImp extends BaseDao implements RecordDao {

	@Override
	public Record queryRecordById(int rid) {
		String sql = "select id,uid,bid,lendTime,returnTime,renew from records where id=?";
		List<Record> rList = null;
		List<Object> params = new ArrayList<Object>();
		params.add(rid);
		try {
			rList = this.operateQuery(sql, params, Record.class);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if (rList.size() > 0)
			return rList.get(0);// 返回集合里面第一个对象
		return null;
	}

	@Override
	public boolean saveRecord(Record record) {
		String sql = "insert into records(uid,bid,lendTime,returnTime,renew) values (?,?,?,?,?)";
		List<Object> params = new ArrayList<Object>();
		params.add(record.getUid());
		params.add(record.getBid());
		params.add(record.getLendTime());
		params.add(record.getReturnTime());
		params.add(record.getRenew());

		return this.operateUpdate(sql, params);
	}

	@Override
	public boolean updateRecord(Record record) {
		String sql = "update records set uid=?,bid=?,lendTime=?,returnTime=?,renew=? where id=?";
		List<Object> params = new ArrayList<Object>();
		params.add(record.getUid());
		params.add(record.getBid());
		params.add(record.getLendTime());
		params.add(record.getReturnTime());
		params.add(record.getRenew());
		params.add(record.getId());

		return this.operateUpdate(sql, params);
	}

	@Override
	public List<Record2> queryAllRecords() {
		String sql = "select r.id,r.bid,u.uname,b.bookName,r.lendTime,r.returnTime,r.renew from "
				+ "users u,books b,records r where u.id=r.uid and b.id=r.bid";
		List<Record2> r2List = null;
		try {
			r2List = this.operateQuery(sql, null, Record2.class);

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return r2List;
	}

	@Override
	public List<Record2> queryUserAllRecords(int uid) {
		String sql = "select r.id,r.bid,u.uname,b.bookName,r.lendTime,r.returnTime,r.renew from "
				+ "users u,books b,records r where u.id=r.uid and b.id=r.bid and u.id = ?";
		List<Record2> r2List = null;
		List<Object> params = new ArrayList<Object>();
		params.add(uid);
		try {
			r2List = this.operateQuery(sql, params, Record2.class);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return r2List;
	}

	@Override
	public List<Record> findAllRecords() {
		String sql = "select r.id,r.uid,r.bid,r.lendTime,r.returnTime,r.renew from "
				+ "users u,books b,records r where u.id=r.uid and b.id=r.bid";
		List<Record> rList = null;
		try {
			rList = this.operateQuery(sql, null, Record.class);

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return rList;
	}

	@Override
	public List<Record2> queryRecordByUname(String uname) {
		String sql = "select r.id,r.bid,u.uname,b.bookName,r.lendTime,r.returnTime,r.renew from "
				+ "users u,books b,records r where u.id=r.uid and b.id=r.bid and u.uname like ?";
		List<Record2> r2List = null;
		List<Object> params = new ArrayList<Object>();
		params.add("%" + uname + "%");
		try {
			r2List = this.operateQuery(sql, params, Record2.class);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return r2List;
	}

	@Override
	public List<Record2> queryRecordByBname(String bname) {

		String sql = "select r.id,r.bid,u.uname,b.bookName,r.lendTime,r.returnTime,r.renew from "
				+ "users u,books b,records r where u.id=r.uid and b.id=r.bid and b.bookName like ?";
		List<Record2> r2List = null;
		List<Object> params = new ArrayList<Object>();
		params.add("%" + bname + "%");
		try {
			r2List = this.operateQuery(sql, params, Record2.class);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return r2List;
	}

	@Override
	public List<Record2> queryUserRecordByReturnTime(boolean flag, String uname) {
		String sql = null;
		if (flag) {
			sql = "select r.id,r.bid as bid,u.uname,b.bookName,r.lendTime,r.returnTime,r.renew from users u,books b,"
					+ "records r where u.id=r.uid and b.id=r.bid and r.returnTime is not null and u.uname=?";
		} else {

			sql = "select r.id,r.bid as bid,u.uname,b.bookName,r.lendTime,r.returnTime,r.renew from users u,books b,"
					+ "records r where u.id=r.uid and b.id=r.bid and r.returnTime is null and u.uname=?";
		}
		List<Record2> r2List = null;
		List<Object> params = new ArrayList<Object>();
		params.add(uname);
		try {
			r2List = this.operateQuery(sql, params, Record2.class);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return r2List;
	}

	@Override
	public Record queryReocrdByBookId(int bid) {
		String sql = "select id,uid,bid,lendTime,returnTime,renew from records where bid=?";
		List<Record> rList = null;
		List<Object> params = new ArrayList<Object>();
		params.add(bid);
		try {
			rList = this.operateQuery(sql, params, Record.class);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if (rList.size() > 0)
			return rList.get(0);// 返回集合里面第一个对象
		return null;
	}

}
