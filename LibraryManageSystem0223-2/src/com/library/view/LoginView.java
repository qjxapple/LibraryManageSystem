package com.library.view;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import com.library.biz.UserBiz;
import com.library.biz.imp.UserBizImp;
import com.library.entity.User;

public class LoginView extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel panel_main = null;// 主面板
	private JPanel panel_left = null;// 左面板
	private JPanel panel_right = null;// 右面板
	private JLabel lb_uname = null;// 用户名标签
	private JLabel lb_upass = null;// 密码标签
	private JLabel lb_kind = null;// 登录类型标签
	private JLabel lb_img = null;// 显示图片的标签
	private JTextField tf_name = null;// 用户文本框
	private JPasswordField pf_pass = null;// 密码文本框
	private JButton btn_login = null;// 登录按钮
	private JButton btn_regist = null;// 注册按钮
	private JComboBox<String> cb_kind = null;// 登录类型下拉列表框
	private UserBiz userBiz = null; //创建一个业务层对象
	// 构造方法初始化控件
		public LoginView() {
			userBiz = new UserBizImp();
			init();
			registListener();
		}

	// 初始化控件的方法
	
	private void init() {
		this.setSize(320, 220);// 设置窗体大小
		this.setResizable(false);// 窗体大小不可拖动
		this.setVisible(true);// 显示窗体
		this.setLocationRelativeTo(null); // 设置居中
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);// 默认关闭窗体
		this.setTitle("登录窗口");// 设置窗口的标题
		// 初始化面板
		panel_main = new JPanel(new GridLayout(1, 2));// 主面板一行两列
		panel_left = new JPanel();
		panel_right = new JPanel(new GridLayout(4, 2, 0, 10));// 右边面板4行2列,间隙0 10
		// 初始化控件
		tf_name = new JTextField(9);// 用户名文本框
		pf_pass = new JPasswordField(9);// 用户账户密码框
		cb_kind = new JComboBox<String>(new String[] { "普通用户", "管理员" });
		btn_login = new JButton("登录");
		btn_regist = new JButton("注册");
		// Lable标签
		lb_uname = new JLabel("用 户：", JLabel.CENTER);
		lb_upass = new JLabel("密 码：", JLabel.CENTER);
		lb_kind = new JLabel("类 型：", JLabel.CENTER);
		lb_img = new JLabel(new ImageIcon(
				ClassLoader.getSystemResource("images/login.jpg")));
		// 把相应的控件放到面板中去
		panel_left.add(lb_img);
		panel_right.add(lb_uname);
		panel_right.add(tf_name);
		panel_right.add(lb_upass);
		panel_right.add(pf_pass);
		panel_right.add(lb_kind);
		panel_right.add(cb_kind);
		panel_right.add(btn_login);
		panel_right.add(btn_regist);
		// 主面板中添加左右两个面板
		panel_main.add(panel_left);
		panel_main.add(panel_right);
		// 在把主面板放到窗体中
		this.getContentPane().add(panel_main);
		this.pack();// 收缩窗体
	}
	//添加控件监听
	public void registListener() {
		btn_regist.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				new UserRegistView();//弹出注册视图
				
				
			}
		});
		btn_login.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				//获取用户名或者密码
				
				String uname = tf_name.getText().trim();
				String upass = new String(pf_pass.getPassword());//获得密码
				int type = cb_kind.getSelectedIndex();
				if(uname.equals(null)) {
					JOptionPane.showMessageDialog(LoginView.this, "用户名不能为空！");
					return;
				}else if(upass.equals(null)) {
					JOptionPane.showMessageDialog(LoginView.this, "密码不能为空！");
					return;
				}else if(uname.length() > 20) {
					JOptionPane.showMessageDialog(LoginView.this, "用户名为长度最大为20位！");
					return;
				}else if(upass.length() > 16) {
					JOptionPane.showMessageDialog(LoginView.this, "密码长度不得超过16位！");
					return;
				}
				
				User user = new User(uname,upass,type);
				
				user = userBiz.login(user);
				
				if(user != null) {
					if(user.getType() == 0) {
						//普通用户为0
						if(user.getIntegration() <=1) {
							JOptionPane.showMessageDialog(LoginView.this, "你的积分不足，无法登陆，请联系管理员！");
						}else 
						new UserMainView(user) ;
						System.out.println(user.getIntegration());
					}else {
						new AdminMainView(user);
					}
					LoginView.this.dispose();//一旦登录成功关闭登录窗口
				}else {
					JOptionPane.showMessageDialog(LoginView.this, "用户名或密码出错！");
				}
			}
		});
	}
	
}
