package com.ljm.view;
/*
 * @����:�����
 * @���ܣ��չ��ﳵ�Ľ���ʵ��
 */
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
public class car_view1  extends JFrame implements ActionListener
{   //�������
	private JLabel j1,j2;
	private JButton btn1;
	//�����¼�û�ID
	private String userId;
	//���캯��
	public car_view1(String userId) 
	{
		this.userId = userId;
		view_6();
	}
	//�չ��ﳵ����
	public void view_6()
	{   //��ʼ������
		this.setSize(800,500);
		this.setTitle("���ﳵ");
		this.setLayout(null);
		this.getContentPane().setBackground(Color.white);
		this.setResizable(false);
		this.setLocationRelativeTo(null);
		//ʵ�������
		j1=new JLabel(new ImageIcon("image\\car_view\\xiaotu.jpg"));
		j2=new JLabel(new ImageIcon("image\\car_view\\carnull.jpg"));
		btn1=new JButton(new ImageIcon("image\\car_view\\qugouwu.jpg"));
		//������
		this.add(j1);
		this.add(j2);
		this.add(btn1);
		//�������
		j1.setBounds(450, 180, 200, 230);
		j2.setBounds(230, 190, 220, 60);
		btn1.setBounds(230, 300, 142, 38);
		btn1.setBorderPainted(false);
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
			new Market(userId);
		}
	}
}
