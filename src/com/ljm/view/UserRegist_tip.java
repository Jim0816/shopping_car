package com.ljm.view;
/*
 * @作者：李际明
 * @功能：封装了用户使用时的一些提示窗口
 */
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
//用户注册成功提示界面类
public class UserRegist_tip extends JFrame implements ActionListener
{
	//定义组件
	private JLabel j1;
	private JButton btn1;
	public UserRegist_tip() 
	{
		view_11();
	}
	public void view_11()
	{
		//初始化窗口
		this.setSize(300,200);
		this.setTitle("提示");
		this.setLayout(null);
		this.getContentPane().setBackground(Color.white);
		this.setResizable(false);
		this.setLocationRelativeTo(null);
		//实例化组件
		j1=new JLabel("操作成功！");
		btn1 = new JButton(new ImageIcon("image\\UserLogin_view\\qudenglu.jpg"));
		btn1.setBorderPainted(false);
		btn1.setFocusPainted(false);
		//添加组件
		this.add(btn1);
		this.add(j1);
		//设置组件位置及其大小
		j1.setBounds(115,10,80,50);
		btn1.setBounds(115, 120, 60, 30);
		//注册监听
      	btn1.addActionListener(this);
      	this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.setVisible(true);
	}

	//事件处理
	public void actionPerformed(ActionEvent e) 
	{
		if(e.getSource()==btn1)
		{
			this.dispose();
			new UserLogin_view();
		}
		else
		{
			//
		}
	}
}


