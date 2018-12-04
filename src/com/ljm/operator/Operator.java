package com.ljm.operator;

import java.sql.*;

public class Operator
{
/************************对用户信息的操作*****************************/	
    //查询某个用户的所有信息
	static void readUserInfo(String id) throws SQLException
	{
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try
		{
			//建立连接
			conn = jdbcTools.getConnection();
			//创建语句
			String sql="select * from user where user_id=?";
			ps = conn.prepareStatement(sql);
			ps.setString(1,id);
			//执行语句
			rs = ps.executeQuery();
			//处理结果
			System.out.println("用户名            编号               密码               真实姓名                电话号码                        地址               消费金额");
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
	//查询所有用户的所有信息
	static void readAllUserInfo() throws SQLException
	{
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try
		{
			//建立连接
			conn = jdbcTools.getConnection();
			//创建语句
			String sql="select * from user";
			ps = conn.prepareStatement(sql);
			//执行语句
			rs = ps.executeQuery();
			//处理结果
			System.out.println("用户名            编号               密码               真实姓名                电话号码                        地址               消费金额");
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
	//添加用户信息
	static void insertUserInfo(String user_id,String user_name,String user_code,String true_name,String tel,String address,float cost_money ) 
	throws SQLException
	{
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try
		{
			//建立连接
			conn = jdbcTools.getConnection();
			//创建语句
			String sql="insert into user(user_id,user_name,user_code,true_name,tel,address,cost_money) values(?,?,?,?,?,?,?)";
			ps = conn.prepareStatement(sql);
			ps.setString(1,user_id);
			ps.setString(2, user_name);
			ps.setString(3, user_code);
			ps.setString(4, true_name);
			ps.setString(5, tel);
			ps.setString(6, address);
			ps.setFloat(7,cost_money );
			//执行语句
			int i = ps.executeUpdate();
			System.out.println("i="+i);
		}
	   finally
	   {
		   jdbcTools.free(rs, ps, conn);
	   }
	}
	//删除用户
	static void deleteUserInfo(String user_id)
	{
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try
		{
			//建立连接
			conn = jdbcTools.getConnection();
			//创建语句
			String sql="delete from user where user_id=?";
			ps = conn.prepareStatement(sql);
			ps.setString(1,user_id);
			//执行语句
			ps.executeUpdate();
			System.out.println("删除成功！");
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
