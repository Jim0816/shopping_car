package com.ljm.view;
/*@作者：李际明
 *@功能： 商城中搜索商品
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
	//定义组件
	JLabel jl1;
	JButton jb1,jb2;
	JTextField jText;
	private String userId;
	Dao dao = new DaoImplement();
	public FindGoods(String userId) 
	{
		this.userId = userId;
		//初始化窗口
		setTitle("搜索商品");
		setSize(300,150);
		this.setLayout(null);
		this.setResizable(false);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//初始化组件
		jl1 = new JLabel("请输入商品的名称：");
		jText = new JTextField(10);
		jb1 = new JButton("确认");
		jb2 = new JButton("取消");
		//设置组件的位置
		jl1.setBounds(60, 20, 120, 30);
		jText.setBounds(170, 25, 50, 20);
		jb1.setBounds(70, 80, 60, 25);
		jb2.setBounds(150, 80, 60, 25);
		//添加组件到窗体
		this.add(jl1);
		this.add(jText);
		this.add(jb1);
		this.add(jb2);
		//监听
		jb1.addActionListener(this);
		jb2.addActionListener(this);
		this.setVisible(true);
	}
	//按钮事件处理
	public void actionPerformed(ActionEvent e)
	{
		if(e.getSource()==jb1)
		{
			if(dao.getProductByname(jText.getText())==null)
			{
				JOptionPane.showMessageDialog(null, "该商品不存在！","搜索商品提示",JOptionPane.WARNING_MESSAGE);
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
