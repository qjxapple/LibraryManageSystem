package com.library.view;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;

import com.library.dao.BookDao;
import com.library.dao.implement.BookDaoImp;
import com.library.entity.Book;

//用户书籍操作视图
public class BookInfoView extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel panel_main = null;// 用来保存JTable的面板
	private JPanel panel_body = null;// 保存图书大概信息
	private JPanel panel_img = null;// 图片面板
	private JLabel lb_img = null;// 图片标签
	private JLabel lb_title = null;// 图书标题
	private JLabel lb_book_name = null;// 图书标题名字
	private JLabel lb_author = null;// 图书作者
	private JLabel lb_book_author = null;
	private JLabel lb_publish_time = null;// 出版时间
	private JLabel lb_book_publish_time = null;
	private ArrayList<String> list = null;
	private JTextArea ta_detail = null;// 图书详细介绍
	private BookDao bookDao = null;

	public BookInfoView(ArrayList<String> list) {
		bookDao = new BookDaoImp();
		this.list = list;
		init();
	}

	private void init() {
		String detail = "";
		this.setTitle("图书详细信息");
		this.setSize(400, 500);
		this.setLocationRelativeTo(null);
		this.setResizable(false);// 窗体大小不可拖动

		Book book = bookDao.queryBookDetailByID(new Integer(list.get(0)));
		// 书籍的详细介绍，如果为空，就显示没有书籍详情介绍
		if (book.getDetail() != null) {
			detail = book.getDetail();
		} else {
			detail = "暂时没有书籍详情介绍";
		}

		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);// 设置窗体关闭自动关闭程序
		this.setLayout(new BorderLayout());// 默认顶层容器BorderLayout
		this.setVisible(true);
		panel_img = new JPanel();

		panel_main = new JPanel(new BorderLayout(5, 15));
		panel_body = new JPanel(new GridLayout(3, 2, 5, 5));
		panel_body.setSize(400, 100);
		// 书籍详情一共9本，超过9本的显示状态暂时未更新图片
		if (new Integer(list.get(0)) > 9) {
			lb_img = new JLabel("暂时没有图片详情");
		} else {
			lb_img = new JLabel(new ImageIcon(
					ClassLoader.getSystemResource("images/"
							+ new Integer(list.get(0)) + ".png")));
		}
		lb_title = new JLabel("  图书标题：");
		lb_book_name = new JLabel(list.get(1));
		lb_author = new JLabel("  图书作者：");
		lb_book_author = new JLabel(list.get(2));
		lb_publish_time = new JLabel("  出版时间：");
		lb_book_publish_time = new JLabel(list.get(3));

		ta_detail = new JTextArea();
		ta_detail.setSize(200, 300);

		ta_detail.setText(detail);
		ta_detail.setLineWrap(true);
		panel_img.add(lb_img);
		panel_body.add(lb_title);
		panel_body.add(lb_book_name);
		panel_body.add(lb_author);
		panel_body.add(lb_book_author);
		panel_body.add(lb_publish_time);
		panel_body.add(lb_book_publish_time);
		ta_detail.setEditable(false);
		panel_main.add(panel_img, BorderLayout.NORTH);
		panel_main.add(ta_detail, BorderLayout.CENTER);
		panel_main.add(panel_body, BorderLayout.SOUTH);
		this.getContentPane().add(panel_main);
		// this.add(panel_main);
	}

}
