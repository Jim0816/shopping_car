package com.ljm.operator;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JPasswordField;
import javax.swing.JTextField;

import com.ljm.model.Product;
import com.ljm.model.ShoppingCar;
import com.ljm.model.User;
/*
 * ���ߣ������
 * ���ܣ��Խӿ�UserDao��ʵ��
 * 
 */
public class DaoImplement implements Dao 
{
/***********************�û����ʽӿڵ�ʵ��***********************/
	//��ѯָ���û�
	public User getUser(String userId) 
	{
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		User user = null;
		try
		{
			//��������
			conn = jdbcTools.getConnection();
			//�������
			String sql="select * from user where user_id=?";
			ps = conn.prepareStatement(sql);
			ps.setString(1,userId);
			//ִ�����
			rs = ps.executeQuery();
			//������
			while(rs.next())	
			{
				user = mappingUser(rs);
			}
	    }
	   catch(SQLException e)
		{
			throw new DaoException(e.getMessage(),e);
		}
		
		return user;
    }
	//��ѯ�����û�
	public void getAllUser()
	{
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try
		{
			//��������
			conn = jdbcTools.getConnection();
			//�������
			String sql="select * from user";
			ps = conn.prepareStatement(sql);
			//ִ�����
			rs = ps.executeQuery();
			//������
			System.out.println("���              �û���               ����              ��ʵ����        �绰����           ��ַ           ���ѽ��");
			while(rs.next())	
			{
				System.out.println(
						rs.getString("user_id")+"\t"+rs.getString("user_name")
						+"\t"+rs.getString("user_code")+"\t"+rs.getString("true_name")
						+"\t"+rs.getString("tel")+"\t"+rs.getString("address")
						+"\t"+rs.getFloat("cost_money")
						          );	
			}
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
	//getUser��findUser���õĲ��ִ��룬��ߴ����������
	private User mappingUser(ResultSet rs) throws SQLException
	{
		User user = new User();
		user.setUser_id(rs.getString("user_id"));
		user.setUser_name(rs.getString("user_name"));
		user.setUser_code(rs.getString("user_code"));
		user.setTrue_name(rs.getString("true_name"));
		user.setTel(rs.getString("tel"));
		user.setAddress(rs.getString("address"));
		user.setCost_money(rs.getFloat("cost_money"));
		return user;
	}
	//�û���¼�����ң�
	public User findUser(String userid,String usercode)
	{
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		User user = null;
		try
		{
			//��������
			conn = jdbcTools.getConnection();
			//�������
			String sql="select * from user where user_id=? and user_code=?";
			ps = conn.prepareStatement(sql);
			ps.setString(1,userid);
			ps.setString(2,usercode);
			rs = ps.executeQuery();
			//������
			while(rs.next())	
			{
				user = mappingUser(rs);
			}
	   }
	   catch(SQLException e)
		{
			throw new DaoException(e.getMessage(),e);
		}
		finally
		{
			   jdbcTools.free(rs, ps, conn);
		}
	
		return user;
	}
	//����û�
	public void addUser(User user)
	{
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try
		{
			//��������
			conn = jdbcTools.getConnection();
			//�������
			String sql="insert into user(user_id,user_name,user_code,true_name,tel,address,cost_money) values(?,?,?,?,?,?,?)";
			ps = conn.prepareStatement(sql);
			ps.setString(1,user.getUser_id());
			ps.setString(2, user.getUser_name());
			ps.setString(3, user.getUser_code());
			ps.setString(4, user.getTrue_name());
			ps.setString(5, user.getTel());
			ps.setString(6, user.getAddress());
			ps.setFloat(7,user.getCost_money() );
			//ִ�����
			ps.executeUpdate();
			System.out.println("��ӳɹ���");
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
	//�޸��û�
	public void updateUser(User user) 
	{
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try
		{
			//��������
			conn = jdbcTools.getConnection();
			//�������
			String sql="update user set user_name=?,user_code=?,true_name=?,tel=?,address=?,cost_money=? where user_id=?";
			ps = conn.prepareStatement(sql);
			ps.setString(1, user.getUser_name());
			ps.setString(2, user.getUser_code());
			ps.setString(3, user.getTrue_name());
			ps.setString(4, user.getTel());
			ps.setString(5, user.getAddress());
			ps.setFloat(6,user.getCost_money() );
			ps.setString(7,user.getUser_id());
			//ִ�����
			ps.executeUpdate();
			System.out.println("�޸ĳɹ���");
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
	//ɾ��ָ���û�
	public void deleteUser(User user)
	{
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try
		{
			//��������
			conn = jdbcTools.getConnection();
			//�������
			String sql="delete from user where user_id=?";
			ps = conn.prepareStatement(sql);
			ps.setString(1,user.getUser_id());
			//ִ�����
			ps.executeUpdate();
			System.out.println("ɾ���ɹ���");
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
/***********************��Ʒ���ʽӿ�ʵ��***********************/
	//ͨ����ƷID��ѯָ����Ʒ
	public Product getProduct(String goodsId)
	{
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;	
		Product product = null;
	 try
		{
			//��������
			conn = jdbcTools.getConnection();
		    //�������
	        String sql="select * from product where goods_id=?";
			ps = conn.prepareStatement(sql);
			ps.setString(1,goodsId);
			//ִ�����
			rs = ps.executeQuery();
			//������
			while(rs.next())	
			{
				product = mappingProduct(rs);
			}
	    }
	   catch(SQLException e)
		{
			throw new DaoException(e.getMessage(),e);
		}
		
		return product;
	}
	//ͨ����Ʒ���Ʋ�ѯָ����Ʒ
	public Product getProductByname(String goodsName)
	{
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;	
		Product product = null;
	  try
		{
			//��������
			conn = jdbcTools.getConnection();
		    //�������
	        String sql="select * from product where goods_name=?";
			ps = conn.prepareStatement(sql);
			ps.setString(1,goodsName);
			//ִ�����
			rs = ps.executeQuery();
			//������
			while(rs.next())	
			{
				product = mappingProduct(rs);
			}
	    }
	   catch(SQLException e)
		{
			throw new DaoException(e.getMessage(),e);
		}
		return product;
	}
    //��ѯ������Ʒ
    public void getAllProduct() 
    {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try
		{
			//��������
			conn = jdbcTools.getConnection();
			//�������
			String sql="select * from product";
			ps = conn.prepareStatement(sql);
			//ִ�����
			rs = ps.executeQuery();
			//������
			while(rs.next())	
			{
				System.out.println(
						rs.getString("goods_id")+"\t\t"+rs.getString("goods_name")
						+"\t\t"+rs.getFloat("price")+"\t\t"+rs.getString("introduce")
						+"\t\t"+rs.getString("num")
						          );
			}
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
    //��ȡ��Ʒ��Ϣ
    public Object[][] getProductInfo()
    {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
    	Object[][] data = new Object[0][];
    	try
    	{
    		conn = jdbcTools.getConnection();
    		 int n = numberCount();
    		 data = new Object[n][];
    		String sql = "select * from product ";
    		ps = conn.prepareStatement(sql);
    		rs = ps.executeQuery();
    		int i = 0;
    		while(rs.next())
    		{
    			Object[] productdata = new Object[] {rs.getString("goods_id"),
    					rs.getString("goods_name"),rs.getFloat("price"),rs.getString("introduce"),rs.getString("num")};
    			data[i] = productdata;
        		i++;
    		}
    	}
    	catch(SQLException e)
		{
			throw new DaoException(e.getMessage(),e);
		}
	    finally
	   {
		   jdbcTools.free(rs, ps, conn);
	   }
    	
		return data;	
    }
    //��ȡ��Ʒ��Ϣ�Ĳ���ģ��
	private Product mappingProduct(ResultSet rs) throws SQLException
	{
		Product product = new Product();
		product.setGoods_id(rs.getString("goods_id"));
		product.setGoods_name(rs.getString("goods_name"));
		product.setPrice(rs.getString("price"));
		product.setIntroduce(rs.getString("introduce"));
		product.setNum(rs.getString("num"));
		return product;
	}
	//ȷ����Ʒ��������
	private int numberCount()
	{
		int n;
		for(n=0;n<50;n++)
		{
		}
		return n;
	}
	//�����Ʒ
    public void addProduct(Product product) 
    {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try
		{
			//��������
			conn = jdbcTools.getConnection();
			//�������
			String sql="insert into product(goods_id,goods_name,price,introduce,num) values(?,?,?,?,?)";
			ps = conn.prepareStatement(sql);
			ps.setString(1,product.getGoods_id());
			ps.setString(2, product.getGoods_name());
			ps.setString(3,product.getPrice());
			ps.setString(4,product.getIntroduce());
			ps.setString(5,product.getNum());
			//ִ�����
			ps.executeUpdate();
			System.out.println("��ӳɹ���");
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
    //�޸���Ʒ
    public void updateProduct(Product product) 
    {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try
		{
			//��������
			conn = jdbcTools.getConnection();
			//�������
			String sql="update product set goods_name=?,price=?,introduce=?,num=? where goods_id=?";
			ps = conn.prepareStatement(sql);
			ps.setString(1, product.getGoods_name());
			ps.setString(2,product.getPrice());
			ps.setString(3,product.getIntroduce());
			ps.setString(4,product.getNum());
			ps.setString(5,product.getGoods_id());
			//ִ�����
			ps.executeUpdate();
			System.out.println("�޸ĳɹ���");
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
    //ɾ��ָ����Ʒ
    public void deleteProduct(Product product)
    {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try
		{
			//��������
			conn = jdbcTools.getConnection();
			//�������
			String sql="delete from product where goods_id=?";
			ps = conn.prepareStatement(sql);
			ps.setString(1,product.getGoods_id());
			//ִ�����
			ps.executeUpdate();
			System.out.println("ɾ���ɹ���");
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
/***********************���ﳵ���ʽӿ�ʵ��***********************/
	//ͨ����ƷID��ѯ���ﳵ����������Ϣ
	public ShoppingCar getCar(String goodsId)
	{
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		ShoppingCar car = null; 
		try
		{
			//��������
			conn = jdbcTools.getConnection();
			//�������
			String sql="select *from shop_car where goods_id=?";
			ps = conn.prepareStatement(sql);
			ps.setString(1,goodsId);
			//ִ�����
			rs = ps.executeQuery();
			//������
			while(rs.next())	
			{
				car = mappingShoppingCar(rs);
			}
	    }
	   catch(SQLException e)
		{
			throw new DaoException(e.getMessage(),e);
		}
		
		return car;
	}
	//ͨ���û�ID�鿴���ﳵ
	public ShoppingCar findCar(String userId)
	{
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		ShoppingCar car = null; 
		try
		{
			//��������
			conn = jdbcTools.getConnection();
			//�������
			String sql="select *from shop_car where user_id=?";
			ps = conn.prepareStatement(sql);
			ps.setString(1,userId);
			//ִ�����
			rs = ps.executeQuery();
			//������
			while(rs.next())	
			{
				car = mappingShoppingCar(rs);
			}
	    }
	   catch(SQLException e)
		{
			throw new DaoException(e.getMessage(),e);
		}
		return car;
	}
	//���õĲ��ִ��룬��ߴ����������
	private ShoppingCar mappingShoppingCar(ResultSet rs) throws SQLException
	{
		ShoppingCar car = new ShoppingCar();
		car.setUser_id(rs.getString("user_id"));
		car.setGoods_id(rs.getString("goods_id"));
		car.setGoods_name(rs.getString("goods_name"));
        car.setPrice(rs.getString("price"));
        car.setGoods_num(rs.getString("goods_num"));
	    car.setOnegoods_sumprice(rs.getString("onegoods_sumprice"));
		return car;
	}
	//��ȡ���ﳵ����Ʒ��Ϣ����ƷID����Ʒ���ơ���Ʒ�۸���Ʒ������������Ʒ�ܼ�      ������ڱ��
	public  Object[][] getCarInfo(String userId)
	{
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
    	Object[][] data = new Object[0][];
    	try
    	{
    		conn = jdbcTools.getConnection();
    		 int n = numberCount();
    		 data = new Object[n][];
    		String sql = "select * from shop_car where user_id=?";
    		ps = conn.prepareStatement(sql);
    		ps.setString(1,userId);
    		rs = ps.executeQuery();
    		int i = 0;
    		while(rs.next())
    		{
    			Object[] cardata = new Object[] {rs.getString("goods_id"),
    					rs.getString("goods_name"),rs.getFloat("price"),rs.getString("goods_num"),rs.getString("onegoods_sumprice")};
    			data[i] = cardata;
        		i++;
    		}
    	}
    	catch(SQLException e)
		{
			throw new DaoException(e.getMessage(),e);
		}
	    finally
	   {
		   jdbcTools.free(rs, ps, conn);
	   }
		return data;
	}
    //�����Ʒ�����ﳵ���������һ��������ShoppingCar
	public void addCar(ShoppingCar car)
	{
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try
		{
			//��������
			conn = jdbcTools.getConnection();
			//�������
			String sql="insert into shop_car(user_id,goods_id,goods_name,price,goods_num,onegoods_sumprice) values(?,?,?,?,?,?)";
			ps = conn.prepareStatement(sql);
			ps.setString(1,car.getUser_id());
			ps.setString(2,car.getGoods_id());
			ps.setString(3,car.getGoods_name());
			ps.setString(4,car.getPrice());
			ps.setString(5,car.getGoods_num());
			ps.setString(6,car.getOnegoods_sumprice());
			//ִ�����
			ps.executeUpdate();
			System.out.println("��ӳɹ���");
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
	//�޸Ĺ��ﳵ����һ�е�������Ϣ,��Ҫ����û��޸���Ʒ����
	public void updateCar(ShoppingCar car)
	{
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try
		{
			//��������
			conn = jdbcTools.getConnection();
			//�������
			String sql="update shop_car set user_id=?,goods_name=?,price=?,goods_num=?,onegoods_sumprice=? where goods_id=?";
			ps = conn.prepareStatement(sql);
			ps.setString(1,car.getUser_id());
			ps.setString(2,car.getGoods_name());
			ps.setString(3,car.getPrice());
			ps.setString(4,car.getGoods_num());
			ps.setString(5,car.getOnegoods_sumprice());
		    ps.setString(6, car.getGoods_id());
			//ִ�����
		    ps.executeUpdate();
			System.out.println("�޸ĳɹ���");
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
	//ɾ�����ﳵ��ָ����Ʒ
	public void deleteCar(ShoppingCar car)
	{
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try
		{
			//��������
			conn = jdbcTools.getConnection();
			//�������
			String sql="delete from shop_car where goods_id=?";
			ps = conn.prepareStatement(sql);
			ps.setString(1,car.getGoods_id());
			//ִ�����
			ps.executeUpdate();
			System.out.println("ɾ���ɹ���");
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
