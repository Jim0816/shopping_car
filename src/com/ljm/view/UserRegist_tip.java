package com.ljm.view;
/*
 * @���ߣ������
 * @���ܣ���װ���û�ʹ��ʱ��һЩ��ʾ����
 */
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
//�û�ע��ɹ���ʾ������
public class UserRegist_tip extends JFrame implements ActionListener
{
	//�������
	private JLabel j1;
	private JButton btn1;
	public UserRegist_tip() 
	{
		view_11();
	}
	public void view_11()
	{
		//��ʼ������
		this.setSize(300,200);
		this.setTitle("��ʾ");
		this.setLayout(null);
		this.getContentPane().setBackground(Color.white);
		this.setResizable(false);
		this.setLocationRelativeTo(null);
		//ʵ�������
		j1=new JLabel("�����ɹ���");
		btn1 = new JButton(new ImageIcon("image\\UserLogin_view\\qudenglu.jpg"));
		btn1.setBorderPainted(false);
		btn1.setFocusPainted(false);
		//������
		this.add(btn1);
		this.add(j1);
		//�������λ�ü����С
		j1.setBounds(115,10,80,50);
		btn1.setBounds(115, 120, 60, 30);
		//ע�����
      	btn1.addActionListener(this);
      	this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.setVisible(true);
	}

	//�¼�����
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


