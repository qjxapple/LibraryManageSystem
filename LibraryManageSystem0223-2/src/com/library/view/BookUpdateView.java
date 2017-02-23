package com.library.view;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import com.library.biz.BookBiz;
import com.library.biz.imp.BookBizImp;
import com.library.entity.Book;
import com.library.util.InputJudgeUtil;

public class BookUpdateView extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel panel_main = null;// 用来保存JTable的面板
	private JPanel panel_body = null;
	private JPanel panel_detail = null;
	private JPanel panel_bottom = null;
	private JTextField tf_bname = null;// 书籍名字文本框
	private JTextField tf_bcount = null;// 书籍借出次数文本框
	private JTextField tf_author = null;// 书籍作者文本框
	private JTextField tf_publish_time = null;// 书籍出版时间文本框
	private JLabel lb_bname = null;// 书籍名字
	private JLabel lb_author = null;// 书籍作者
	private JLabel lb_publish_time = null;// 出版时间
	private JLabel lb_bcount = null;// 书籍借出次数
	private JLabel lb_detail = null;// 简介
	private JTextArea ta_detail = null;// 图书详情介绍
	private JButton btn_confirm = null;
	private JButton btn_back = null;
	private BookBiz bookBiz = null;// 创建业务层的对象
	private List<String> list = null;
	private Book book = null;
	public BookUpdateView(List<String> list) {
		this.list = list;
		bookBiz = new BookBizImp();
		init();
		registerListener();
	}

	private void init() {
		 book = bookBiz.queryBookById(new Integer(list.get(0)));
		
		// 控件初始化
		lb_bname = new JLabel("书籍名字： ");
		lb_author = new JLabel("书籍作者： ");
		lb_publish_time = new JLabel("出版时间： ");
		lb_bcount = new JLabel("借出次数： ");
		lb_detail = new JLabel("书籍简介：");
		lb_bname.setFont(new Font("宋体", Font.BOLD, 13));
		lb_author.setFont(new Font("宋体", Font.BOLD, 13));
		lb_publish_time.setFont(new Font("宋体", Font.BOLD, 13));
		lb_bcount.setFont(new Font("宋体", Font.BOLD, 13));
		lb_detail.setFont(new Font("宋体", Font.BOLD, 13));
		tf_bname = new JTextField(15);
		tf_bname.setPreferredSize(new Dimension(175, 20));
		tf_author = new JTextField(15);
		tf_publish_time = new JTextField(15);
		tf_bcount = new JTextField(15);
		ta_detail = new JTextArea();
		if(book != null) {
			tf_bname.setText(book.getBookName());
			tf_author.setText(book.getBookAuthor());
			tf_publish_time.setText(book.getPublishTime());
			tf_bcount.setText(list.get(4));
			ta_detail.setText(book.getDetail());
		}
		btn_confirm = new JButton("确认更新");
		btn_back = new JButton("返回");
		ta_detail.setLineWrap(true);
		ta_detail.setSize(350,500);
		panel_main = new JPanel(new GridLayout(3, 1,30,20));
		panel_body = new JPanel(new GridLayout(4,2,5,20));
		panel_detail = new JPanel();
		panel_bottom = new JPanel();
		
		panel_body.add(lb_bname);
		panel_body.add(tf_bname);
		panel_body.add(lb_author);
		panel_body.add(tf_author);
		panel_body.add(lb_publish_time);
		panel_body.add(tf_publish_time);
		panel_body.add(lb_bcount);
		panel_body.add(tf_bcount);
		
		
		panel_detail.add(ta_detail);
		
		panel_bottom.add(btn_confirm);
		panel_bottom.add(btn_back);
		panel_main.add(panel_body);
		panel_main.add(panel_detail);
		panel_main.add(panel_bottom);
		this.setTitle("更新图书信息");
		this.getContentPane().add(panel_main);
		this.setSize(350, 650);// 设置窗体大小
		this.setLocationRelativeTo(null);
		//this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);// 默认关闭
		this.setResizable(false);// 不可调整大小
		this.getRootPane().setDefaultButton(btn_confirm);// 默认获得焦点的按钮是确认按钮
		this.setVisible(true); // 设置可见性

	}

	private void registerListener() {
		btn_back.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				BookUpdateView.this.dispose();

			}
		});
		btn_confirm.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				String bname = tf_bname.getText().trim();
				String author = tf_author.getText().trim();
				String publish_time = tf_publish_time.getText().trim();
				String bcount = tf_bcount.getText().trim();
				String detail = ta_detail.getText().trim();
				if (bname.equals("")) {
					JOptionPane.showMessageDialog(
							BookUpdateView.this, "书籍名字不能为空！");
					return;
				} else if (bname.length() > 30) {
					JOptionPane.showMessageDialog(
							BookUpdateView.this, "书籍名字最长为30！");
					return;
				} else if (bcount.equals("")) {
					JOptionPane.showMessageDialog(
							BookUpdateView.this, "书籍借出次数不能为空！");
					return;
				} else if (bcount.length() > 4) {
					JOptionPane.showMessageDialog(
							BookUpdateView.this, "借出次数不能超过9999！");
					return;
				} else if (author.equals("")) {
					JOptionPane.showMessageDialog(
							BookUpdateView.this, "作者名字不能为空！");
					return;
				} else if (author.length() > 20) {
					JOptionPane.showMessageDialog(
							BookUpdateView.this, "作者名字长度超出限制！");
					return;
				} else if (publish_time.equals("")) {
					JOptionPane.showMessageDialog(
							BookUpdateView.this, "书籍出版时间不能为空！");
					return;
				} else if (!publish_time
						.matches("([0-9]{3}[1-9]|[0-9]{2}[1-9][0-9]{1}|[0-9]{1}[1-9][0-9]{2}|[1-9][0-9]{3})-(((0[13578]|1[02])-(0[1-9]|[12][0-9]|3[01]))|((0[469]|11)-(0[1-9]|[12][0-9]|30))|(02-(0[1-9]|[1][0-9]|2[0-8])))")) {
					JOptionPane.showMessageDialog(
							BookUpdateView.this, "日期格式输入错误，应为yyyy-MM-dd！");
					return;
				} else if (!InputJudgeUtil.isNumber(bcount)) {
					JOptionPane.showMessageDialog(
							BookUpdateView.this, "书籍借出次数只能为数字！");
				}
				int flag = JOptionPane.showConfirmDialog(
						BookUpdateView.this, "是否更新该书籍？", "确认信息",
						JOptionPane.YES_NO_OPTION);
				if (flag == JOptionPane.YES_OPTION) {
					//new Book(bname,author,publish_time,new Integer(bcount),0,detail)
					book.setDetail(detail);
					book.setBookName(bname);
					book.setBookAuthor(author);
					book.setBookCount(new Integer(bcount));
					book.setPublishTime(publish_time);
					book.setStatus(0);
					boolean res = bookBiz.modifyBook(book);
					if(res) {
						JOptionPane.showMessageDialog(
								BookUpdateView.this, "更新成功！");
						BookUpdateView.this.dispose();
					}else {
						JOptionPane.showMessageDialog(
								BookUpdateView.this, "更新失败，请重试！");
						return;
					}
				}
			}

		});
	}
}
