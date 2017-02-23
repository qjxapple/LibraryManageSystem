package com.library.view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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

import com.library.biz.RecordBiz;
import com.library.biz.imp.RecordBizImp;
import com.library.entity.Record2;

//用户查询租赁窗口
public class AdminRecordManageView extends JInternalFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel panel_table = null;// 用来保存JTable的面板
	private JTable table = null;// 表格控件
	private JPanel panel_btn = null;// 按钮面板
	private JButton btn_query = null;// 查询按钮
	private JTextField tf_search = null;// 查询指定用户或书籍的文本框
	private JButton btn_exit = null;// 退出按钮
	private JComboBox<String> cb_type = null;// 下来列表框
	private JLabel lb_type = null;// 查询类型
	private RecordBiz recordBiz = null;// 创建业务层的对象
	private List<Record2> recordList = null;// 保存查询出来的所有record的信息
	private RecordInfoTableModel infoTableModel = null;

	public AdminRecordManageView() {
		recordBiz = new RecordBizImp();
		init();
		registListener();
	}

	private void init() {
		recordList = new ArrayList<Record2>() ;
		this.setTitle("书籍借阅记录查询");
		this.setSize(800, 500);
		this.setIconifiable(true);// 窗体可以最小化
		this.setClosable(true);// 窗体可被关闭
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);// 设置窗体关闭自动关闭程序
		this.setLayout(new BorderLayout());// 默认顶层容器BorderLayout
		panel_table = new JPanel(new BorderLayout());// 创建面板
		// 设置table面板边框
		panel_table.setBorder(BorderFactory.createTitledBorder(
				BorderFactory.createEtchedBorder(null, null), "借阅记录查询"));
		table = new JTable();
		JScrollPane jsp = new JScrollPane(table);
		
		// 添加table
		refreshTable(recordList);
		panel_table.add(jsp);
		table.getTableHeader().setPreferredSize(new Dimension(0, 30));
		table.setRowHeight(20);
		this.add(panel_table, BorderLayout.CENTER);
		// 设置btn面板边框，以及添加空间
		panel_btn = new JPanel(new GridLayout(7, 1, 10, 30));
		panel_btn.setBorder(BorderFactory.createTitledBorder(
				BorderFactory.createEtchedBorder(null, null), "查询条件"));
		this.add(panel_btn, BorderLayout.EAST);
		// combox空间初始化
		cb_type = new JComboBox<String>(
				new String[] { "指定用户借阅记录", "指定书籍的借阅记录","全部借阅记录" });
		// 查询类型label初始化
		lb_type = new JLabel("查询类型");
		btn_query = new JButton("查询");
		btn_exit = new JButton("退出");
		tf_search = new JTextField();

		panel_btn.add(lb_type);
		panel_btn.add(cb_type);
		panel_btn.add(tf_search);
		panel_btn.add(btn_query);

		panel_btn.add(new JLabel());
		panel_btn.add(new JLabel());
		panel_btn.add(btn_exit);
		this.setVisible(true);

	}

	private void registListener() {
		btn_query.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				int index = cb_type.getSelectedIndex();
				String content = tf_search.getText().trim();
				if (index != 2 && content.equals("")) {
					JOptionPane.showInternalMessageDialog(
							AdminRecordManageView.this, "查询内容不能为空！");
					return;
				}
				// 先dvdList清除数据，防止数据的累加
				if (recordList != null) {
					recordList.clear();
				}
				
				if (index == 0) {
		
					recordList = recordBiz.queryUserRecords(content);// 查找指定用户名的记录
					
				} else if(index == 1){
					recordList =  recordBiz.queryBookRecords(content);// 直接通过书籍名字查询,保存到集合中
				}else if(index == 2) {
					recordList = recordBiz.queryAllRecords();//查询全部书籍借阅记录
				}
				
				refreshTable(recordList);

				if (recordList.size() == 0) {
					JOptionPane.showInternalMessageDialog(
							AdminRecordManageView.this, "没有你要查询的内容！");
				}

			}
		});

		btn_exit.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				int flag = JOptionPane.showInternalConfirmDialog(
						AdminRecordManageView.this, "是否确认退出？", "确认信息",
						JOptionPane.YES_NO_CANCEL_OPTION);
				if (flag == JOptionPane.YES_OPTION) {
					AdminRecordManageView.this.dispose();
				}
			}
		});
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
			return 5; //用户名，书籍名字，借出时间，归还时间，是否续借
		}

		// JTable的显示列名
		@Override
		public String getColumnName(int columnIndex) {
			if (columnIndex == 0) {
				return "用户名";
			} else if (columnIndex == 1) {
				return "书名";
			} else if (columnIndex == 2) {
				return "借出时间";
			} else if (columnIndex == 3) {
				return "归还时间";
			} else if(columnIndex == 4) 
				return "是否续借";
			else
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
				return record2.getUname();
			} else if (columnIndex == 1) {
				return record2.getBookName();
			} else if (columnIndex == 2) {
				return record2.getLendTime();
			} else if (columnIndex == 3) {
				return record2.getReturnTime();
			} else if(columnIndex == 4) {
				return ""+((record2.getRenew() == 0 ? "未续借":"续借"));
			}else{
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
}
