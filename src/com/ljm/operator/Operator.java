package com.ljm.operator;

import java.sql.*;

public class Operator
{
/************************���û���Ϣ�Ĳ���*****************************/	
    //��ѯĳ���û���������Ϣ
	static void readUserInfo(String id) throws SQLException
	{
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try
		{
			//��������
			conn = jdbcTools.getConnection();
			//�������
			String sql="select * from user where user_id=?";
			ps = conn.prepareStatement(sql);
			ps.setString(1,id);
			//ִ�����
			rs = ps.executeQuery();
			//������
			System.out.println("�û���            ���               ����               ��ʵ����                �绰����                        ��ַ               ���ѽ��");
			while(rs.next())	
			{
				System.out.println(
						rs.getString("user_name")+"\t"+rs.getString("user_id")
						+"\t"+rs.getString("user_code")+"\t"+rs.getString("true_name")
						+"\t"+rs.getString("tel")+"\t"+rs.getString("address")
						+"\t"+rs.getFloat("cost_money")
						          );
			}
	   }
	   finally
	   {
		   jdbcTools.free(rs, ps, conn);
	   }
	}
	//��ѯ�����û���������Ϣ
	static void readAllUserInfo() throws SQLException
	{
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try
		{
			//��������
			conn = jdbcTools.getConnection();
			//�������
			String sql="select * from user";
			ps = conn.prepareStatement(sql);
			//ִ�����
			rs = ps.executeQuery();
			//������
			System.out.println("�û���            ���               ����               ��ʵ����                �绰����                        ��ַ               ���ѽ��");
			while(rs.next())	
			{
				System.out.println(
						rs.getString("user_name")+"\t"+rs.getString("user_id")
						+"\t"+rs.getString("user_code")+"\t"+rs.getString("true_name")
						+"\t"+rs.getString("tel")+"\t"+rs.getString("address")
						+"\t"+rs.getFloat("cost_money")
						          );
			}
	   }
	   finally
	   {
		   jdbcTools.free(rs, ps, conn);
	   }
	}
	//����û���Ϣ
	static void insertUserInfo(String user_id,String user_name,String user_code,String true_name,String tel,String address,float cost_money ) 
	throws SQLException
	{
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try
		{
			//��������
			conn = jdbcTools.getConnection();
			//�������
			String sql="insert into user(user_id,user_name,user_code,true_name,tel,address,cost_money) values(?,?,?,?,?,?,?)";
			ps = conn.prepareStatement(sql);
			ps.setString(1,user_id);
			ps.setString(2, user_name);
			ps.setString(3, user_code);
			ps.setString(4, true_name);
			ps.setString(5, tel);
			ps.setString(6, address);
			ps.setFloat(7,cost_money );
			//ִ�����
			int i = ps.executeUpdate();
			System.out.println("i="+i);
		}
	   finally
	   {
		   jdbcTools.free(rs, ps, conn);
	   }
	}
	//ɾ���û�
	static void deleteUserInfo(String user_id)
	{
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try
		{
			//��������
			conn = jdbcTools.getConnection();
			//�������
			String sql="delete from user where user_id=?";
			ps = conn.prepareStatement(sql);
			ps.setString(1,user_id);
			//ִ�����
			ps.executeUpdate();
			System.out.println("ɾ���ɹ���");
		}
		catch(SQLException e)
		{
			throw new DaoException(e.getMessage(),e);
		}
	   finally
	   {
		   jdbcTools.free(rs, ps, conn);
	   }
	}	
}
