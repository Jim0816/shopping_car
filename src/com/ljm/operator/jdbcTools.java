package com.ljm.operator;
/*
*���ܣ�jdbc�Ĺ淶���ܰ�
*���ߣ������
*
*/
import java.sql.*;
public final class jdbcTools
{
	private static String url = "jdbc:mysql://localhost:3306/shop?useSSL=false";
	private static String user = "root";
	private static String password = "";
	static 
	{
		try 
		{
			Class.forName("com.mysql.jdbc.Driver");
		}
		catch(ClassNotFoundException e)
		{
			throw new ExceptionInInitializerError(e);
		}
	}
	public static Connection getConnection() throws SQLException
	{
		return DriverManager.getConnection(url,user,password);
	}
	public static void free(ResultSet rs,Statement st,Connection conn)
	{
		try
		{
			if(rs!=null)
				rs.close();
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		finally
		{
			try
			{
				if(st!=null)
					try {
						st.close();
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
			}
			finally
			{
				if(conn!=null)
					try 
				    {
						conn.close();
					} 
				   catch (SQLException e) 
				    {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
		}	
	}	
}