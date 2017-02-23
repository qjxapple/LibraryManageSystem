package com.library.test;

import java.util.List;

import com.library.biz.imp.RecordBizImp;
import com.library.entity.Record2;

public class RecordBizImpTest {

	public static void main(String[] args) {
		RecordBizImp rb = new RecordBizImp();
//		List<Record2> queryAllRecords = rb.queryAllRecords();
//		System.out.println(queryAllRecords);
		
//		List<Record2> queryBookRecords = rb.queryBookRecords("HelloWorld");
//		System.out.println(queryBookRecords);
		
		
//		List<Record2> queryUserRecords = rb.queryUserRecords("hello");
//		System.out.println(queryUserRecords);
		
//		List<Record2> queryHasReturnedRecords = rb.queryHasReturnedRecords("hello");
//		System.out.println(queryHasReturnedRecords);
		
		List<Record2> queryNoReturnedRecords = rb.queryNoReturnedRecords("hello1");
		System.out.println(queryNoReturnedRecords);
	}

}
