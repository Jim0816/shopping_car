package com.ljm.view;
/*
 * @作者:李际明
 * @功能：空购物车的界面实现
 */
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
public class car_view1  extends JFrame implements ActionListener
{   //定义组件
	private JLabel j1,j2;
	private JButton btn1;
	//定义登录用户ID
	private String userId;
	//构造函数
	public car_view1(String userId) 
	{
		this.userId = userId;
		view_6();
	}
	//空购物车界面
	public void view_6()
	{   //初始化窗口
		this.setSize(800,500);
		this.setTitle("购物车");
		this.setLayout(null);
		this.getContentPane().setBackground(Color.white);
		this.setResizable(false);
		this.setLocationRelativeTo(null);
		//实例化组件
		j1=new JLabel(new ImageIcon("image\\car_view\\xiaotu.jpg"));
		j2=new JLabel(new ImageIcon("image\\car_view\\carnull.jpg"));
		btn1=new JButton(new ImageIcon("image\\car_view\\qugouwu.jpg"));
		//添加组件
		this.add(j1);
		this.add(j2);
		this.add(btn1);
		//设置组件
		j1.setBounds(450, 180, 200, 230);
		j2.setBounds(230, 190, 220, 60);
		btn1.setBounds(230, 300, 142, 38);
		btn1.setBorderPainted(false);
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
			new Market(userId);
		}
	}
}
