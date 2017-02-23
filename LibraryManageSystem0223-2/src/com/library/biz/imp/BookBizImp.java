package com.library.biz.imp;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import com.library.biz.BookBiz;
import com.library.dao.BookDao;
import com.library.dao.OrderDao;
import com.library.dao.RecordDao;
import com.library.dao.UserDao;
import com.library.dao.implement.BookDaoImp;
import com.library.dao.implement.OrderDaoImp;
import com.library.dao.implement.RecordDaoImp;
import com.library.dao.implement.UserDaoImp;
import com.library.entity.Book;
import com.library.entity.Order;
import com.library.entity.Order2;
import com.library.entity.Record;
import com.library.entity.User;
import com.library.util.DaysBetweenUtil;
import com.library.util.DaysDifferUtil;

public class BookBizImp implements BookBiz {
	private BookDao bookDao = null;
	private RecordDao recordDao = null;
	private OrderDao orderDao = null;
	private UserDao userDao = null;

	public BookBizImp() {
		userDao = new UserDaoImp();
		bookDao = new BookDaoImp();
		recordDao = new RecordDaoImp();
		orderDao = new OrderDaoImp();
	}

	@Override
	public boolean addBook(Book book) {

		return bookDao.addBook(book);
	}

	@Override
	public boolean delBook(int bid) {
		return bookDao.deleteBook(bid);
	}

	@Override
	public boolean modifyBook(Book book) {
		return bookDao.updateBook(book);
	}

	@Override
	public List<Book> queryAllBooks() {
		// TODO Auto-generated method stub
		return bookDao.queryBooks();
	}

	@Override
	public List<Book> queryBookByName(String bname) {
		// TODO Auto-generated method stub
		return bookDao.queryBookByName(bname);
	}

	@Override
	public Book queryBookById(int bid) {
		// TODO Auto-generated method stub
		return bookDao.queryBookByID(bid);
	}

	@Override
	public List<Book> queryByAuthor(String author) {
		// TODO Auto-generated method stub
		return bookDao.queryBookByAuthor(author);
	}

	@Override
	public List<Book> canLendBook() {

		return bookDao.queryBookByStatus(0, 0, 0);
	}

	@Override
	public List<Book> hasLendedBook() {
		// TODO Auto-generated method stub
		return bookDao.queryBookByStatus(1, 2, 3);
	}

	@Override
	public int lendBook(int bid, int uid) {
		User user = userDao.queryUserById(uid);
		Order order = orderDao.queryOrderByBid(bid);
		Book book = this.queryBookById(bid);

		if (order != null) {
			// 预约记录中，书籍的Id和当前传入的书的id相同，并且预约记录中的用户uid
			// 不等于当前用户的id，并且书的预约状态是预约中,表示书籍已经被别人预约了
			if (order.getBid() == bid && order.getUid() != uid
					&& order.getStatus() == 1)
				return 6; // 表示已经被别人预约了，不可以借
			else if (order.getBid() == bid && order.getUid() == uid)
				book.setStatus(1);// 更新book状态，已被借出
			book.setBookCount(book.getBookCount() + 1);// 书籍借出数量加1
			boolean flag1 = bookDao.updateBook(book);// 更新book表信息
			Record record = new Record(uid, book.getId(), new SimpleDateFormat(
					"yyyy-MM-dd").format(new Date()), null, 0);
			boolean flag2 = recordDao.saveRecord(record);
			order = orderDao.queryOrderByUId(uid);
			boolean flag3 = orderDao.deleteOrder(order.getId()); // 是自己的书并且借阅成功后，删除预约记录
			user.setIntegration(user.getIntegration() - 1); // 用户借阅成功的话，积分减去一分
			boolean flag4 = userDao.updateUser(user);
			if (flag1 && flag2 && flag3 && flag4) {
				return 4; // 表示自己预约的书，可以直接借
			} else
				return 3;// 借出失败
		}

		if (user.getIntegration() <= 1) {
			return 5;// 积分不够，不可以借书
		}

		if (book == null) {
			return 0;// 没有要借的书
		} else {
			if (book.getStatus() == 1) {
				return 1;// 代表不可以借，已经被借出去了
			} else {

				book.setStatus(1);// 更新book状态，已被借出
				book.setBookCount(book.getBookCount() + 1);// 书籍借出数量加1
				boolean flag1 = bookDao.updateBook(book);// 更新book表信息
				Record record = new Record(uid, book.getId(),
						new SimpleDateFormat("yyyy-MM-dd").format(new Date()),
						null, 0);
				boolean flag2 = recordDao.saveRecord(record);
				order = orderDao.queryOrderByUId(uid);
				if (order != null) {

					order.setStatus(2);
					orderDao.updateOrder(order);
				}
				user.setIntegration(user.getIntegration() - 1); // 用户借阅成功的话，积分减去一分
				boolean flag4 = userDao.updateUser(user);

				if (flag1 && flag2 && flag4) {
					return 2;// 借出成功
				} else
					return 3;// 借出失败
			}
		}
	}

