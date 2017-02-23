package com.library.view;

import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import com.library.biz.UserBiz;
import com.library.biz.imp.UserBizImp;
import com.library.dao.UserDao;
import com.library.dao.implement.UserDaoImp;
import com.library.entity.User;
import com.library.util.InputJudgeUtil;

public class UserInfoView extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel panel_main = null;
	private JPanel panel1 = null;// 做填充
	private JPanel panel2 = null;// 分别两个空间
	private JPanel panel3 = null;
	private JPanel panel4 = null;
	private JPanel panel5 = null;
	private JPanel panel6 = null;
	private JLabel lb_name = null;
	private JLabel lb_formor_pass = null;
	private JLabel lb_init_pass = null;
	private JLabel lb_confirm_pass = null;
	private JTextField tf_name = null;
	private JPasswordField user_formor_pass = null;
	private JPasswordField user_pass_init = null;
	private JPasswordField user_pass_confirm = null;
	private JButton btn_confirm = null;
	private JButton btn_back = null;
	private UserBiz userBiz = null;
	private User user = null;

	public UserInfoView(User user) {
		this.user = user;
		userBiz = new UserBizImp();
		init();
		registListener();
	}

	private void init() {

		tf_name = new JTextField(15);
		tf_name.setText(user.getUname());
		tf_name.setEditable(false);
		user_pass_init = new JPasswordField(15);
		user_pass_confirm = new JPasswordField(15);
		user_formor_pass = new JPasswordField(15);
		btn_confirm = new JButton("确认提交");
		btn_back = new JButton("返回");
		panel_main = new JPanel(new GridLayout(6, 1));// 主面板五行一列
		panel1 = new JPanel();
		panel2 = new JPanel();
		panel3 = new JPanel();
		panel4 = new JPanel();
		panel5 = new JPanel();
		panel6 = new JPanel();
		// 标签初始化
		lb_name = new JLabel("       用 户 名： ");
		lb_name.setFont(new Font("宋体", Font.BOLD, 15));
		lb_formor_pass = new JLabel("请输入原密码： ");
		lb_formor_pass.setFont(new Font("宋体", Font.BOLD, 15));
		lb_init_pass = new JLabel("请输入新密码： ");
		lb_init_pass.setFont(new Font("宋体", Font.BOLD, 15));
		lb_confirm_pass = new JLabel("请确认新密码： ");
		lb_confirm_pass.setFont(new Font("宋体", Font.BOLD, 15));

		panel2.add(lb_name);
		panel2.add(tf_name);
		panel3.add(lb_formor_pass);
		panel3.add(user_formor_pass);
		panel4.add(lb_init_pass);
		panel4.add(user_pass_init);
		panel5.add(lb_confirm_pass);
		panel5.add(user_pass_confirm);
		panel6.add(btn_confirm);
		panel6.add(btn_back);
		// 将面板添加到主面板
		panel_main.add(panel1);
		panel_main.add(panel2);
		panel_main.add(panel3);
		panel_main.add(panel4);
		panel_main.add(panel5);
		panel_main.add(panel6);
		// 主面板添加到窗体中
		this.getContentPane().add(panel_main);

		this.setSize(450, 260);// 设置窗体大小
		this.setLocationRelativeTo(null);
		this.setResizable(false);// 不可调整大小
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);// 默认关闭
		this.getRootPane().setDefaultButton(btn_confirm);// 默认获得焦点的按钮是确认按钮
		this.setVisible(true); // 设置可见性
	}

	private void registListener() {
		btn_back.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				UserInfoView.this.dispose();
			}
		});

		btn_confirm.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				String name = tf_name.getText().trim();
				String formorPass = new String(user_formor_pass.getPassword());
				String initPass = new String(user_pass_init.getPassword());
				String confirmPass = new String(user_pass_confirm.getPassword());
				if (name.equals("")) {
					JOptionPane
							.showMessageDialog(UserInfoView.this, "用户名不能为空！");
					return;
				} else if (initPass.equals("")) {
					JOptionPane.showMessageDialog(UserInfoView.this, "新密码不能为空！");
					return;
				} else if (confirmPass.equals("")) {
					JOptionPane.showMessageDialog(UserInfoView.this,
							"确认密码不能为空！");
					return;
				} else if (!initPass.equals(confirmPass)) {
					JOptionPane.showMessageDialog(UserInfoView.this,
							"新密码两次输入不一致，请重新输入！");
					return;
				} else if (name.length() > 20) {
					JOptionPane.showMessageDialog(UserInfoView.this,
							"用户名长度最大为20位");
					return;
				} else if (initPass.length() > 16 || confirmPass.length() > 16) {
					JOptionPane.showMessageDialog(UserInfoView.this,
							"密码长度不能超过16位！");
					return;
				} else if (formorPass.equals("")) {
					JOptionPane
							.showMessageDialog(UserInfoView.this, "原密码不能为空！");
					return;
				} else if (!user.getUpass().equals(formorPass)) {
					JOptionPane.showMessageDialog(UserInfoView.this,
							"原密码输入错误，请重新输入！");
					return;
				}
				user.setUpass(confirmPass);
				boolean flag = userBiz.modifyUser(user);
				if(flag) {
					JOptionPane.showMessageDialog(UserInfoView.this, "密码修改成功，请重新登录！");
					UserInfoView.this.dispose();
					return;
				}else {
					JOptionPane.showMessageDialog(UserInfoView.this, "密码更新失败，请重试！");
				return;
				}

			}
		});

	}
}
