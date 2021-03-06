package com.library.view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.event.TableModelListener;
import javax.swing.table.TableModel;

import com.library.biz.BookBiz;
import com.library.biz.imp.BookBizImp;
import com.library.entity.Book;
import com.library.entity.User;
import com.library.util.InputJudgeUtil;

	//用户书籍操作视图
public class UserBookOperateView extends JInternalFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel panel_table = null;// 用来保存JTable的面板
	private JTable table = null;// 表格控件
	private JPanel panel_bottom = null;// 底部面板
	private JPanel panel_btn = null;// 按钮面板
	private JButton btn_query = null;// 查询按钮
	private JButton btn_lend = null;// 借书按钮
	private JButton btn_order = null;// 预约书籍按钮
	private JButton btn_exit = null;// 退出按钮
	private JComboBox<String> cb_type = null;// 书籍查询类型列表框
	private JComboBox<String> cb_status = null;// 书籍状态下拉列表框
	private JTextField tf_bname = null;// 书籍名字文本框
	private JTextField tf_bcount = null;// 书籍借出次数文本框
	private JTextField tf_search = null;// 搜索书籍文本框
	private JTextField tf_author = null;// 书籍作者文本框
	private JTextField tf_publish_time = null;// 书籍出版时间文本框

	private JLabel lb_type = null;// 查询类型
	private JLabel lb_bname = null;// 书籍名字
	private JLabel lb_author = null;// 书籍作者
	private JLabel lb_publish_time = null;// 出版时间

	private JLabel lb_bcount = null;// 书籍借出次数
	private JLabel lb_bstatus = null;// 书籍状态
	private BookBiz bookBiz = null;// 创建业务层的对象
	private List<Book> bookList = null;// 保存查询出来的所有dvd的信息
	private BookInfoTableModel infoTableModel = null;
	private User user = null;

	public UserBookOperateView(User user) {
		this.user = user;
		bookBiz = new BookBizImp();
		init();
		registListener();
	}

	private void init() {

		bookList = new ArrayList<Book>();// 初始化一个bookList的ArrayList集合
		this.setTitle("图书操作界面");
		this.setSize(900, 700);
		this.setIconifiable(true);// 窗体可以最小化
		this.setClosable(true);// 窗体可被关闭
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);// 设置窗体关闭自动关闭程序
		this.setLayout(new BorderLayout());// 默认顶层容器BorderLayout
		panel_table = new JPanel(new BorderLayout());// 创建面板
		// 设置table面板边框
		panel_table.setBorder(BorderFactory.createTitledBorder(
				BorderFactory.createEtchedBorder(null, null), "查询信息"));
		// 给JTable初始化
		table = new JTable();
		table.getTableHeader().setPreferredSize(new Dimension(0, 30));
		table.setRowHeight(20);
		// 让JTable绑定数据模型，让Table呈现数据
		refreshTable(bookList);
		// 设置底部面板边框
		panel_bottom = new JPanel(new GridLayout(3, 4));// 定义三行行四列的流式管理
		panel_bottom.setSize(800, 20);// 设置底部panel的宽、长
		// 底部控件初始化
		lb_bname = new JLabel("书籍名字");
		lb_bcount = new JLabel("借出次数");
		lb_bstatus = new JLabel("书籍状态");
		lb_author = new JLabel("书籍作者");
		lb_publish_time = new JLabel("出版时间");
		tf_bname = new JTextField();
		tf_bcount = new JTextField();
		tf_author = new JTextField();
		tf_publish_time = new JTextField();
		cb_status = new JComboBox<String>(new String[] { "可借", "已借", "已借，被预约",
				"预约中" });
		tf_bname.setEditable(false);
		tf_bcount.setEditable(false);
		tf_author.setEditable(false);
		tf_publish_time.setEditable(false);
		// 将底部控件添加到底部面板中
		panel_bottom.add(lb_bname);
		panel_bottom.add(tf_bname);
		panel_bottom.add(lb_author);
		panel_bottom.add(tf_author);
		panel_bottom.add(lb_publish_time);
		panel_bottom.add(tf_publish_time);
		panel_bottom.add(lb_bcount);
		panel_bottom.add(tf_bcount);
		panel_bottom.add(lb_bstatus);
		panel_bottom.add(cb_status);

		table.setSize(800, 900);
		// 添加table,使用ScrollPane添加
		JScrollPane jsp = new JScrollPane(table);
		panel_table.add(jsp, BorderLayout.NORTH);
		// 添加底部panel
		panel_table.add(panel_bottom, BorderLayout.SOUTH);
		panel_table.add(panel_bottom);
		this.add(panel_table, BorderLayout.CENTER);

		// 设置btn面板边框，以及添加控件
		panel_btn = new JPanel(new GridLayout(9, 1, 10, 30));
		panel_btn.setBorder(BorderFactory.createTitledBorder(
				BorderFactory.createEtchedBorder(null, null), "查询条件"));
		this.add(panel_btn, BorderLayout.EAST);
		// combox空间初始化
		cb_type = new JComboBox<String>(new String[] { "全部书籍", "模糊书名", "指定书号",
				"指定作者", "查看可借", "查看不可借" });
		// 查询类型label初始化
		lb_type = new JLabel("查询类型");
		btn_query = new JButton("查询");
		btn_lend = new JButton("借阅书籍");

		btn_order = new JButton("预约书籍");
		btn_exit = new JButton("退出");
		tf_search = new JTextField();

		tf_search.setEditable(false);// 默认不可用，当查询的名字或者id的时候可以用
		panel_btn.add(lb_type);
		panel_btn.add(cb_type);
		panel_btn.add(tf_search);
		panel_btn.add(btn_query);
		panel_btn.add(btn_lend);
		panel_btn.add(btn_order);
		panel_btn.add(new JLabel());
		panel_btn.add(new JLabel());
		panel_btn.add(btn_exit);
		this.setVisible(true);

	}

	private void registListener() {
		btn_exit.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				int flag = JOptionPane.showInternalConfirmDialog(
						UserBookOperateView.this, "是否确认退出？", "确认信息",
						JOptionPane.YES_NO_CANCEL_OPTION);
				if (flag == JOptionPane.YES_OPTION) {
					UserBookOperateView.this.dispose();
				}
			}

		});
		// 删除按钮监听
		btn_order.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				int row = table.getSelectedRow();
				int bid = (Integer) table.getValueAt(row, 0);
				int flag = JOptionPane.showInternalConfirmDialog(
						UserBookOperateView.this, "是否预约书籍？", "确认信息",
						JOptionPane.YES_NO_CANCEL_OPTION);
				if (flag == JOptionPane.YES_OPTION) {
					int res = bookBiz.orderBook(bid, user.getId());

					if (res == 0) {
						JOptionPane.showInternalMessageDialog(
								UserBookOperateView.this, "已经预约过该书，不可以重新预约！");
					} else if (res == 1) {
						JOptionPane.showInternalMessageDialog(
								UserBookOperateView.this, "预约失败，该书已被其他用户预约！");
					} else if (res == 2) {
						JOptionPane.showInternalMessageDialog(
								UserBookOperateView.this, "预约成功！");
					} else if (res == 3) {
						JOptionPane.showInternalMessageDialog(
								UserBookOperateView.this, "预约失败，请联系管理员！");
					} else if (res == 4) {
						JOptionPane.showInternalMessageDialog(
								UserBookOperateView.this, "预约失败，你已经预约过一本书！");
					} else if (res == 5) {
						JOptionPane.showInternalMessageDialog(
								UserBookOperateView.this, "预约失败，你已经借阅此书！");
					}
				}

			}

		});

		// 添加选中项鼠标监听事件
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				super.mouseClicked(e);
				if (table.getSelectedColumn() != -1) {// 假设选中一行，预约、续借按钮可用
					btn_order.setEnabled(true);
				}
				int row = table.getSelectedRow();// 得到选中行下标
				String bname = table.getValueAt(row, 1).toString(); // 获得选中行名字
				String author = table.getValueAt(row, 2).toString();
				String publish_time = table.getValueAt(row, 3).toString();
				String bcount = table.getValueAt(row, 4).toString();
				String status = table.getValueAt(row, 5).toString();

				tf_bname.setText(bname);
				tf_author.setText(author);
				tf_publish_time.setText(publish_time);
				tf_bcount.setText(bcount);
				cb_status.setSelectedItem(status);
				ArrayList<String> list = new ArrayList<String>();
				list.add(table.getValueAt(row, 0).toString());
				list.add(bname);
				list.add(author);
				list.add(publish_time);
				
				if (e.getClickCount() == 2) {
					new BookInfoView(list);
				}
			}
		});
		// 选项监听
		cb_type.addItemListener(new ItemListener() {

			@Override
			public void itemStateChanged(ItemEvent e) {
				String item = e.getItem().toString();
				tf_search.setText("");// 清空文本框内容
				if (item.equals("全部书籍")) {
					tf_search.setEditable(false);
				} else {
					tf_search.setEditable(true);
				}
			}
		});
		// 查询按钮注册监听事件
		btn_query.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				int index = cb_type.getSelectedIndex();
				String content = tf_search.getText().trim();
				if ((index == 1 || index == 2 || index == 3)
						&& content.equals("")) {
					JOptionPane.showInternalMessageDialog(
							UserBookOperateView.this, "查询内容不能为空！");
					return;
				}
				// 先dvdList清除数据，防止数据的累加
				if (bookList != null) {
					bookList.clear();
				}
				// "全部书籍", "指定书名","指定书号","指定作者" ,"查看可借","查看不可借"
				if (index == 0) {
					bookList = bookBiz.queryAllBooks();

				} else if (index == 1) {
					bookList = bookBiz.queryBookByName(content);
				} else if (index == 2) {
					boolean flag = InputJudgeUtil.isNumber(content);
					if (flag) {
						Book book = bookBiz.queryBookById(Integer
								.parseInt(content));
						if (book != null) {
							bookList.add(book);
						}
					} else {
						JOptionPane.showInternalMessageDialog(
								UserBookOperateView.this, "书籍书号只能为数字");
					}
				} else if (index == 3) {
					bookList = bookBiz.queryByAuthor(content);
				} else if (index == 4) {
					bookList = bookBiz.canLendBook();
				} else {
					bookList = bookBiz.hasLendedBook();
					System.out.println(bookList);
				}
				if (bookList != null) {
					refreshTable(bookList);
					btn_order.setEnabled(false);

				} else {
					JOptionPane.showInternalMessageDialog(
							UserBookOperateView.this, "没有你要查询的内容！");
					return;
				}

			}
		});
		// 添加按钮注册监听事件
		btn_lend.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				int row = table.getSelectedRow();
				String bid = table.getValueAt(row, 0).toString();
				int flag = JOptionPane.showInternalConfirmDialog(
						UserBookOperateView.this, "是否确认借阅？", "确认信息",
						JOptionPane.YES_NO_CANCEL_OPTION);
				if (flag == JOptionPane.YES_OPTION) {

					int res = bookBiz.lendBook(new Integer(bid), user.getId());
					System.out.println(res);
					if (res == 1) {
						JOptionPane.showInternalMessageDialog(
								UserBookOperateView.this, "该书籍已经被借出去了！");
						return;
					} else if (res == 2) {
						JOptionPane.showInternalMessageDialog(
								UserBookOperateView.this, "借阅成功！");
						return;
					} else if (res == 3) {
						JOptionPane.showInternalMessageDialog(
								UserBookOperateView.this, "借阅失败！");
						return;
					} else if (res == 4) {
						JOptionPane.showInternalMessageDialog(
								UserBookOperateView.this, "预约完成，现已借阅成功！");
						return;
					} else if (res == 5) {
						JOptionPane.showInternalMessageDialog(
								UserBookOperateView.this, "你的积分不够，无法借阅，请联系管理员");
						return;
					} else if (res == 6) {
						JOptionPane.showInternalMessageDialog(
								UserBookOperateView.this, "该书已被预约，借阅失败！");
						return;
					}
				}
			}
		});
	}

	private class BookInfoTableModel implements TableModel {
		private List<Book> bookList = null;

		// 将保存的数据传入
		public BookInfoTableModel(List<Book> bookList) {
			this.bookList = bookList;
		}

		// JTable数据的行数
		@Override
		public int getRowCount() {
			return bookList.size();
		}

		// JTable数据的列数
		@Override
		public int getColumnCount() {
			return 6; // 书籍id，名字，作者，出版时间，借出次数，状态
		}

		// JTable的显示列名
		@Override
		public String getColumnName(int columnIndex) {
			if (columnIndex == 0) {
				return "图书书号";
			} else if (columnIndex == 1) {
				return "图书名字";
			} else if (columnIndex == 2) {
				return "图书作者";
			} else if (columnIndex == 3) {
				return "出版时间";
			} else if (columnIndex == 4) {
				return "借出次数";
			} else if (columnIndex == 5) {
				return "图书状态";
			} else
				return "出错";
		}

		// JTable列的数据类型
		@Override
		public Class<?> getColumnClass(int columnIndex) {
			return String.class;
		}

		// 设置JTable单元格是否可以编辑
		@Override
		public boolean isCellEditable(int rowIndex, int columnIndex) {
			return false;// 不可编辑
		}

		// 获得JTable 第几行，第几列的值
		@Override
		public Object getValueAt(int rowIndex, int columnIndex) {
			Book book = bookList.get(rowIndex);// 得到的就是Book对象
			if (columnIndex == 0) {
				return book.getId();
			} else if (columnIndex == 1) {
				return book.getBookName();
			} else if (columnIndex == 2) {
				return book.getBookAuthor();
			} else if (columnIndex == 3) {
				return book.getPublishTime();
			} else if (columnIndex == 4) {
				return book.getBookCount();
			} else if (columnIndex == 5) {
				if (book.getStatus() == 0)
					return "可借";
				else if (book.getStatus() == 1)
					return "已借";
				else if (book.getStatus() == 2)
					return "已借，被预约";
				else if (book.getStatus() == 3)
					return "预约中";
			}
			return "出错";

		}

		// 当单元格值发生变化的时候修改
		@Override
		public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
			// TODO Auto-generated method stub

		}

		// 给JTable添加事件的时候触发
		@Override
		public void addTableModelListener(TableModelListener l) {
			// TODO Auto-generated method stub

		}

		// 给JTable删除事件的时候触发
		@Override
		public void removeTableModelListener(TableModelListener l) {
			// TODO Auto-generated method stub

		}

	}

	/**
	 * 刷新JTable，并显示数据
	 * 
	 * @param bookList
	 */
	private void refreshTable(List<Book> bookList) {
		infoTableModel = new BookInfoTableModel(bookList);
		// 让Table绑定数据模型
		table.setModel(infoTableModel);
	}
}
