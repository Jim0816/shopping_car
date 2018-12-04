package com.ljm.operator;

import java.sql.SQLException;

import javax.swing.JPasswordField;
import javax.swing.JTextField;

import com.ljm.model.Product;
import com.ljm.model.ShoppingCar;
import com.ljm.model.User;
/*
 * 作者：李际明
 * 功能：定义对数据库的公共访问接口
 */
public interface Dao 
{
/***********************用户访问接口***********************/
	//查询指定用户
	public User getUser(String userId);
	//查询所有用户
	public void getAllUser();
	//用户登录（查找）
	public User findUser(String userid,String usercode);
	//添加用户
	public void addUser(User user);
    //修改用户
	public void updateUser(User user);
	//删除指定用户
	public void deleteUser(User user);	
/***********************商品访问接口***********************/
	//通过商品ID查询指定商品
	public Product getProduct(String goodsId);
	//通过商品名称查询指定商品
	public Product getProductByname(String goodsName);
	//查询所有商品
	public void getAllProduct();
	//提取商品信息
	public  Object[][] getProductInfo();
    //添加商品
	public void addProduct(Product product);
	//修改商品
	public void updateProduct(Product product);
	//删除指定商品
	public void deleteProduct(Product product);
/***********************购物车访问接口***********************/
	//查询购物车中指定商品
	public ShoppingCar getCar(String goodsId);
	//通过用户ID查看购物车
	public ShoppingCar findCar(String userId);
	//提取购物车中商品信息
	public  Object[][] getCarInfo(String userId);
    //添加商品到购物车
	public void addCar(ShoppingCar car);
	//修改购物车中商品
	public void updateCar(ShoppingCar car);
	//删除购物车中指定商品
	public void deleteCar(ShoppingCar car);
}
