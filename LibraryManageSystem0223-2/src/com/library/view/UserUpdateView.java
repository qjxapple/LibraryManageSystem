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

public class UserUpdateView extends JFrame {

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
	private JLabel lb_name = null;
	private JLabel lb_init_pass = null;
	private JLabel lb_integration = null;
	private JTextField tf_name = null;
	private JTextField tf_integration = null;
	private JTextField tf_pass = null;
	private JButton btn_confirm = null;
	private JButton btn_back = null;
	private UserBiz userBiz = null;
	private List<String> list = null;
	private UserDao userDao = null;
	public UserUpdateView(List<String> list) {
		this.list = list;
		userBiz = new UserBizImp();
		userDao = new UserDaoImp();
		init();
		
		registListener();
	}

	private void init() {
		User user =  userDao.queryUserById(new Integer(list.get(0)));
		
		tf_name = new JTextField(15);
		tf_name.setText(user.getUname());
		tf_pass = new JPasswordField(15);
		tf_pass.setText(user.getUname());
		tf_integration = new JTextField(15);
		tf_integration.setText(list.get(3));
		btn_confirm = new JButton("确认更新");
		btn_back = new JButton("返回");
		panel_main = new JPanel(new GridLayout(5, 1));// 主面板五行一列
		panel1 = new JPanel();
		panel2 = new JPanel();
		panel3 = new JPanel();
		panel4 = new JPanel();
		panel5 = new JPanel();
		// 标签初始化
		lb_name = new JLabel("    用 户 名:   ");
		lb_name.setFont(new Font("宋体", Font.BOLD, 15));
		lb_init_pass = new JLabel("请输入密码： ");
		lb_init_pass.setFont(new Font("宋体", Font.BOLD, 15));
		lb_integration = new JLabel("请输入积分： ");
		lb_integration.setFont(new Font("宋体", Font.BOLD, 15));
		panel2.add(lb_name);
		panel2.add(tf_name);
		panel3.add(lb_init_pass);
		panel3.add(tf_pass);
		panel4.add(lb_integration);
		panel4.add(tf_integration);
		panel5.add(btn_confirm);
		panel5.add(btn_back);
		// 将面板添加到主面板
		panel_main.add(panel1);
		panel_main.add(panel2);
		panel_main.add(panel3);
		panel_main.add(panel4);
		panel_main.add(panel5);
		// 主面板添加到窗体中
		this.getContentPane().add(panel_main);
		this.setTitle("更新用户信息");
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
				
				UserUpdateView.this.dispose();
			}
		});

		btn_confirm.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				String name = tf_name.getText().trim();
				String pass = new String(tf_pass.getText());
				String integration = new String(tf_integration.getText());
				if (name.equals("")) {
					JOptionPane.showMessageDialog(UserUpdateView.this,
							"用户名不能为空！");
					return;
				} else if (pass.equals("")) {
					JOptionPane.showMessageDialog(UserUpdateView.this,
							"密码不能为空！");
					return;
				} else if(name.length() > 20) {
					JOptionPane.showMessageDialog(UserUpdateView.this, "用户名长度最大为20位");
					return;
				}else if(!InputJudgeUtil.isNumber(integration)) {
					JOptionPane.showMessageDialog(UserUpdateView.this, "积分只能为数字！");
					return;
				}else if(integration.length() > 4) {
					JOptionPane.showMessageDialog(UserUpdateView.this, "积分最大限制为9999！");
					return;
				}
				
				int flag = JOptionPane.showConfirmDialog(
						UserUpdateView.this, "是否更新该用户？", "确认信息",
						JOptionPane.YES_NO_OPTION);
				if (flag == JOptionPane.YES_OPTION) {
					User user = new User(new Integer(list.get(0)),name,pass,0,new Integer(integration));
					// 看更新是否成功
					System.out.println(user);
					boolean res = userBiz.modifyUser(user);
					if (res) {
						JOptionPane.showMessageDialog(
								UserUpdateView.this, "更新成功！");
							UserUpdateView.this.dispose();
							return;
					} else {
						JOptionPane.showMessageDialog(
								UserUpdateView.this, "更新失败，请联系管理员！");
						return;
					}
				}
				
				
			}
		});

	}
}
