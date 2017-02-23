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

import com.library.biz.UserBiz;
import com.library.biz.imp.UserBizImp;
import com.library.entity.User;
import com.library.util.InputJudgeUtil;

//用户查询租赁窗口
public class AdminUserManageView extends JInternalFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel panel_table = null;// 用来保存JTable的面板
	private JTable table = null;// 表格控件
	private JPanel panel_btn = null;// 按钮面板
	private JButton btn_query = null;// 查询按钮
	private JButton btn_add = null;// 添加用户按钮
	private JButton btn_delete = null;// 删除用户按钮
	private JButton btn_exit = null;// 退出按钮
	private JComboBox<String> cb_type = null;// 用户查询类型列表框
	private JTextField tf_search = null;//查询文本框
	private JLabel lb_type = null;// 查询类型
	private UserBiz userBiz = null;// 创建业务层的对象
	private List<User> userList= null;// 保存查询出来的所有dvd的信息
	private UserInfoTableModel infoTableModel = null;

	public AdminUserManageView() {
		userBiz = new UserBizImp();
		init();
		registListener();
	}

	private void init() {
		userList = new ArrayList<User>();// 初始化一个bookList的ArrayList集合
		this.setTitle("用户信息更新");
		this.setSize(800, 530);
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
		refreshTable(userList);
		// 设置底部面板边框
		// 底部控件初始化
		tf_search = new JTextField();
		// 将底部控件添加到底部面板中
		table.setSize(800, 900);
		// 添加table,使用ScrollPane添加
		JScrollPane jsp = new JScrollPane(table);
		panel_table.add(jsp, BorderLayout.NORTH);
		// 添加底部panel
		this.add(panel_table, BorderLayout.CENTER);

		// 设置btn面板边框，以及添加控件
		panel_btn = new JPanel(new GridLayout(8, 1, 10, 30));
		panel_btn.setBorder(BorderFactory.createTitledBorder(
				BorderFactory.createEtchedBorder(null, null), "查询条件"));
		this.add(panel_btn, BorderLayout.EAST);
		// combox空间初始化
		cb_type = new JComboBox<String>(new String[] { "全部用户", "指定ID用户"});
		// 查询类型label初始化
		lb_type = new JLabel("查询类型");
		btn_query = new JButton("查询");
		
		btn_add = new JButton("增加用户");
		btn_delete = new JButton("删除用户");
		btn_exit = new JButton("退出");

		panel_btn.add(lb_type);
		panel_btn.add(cb_type);
		panel_btn.add(tf_search);
		panel_btn.add(btn_query);
		panel_btn.add(btn_add);
		panel_btn.add(btn_delete);
		panel_btn.add(new JLabel());
		panel_btn.add(btn_exit);
		this.setVisible(true);

	}

	private void registListener() {
		btn_exit.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				int flag = JOptionPane.showInternalConfirmDialog(
						AdminUserManageView.this, "是否确认退出？", "确认信息",
						JOptionPane.YES_NO_CANCEL_OPTION);
				if (flag == JOptionPane.YES_OPTION) {
					AdminUserManageView.this.dispose();
				}
			}

		});
		// 删除按钮监听
		btn_delete.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				int row = table.getSelectedRow();
				int uid = (Integer) table.getValueAt(row, 0);
				int flag = JOptionPane.showInternalConfirmDialog(
						AdminUserManageView.this, "是否删除用户？", "确认信息",
						JOptionPane.YES_NO_CANCEL_OPTION);
				if (flag == JOptionPane.YES_OPTION) {
					boolean res = userBiz.deleteUser(uid);
					if (res) {
						JOptionPane.showInternalMessageDialog(
								AdminUserManageView.this, "删除成功！");
					} else {
						JOptionPane.showInternalMessageDialog(
								AdminUserManageView.this, "删除失败，请联系管理员！");
					}
				}

			}

		});
	
		// 添加选中项鼠标监听事件
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				super.mouseClicked(e);
				if (table.getSelectedColumn() != -1) {// 假设选中一行，更新删除按钮可用
					btn_delete.setEnabled(true);
				}
				int row = table.getSelectedRow();// 得到选中行下标
				String uname = table.getValueAt(row, 1).toString(); // 获得选中行名字
				String upass = table.getValueAt(row, 2).toString();
				String integration = table.getValueAt(row, 4).toString();
				
				
				
				
				ArrayList<String > list = new ArrayList<String>();
				list.add(table.getValueAt(row, 0).toString());
				list.add(uname);
				list.add(upass);
				list.add(integration);
				if(e.getClickCount() == 2) {
					new UserUpdateView(list);
				}
			}
		});
		// 选项监听
		cb_type.addItemListener(new ItemListener() {

			@Override
			public void itemStateChanged(ItemEvent e) {
				String item = e.getItem().toString();
				tf_search.setText("");// 清空文本框内容
				if (item.equals("全部用户")) {
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
				if (index !=0 && content.equals("")) {
					JOptionPane.showInternalMessageDialog(
							AdminUserManageView.this, "查询内容不能为空！");
					return;
				}
				// 先dvdList清除数据，防止数据的累加
				if (userList != null) {
					userList.clear();
				}
				// 全部用户，指定id用户
				if (index == 0) {
					userList = userBiz.queryAllUsers();

				} else  {
					boolean flag = InputJudgeUtil.isNumber(content);
					if (flag) {
						if(new Integer(content) == 0) {
							JOptionPane.showInternalMessageDialog(
									AdminUserManageView.this, "没有ID为0的用户！");
							return ;
						}
						if(new Integer(content) < 0) {
							JOptionPane.showInternalMessageDialog(
									AdminUserManageView.this, "输入有误");
							return;
						}
						User user = userBiz.queryUserById(new Integer(content));
						if (user != null) {
							userList.add(user);
						}
					} else {
						JOptionPane.showInternalMessageDialog(
								AdminUserManageView.this, "输入有误");
					}
				}
				if (userList != null) {
					refreshTable(userList);
					btn_delete.setEnabled(false);
					
				}else {
					JOptionPane.showInternalMessageDialog(
							AdminUserManageView.this, "没有你要查询的内容！");
					return;
				}
				
				
			}
		});
		// 添加按钮注册监听事件
		btn_add.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				new UserAddView();
				
			}
		});
	}

	private class UserInfoTableModel implements TableModel {
		private List<User> userList = null;

		// 将保存的数据传入
		public UserInfoTableModel(List<User> userList) {
			this.userList = userList;
		}

		// JTable数据的行数
		@Override
		public int getRowCount() {
			return userList.size();
		}

		// JTable数据的列数
		@Override
		public int getColumnCount() {
			return 5; //用户id，用户名，密码，类型，积分
		}

		// JTable的显示列名
		@Override
		public String getColumnName(int columnIndex) {
			if (columnIndex == 0) {
				return "用户ID";
			} else if (columnIndex == 1) {
				return "用户姓名";
			} else if (columnIndex == 2) {
				return "用户密码";
			} else if (columnIndex == 3) {
				return "用户类型";
			} else if (columnIndex == 4) {
				return "用户积分";
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
			User user = userList.get(rowIndex);// 得到的就是User对象
			if (columnIndex == 0) {
				return user.getId();
			} else if (columnIndex == 1) {
				return user.getUname();
			} else if (columnIndex == 2) {
				return user.getUpass();
			} else if (columnIndex == 3) {
				
				return ""+((user.getType() == 0) ? "用户" :"管理员");
			} else if (columnIndex == 4) {
				
				return user.getIntegration();
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
	private void refreshTable(List<User> userkList) {
		infoTableModel = new UserInfoTableModel(userkList);
		// 让Table绑定数据模型
		table.setModel(infoTableModel);
	}
}
