package com.library.test;

import java.util.List;

import com.library.dao.implement.RecordDaoImp;
import com.library.entity.Record;
import com.library.entity.Record2;

public class RecordDaoImpTest {

	public static void main(String[] args) {
		RecordDaoImp rd = new RecordDaoImp();
			//boolean saveRecord = rd.saveRecord(new Record(5,5,null,null,0));
		//List<Record2> queryRecordByBname = rd.queryRecordByBname("hallyworld");	
		List<Record2> queryRecordByUname = rd.queryRecordByBname("HelloWorld");
		System.out.println(queryRecordByUname);
	}

}
