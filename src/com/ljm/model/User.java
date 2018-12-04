package com.ljm.model;
/**
*作者：李际明
*用户类
*/
public class User
{
	//定义用户属性
	private String user_id;
	private String user_name;
	private String user_code;
	private String true_name;
	private String tel;
	private String address;
	private float cost_money;
	//用户编号
	public String getUser_id() 
	{
		return user_id;
	}
	public void setUser_id(String user_id)
	{
		this.user_id = user_id;
	}
	//用户名
	public String getUser_name()
	{
		return user_name;
	}
	public void setUser_name(String user_name)
	{
		this.user_name = user_name;
	}
	//用户密码
	public String getUser_code() 
	{
		return user_code;
	}
	public void setUser_code(String user_code) 
	{
		this.user_code = user_code;
	}
	//用户真实姓名
	public String getTrue_name() 
	{
		return true_name;
	}
	public void setTrue_name(String true_name) 
	{
		this.true_name = true_name;
	}
	//用户电话
	public String getTel()
	{
		return tel;
	}
	public void setTel(String tel) 
	{
		this.tel = tel;
	}
	//用户地址
	public String getAddress() 
	{
		return address;
	}
	public void setAddress(String address) 
	{
		this.address = address;
	}
	//用户消费金额
	public float getCost_money() 
	{
		return cost_money;
	}
	public void setCost_money(float cost_money)
	{
		this.cost_money = cost_money;
	}	
}
