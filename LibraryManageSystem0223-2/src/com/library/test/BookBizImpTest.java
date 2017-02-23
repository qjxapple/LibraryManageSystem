package com.library.test;

import com.library.biz.imp.BookBizImp;

public class BookBizImpTest {

	public static void main(String[] args) {
		BookBizImp bb = new BookBizImp();
		int lendBook = bb.lendBook(1, 2);
		System.out.println("main" + lendBook);
	}

}
