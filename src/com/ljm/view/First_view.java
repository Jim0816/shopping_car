package com.ljm.view;
/*
 * @���ߣ������
 * @���ܣ�ϵͳ�������ѡ�����
 */
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
public class First_view extends JFrame implements ActionListener
{
	//�������
	private JLabel j3;
	private JButton btn1,btn2;
	//���캯��
	public  First_view()
	{
		view_1();	
	}
	//ѡ���¼����
	public void view_1()
	{
		//��ʼ������
		this.setSize(425,300);
		this.setTitle("���ﳵϵͳ");
		this.setLayout(null);
		this.getContentPane().setBackground(Color.white);
		this.setResizable(false);
		this.setLocationRelativeTo(null);						
	    //ʵ�������
		j3=new JLabel(new ImageIcon("image\\xuanzedenglu_view\\shang.gif"));
		btn1=new JButton(new ImageIcon("image\\xuanzedenglu_view\\guanli.gif"));
		btn2=new JButton(new ImageIcon("image\\xuanzedenglu_view\\yonghu.gif"));					
		//����������λ�ü���С
		j3.setBounds(0, 0, 425, 120);
		btn1.setBounds(70,200,110,27);
		btn2.setBounds(245,200,110,27);					
		//������������
		this.add(j3,"North");
		this.add(btn1);
		this.add(btn2);	
		//ע�����
		btn1.addActionListener(this);	
		btn2.addActionListener(this);	
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.setVisible(true);
	}
	//�¼�����
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
			System.out.println("����");
		}	
	}		
}



