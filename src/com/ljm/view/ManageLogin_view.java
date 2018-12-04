package com.ljm.view;
/*
 * @���ߣ������
 * @���ܣ�����Ա��¼����
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
	//�������
	private JLabel j2,j3;
	private JTextField uTxt;
	private JPasswordField pTxt;
	private JButton btn1;					
	//���캯��
	public ManageLogin_view()
	{
		view_2();
	}
	//����Ա��¼����
	public void view_2()
	{
		//��ʼ������
		this.setSize(425,300);
		this.setTitle("����Ա��¼");
		this.setLayout(null);
		this.getContentPane().setBackground(Color.white);
		this.setResizable(false);
		this.setLocationRelativeTo(null);			
		//ʵ�������
		j2=new JLabel(new ImageIcon("image\\ManageLogin_view\\touxiang.gif"));
		j3=new JLabel(new ImageIcon("image\\ManageLogin_view\\shang.gif"));
		uTxt=new JTextField(20);
		pTxt=new JPasswordField(20);
		btn1=new JButton(new ImageIcon("image\\ManageLogin_view\\anquandenglu.gif"));		
		//��������
		j2.setBounds(45, 145, 49, 55);
	    j3.setBounds(0, 0, 425, 120);
		uTxt.setBounds(105,145,245,25);
		uTxt.setBorder(javax.swing.BorderFactory.createLineBorder(Color.LIGHT_GRAY, 3));
		pTxt.setBounds(105,175,245,25);
		pTxt.setBorder(javax.swing.BorderFactory.createLineBorder(Color.LIGHT_GRAY, 3));
		btn1.setBounds(135,220,180,35);			
		//������������
		this.add(j2);
		this.add(j3,"North");
		this.add(uTxt);
		this.add(pTxt);
		this.add(btn1);
		//ע�����
        btn1.addActionListener(this);
        //ָ��action����		
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
	}
	//��¼��֤����ģ��
	private Manager mappingManager(ResultSet rs) throws SQLException
	{
		Manager manager = new Manager();
		manager.setManager_id(rs.getString("manager_id"));
		manager.setManager_code(rs.getString("manager_code"));
		manager.setManager_name(rs.getString("manager_name"));
		return manager;
	}
	
	//����Ա��¼��֤
	public Manager findManager(String managerid,String managercode)
	{
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		Manager manager = null;
		try
		{
			//��������
			conn = jdbcTools.getConnection();
			//�������
			String sql="select * from manager where manager_id=? and manager_code=?";
			ps = conn.prepareStatement(sql);
			ps.setString(1,managerid);
			ps.setString(2,managercode);
			rs = ps.executeQuery();
			//������
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
	//�԰�ť�¼�����ķ���
	public void actionPerformed(ActionEvent e) 
	{
		String id = uTxt.getText();
		char []password = pTxt.getPassword();//����򷵻�char����
		String code = String.valueOf(password);//��char����ת��ΪString
		if(e.getSource()==btn1)
		{
			if(findManager(id,code)==null)
			{
				JOptionPane.showMessageDialog(null, "��������ȷ�ĵ�¼��Ϣ��","��¼��ʾ",JOptionPane.WARNING_MESSAGE);
			}
			else
			{
				this.dispose();
				new Manager_view();
			}
		}
	}
}
