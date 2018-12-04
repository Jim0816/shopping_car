package com.ljm.operator;

import java.sql.SQLException;

import javax.swing.JPasswordField;
import javax.swing.JTextField;

import com.ljm.model.Product;
import com.ljm.model.ShoppingCar;
import com.ljm.model.User;
/*
 * ���ߣ������
 * ���ܣ���������ݿ�Ĺ������ʽӿ�
 */
public interface Dao 
{
/***********************�û����ʽӿ�***********************/
	//��ѯָ���û�
	public User getUser(String userId);
	//��ѯ�����û�
	public void getAllUser();
	//�û���¼�����ң�
	public User findUser(String userid,String usercode);
	//����û�
	public void addUser(User user);
    //�޸��û�
	public void updateUser(User user);
	//ɾ��ָ���û�
	public void deleteUser(User user);	
/***********************��Ʒ���ʽӿ�***********************/
	//ͨ����ƷID��ѯָ����Ʒ
	public Product getProduct(String goodsId);
	//ͨ����Ʒ���Ʋ�ѯָ����Ʒ
	public Product getProductByname(String goodsName);
	//��ѯ������Ʒ
	public void getAllProduct();
	//��ȡ��Ʒ��Ϣ
	public  Object[][] getProductInfo();
    //�����Ʒ
	public void addProduct(Product product);
	//�޸���Ʒ
	public void updateProduct(Product product);
	//ɾ��ָ����Ʒ
	public void deleteProduct(Product product);
/***********************���ﳵ���ʽӿ�***********************/
	//��ѯ���ﳵ��ָ����Ʒ
	public ShoppingCar getCar(String goodsId);
	//ͨ���û�ID�鿴���ﳵ
	public ShoppingCar findCar(String userId);
	//��ȡ���ﳵ����Ʒ��Ϣ
	public  Object[][] getCarInfo(String userId);
    //�����Ʒ�����ﳵ
	public void addCar(ShoppingCar car);
	//�޸Ĺ��ﳵ����Ʒ
	public void updateCar(ShoppingCar car);
	//ɾ�����ﳵ��ָ����Ʒ
	public void deleteCar(ShoppingCar car);
}
