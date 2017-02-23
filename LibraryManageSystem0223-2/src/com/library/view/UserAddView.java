package com.library.view;

import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

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

public class UserAddView extends JFrame {

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
	private JLabel lb_init_pass = null;
	private JLabel lb_confirm_pass = null;
	private JLabel lb_integration = null;
	private JTextField tf_name = null;
	private JTextField tf_integration = null;
	private JPasswordField userPassInit = null;
	private JPasswordField userPassConfirm = null;
	private JButton btn_confirm = null;
	private JButton btn_back = null;
	private UserBiz userBiz = null;
	public UserAddView() {
		userBiz = new UserBizImp();
		init();
		registListener();
	}

	private void init() {
		
		
		tf_name = new JTextField(15);
		userPassInit = new JPasswordField(15);
		userPassConfirm = new JPasswordField(15);
		tf_integration = new JTextField(15);
		btn_confirm = new JButton("添加用户");
		btn_back = new JButton("返回");
		panel_main = new JPanel(new GridLayout(6, 1));// 主面板五行一列
		panel1 = new JPanel();
		panel2 = new JPanel();
		panel3 = new JPanel();
		panel4 = new JPanel();
		panel5 = new JPanel();
		panel6 = new JPanel();
		// 标签初始化
		lb_name = new JLabel("    用 户 名:   ");
		lb_name.setFont(new Font("宋体", Font.BOLD, 15));
		lb_init_pass = new JLabel("请输入密码： ");
		lb_init_pass.setFont(new Font("宋体", Font.BOLD, 15));
		lb_confirm_pass = new JLabel("请确认密码： ");
		lb_confirm_pass.setFont(new Font("宋体", Font.BOLD, 15));
		lb_integration = new JLabel("请输入积分： ");
		lb_integration.setFont(new Font("宋体", Font.BOLD, 15));
		panel2.add(lb_name);
		panel2.add(tf_name);
		panel3.add(lb_init_pass);
		panel3.add(userPassInit);
		panel4.add(lb_confirm_pass);
		panel4.add(userPassConfirm);
		panel5.add(lb_integration);
		panel5.add(tf_integration);
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
		this.setTitle("添加用户");
		this.setSize(450, 260);// 设置窗体大小
		this.setLocationRelativeTo(null);
		this.setResizable(false);// 不可调整大小
		this.getRootPane().setDefaultButton(btn_confirm);// 默认获得焦点的按钮是确认按钮
		this.setVisible(true); // 设置可见性
	}

	private void registListener() {
		btn_back.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				UserAddView.this.dispose();
			}
		});

		btn_confirm.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				String name = tf_name.getText().trim();
				String initPass = new String(userPassInit.getPassword());
				String confirmPass = new String(userPassConfirm.getPassword());
				String integration = new String(tf_integration.getText());
				if (name.equals("")) {
					JOptionPane.showMessageDialog(UserAddView.this,
							"用户名不能为空！");
					return;
				} else if (initPass.equals("")) {
					JOptionPane.showMessageDialog(UserAddView.this,
							"密码不能为空！");
					return;
				} else if (confirmPass.equals("")) {
					JOptionPane.showMessageDialog(UserAddView.this,
							"确认密码不能为空！");
					return;
				}else if(!initPass.equals(confirmPass)) {
					JOptionPane.showMessageDialog(UserAddView.this,
							"密码输入不一致，请重新输入！");
					return;
				}else if(name.length() > 20) {
					JOptionPane.showMessageDialog(UserAddView.this, "用户名长度最大为20位");
					return;
				}else if(initPass.length() > 16 || confirmPass.length() > 16) {
					JOptionPane.showMessageDialog(UserAddView.this, "密码长度不能超过16位！");
					return;
				}else if(!InputJudgeUtil.isNumber(integration)) {
					JOptionPane.showMessageDialog(UserAddView.this, "积分只能为数字！");
					return;
				}else if(integration.length() > 4) {
					JOptionPane.showMessageDialog(UserAddView.this, "积分最大限制为9999！");
					return;
				}
				
				User user = new User(name,initPass,0,new Integer(integration));
				
				boolean flag = userBiz.addUser(user);
				if(flag) {
					JOptionPane.showMessageDialog(UserAddView.this,
							"添加用户成功");
					UserAddView.this.dispose();
					return;
				}else {
					JOptionPane.showMessageDialog(UserAddView.this,
							"添加用户失败，请联系管理员！");
					return;
				}
				
				
			}
		});

	}
}
