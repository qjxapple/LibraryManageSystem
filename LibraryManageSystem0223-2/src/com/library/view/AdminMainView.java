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

import com.library.biz.BookBiz;
import com.library.biz.imp.BookBizImp;
import com.library.entity.User;

public class AdminMainView extends JFrame {
	private static final long serialVersionUID = 1L;
	private JPanel main_panel = null;// 主面板
	private JPanel welcome_panel = null;// 欢迎面板
	private JDesktopPane functionDesktop = null;// 桌面面板
	private JPanel btn_panel = null;// 功能面板
	private JButton btn_query_rent_book = null;// 查询书籍借还功能
	private JButton btn_query_order = null;// 查询预约记录
	private JButton btn_query_record = null;// 查看记录按钮
	private JButton btn_operate_user;// 操作用户按钮
	private JButton btn_update;// 刷新数据
	private JButton btn_exit = null;// 退出按钮
	private JLabel deskLabel = null;// 存放图片的按钮
	private JLabel lb_welcome = null;// 欢迎标题
	private User user = null;
	private BookBiz bookBiz = null;

	public AdminMainView(User user) {
		bookBiz = new BookBizImp();
		this.user = user;
		init();
		registerListener();
	}

	private void init() {
		main_panel = new JPanel(new BorderLayout());// 使用boderlayout布局管理器
		btn_panel = new JPanel(new GridLayout(9, 1, 0, 35));
		btn_operate_user = new JButton("用户管理");
		btn_query_rent_book = new JButton("图书管理");
		btn_query_record = new JButton("历史记录查询");
		btn_query_order = new JButton("预约记录查询");
		btn_update = new JButton("刷新数据");
		btn_exit = new JButton("退出窗口");
		// 前几个用来填充空间标签
		btn_panel.add(btn_operate_user);
		btn_panel.add(btn_query_rent_book); // 放置3个功能按钮
		btn_panel.add(btn_query_record);
		btn_panel.add(btn_query_order);
		btn_panel.add(btn_update);
		btn_panel.add(new JLabel());
		btn_panel.add(new JLabel());
		btn_panel.add(new JLabel());
		btn_panel.add(btn_exit);
		// 设置面板外框外观
		btn_panel.setBorder(BorderFactory.createTitledBorder(BorderFactory
				.createBevelBorder(BevelBorder.LOWERED), "快捷功能区",
				TitledBorder.CENTER, TitledBorder.TOP, new Font("宋体",
						Font.BOLD, 15), Color.RED));
		// 初始化欢迎面板
		welcome_panel = new JPanel();
		lb_welcome = new JLabel("欢	迎	管	理	员	" + user.getUname()
				+ " 使	用	图	书	管	理	系	统");
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
				int flag = JOptionPane.showConfirmDialog(AdminMainView.this,
						"是否确认退出？", "确认信息", JOptionPane.YES_NO_CANCEL_OPTION);
				if (flag == JOptionPane.YES_OPTION) {
					AdminMainView.this.dispose();
				}
			}
		});

		btn_update.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				int res = bookBiz.updateData();
				System.out.println(res);
				if (res == 5) {
					JOptionPane.showMessageDialog(AdminMainView.this,
							"数据暂时未做变动，无需刷新！");
					return;
				} else if (res == 1 || res == 3) {
					JOptionPane.showMessageDialog(AdminMainView.this, "数据刷新成功");
					return;
				} else if (res == 2 || res == 4) {
					JOptionPane.showMessageDialog(AdminMainView.this, "数据刷新失败");
					return;
				}

			}
		});

		btn_query_order.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				AdminOrderManageView order_manage_view = new AdminOrderManageView();
				functionDesktop.add(order_manage_view);
				order_manage_view.toFront();
			}
		});
		btn_operate_user.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				AdminUserManageView user_manage_view = new AdminUserManageView();
				functionDesktop.add(user_manage_view);// 把指定的视图添加到桌面容器中
				user_manage_view.toFront();// 将视图显示在最前面

			}
		});
		btn_query_rent_book.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				AdminBookManageView book_manage_view = new AdminBookManageView();
				functionDesktop.add(book_manage_view);// 把指定的视图添加到桌面容器中
				book_manage_view.toFront();// 将视图显示在最前面
			}
		});
		btn_query_record.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				AdminRecordManageView reocrd_manage_view = new AdminRecordManageView();
				functionDesktop.add(reocrd_manage_view);// 把制定的视图添加到桌面容器中
				reocrd_manage_view.toFront();// 将视图显示在最前面
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
