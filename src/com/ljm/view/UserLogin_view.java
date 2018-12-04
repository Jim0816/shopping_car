
package com.ljm.view;
/*
 * @作者：李际明
 * @功能：用户登录界面
 */
import com.ljm.operator.*;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import com.ljm.operator.Dao;
import com.ljm.operator.DaoImplement;

public class UserLogin_view extends JFrame implements ActionListener
{
	//定义组件
	private JLabel j2,j3;
	private JTextField uTxt;
	private JPasswordField pTxt;
	private JButton btn1,btn2;
    //构造函数
	public UserLogin_view()
	{
		view_3();
	}
	//用户登录界面
	public void view_3()
	{
		//初始化窗口
		this.setSize(425,300);
		this.setTitle("用户登录");
		this.setLayout(null);
		this.getContentPane().setBackground(Color.white);
		this.setResizable(false);
		this.setLocationRelativeTo(null);				
		//实例化组件
		j2=new JLabel(new ImageIcon("image\\UserLogin_view\\touxiang.gif"));
		j3=new JLabel(new ImageIcon("image\\UserLogin_view\\shang.gif"));
		uTxt=new JTextField(20);
		pTxt=new JPasswordField(20);
		btn1=new JButton(new ImageIcon("image\\UserLogin_view\\denglu.gif"));
		btn2=new JButton(new ImageIcon("image\\UserLogin_view\\zuche.gif"));		
		//定义各组件的位置及大小
		j2.setBounds(45, 145, 49, 55);
	    j3.setBounds(0, 0, 425, 120);
		uTxt.setBounds(105,145,245,25);
		uTxt.setBorder(javax.swing.BorderFactory.createLineBorder(Color.LIGHT_GRAY, 3));
		pTxt.setBounds(105,175,245,25);
		pTxt.setBorder(javax.swing.BorderFactory.createLineBorder(Color.LIGHT_GRAY, 3));
		btn1.setBounds(105,220,74,29);
		btn2.setBounds(275,220,74,29);		
		//添加组件到窗口
		this.add(j2);
		this.add(j3,"North");
		this.add(uTxt);
		this.add(pTxt);
		this.add(btn1);
		this.add(btn2);
		//注册监听
		btn1.addActionListener(this);	
		btn2.addActionListener(this);	
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
	}
	//事件处理
	public void actionPerformed(ActionEvent e)
	{
		String id = uTxt.getText();
		char []password = pTxt.getPassword();//密码框返回char数组
		String code = String.valueOf(password);//将char数组转换为String
		if(e.getSource()==btn1)
		{
			Dao dao = new DaoImplement();
			if(dao.findUser(id,code)==null)
			{
				JOptionPane.showMessageDialog(null, "请输入正确的登录信息！","登录提示",JOptionPane.WARNING_MESSAGE);
			}
			else
			{
				this.dispose();
				new userOption_view(id);
			}	
		}
		else if(e.getSource()==btn2)
		{
			this.dispose();
			new UserRegist();
		}
		else
		{
			System.out.println("其它");
		}
	}
}
