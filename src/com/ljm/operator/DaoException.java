package com.ljm.operator;
/*
 * 作者：李际明
 * 功能：处理UserDao类的异常
 */
public class DaoException extends RuntimeException 
{
	private static final long serialVersionUID = 1L;
	public DaoException() 
	{
		// TODO Auto-generated constructor stub
	}
	public DaoException(String message) 
	{
		super(message);
		// TODO Auto-generated constructor stub
	}
	public DaoException(Throwable cause) 
	{
		super(cause);
		// TODO Auto-generated constructor stub
	}
	public DaoException(String message, Throwable cause) 
	{
		super(message, cause);
		// TODO Auto-generated constructor stub
	}
	public DaoException(String message, Throwable cause, boolean arg2, boolean arg3)
	{
		super(message, cause, arg2, arg3);
		// TODO Auto-generated constructor stub
	}
}
