package com.library.biz.imp;

import java.util.List;

import com.library.biz.RecordBiz;
import com.library.dao.BookDao;
import com.library.dao.RecordDao;
import com.library.dao.implement.BookDaoImp;
import com.library.dao.implement.RecordDaoImp;
import com.library.entity.Record2;

public class RecordBizImp implements RecordBiz {

	private BookDao bookDao = null;
	private RecordDao recordDao = null;

	public RecordBizImp() {
		bookDao = new BookDaoImp();
		recordDao = new RecordDaoImp();
	}

	@Override
	public List<Record2> queryUserRecords(String uname) {
		// TODO Auto-generated method stub
		return recordDao.queryRecordByUname(uname);
	}

	@Override
	public List<Record2> queryBookRecords(String bname) {
		return recordDao.queryRecordByBname(bname);
	}

	@Override
	public List<Record2> queryHasReturnedRecords(String uname) {
		// TODO Auto-generated method stub
		return recordDao.queryUserRecordByReturnTime(true, uname);
	}

	@Override
	public List<Record2> queryNoReturnedRecords(String uname) {
		// TODO Auto-generated method stub
		return recordDao.queryUserRecordByReturnTime(false, uname);
	}

	@Override
	public List<Record2> queryAllRecords() {
		// TODO Auto-generated method stub
		return recordDao.queryAllRecords();
	}

	@Override
	public List<Record2> queryUserAllRecords(int uid) {
		// TODO Auto-generated method stub
		return recordDao.queryUserAllRecords(uid);
	}

}