	@Override
	public int returnBook(int uid,int rid) {
		// 1.首先查看是否借过该书籍
		Record record = recordDao.queryRecordById(rid);
		
		if (record == null) {
			return 1;// 没有相应的借阅信息
		} else if (record.getReturnTime() != null) {
			return 2;// 代表书籍已经被归还
		}else if(record.getUid() != uid) {
			return 5;
		}else {
			record.setReturnTime(new SimpleDateFormat("yyyy-MM-dd")
					.format(new Date()));// 记录归还时间
			boolean flag1 = recordDao.updateRecord(record);// 更新记录表
			// 更新book借阅信息
			Book book = bookDao.queryBookByID(record.getBid());
			// 根据记录表获取用户的id，然后在预约表中查询是否有其他用户预约过该本书，如果预约过，设置其预约时间为该书的归还时间
			Order order = orderDao.queryOrderByBid(record.getBid());
			boolean flag3 = false;
			if (order != null) {

				order.setOrderTime(record.getReturnTime()); // 设置用户的归还时间为另外一个用户的预约时间

				flag3 = orderDao.updateOrder(order); // 更新预约表用的信息
			}
			// 书籍状态为预约中并且flag3为true
			if (flag3 && order.getStatus() == 1) {
				book.setStatus(3);// 修改书籍为预约中
			} else
				book.setStatus(0);// 设置书籍为 可借
			boolean flag2 = bookDao.updateBook(book);
			if (flag1 && flag2) {
				return 3;// 归还成功
			} else
				return 4;// 归还失败
		}
	}

	@Override
	public int renewBook(int rid) {
		Record record = recordDao.queryRecordById(rid);
		if (record == null) {
			return 0;// 没有相应的借阅信息
		} else if (record.getReturnTime() != null) {
			return 1;// 表示该书已经归还了不能续借
		} else {
			// 将查询出来的record的bid作为orderDao通过bid查询方法的参数
			// 如果有其他用户预约过这本书，就不可以续借
			Order order = orderDao.queryOrderByBid(record.getBid());
			if (order != null) {
				return 2;// 表示该书已经被别人预约，不可以续借
			} else {

				record.setLendTime(new SimpleDateFormat("yyyy-MM-dd")
						.format(new Date()));// 将新的时间记录，作为记录时间
				record.setRenew(1);
				boolean flag1 = recordDao.updateRecord(record);// 更新记录表
				User user = userDao.queryUserById(record.getUid());

				user.setIntegration(user.getIntegration() - 1);
				boolean flag2 = userDao.updateUser(user);
				if (flag1 && flag2) {
					return 3;// 表示续借成功
				} else
					return 4;// 续借失败，联系管理员
			}

		}
	}

