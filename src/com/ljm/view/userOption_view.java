package com.ljm.view;
/*
 * @���ߣ������
 * @���ܣ��û���¼�ɹ���Ĳ���ѡ�����
 */
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import com.ljm.operator.Dao;
import com.ljm.operator.DaoImplement;
public class userOption_view extends JFrame implements ActionListener
{
	//�������
	private JLabel j1,j2,j3,j4;
	private JTextField text1;
	private JButton btn1,btn2;
	private JPanel jp1,jp2;
	JLayeredPane layeredPane;
	//�����ȡ�û�����
    private String TextName;
	private String userId;
	//
	Dao dao = new DaoImplement();
	//���캯��
	public userOption_view(String userId)
	{
		this.userId = userId;
		layeredPane = new JLayeredPane();    
		//��ʼ������
		this.setSize(900,600);
		this.setTitle("�û�ѡ��");
		this.setLayout(null);
		this.getContentPane().setBackground(Color.white);
		this.setResizable(false);
		this.setLocationRelativeTo(null);					
	    //ʵ�������
		j1=new JLabel(new ImageIcon("image\\userOption_view\\baise.jpg"));
		j2=new JLabel(new ImageIcon("image\\userOption_view\\daheiban.jpg"));
		j3=new JLabel(new ImageIcon("image\\userOption_view\\ren.jpg"));
		j4=new JLabel(new ImageIcon("image\\userOption_view\\caodi.jpg"));
		TextName = dao.getUser(userId).getTrue_name();                   //��ȡ��¼�û�����ʵ����
		text1 = new JTextField(TextName+",���ã�",9);                      //����ʵ���������ı�����ʾ�ڽ���
		text1.setBorder(javax.swing.BorderFactory.createLineBorder(Color.WHITE, 1));
		btn1=new JButton(new ImageIcon("image\\userOption_view\\xgxx.jpg"));
		btn1.setBorderPainted(false);
		btn2=new JButton(new ImageIcon("image\\userOption_view\\gwgl.jpg"));	
		btn2.setBorderPainted(false);
		//�����ǩ��λ�ü���С
		j1.setBounds(0, 0, 900, 550);
		j2.setBounds(200, 150, 520, 188);
		j3.setBounds(650, 140, 150, 230);
		j4.setBounds(0, 380, 900, 150);
		//��ť����jp1���,����jp1��λ�ü����С
		jp1 = new JPanel(new GridLayout(1,1,85,22));
		jp1.setBounds(275,270,300,36);
		jp1.setOpaque(false);
		jp1.add(btn1);
		jp1.add(btn2);
		//��ʾ���ֵ�С��ǩ����jp2���,����jp2��λ�ü����С
		jp2 = new JPanel();
		jp2.add(text1);
		jp2.setBounds(665,220,120,30);
		jp2.setOpaque(false);
		//ע�����
		btn1.addActionListener(this);	
		btn2.addActionListener(this);	
		layeredPane.add(j1,JLayeredPane.DEFAULT_LAYER);
		layeredPane.add(j3,JLayeredPane.MODAL_LAYER);
		layeredPane.add(j2,JLayeredPane.MODAL_LAYER);
		layeredPane.add(jp1,JLayeredPane.POPUP_LAYER);
		layeredPane.add(jp2,JLayeredPane.POPUP_LAYER);
		layeredPane.add(j4,JLayeredPane.POPUP_LAYER);
		this.setLayeredPane(layeredPane);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.setVisible(true);
	}	
	//�¼�����
		public void actionPerformed(ActionEvent e)
		{
			if(e.getSource()==btn1)
			{
				this.dispose();
			    new UpdateUser_view(userId);
			}
			else if(e.getSource()==btn2)
			{
				this.dispose();
				if(dao.findCar(userId)==null)
				{
					new car_view1(userId);
				}
				else
				{
					new car_view2(userId);
				}	
			}
			else
			{
				System.out.println("����");
			}
		}	
}
