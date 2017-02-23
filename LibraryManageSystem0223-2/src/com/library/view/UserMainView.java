package com.library.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.BevelBorder;
import javax.swing.border.TitledBorder;

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
import com.library.entity.User;

public class UserMainView extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel main_panel = null;// 主面板
	private JPanel welcome_panel = null;// 欢迎面板
	private JDesktopPane functionDesktop = null;// 桌面面板
	private JPanel btn_panel = null;// 功能面板
	private JButton btn_query_rent_boook = null;// 书籍管理功能
	private JButton btn_query_order = null;// 查询预约记录
	private JButton btn_query_record = null;// 查看历史记录按钮
	private JButton btn_exit = null;// 退出按钮
	private JButton btn_setting = null;//设置
	private JButton btn_about = null;// 关于按钮
	private JLabel deskLabel = null;// 存放图片的按钮
	private JLabel lb_welcome = null;// 欢迎标题
	private User user = null;
	private UserDao userDao = null;
	private OrderDao orderDao = null;
	private BookDao bookDao = null;
	public UserMainView(User user) {
		
		userDao = new UserDaoImp();
		orderDao = new OrderDaoImp();
		bookDao = new BookDaoImp();
		this.user = user;
		init();
		registerListener();
	}

	

	private void init() {
		user = userDao.queryUser(user);
		Order order = orderDao.queryOrderByUId(user.getId());
		
		if(order != null) {
			Book book = bookDao.queryBookByID(order.getBid());
			if(book != null) {
				if(book.getStatus() == 3) {
					JOptionPane.showMessageDialog(UserMainView.this, "你预约的书籍已被归还，请速借阅！");
					
				}
			}
		}
		main_panel = new JPanel(new BorderLayout());// 使用boderlayout布局管理器
		btn_panel = new JPanel(new GridLayout(9, 1, 0, 35));
		btn_query_rent_boook = new JButton("图书操作");
		btn_query_record = new JButton("历史记录查询");
		btn_query_order = new JButton("预约记录查询");
		btn_about = new JButton("关于");
		btn_setting = new JButton("修改密码");
		btn_exit = new JButton("退出窗口");
		// 前几个用来填充空间标签
		btn_panel.add(btn_query_rent_boook); // 放置3个功能按钮
		btn_panel.add(btn_query_record);
		btn_panel.add(btn_query_order);
		btn_panel.add(new JLabel());
		btn_panel.add(new JLabel());
		btn_panel.add(new JLabel());
		btn_panel.add(btn_setting);
		btn_panel.add(btn_about);
		btn_panel.add(btn_exit);
		// 设置面板外框外观
		btn_panel.setBorder(BorderFactory.createTitledBorder(BorderFactory
				.createBevelBorder(BevelBorder.LOWERED), "快捷功能区",
				TitledBorder.CENTER, TitledBorder.TOP, new Font("宋体",
						Font.BOLD, 15), Color.RED));
		// 初始化欢迎面板
		welcome_panel = new JPanel();
		lb_welcome = new JLabel("欢	迎	用	户	" + user.getUname()
				+ "  使	用	图	书	管	理	系	统");
		lb_welcome.setFont(new Font("宋体", Font.BOLD, 23));
		lb_welcome.setForeground(Color.BLUE);
		welcome_panel.add(lb_welcome);
		// 初始化桌面面板
		functionDesktop = new JDesktopPane();
		ImageIcon imageIcon = new ImageIcon("src/images/bgimg.jpg");
		deskLabel = new JLabel(imageIcon);
		deskLabel.setBounds(0, 0, imageIcon.getIconWidth(),
				imageIcon.getIconHeight());
		functionDesktop.add(deskLabel, new Integer(Integer.MIN_VALUE));// 将图片放在最底层
		// 将各个面板放置到主面板中，上北下南的布局
		main_panel.add(btn_panel, BorderLayout.EAST);
		main_panel.add(welcome_panel, BorderLayout.NORTH);
		main_panel.add(functionDesktop, BorderLayout.CENTER);

		java.awt.EventQueue.invokeLater(new Runnable() {

			@Override
			public void run() {
				new Thread(new DynaminThread()).start();
			}
		});

		// 设置面板标题
		this.setTitle("图书管理系统");
		// 将主面板添加到
		this.getContentPane().add(main_panel);
		this.setSize(1200, 853);// 图片的宽、高
		this.setVisible(true);// 是否允许窗口可见
		this.setResizable(false);// 是否允许调整大小
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);

	}

	// 给按钮添加监听器
	private void registerListener() {
		btn_exit.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				int flag = JOptionPane.showConfirmDialog(UserMainView.this,
						"是否确认退出？", "确认信息", JOptionPane.YES_NO_CANCEL_OPTION);
				if (flag == JOptionPane.YES_OPTION) {
					UserMainView.this.dispose();
				}
			}
		});
		btn_setting.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				new UserInfoView(user);
				
			}
		});
		btn_about.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(UserMainView.this,
						"测试版关于对话框，以后可以显示关于版本信息，作者，版权信息等功能 "
						+ "Author:钱加祥"
						+ "Version:1.0.0");
			}
		});
		btn_query_order.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				UserBookOrderRecordView order_record_view = new UserBookOrderRecordView();
				functionDesktop.add(order_record_view);
				order_record_view.toFront();

			}
		});
		btn_query_rent_boook.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				UserBookOperateView ubook = new UserBookOperateView(user);
				functionDesktop.add(ubook);// 把指定的视图添加到桌面容器中
				ubook.toFront();// 将视图显示在最前面
			}
		});
		btn_query_record.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				UserBookRecordView record_view = new UserBookRecordView(user);
				functionDesktop.add(record_view);
				record_view.toFront();
			}
		});
	}

	/**
	 * 将欢迎label标签进行滚动
	 * 
	 * @author SkyEgine
	 *
	 */
	private class DynaminThread implements Runnable {

		@Override
		public void run() {
			while (true) {
				for (int i = 900; i > -750; i--) {
					try {

						Thread.sleep(10);
						lb_welcome.setLocation(i, 5);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		}

	}
}
