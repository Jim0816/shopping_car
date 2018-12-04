
package com.ljm.view;
/*
 * @���ߣ������
 * @���ܣ��û���¼����
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
	//�������
	private JLabel j2,j3;
	private JTextField uTxt;
	private JPasswordField pTxt;
	private JButton btn1,btn2;
    //���캯��
	public UserLogin_view()
	{
		view_3();
	}
	//�û���¼����
	public void view_3()
	{
		//��ʼ������
		this.setSize(425,300);
		this.setTitle("�û���¼");
		this.setLayout(null);
		this.getContentPane().setBackground(Color.white);
		this.setResizable(false);
		this.setLocationRelativeTo(null);				
		//ʵ�������
		j2=new JLabel(new ImageIcon("image\\UserLogin_view\\touxiang.gif"));
		j3=new JLabel(new ImageIcon("image\\UserLogin_view\\shang.gif"));
		uTxt=new JTextField(20);
		pTxt=new JPasswordField(20);
		btn1=new JButton(new ImageIcon("image\\UserLogin_view\\denglu.gif"));
		btn2=new JButton(new ImageIcon("image\\UserLogin_view\\zuche.gif"));		
		//����������λ�ü���С
		j2.setBounds(45, 145, 49, 55);
	    j3.setBounds(0, 0, 425, 120);
		uTxt.setBounds(105,145,245,25);
		uTxt.setBorder(javax.swing.BorderFactory.createLineBorder(Color.LIGHT_GRAY, 3));
		pTxt.setBounds(105,175,245,25);
		pTxt.setBorder(javax.swing.BorderFactory.createLineBorder(Color.LIGHT_GRAY, 3));
		btn1.setBounds(105,220,74,29);
		btn2.setBounds(275,220,74,29);		
		//������������
		this.add(j2);
		this.add(j3,"North");
		this.add(uTxt);
		this.add(pTxt);
		this.add(btn1);
		this.add(btn2);
		//ע�����
		btn1.addActionListener(this);	
		btn2.addActionListener(this);	
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
	}
	//�¼�����
	public void actionPerformed(ActionEvent e)
	{
		String id = uTxt.getText();
		char []password = pTxt.getPassword();//����򷵻�char����
		String code = String.valueOf(password);//��char����ת��ΪString
		if(e.getSource()==btn1)
		{
			Dao dao = new DaoImplement();
			if(dao.findUser(id,code)==null)
			{
				JOptionPane.showMessageDialog(null, "��������ȷ�ĵ�¼��Ϣ��","��¼��ʾ",JOptionPane.WARNING_MESSAGE);
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
			System.out.println("����");
		}
	}
}
