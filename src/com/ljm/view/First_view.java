package com.ljm.view;
/*
 * @作者：李际明
 * @功能：系统登入身份选择界面
 */
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
public class First_view extends JFrame implements ActionListener
{
	//定义组件
	private JLabel j3;
	private JButton btn1,btn2;
	//构造函数
	public  First_view()
	{
		view_1();	
	}
	//选择登录界面
	public void view_1()
	{
		//初始化窗口
		this.setSize(425,300);
		this.setTitle("购物车系统");
		this.setLayout(null);
		this.getContentPane().setBackground(Color.white);
		this.setResizable(false);
		this.setLocationRelativeTo(null);						
	    //实例化组件
		j3=new JLabel(new ImageIcon("image\\xuanzedenglu_view\\shang.gif"));
		btn1=new JButton(new ImageIcon("image\\xuanzedenglu_view\\guanli.gif"));
		btn2=new JButton(new ImageIcon("image\\xuanzedenglu_view\\yonghu.gif"));					
		//定义各组件的位置及大小
		j3.setBounds(0, 0, 425, 120);
		btn1.setBounds(70,200,110,27);
		btn2.setBounds(245,200,110,27);					
		//添加组件到窗口
		this.add(j3,"North");
		this.add(btn1);
		this.add(btn2);	
		//注册监听
		btn1.addActionListener(this);	
		btn2.addActionListener(this);	
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.setVisible(true);
	}
	//事件处理
	public void actionPerformed(ActionEvent e)
	{
		if(e.getSource()==btn1)
		{
			this.dispose();
		    new ManageLogin_view();
		}
		else if(e.getSource()==btn2)
		{
			this.dispose();
			new UserLogin_view();
		}
		else
		{
			System.out.println("其它");
		}	
	}		
}



