package com.ljm.view;
/*
 * @作者：李际明
 * @功能：用户登录成功后的操作选择界面
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
	//定义组件
	private JLabel j1,j2,j3,j4;
	private JTextField text1;
	private JButton btn1,btn2;
	private JPanel jp1,jp2;
	JLayeredPane layeredPane;
	//定义获取用户姓名
    private String TextName;
	private String userId;
	//
	Dao dao = new DaoImplement();
	//构造函数
	public userOption_view(String userId)
	{
		this.userId = userId;
		layeredPane = new JLayeredPane();    
		//初始化窗口
		this.setSize(900,600);
		this.setTitle("用户选择");
		this.setLayout(null);
		this.getContentPane().setBackground(Color.white);
		this.setResizable(false);
		this.setLocationRelativeTo(null);					
	    //实例化组件
		j1=new JLabel(new ImageIcon("image\\userOption_view\\baise.jpg"));
		j2=new JLabel(new ImageIcon("image\\userOption_view\\daheiban.jpg"));
		j3=new JLabel(new ImageIcon("image\\userOption_view\\ren.jpg"));
		j4=new JLabel(new ImageIcon("image\\userOption_view\\caodi.jpg"));
		TextName = dao.getUser(userId).getTrue_name();                   //获取登录用户的真实姓名
		text1 = new JTextField(TextName+",您好！",9);                      //将真实姓名放入文本框，显示在界面
		text1.setBorder(javax.swing.BorderFactory.createLineBorder(Color.WHITE, 1));
		btn1=new JButton(new ImageIcon("image\\userOption_view\\xgxx.jpg"));
		btn1.setBorderPainted(false);
		btn2=new JButton(new ImageIcon("image\\userOption_view\\gwgl.jpg"));	
		btn2.setBorderPainted(false);
		//定义标签的位置及大小
		j1.setBounds(0, 0, 900, 550);
		j2.setBounds(200, 150, 520, 188);
		j3.setBounds(650, 140, 150, 230);
		j4.setBounds(0, 380, 900, 150);
		//按钮加入jp1面板,设置jp1的位置及其大小
		jp1 = new JPanel(new GridLayout(1,1,85,22));
		jp1.setBounds(275,270,300,36);
		jp1.setOpaque(false);
		jp1.add(btn1);
		jp1.add(btn2);
		//显示名字的小标签加入jp2面板,设置jp2的位置及其大小
		jp2 = new JPanel();
		jp2.add(text1);
		jp2.setBounds(665,220,120,30);
		jp2.setOpaque(false);
		//注册监听
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
	//事件处理
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
				System.out.println("其它");
			}
		}	
}
