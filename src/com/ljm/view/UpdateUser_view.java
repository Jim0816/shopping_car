package com.ljm.view;
/*
 * @作者：李际明
 * @功能：用户修改个人信息界面
 */
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import com.ljm.model.User;
import com.ljm.operator.Dao;
import com.ljm.operator.DaoImplement;
public class UpdateUser_view extends JFrame implements ActionListener
{
	//定义组件
	JLayeredPane layeredPane;
	private JLabel j1,j2,j3,j4,j5,j6,j7,j8;
	private JTextField jTxt1,jTxt2,jTxt3,jTxt4,jTxt5,jTxt6,jTxt7; 
	private JButton btn1,btn2;
    private JPanel jp1,jp2,jp3;
    ImageIcon image;
	//构造函数
    private String userId; 
	public UpdateUser_view(String userId)
	{
		this.userId = userId;
		view_10();
	}
	public void view_10()
	{
		layeredPane = new JLayeredPane();   
		image = new ImageIcon("image\\UserLogin_view\\haiyang.jpg");     //获取窗体背景图片
		//初始化窗口
		this.setSize(409,513);
		this.setTitle("修改信息");
		this.setLayout(null);
		this.setResizable(false);
		this.setLocationRelativeTo(null);	
		//实例化组件
		j1=new JLabel("用户ID:");
		j2=new JLabel("密码:");
		j3=new JLabel("用户名:");
		j4=new JLabel("真实姓名:");
		j5=new JLabel("联系电话:");
		j6=new JLabel("地址:");
		//j7=new JLabel("消费金额:");
		j8 = new JLabel(image);
		jTxt1=new JTextField(20);
		jTxt1.setBackground(new Color(229,229,229));
		jTxt2=new JTextField(20);
		jTxt2.setBackground(new Color(229,229,229));
		jTxt3=new JTextField(20);
		jTxt3.setBackground(new Color(229,229,229));
		jTxt4=new JTextField(20);
		jTxt4.setBackground(new Color(229,229,229));
		jTxt5=new JTextField(20);
		jTxt5.setBackground(new Color(229,229,229));
		jTxt6=new JTextField(20);
		jTxt6.setBackground(new Color(229,229,229));
		btn1 = new JButton("确认");
		btn2 = new JButton("返回");		
		//添加组件
		jp1 = new JPanel(new GridLayout(7,1,10,22));
		jp2 = new JPanel(new GridLayout(7,1,10,25));
		jp3 = new JPanel(new GridLayout(1,1,40,20));
		jp1.add(j1);
		jp1.add(j2);
		jp1.add(j3);
		jp1.add(j4);
		jp1.add(j5);
		jp1.add(j6);
		//jp1.add(j7);
		jp2.add(jTxt1);
		jp2.add(jTxt2);
		jp2.add(jTxt3);
		jp2.add(jTxt4);
		jp2.add(jTxt5);
		jp2.add(jTxt6);
		//jp2.add(jTxt7);
		jp3.add(btn1);
		jp3.add(btn2);	
		//设置组件
		btn1.setBorderPainted(false);       //取消按钮边框
		btn2.setBorderPainted(false);
		j8.setBounds(0, 0, 409, 513);
	    jp1.setBounds(30, 20, 55, 350); 
	    jp1.setOpaque(false);
	    jp2.setBounds(100,20,250,350);
	    jp2.setOpaque(false);
	    jp3.setBounds(140, 350, 170, 25);
	    jp3.setOpaque(false);
		//把组件放入分层面板
		layeredPane.add(j8,JLayeredPane.DEFAULT_LAYER);
		layeredPane.add(jp1,JLayeredPane.MODAL_LAYER);
		layeredPane.add(jp2,JLayeredPane.MODAL_LAYER);
		layeredPane.add(jp3,JLayeredPane.MODAL_LAYER);
		//注册监听
      	btn1.addActionListener(this);
      	btn2.addActionListener(this);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.setLayeredPane(layeredPane);
		this.setVisible(true);
	}
	//处理鼠标按钮事件
	public void actionPerformed(ActionEvent e) 
	{
		if(e.getSource()==btn1)
		{
			if(jTxt1.getText().equals("")||jTxt2.getText().equals("")||jTxt3.getText().equals("")||jTxt4.getText().equals("")||jTxt5.getText().equals("")||jTxt6.getText().equals(""))
			{
				JOptionPane.showMessageDialog(null, "请输入您的所有信息！","修改信息提示",JOptionPane.WARNING_MESSAGE);
			}
			else
			{
				this.dispose();
				Dao dao = new DaoImplement();
				User user = new User();
				user.setUser_id(jTxt1.getText());
				user.setUser_code(jTxt2.getText());
				user.setUser_name(jTxt3.getText());
				user.setTrue_name(jTxt4.getText());
				user.setTel(jTxt5.getText());
				user.setAddress(jTxt6.getText());
				user.setCost_money(0);
				dao.updateUser(user);
				new UserRegist_tip();
			}
		}
		else if(e.getSource()==btn2)
		{
			this.dispose();
			new userOption_view(userId);
		}	
	}
}

