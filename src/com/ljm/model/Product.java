package com.ljm.model;
/**
*���ߣ������
*��Ʒ��
*/
public class Product
{
	//������Ʒ����
	private String goods_id;
	private String goods_name;
	private String price;
	private String introduce;
	private String num;	
	//��Ʒ���
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
	public String  getPrice()
	{
		return price;
	}
	public void setPrice(String  price) 
	{
		this.price = price;
	}
	//��Ʒ���
	public String getIntroduce() 
	{
		return introduce;
	}
	public void setIntroduce(String introduce)
	{
		this.introduce = introduce;
	}
	//��Ʒ����
	public String getNum() 
	{
		return num;
	}
	public void setNum(String num)
	{
		this.num = num;
	}
}