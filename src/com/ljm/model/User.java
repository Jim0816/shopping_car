package com.ljm.model;
/**
*���ߣ������
*�û���
*/
public class User
{
	//�����û�����
	private String user_id;
	private String user_name;
	private String user_code;
	private String true_name;
	private String tel;
	private String address;
	private float cost_money;
	//�û����
	public String getUser_id() 
	{
		return user_id;
	}
	public void setUser_id(String user_id)
	{
		this.user_id = user_id;
	}
	//�û���
	public String getUser_name()
	{
		return user_name;
	}
	public void setUser_name(String user_name)
	{
		this.user_name = user_name;
	}
	//�û�����
	public String getUser_code() 
	{
		return user_code;
	}
	public void setUser_code(String user_code) 
	{
		this.user_code = user_code;
	}
	//�û���ʵ����
	public String getTrue_name() 
	{
		return true_name;
	}
	public void setTrue_name(String true_name) 
	{
		this.true_name = true_name;
	}
	//�û��绰
	public String getTel()
	{
		return tel;
	}
	public void setTel(String tel) 
	{
		this.tel = tel;
	}
	//�û���ַ
	public String getAddress() 
	{
		return address;
	}
	public void setAddress(String address) 
	{
		this.address = address;
	}
	//�û����ѽ��
	public float getCost_money() 
	{
		return cost_money;
	}
	public void setCost_money(float cost_money)
	{
		this.cost_money = cost_money;
	}	
}
