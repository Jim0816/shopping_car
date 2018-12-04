package com.ljm.view;
/*@���ߣ������
 *@���ܣ� �̳���������Ʒ
 */
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import com.ljm.operator.Dao;
import com.ljm.operator.DaoImplement;
public class FindGoods extends JFrame implements ActionListener
{
	//�������
	JLabel jl1;
	JButton jb1,jb2;
	JTextField jText;
	private String userId;
	Dao dao = new DaoImplement();
	public FindGoods(String userId) 
	{
		this.userId = userId;
		//��ʼ������
		setTitle("������Ʒ");
		setSize(300,150);
		this.setLayout(null);
		this.setResizable(false);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//��ʼ�����
		jl1 = new JLabel("��������Ʒ�����ƣ�");
		jText = new JTextField(10);
		jb1 = new JButton("ȷ��");
		jb2 = new JButton("ȡ��");
		//���������λ��
		jl1.setBounds(60, 20, 120, 30);
		jText.setBounds(170, 25, 50, 20);
		jb1.setBounds(70, 80, 60, 25);
		jb2.setBounds(150, 80, 60, 25);
		//������������
		this.add(jl1);
		this.add(jText);
		this.add(jb1);
		this.add(jb2);
		//����
		jb1.addActionListener(this);
		jb2.addActionListener(this);
		this.setVisible(true);
	}
	//��ť�¼�����
	public void actionPerformed(ActionEvent e)
	{
		if(e.getSource()==jb1)
		{
			if(dao.getProductByname(jText.getText())==null)
			{
				JOptionPane.showMessageDialog(null, "����Ʒ�����ڣ�","������Ʒ��ʾ",JOptionPane.WARNING_MESSAGE);
			}
			else
			{
				this.dispose();
				new NumOption(userId,jText.getText()); 
			}
		}
		else if(e.getSource()==jb2)
		{
			new Market(userId);
		}	
	}
}