	@Override
	public int orderBook(int bid, int uid) {

		// 查看是否预约过书籍
		List<Order> orders = orderDao.findAllOrders();

		for (Order order : orders) {
			if (order.getUid() == uid && order.getStatus() == 1) {
				return 4;// 已经预约过一本书了不能继续预约
			}
		}
		// 首先查看是否预约过这本书
		Order order_by_uid = orderDao.queryOrderByUId(uid);

		if (order_by_uid != null && order_by_uid.getStatus() == 1) {
			return 0;// 已经预约过这本书，不可以重新预约
		} else {
			// 查看是否有其他用户预约过这本书
			Order order_by_bid = orderDao.queryOrderByBid(bid);

			if (order_by_bid == null) {

				boolean flag1 = false; // 作为保存预约表和更新书籍表的判断
				boolean flag2 = false;
				// 查询书籍的状态
				Book book = bookDao.queryBookByID(bid);
				if (book.getStatus() == 0) {
					// 书籍状态等于0，可以将当前预约时间当做预约时间，时间期限为3天,同时添加一个新的预约记录
					Order order = new Order(uid, bid, new SimpleDateFormat(
							"yyyy-MM-dd").format(new Date()), 1);
					flag1 = orderDao.saveOrder(order);// 添加预约记录
					// 更新书籍状态
					book.setStatus(3);
					flag2 = bookDao.updateBook(book);// 更新书籍状态
					// 如果书籍已经被借了，则记录用户归还时间作为预约时间
				} else if (book.getStatus() == 1) {

					Record record = recordDao.queryReocrdByBookId(bid);
					if (record.getBid() == bid && record.getUid() == uid) {
						return 5;// 代表书是用户借的，不能再被预约了
					}
					String returnTime = record.getReturnTime();
					Order order = new Order(uid, bid, returnTime, 1);
					flag1 = orderDao.saveOrder(order);
					// 更新书籍状态
					book.setStatus(2);
					flag2 = bookDao.updateBook(book);
				}
				if (flag1 && flag2) {
					return 2;// 预约成功
				} else
					return 3;// 预约失败，联系管理员
			} else {
				return 1;// 已经有其他用户预约过这本书，不可以预约
			}
		}

	}

	@Override
	public int updateData() {
		// 首先当用户借书时间超过30天时，如果还没有归还，用户积分-1
		int days = 0;

		List<Record> recordsList = recordDao.findAllRecords();

		for (Record record : recordsList) {
			if (record.getReturnTime() == null) {
				User user = userDao.queryUserById(record.getUid());
				if (user != null) {
					if (user.getIntegration() <= 1) {
						break;
					}
					try {
						String return_day = DaysDifferUtil.daysCount(
								record.getLendTime(), 30);
						days = DaysBetweenUtil.daysBetween(
								record.getLendTime(), return_day);
					} catch (ParseException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					if (Math.abs(days) > 30)
						user.setIntegration(user.getIntegration() - 1);
					boolean flag1 = userDao.updateUser(user);
					System.out.println("flag1: " + flag1);
					if (flag1) {
						return 1;// 用户更新成功
					} else
						return 2;// 用户更新失败

				}
			}

			// 2、预约时间限定取消
			List<Order2> orders = orderDao.queryAllOrders();
			for (Order2 order2 : orders) {
				if (order2.getOrderTime() != null) {
					try {
						String return_day = DaysDifferUtil.daysCount(
								order2.getOrderTime(), 3);
						days = DaysBetweenUtil.daysBetween(
								order2.getOrderTime(), return_day);
					} catch (ParseException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					// 如果预约时间超过3天的话就取消预约，并且设置book表里面的书籍状态为可借
					if (Math.abs(days) > 3)
						order2.setStatus(0);
					Book book = bookDao.queryBookByID(order2.getBid());
					book.setStatus(0);
					boolean flag2 = bookDao.updateBook(book);
					if (flag2) {
						return 3;// 预约表更新成功
					} else {
						return 4;// 预约表更新失败
					}
				}
			}
		}
		return 5;// 出错

	}
}
