package com.ljm.view;
/*
 * @author：李际明
 * @function:选择商品数量界面
 */
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import com.ljm.model.Product;
import com.ljm.model.ShoppingCar;
import com.ljm.operator.Dao;
import com.ljm.operator.DaoImplement;
public class NumOption extends JFrame implements ActionListener
{
	//定义组件
	private JLabel jl1,jl2,jl3,jl4,jl5,jl6,jl7;
	private JButton jb1,jb2;
	private JPanel jp1,jp2,jp3,jp4;
	private JTextField jt1,jt2,jt3,jt4,jt5,jt6;
	JLayeredPane layeredPane;
	private String userId;
	private String goodsName;
	Dao dao = new DaoImplement();
	public NumOption(String userId,String goodsName) 
	{
		this.userId = userId;
		this.goodsName = goodsName;
		//初始化窗口
		layeredPane = new JLayeredPane();                 //初始化分层面
		setTitle("购物");
		setSize(414,525);
		this.setLayout(null);
		this.setResizable(false);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//设置介绍商品的区域的背景
		jl1 = new JLabel(new ImageIcon("image\\NumOption\\jieshao.jpg"));
		jl1.setBounds(0, 0, 414, 525);
		//两个面板jp1,jp2，在jp1上加5个标签，在jp2上加5个文本框
		jp1 = new JPanel(new GridLayout(5,1,50,30));
		jp1.setBounds(110, 90, 40, 200);
		jp1.setOpaque(false);
		jl2 = new JLabel("ID：");
		jl3 = new JLabel("名称：");
		jl4 = new JLabel("价格：");
		jl5 = new JLabel("简介：");
		jl6 = new JLabel("库存：");
		jp1.add(jl2);
		jp1.add(jl3);
		jp1.add(jl4);
		jp1.add(jl5);
		jp1.add(jl6);
		jp2 = new JPanel(new GridLayout(5,1,20,25));
		jp2.setBounds(150, 90, 160, 200);
		jp2.setOpaque(false);
		jt1 = new JTextField(dao.getProductByname(goodsName).getGoods_id(),15);
		jt1.setBorder(javax.swing.BorderFactory.createLineBorder(Color.WHITE, 0));
		jt2 = new JTextField(goodsName,15);
		jt2.setBorder(javax.swing.BorderFactory.createLineBorder(Color.WHITE, 0));
		jt3 = new JTextField(dao.getProductByname(goodsName).getPrice(),15);
		jt3.setBorder(javax.swing.BorderFactory.createLineBorder(Color.WHITE, 0));
		jt4 = new JTextField(dao.getProductByname(goodsName).getIntroduce(),15);
		jt4.setBorder(javax.swing.BorderFactory.createLineBorder(Color.WHITE, 0));
		jt5 = new JTextField(dao.getProductByname(goodsName).getNum(),15);
		jt5.setBorder(javax.swing.BorderFactory.createLineBorder(Color.WHITE, 0));
		jp2.add(jt1);
		jp2.add(jt2);
		jp2.add(jt3);
		jp2.add(jt4);
		jp2.add(jt5);
		//在面板jp3上加入标签jl7，文本框jt6；
		jp3 = new JPanel(new GridLayout(1,1,10,25));
		jp3.setBounds(100, 380, 160, 30);
		jp3.setOpaque(false);
		jl7 = new JLabel("添加数量：");
		jt6 = new JTextField("1",3);
		jp3.add(jl7);
		jp3.add(jt6);
		//在面板jp4上添加两个按钮
		jp4 = new JPanel(new GridLayout(1,1,85,50));
		jp4.setBounds(57, 420, 300,50 );
		jp4.setOpaque(false);
		jb1 = new JButton(new ImageIcon("image\\NumOption\\queding.jpg"));
		jb1.setBorderPainted(false);
		jb2 = new JButton(new ImageIcon("image\\NumOption\\fanhui2.jpg"));
		jb2.setBorderPainted(false);
		jp4.add(jb1);
		jp4.add(jb2);
		//监听
		jb1.addActionListener(this);
		jb2.addActionListener(this);
		 //面板分层布局
	    layeredPane.add(jl1,JLayeredPane.DEFAULT_LAYER);
	    layeredPane.add(jp1,JLayeredPane.MODAL_LAYER);
	    layeredPane.add(jp2,JLayeredPane.MODAL_LAYER);
	    layeredPane.add(jp3,JLayeredPane.MODAL_LAYER);
	    layeredPane.add(jp4,JLayeredPane.MODAL_LAYER);
	   // layeredPane.add(jp4,JLayeredPane.MODAL_LAYER);
	    this.setLayeredPane(layeredPane);
		this.setVisible(true);
	}
	//按钮事件处理
	public void actionPerformed(ActionEvent e) 
	{
		if(e.getSource()==jb1)
		{
			Product product = new Product();
			product.setGoods_id(jt1.getText());
			product.setGoods_name(jt2.getText());
			product.setPrice(jt3.getText());
			product.setIntroduce(jt4.getText());
			product.setNum(jt5.getText());
			if(Integer.parseInt(jt6.getText())>Integer.parseInt(jt5.getText()))
			{
				JOptionPane.showMessageDialog(null,"该商品库存不足");
			}
			else
			{
				int Num = Integer.parseInt(jt5.getText())-Integer.parseInt(jt6.getText());   //商城所剩该商品数量
				product.setNum(String.valueOf(Num));
				dao.updateProduct(product);
				ShoppingCar car = new ShoppingCar();
				car.setUser_id(userId);
				car.setGoods_id(jt1.getText());
				car.setGoods_name(jt2.getText());
				car.setPrice(jt3.getText());
				car.setGoods_num(jt6.getText());
				Number sp = Float.parseFloat(jt3.getText())*Integer.parseInt(jt6.getText());      //商品总价
			    Float sum = sp.floatValue();                          
				car.setOnegoods_sumprice(String.valueOf(sum));
				dao.addCar(car);
				this.dispose();
				JOptionPane.showMessageDialog(null,"商品成功加入购物车");
				new Market(userId);
			}
		}
		else if(e.getSource()==jb2)
		{
			this.dispose();
			new Market(userId);
		}
	}
}
