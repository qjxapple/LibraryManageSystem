package com.library.view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
import javax.swing.event.TableModelListener;
import javax.swing.table.TableModel;

import com.library.biz.BookBiz;
import com.library.biz.RecordBiz;
import com.library.biz.imp.BookBizImp;
import com.library.biz.imp.RecordBizImp;
import com.library.entity.Record2;
import com.library.entity.User;

//用户查询租赁窗口
public class UserBookRecordView extends JInternalFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel panel_table = null;// 用来保存JTable的面板
	private JTable table = null;// 表格控件
	private JPanel panel_btn = null;// 按钮面板
	private JButton btn_query = null;// 查询按钮
	private JButton btn_return = null;// 归还按钮
	private JButton btn_renew = null;// 续借按钮
	private JButton btn_exit = null;// 退出按钮
	private JComboBox<String> cb_type = null;// 下来列表框
	private JLabel lb_type = null;// 查询类型
	private RecordBiz recordBiz = null;// 创建业务层的对象
	private List<Record2> recordList = null;// 保存查询出来的所有record的信息
	private RecordInfoTableModel infoTableModel = null;
	private User user = null;
	private BookBiz bookBiz = null;// 创建业务层的对象

	public UserBookRecordView(User user) {
		this.user = user;
		bookBiz = new BookBizImp();
		recordBiz = new RecordBizImp();
		init();
		registListener();
	}

	private void init() {
		this.setTitle("图书借阅历史记录查询");
		this.setSize(800, 500);
		this.setIconifiable(true);// 窗体可以最小化
		this.setClosable(true);// 窗体可被关闭
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);// 设置窗体关闭自动关闭程序
		this.setLayout(new BorderLayout());// 默认顶层容器BorderLayout
		panel_table = new JPanel(new BorderLayout());// 创建面板
		// 设置table面板边框
		panel_table.setBorder(BorderFactory.createTitledBorder(
				BorderFactory.createEtchedBorder(null, null), "用户借阅记录查询"));

		table = new JTable();
		table.getTableHeader().setPreferredSize(new Dimension(0, 30));
		table.setRowHeight(20);
		JScrollPane jsp = new JScrollPane(table);
		// 添加table
		// refreshTable(recordList);
		panel_table.add(jsp);
		this.add(panel_table, BorderLayout.CENTER);
		// 设置btn面板边框，以及添加空间
		panel_btn = new JPanel(new GridLayout(7, 1, 10, 30));
		panel_btn.setBorder(BorderFactory.createTitledBorder(
				BorderFactory.createEtchedBorder(null, null), "查询条件"));
		this.add(panel_btn, BorderLayout.EAST);
		// combox空间初始化
		cb_type = new JComboBox<String>(new String[] { "全部借阅记录", "未归还记录",
				"已归还记录" });
		// 查询类型label初始化
		lb_type = new JLabel("查询类型");
		btn_query = new JButton("查询");
		btn_return = new JButton("归还");
		btn_renew = new JButton("续借");
		btn_exit = new JButton("退出");
		// btn_return.setEnabled(false);

		panel_btn.add(lb_type);
		panel_btn.add(cb_type);
		panel_btn.add(btn_query);
		panel_btn.add(btn_return);
		panel_btn.add(btn_renew);
		panel_btn.add(new JLabel());
		panel_btn.add(btn_exit);
		this.setVisible(true);
	}

	private void registListener() {
		btn_renew.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				int row = table.getSelectedRow();
				String rid = table.getValueAt(row, 0).toString();
				int flag = JOptionPane.showInternalConfirmDialog(
						UserBookRecordView.this, "是否确认续借书籍", "确认信息",
						JOptionPane.YES_NO_CANCEL_OPTION);
				if(flag == JOptionPane.YES_OPTION) {
					int res = bookBiz.renewBook(new Integer(rid));
					if(res == 0) {
						JOptionPane.showInternalMessageDialog(UserBookRecordView.this, "没有相应的借阅信息，不可以续借！");
						return ;
					}else if (res == 1) {
						JOptionPane.showInternalMessageDialog(UserBookRecordView.this, "该书已经归还了，不可以续借！");
						return ;
					}else if (res == 2) {
						JOptionPane.showInternalMessageDialog(UserBookRecordView.this, "该书已经被别人预约，不可以续借");
						return ;
					}else if (res == 3) {
						JOptionPane.showInternalMessageDialog(UserBookRecordView.this, "续借成功！");
						return ;
					}else if(res == 4) {
						JOptionPane.showInternalMessageDialog(UserBookRecordView.this, "续借失败，请联系管理员！");
						return ;
					}
				}
				
			}
		});
		btn_return.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				int row = table.getSelectedRow();// 得到选中行下标
				String rid = table.getValueAt(row, 0).toString();

				int flag = JOptionPane.showInternalConfirmDialog(
						UserBookRecordView.this, "是否确认归还书籍", "确认信息",
						JOptionPane.YES_NO_CANCEL_OPTION);
				if (flag == JOptionPane.YES_OPTION) {
					System.out.println("returnbook调用前userid:"+user.getId());
					int res = bookBiz.returnBook(user.getId(),new Integer(rid));
					
					if (res == 1) {
						JOptionPane.showInternalMessageDialog(
								UserBookRecordView.this, "输入不正确，没有对应的借阅信息");
					} else if (res == 2) {
						JOptionPane.showInternalMessageDialog(
								UserBookRecordView.this, "书籍已经归还！");
					} else if (res == 3) {
						JOptionPane.showInternalMessageDialog(
								UserBookRecordView.this, "归还成功！");
					} else if (res == 4) {
						JOptionPane.showInternalMessageDialog(
								UserBookRecordView.this, "归还失败，请联系管理员！");
					}else if(res == 5) {
						JOptionPane.showInternalMessageDialog(
								UserBookRecordView.this, "该书不是你借阅的不能借阅！！");
						
					}
				}
			}
		});
		btn_query.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				int index = cb_type.getSelectedIndex();

				// 先dvdList清除数据，防止数据的累加
				if (recordList != null) {
					recordList.clear();
				}

				if (index == 0) {
					recordList = recordBiz.queryUserAllRecords(user.getId());// 查询全部的记录
				} else if (index == 1) {
					recordList = recordBiz.queryNoReturnedRecords(user
							.getUname());// 查询未归还的租赁
				} else if (index == 2) {
					recordList = recordBiz.queryHasReturnedRecords(user
							.getUname());// 查询已经归还的记录
				}

				refreshTable(recordList);

				if (recordList.size() == 0) {
					JOptionPane.showInternalMessageDialog(
							UserBookRecordView.this, "没有你要查询的内容！");
				}

			}
		});

		btn_exit.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				int flag = JOptionPane.showInternalConfirmDialog(
						UserBookRecordView.this, "是否确认退出？", "确认信息",
						JOptionPane.YES_NO_CANCEL_OPTION);
				if (flag == JOptionPane.YES_OPTION) {
					UserBookRecordView.this.dispose();
				}
			}
		});
	}

	/**
	 * 刷新JTable，并显示数据
	 * 
	 * @param recordList
	 */
	private void refreshTable(List<Record2> recordList) {
		infoTableModel = new RecordInfoTableModel(recordList);

		// 让Table绑定数据模型
		table.setModel(infoTableModel);
	}

	private class RecordInfoTableModel implements TableModel {
		private List<Record2> recordList = null;

		// 将保存的数据传入
		public RecordInfoTableModel(List<Record2> recordList) {
			this.recordList = recordList;

		}

		// JTable数据的行数
		@Override
		public int getRowCount() {
			return recordList.size();
		}

		// JTable数据的列数
		@Override
		public int getColumnCount() {
			return 7; // 记录表id，书籍id，用户名，书籍名字，借出时间，归还时间，是否被续借
		}

		// JTable的显示列名
		@Override
		public String getColumnName(int columnIndex) {
			if (columnIndex == 0) {
				return "记录ID号";
			} else if (columnIndex == 1) {
				return "书籍ID";
			} else if (columnIndex == 2) {
				return "用户名";
			} else if (columnIndex == 3) {
				return "书籍名字";
			} else if (columnIndex == 4) {
				return "借出时间";
			} else if (columnIndex == 5) {
				return "归还时间";
			} else if (columnIndex == 6)
				return "是否被续借";
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
			Record2 record2 = recordList.get(rowIndex);// 得到的就是Record2对象

			if (columnIndex == 0) {
				return record2.getId();
			} else if (columnIndex == 1) {
				return record2.getBid();
			} else if (columnIndex == 2) {
				return record2.getUname();
			} else if (columnIndex == 3) {
				return record2.getBookName();
			} else if (columnIndex == 4) {
				return record2.getLendTime();
			} else if (columnIndex == 5) {
				return record2.getReturnTime();
			} else if (columnIndex == 6) {
				if(record2.getRenew() == 0) {
					return "未续借";
				}else 
					return "续借";
			} else {
				return "出错";
			}
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
}
