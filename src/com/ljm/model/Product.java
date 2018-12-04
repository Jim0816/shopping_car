package com.ljm.model;
/**
*作者：李际明
*商品类
*/
public class Product
{
	//定义商品属性
	private String goods_id;
	private String goods_name;
	private String price;
	private String introduce;
	private String num;	
	//商品编号
	public String getGoods_id() 
	{
		return goods_id;
	}
	public void setGoods_id(String goods_id) 
	{
		this.goods_id = goods_id;
	}
	//商品名称
	public String getGoods_name() 
	{
		return goods_name;
	}
	public void setGoods_name(String goods_name) 
	{
		this.goods_name = goods_name;
	}
	//商品单价
	public String  getPrice()
	{
		return price;
	}
	public void setPrice(String  price) 
	{
		this.price = price;
	}
	//商品简介
	public String getIntroduce() 
	{
		return introduce;
	}
	public void setIntroduce(String introduce)
	{
		this.introduce = introduce;
	}
	//商品数量
	public String getNum() 
	{
		return num;
	}
	public void setNum(String num)
	{
		this.num = num;
	}
}