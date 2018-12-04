package com.ljm.view;
/*
 * @作者：李际明
 * @功能：管理员登录界面
 */
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import com.ljm.model.Manager;
import com.ljm.model.User;
import com.ljm.operator.Dao;
import com.ljm.operator.DaoException;
import com.ljm.operator.DaoImplement;
import com.ljm.operator.jdbcTools;
import com.sun.java.swing.plaf.windows.WindowsLookAndFeel;
public class ManageLogin_view extends JFrame implements ActionListener
{
	//定义组件
	private JLabel j2,j3;
	private JTextField uTxt;
	private JPasswordField pTxt;
	private JButton btn1;					
	//构造函数
	public ManageLogin_view()
	{
		view_2();
	}
	//管理员登录界面
	public void view_2()
	{
		//初始化窗口
		this.setSize(425,300);
		this.setTitle("管理员登录");
		this.setLayout(null);
		this.getContentPane().setBackground(Color.white);
		this.setResizable(false);
		this.setLocationRelativeTo(null);			
		//实例化组件
		j2=new JLabel(new ImageIcon("image\\ManageLogin_view\\touxiang.gif"));
		j3=new JLabel(new ImageIcon("image\\ManageLogin_view\\shang.gif"));
		uTxt=new JTextField(20);
		pTxt=new JPasswordField(20);
		btn1=new JButton(new ImageIcon("image\\ManageLogin_view\\anquandenglu.gif"));		
		//定义各组件
		j2.setBounds(45, 145, 49, 55);
	    j3.setBounds(0, 0, 425, 120);
		uTxt.setBounds(105,145,245,25);
		uTxt.setBorder(javax.swing.BorderFactory.createLineBorder(Color.LIGHT_GRAY, 3));
		pTxt.setBounds(105,175,245,25);
		pTxt.setBorder(javax.swing.BorderFactory.createLineBorder(Color.LIGHT_GRAY, 3));
		btn1.setBounds(135,220,180,35);			
		//添加组件到窗口
		this.add(j2);
		this.add(j3,"North");
		this.add(uTxt);
		this.add(pTxt);
		this.add(btn1);
		//注册监听
        btn1.addActionListener(this);
        //指定action命令		
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
	}
	//登录验证部分模块
	private Manager mappingManager(ResultSet rs) throws SQLException
	{
		Manager manager = new Manager();
		manager.setManager_id(rs.getString("manager_id"));
		manager.setManager_code(rs.getString("manager_code"));
		manager.setManager_name(rs.getString("manager_name"));
		return manager;
	}
	
	//管理员登录验证
	public Manager findManager(String managerid,String managercode)
	{
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		Manager manager = null;
		try
		{
			//建立连接
			conn = jdbcTools.getConnection();
			//创建语句
			String sql="select * from manager where manager_id=? and manager_code=?";
			ps = conn.prepareStatement(sql);
			ps.setString(1,managerid);
			ps.setString(2,managercode);
			rs = ps.executeQuery();
			//处理结果
			while(rs.next())	
			{
				manager = mappingManager(rs);
			}
	   }
	   catch(SQLException e)
		{
			throw new DaoException(e.getMessage(),e);
		}
		finally
		{
			   jdbcTools.free(rs, ps, conn);
		}
		return manager;
	}
	//对按钮事件处理的方法
	public void actionPerformed(ActionEvent e) 
	{
		String id = uTxt.getText();
		char []password = pTxt.getPassword();//密码框返回char数组
		String code = String.valueOf(password);//将char数组转换为String
		if(e.getSource()==btn1)
		{
			if(findManager(id,code)==null)
			{
				JOptionPane.showMessageDialog(null, "请输入正确的登录信息！","登录提示",JOptionPane.WARNING_MESSAGE);
			}
			else
			{
				this.dispose();
				new Manager_view();
			}
		}
	}
}
