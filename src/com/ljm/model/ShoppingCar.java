package com.ljm.model;
/**
*���ߣ������
*���ܣ� ���ﳵ��
*/
public class ShoppingCar
{
	//������Ʒ����
	private String user_id;
	private String goods_id;
	private String goods_name;
	private String price;	
	private String goods_num;
	private String onegoods_sumprice;
	//�û�ID
	public String getUser_id()
	{
		return user_id;
	}
	public void setUser_id(String user_id)
	{
		this.user_id = user_id;
	}
	//��ƷID
	public String getGoods_id()
	{
		return goods_id;
	}
	public void setGoods_id(String goods_id)
	{
		this.goods_id = goods_id;
	}
	//��Ʒ����
	public String getGoods_name() 
	{
		return goods_name;
	}
	public void setGoods_name(String goods_name) 
	{
		this.goods_name = goods_name;
	}
	//��Ʒ����
	public String getPrice() 
	{
		return price;
	}
	public void setPrice(String price) 
	{
		this.price = price;
	}
	//���ﳵ��Ʒ����
	public String getGoods_num() 
	{
		return goods_num;
	}
	public void setGoods_num(String goods_num)
	{
		this.goods_num = goods_num;
	}
	//һ����Ʒ���ܼ�
	public String getOnegoods_sumprice()
	{
		return onegoods_sumprice;
	}
	public void setOnegoods_sumprice(String onegoods_sumprice) 
	{
		this.onegoods_sumprice = onegoods_sumprice;
	}	
}