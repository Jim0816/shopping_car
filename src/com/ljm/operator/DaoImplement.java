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
 * 作者：李际明
 * 功能：对接口UserDao的实现
 * 
 */
public class DaoImplement implements Dao 
{
/***********************用户访问接口的实现***********************/
	//查询指定用户
	public User getUser(String userId) 
	{
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		User user = null;
		try
		{
			//建立连接
			conn = jdbcTools.getConnection();
			//创建语句
			String sql="select * from user where user_id=?";
			ps = conn.prepareStatement(sql);
			ps.setString(1,userId);
			//执行语句
			rs = ps.executeQuery();
			//处理结果
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
	//查询所有用户
	public void getAllUser()
	{
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try
		{
			//建立连接
			conn = jdbcTools.getConnection();
			//创建语句
			String sql="select * from user";
			ps = conn.prepareStatement(sql);
			//执行语句
			rs = ps.executeQuery();
			//处理结果
			System.out.println("编号              用户名               密码              真实姓名        电话号码           地址           消费金额");
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
	//getUser和findUser公用的部分代码，提高代码的重用性
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
	//用户登录（查找）
	public User findUser(String userid,String usercode)
	{
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		User user = null;
		try
		{
			//建立连接
			conn = jdbcTools.getConnection();
			//创建语句
			String sql="select * from user where user_id=? and user_code=?";
			ps = conn.prepareStatement(sql);
			ps.setString(1,userid);
			ps.setString(2,usercode);
			rs = ps.executeQuery();
			//处理结果
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
	//添加用户
	public void addUser(User user)
	{
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try
		{
			//建立连接
			conn = jdbcTools.getConnection();
			//创建语句
			String sql="insert into user(user_id,user_name,user_code,true_name,tel,address,cost_money) values(?,?,?,?,?,?,?)";
			ps = conn.prepareStatement(sql);
			ps.setString(1,user.getUser_id());
			ps.setString(2, user.getUser_name());
			ps.setString(3, user.getUser_code());
			ps.setString(4, user.getTrue_name());
			ps.setString(5, user.getTel());
			ps.setString(6, user.getAddress());
			ps.setFloat(7,user.getCost_money() );
			//执行语句
			ps.executeUpdate();
			System.out.println("添加成功！");
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
	//修改用户
	public void updateUser(User user) 
	{
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try
		{
			//建立连接
			conn = jdbcTools.getConnection();
			//创建语句
			String sql="update user set user_name=?,user_code=?,true_name=?,tel=?,address=?,cost_money=? where user_id=?";
			ps = conn.prepareStatement(sql);
			ps.setString(1, user.getUser_name());
			ps.setString(2, user.getUser_code());
			ps.setString(3, user.getTrue_name());
			ps.setString(4, user.getTel());
			ps.setString(5, user.getAddress());
			ps.setFloat(6,user.getCost_money() );
			ps.setString(7,user.getUser_id());
			//执行语句
			ps.executeUpdate();
			System.out.println("修改成功！");
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
	//删除指定用户
	public void deleteUser(User user)
	{
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try
		{
			//建立连接
			conn = jdbcTools.getConnection();
			//创建语句
			String sql="delete from user where user_id=?";
			ps = conn.prepareStatement(sql);
			ps.setString(1,user.getUser_id());
			//执行语句
			ps.executeUpdate();
			System.out.println("删除成功！");
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
/***********************商品访问接口实现***********************/
	//通过商品ID查询指定商品
	public Product getProduct(String goodsId)
	{
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;	
		Product product = null;
	 try
		{
			//建立连接
			conn = jdbcTools.getConnection();
		    //创建语句
	        String sql="select * from product where goods_id=?";
			ps = conn.prepareStatement(sql);
			ps.setString(1,goodsId);
			//执行语句
			rs = ps.executeQuery();
			//处理结果
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
	//通过商品名称查询指定商品
	public Product getProductByname(String goodsName)
	{
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;	
		Product product = null;
	  try
		{
			//建立连接
			conn = jdbcTools.getConnection();
		    //创建语句
	        String sql="select * from product where goods_name=?";
			ps = conn.prepareStatement(sql);
			ps.setString(1,goodsName);
			//执行语句
			rs = ps.executeQuery();
			//处理结果
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
    //查询所有商品
    public void getAllProduct() 
    {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try
		{
			//建立连接
			conn = jdbcTools.getConnection();
			//创建语句
			String sql="select * from product";
			ps = conn.prepareStatement(sql);
			//执行语句
			rs = ps.executeQuery();
			//处理结果
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
    //提取商品信息
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
    //提取商品信息的部分模块
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
	//确定商品栏的行数
	private int numberCount()
	{
		int n;
		for(n=0;n<50;n++)
		{
		}
		return n;
	}
	//添加商品
    public void addProduct(Product product) 
    {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try
		{
			//建立连接
			conn = jdbcTools.getConnection();
			//创建语句
			String sql="insert into product(goods_id,goods_name,price,introduce,num) values(?,?,?,?,?)";
			ps = conn.prepareStatement(sql);
			ps.setString(1,product.getGoods_id());
			ps.setString(2, product.getGoods_name());
			ps.setString(3,product.getPrice());
			ps.setString(4,product.getIntroduce());
			ps.setString(5,product.getNum());
			//执行语句
			ps.executeUpdate();
			System.out.println("添加成功！");
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
    //修改商品
    public void updateProduct(Product product) 
    {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try
		{
			//建立连接
			conn = jdbcTools.getConnection();
			//创建语句
			String sql="update product set goods_name=?,price=?,introduce=?,num=? where goods_id=?";
			ps = conn.prepareStatement(sql);
			ps.setString(1, product.getGoods_name());
			ps.setString(2,product.getPrice());
			ps.setString(3,product.getIntroduce());
			ps.setString(4,product.getNum());
			ps.setString(5,product.getGoods_id());
			//执行语句
			ps.executeUpdate();
			System.out.println("修改成功！");
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
    //删除指定商品
    public void deleteProduct(Product product)
    {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try
		{
			//建立连接
			conn = jdbcTools.getConnection();
			//创建语句
			String sql="delete from product where goods_id=?";
			ps = conn.prepareStatement(sql);
			ps.setString(1,product.getGoods_id());
			//执行语句
			ps.executeUpdate();
			System.out.println("删除成功！");
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
/***********************购物车访问接口实现***********************/
	//通过商品ID查询购物车表中所有信息
	public ShoppingCar getCar(String goodsId)
	{
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		ShoppingCar car = null; 
		try
		{
			//建立连接
			conn = jdbcTools.getConnection();
			//创建语句
			String sql="select *from shop_car where goods_id=?";
			ps = conn.prepareStatement(sql);
			ps.setString(1,goodsId);
			//执行语句
			rs = ps.executeQuery();
			//处理结果
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
	//通过用户ID查看购物车
	public ShoppingCar findCar(String userId)
	{
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		ShoppingCar car = null; 
		try
		{
			//建立连接
			conn = jdbcTools.getConnection();
			//创建语句
			String sql="select *from shop_car where user_id=?";
			ps = conn.prepareStatement(sql);
			ps.setString(1,userId);
			//执行语句
			rs = ps.executeQuery();
			//处理结果
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
	//公用的部分代码，提高代码的重用性
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
	//提取购物车中商品信息：商品ID、商品名称、商品价格、商品数量、单种商品总价      方便存于表格
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
    //添加商品到购物车，必须添加一个完整的ShoppingCar
	public void addCar(ShoppingCar car)
	{
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try
		{
			//建立连接
			conn = jdbcTools.getConnection();
			//创建语句
			String sql="insert into shop_car(user_id,goods_id,goods_name,price,goods_num,onegoods_sumprice) values(?,?,?,?,?,?)";
			ps = conn.prepareStatement(sql);
			ps.setString(1,car.getUser_id());
			ps.setString(2,car.getGoods_id());
			ps.setString(3,car.getGoods_name());
			ps.setString(4,car.getPrice());
			ps.setString(5,car.getGoods_num());
			ps.setString(6,car.getOnegoods_sumprice());
			//执行语句
			ps.executeUpdate();
			System.out.println("添加成功！");
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
	//修改购物车表中一行的所有信息,主要针对用户修改商品数量
	public void updateCar(ShoppingCar car)
	{
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try
		{
			//建立连接
			conn = jdbcTools.getConnection();
			//创建语句
			String sql="update shop_car set user_id=?,goods_name=?,price=?,goods_num=?,onegoods_sumprice=? where goods_id=?";
			ps = conn.prepareStatement(sql);
			ps.setString(1,car.getUser_id());
			ps.setString(2,car.getGoods_name());
			ps.setString(3,car.getPrice());
			ps.setString(4,car.getGoods_num());
			ps.setString(5,car.getOnegoods_sumprice());
		    ps.setString(6, car.getGoods_id());
			//执行语句
		    ps.executeUpdate();
			System.out.println("修改成功！");
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
	//删除购物车中指定商品
	public void deleteCar(ShoppingCar car)
	{
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try
		{
			//建立连接
			conn = jdbcTools.getConnection();
			//创建语句
			String sql="delete from shop_car where goods_id=?";
			ps = conn.prepareStatement(sql);
			ps.setString(1,car.getGoods_id());
			//执行语句
			ps.executeUpdate();
			System.out.println("删除成功！");
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
