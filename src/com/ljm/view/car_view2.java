package com.ljm.view;
import java.awt.BorderLayout;
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
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
import com.ljm.model.Product;
import com.ljm.model.ShoppingCar;
import com.ljm.operator.Dao;
import com.ljm.operator.DaoImplement;
/*
 * @作者：李际明
 * @功能：购物车主界面
 */
public class car_view2 extends JFrame implements ActionListener
{
	//定义组件
    DefaultTableModel tableModel;
	private JTable table;
	public JScrollPane scrollPane;
	JLayeredPane layeredPane;
	private JPanel jp1,jp2,jp3,jp4;
	private JTextField text1,text2;
	private JLabel j1,j2;
	public JButton addBtn,updBtn,delBtn,payBtn,goshopBtn,returnBtn;
	ImageIcon image;
	//定义所需数据
	String[] columnNames = {"商品编号","商品名称","商品价格","商品数量","总价"};
	String [] column = new String[]{"商品编号","商品名称","商品价格","商品数量","总价"};
	Object[][] tableData = null;              
	Dao dao = new DaoImplement();
	private String userId;
	//构造方法
	public car_view2(String userId)
	{   
		this.userId = userId;
	    view_14();
	}
	//管理商品界面
	public void view_14()
	{   layeredPane = new JLayeredPane();                 //初始化分层面板
		image = new ImageIcon("image\\car_view\\gwcbj.jpg");     //获取窗体背景图片
		//初始化窗口
		setTitle("购物车");
		setSize(image.getIconWidth(),image.getIconHeight());   //918*518
		this.setLayout(null);
		this.setResizable(false);
		this.setLocationRelativeTo(null);
		//初始化背景标签
		j1=new JLabel(image);
		j1.setBounds(0, 0,image.getIconWidth(),image.getIconHeight());
		//表格模型获取数据
	    tableData = dao.getCarInfo(userId);                              //提取数据库中商品表的信息
		tableModel = new DefaultTableModel (tableData,columnNames);
		//初始化表格
		table = new JTable(tableModel);                                //表格模型实例化
		table.setRowSorter(new TableRowSorter(tableModel));            //设置表的排序器
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);   //设置为单选模式
		table.setForeground(Color.BLACK);                             //设置表格前景色
		table.setSelectionForeground(Color.BLUE);                       //设置被选择行的前景色
	    table.setRowHeight(30);                                        //设置表格行高
	    //获取总价所在列的和
	    Double sumPrice = (double) 0;
	    int rowNum = table.getRowCount();
	    for(int i=0;i<rowNum;i++)
	    {
	    	try
	    	{
	    		sumPrice=sumPrice+Double.parseDouble(tableModel.getValueAt(i, 4).toString());
	    	}
	    	catch(Exception e)
	    	{
	    		e.printStackTrace();
	    	}
	    }
	    //把表格添加到滚动面板
	    scrollPane = new JScrollPane(table);
	    scrollPane.setBounds(109,50,700,250);
	    //设置表格和滚动面板透明
	    DefaultTableCellRenderer render = new DefaultTableCellRenderer();
	    render.setOpaque(false);
	    for(int i=0;i<column.length;i++)
	    {
	    	table.getColumn(column[i]).setCellRenderer(render);
	    }
	    table.setOpaque(false);
	    scrollPane.setOpaque(false);
	    scrollPane.getViewport().setOpaque(false);
	    //创建一个新面板jp1，放入修改商品数量的设置框
	    jp1 = new JPanel();     
	    jp1.setBounds(350, 325, 200, 30);
	    jp1.setOpaque(false);
	    text1 = new JTextField(2);
	    jp1.add(text1);
	    //创建一个新面板jp2，放入商品操作按钮
	    jp2 = new JPanel(new GridLayout(1,1,45,15));     
	    jp2.setBounds(345, 355, 210, 35);
	    jp2.setOpaque(false);
	    addBtn = new JButton(new ImageIcon("image\\car_view\\zengjia.jpg"));
	    addBtn.setBorderPainted(false);
	    updBtn = new JButton(new ImageIcon("image\\car_view\\xiugai2.jpg"));
	    updBtn.setBorderPainted(false);
	    delBtn = new JButton(new ImageIcon("image\\car_view\\jianshao.jpg"));
	    delBtn.setBorderPainted(false);
	    jp2.add(addBtn);
	    jp2.add(updBtn);
	    jp2.add(delBtn);
	   //创建一个新面板jp3，放入费用计算相关的标签、文本框
	    jp3 = new JPanel(new GridLayout(2,1,20,50));     
	    jp3.setBounds(0, 370, 100, 100);
	    jp3.setOpaque(false);
	    j2=new JLabel(new ImageIcon("image\\car_view\\heji.jpg"));
	    text2 = new JTextField(8);
	    text2.setText(String.valueOf(sumPrice));
	    jp3.add(j2);
	    jp3.add(text2);
	    //新建一个面板jp4，放入“”去商场”，“结算”按钮
	    jp4 = new JPanel(new GridLayout(1,1,110,32));
	    jp4.setBounds(275, 443, 360, 28);
	    jp4.setOpaque(false);
	    payBtn = new JButton(new ImageIcon("image\\car_view\\jiesuan.jpg"));
	    goshopBtn = new JButton(new ImageIcon("image\\car_view\\shangcheng.jpg"));
	    returnBtn = new JButton(new ImageIcon("image\\car_view\\fanhui.jpg"));
	    jp4.add(payBtn);
	    jp4.add(goshopBtn);
	    jp4.add(returnBtn);
	    //注册监听
	    addBtn.addActionListener(this);	
	    updBtn.addActionListener(this);	
	    delBtn.addActionListener(this);	
	    payBtn.addActionListener(this);
	    goshopBtn.addActionListener(this);
	    returnBtn.addActionListener(this);
	    //面板分层布局
	    layeredPane.add(j1,JLayeredPane.DEFAULT_LAYER);
	    layeredPane.add(scrollPane,JLayeredPane.MODAL_LAYER);
	    layeredPane.add(jp1,JLayeredPane.MODAL_LAYER);
	    layeredPane.add(jp2,JLayeredPane.MODAL_LAYER);
	    layeredPane.add(jp3,JLayeredPane.MODAL_LAYER);
	    layeredPane.add(jp4,JLayeredPane.MODAL_LAYER);
	    this.setLayeredPane(layeredPane);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
	}
    //对事件处理的方法
	public void actionPerformed(ActionEvent e) 
	{
		//添加商品数量
		if(e.getSource()==addBtn)
		{
			//选中添加
			int selectedRow = table.getSelectedRow();       //获取被选择的行
			if(selectedRow!=-1)
			{   
			    Product product = new Product();
			    product = dao.getProduct(tableModel.getValueAt(selectedRow, 0).toString());
			    if(Integer.parseInt(product.getNum())>=1)
			    {
			    	this.dispose();
			    	//从商城中取商品
				    int num = Integer.parseInt(product.getNum())-1;
				    product.setNum(String.valueOf(num));
				    dao.updateProduct(product);
				    //加入到购物车
					ShoppingCar car = new ShoppingCar();
					car.setUser_id((dao.getCar(tableModel.getValueAt(selectedRow, 0).toString())).getUser_id());
	         		car.setGoods_id(tableModel.getValueAt(selectedRow, 0).toString());
			        car.setGoods_name(tableModel.getValueAt(selectedRow, 1).toString());
			        car.setPrice(tableModel.getValueAt(selectedRow, 2).toString());
			        car.setGoods_num(tableModel.getValueAt(selectedRow, 3).toString());
			        car.setOnegoods_sumprice(tableModel.getValueAt(selectedRow, 4).toString());
			        //只增加商品数量
			        int s = Integer.parseInt(car.getGoods_num())+1;
			        //获取商品总价
			        Number sp = Float.parseFloat(car.getPrice())*s;      //商品总价
			        Float sum = sp.floatValue();                             //商品总价转换
			        car.setGoods_num(String.valueOf(s));
			        car.setOnegoods_sumprice(String.valueOf(sum));
			        dao.updateCar(car);
			        new car_view2(userId);
			    }
			    else
			    {
			    	JOptionPane.showMessageDialog(null,"该商品库存不足");
			    }       
			}
			else
    		{
    			JOptionPane.showMessageDialog(null,"请选择需要被添加的商品");
    		}
		}
		//设置商品数量
		else if(e.getSource()==updBtn)
		{
			//选中添加
			int selectedRow = table.getSelectedRow();       //获取被选择的行
			if(selectedRow!=-1)
			{
				Product product = new Product();
			    product = dao.getProduct(tableModel.getValueAt(selectedRow, 0).toString());
				//设置购物车中商品的数量
				ShoppingCar car = new ShoppingCar();
				car.setUser_id((dao.getCar(tableModel.getValueAt(selectedRow, 0).toString())).getUser_id());
         		car.setGoods_id(tableModel.getValueAt(selectedRow, 0).toString());
		        car.setGoods_name(tableModel.getValueAt(selectedRow, 1).toString());
		        car.setPrice(tableModel.getValueAt(selectedRow, 2).toString());
		        car.setGoods_num(tableModel.getValueAt(selectedRow, 3).toString());
		        car.setOnegoods_sumprice(tableModel.getValueAt(selectedRow, 4).toString());
		        //文本框设置的商品数量
		        int s = Integer.parseInt(text1.getText());  
		        //设置的商品数量与购物车原本数量的比较值
		        int addnum = s-Integer.parseInt(tableModel.getValueAt(selectedRow, 3).toString());     
		        if(s>0)
		        {
		        	//向购物车增加商品的修改方式
			        if(addnum>0)
			        {
				        if(Integer.parseInt(product.getNum())<addnum)
				        {
				        	JOptionPane.showMessageDialog(null,"该商品库存不足");
				        }
				        else
				        {
				        	this.dispose();
				        	//从商城中取商品
						    int num = Integer.parseInt(product.getNum())-addnum;         //加入购物车后商城的商品数量
						    product.setNum(String.valueOf(num));
						    dao.updateProduct(product);
						    //添加到购物车
					        int num1 = Integer.parseInt(car.getGoods_num())+addnum;      //购物车增加商品后的商品数量
					        Number sp = Float.parseFloat(car.getPrice())*num1;           //商品新总价
					        Float sum = sp.floatValue();                                 //商品总价转换
					        car.setGoods_num(String.valueOf(s));
					        car.setOnegoods_sumprice(String.valueOf(sum));
					        dao.updateCar(car);
						    new car_view2(userId);
				        }
			        }
			        //减少购物车商品的修改方式
			        else if(addnum<0)
			        {
			        	this.dispose();
			        	//商城的商品数量增加
			        	int num = Integer.parseInt(product.getNum())-addnum;       //购物车减少商品后商城的商品数量     
			        	product.setNum(String.valueOf(num));
					    dao.updateProduct(product);
					    //购物车减少商品
					    int num1 = Integer.parseInt(car.getGoods_num())+addnum;      //购物车减少商品后的商品数量
				        Number sp = Float.parseFloat(car.getPrice())*num1;           //商品新总价
				        Float sum = sp.floatValue();                                 //商品总价转换
				        car.setGoods_num(String.valueOf(s));
				        car.setOnegoods_sumprice(String.valueOf(sum));
				        dao.updateCar(car); 
				        new car_view2(userId);
			        }
			        else if(addnum==0)
			        {
			        	this.dispose();
			        	new car_view2(userId);
			        }
		        }
		        else
		        {
		        	int n1 = Integer.parseInt(tableModel.getValueAt(selectedRow, 3).toString());   //购物车当前商品数量
		        	int n2 = Integer.parseInt(product.getNum());                                   //商城中该商品的数量
		        	this.dispose(); 
		        	dao.deleteCar(car);
		        	product.setNum(String.valueOf(n1+n2));
		        	dao.updateProduct(product);
					//判断购物车是否为空
					if(dao.findCar(userId)==null)
					{
						new car_view1(userId);
					}
					else
					{
						new car_view2(userId);
					}
		        }
			}
			else
    		{   
    			JOptionPane.showMessageDialog(null,"请选择需要修改数量的商品");
    		}
		}
		//减少商品数量
		else if(e.getSource()==delBtn)
		{
			//选中添加
			int selectedRow = table.getSelectedRow();       //获取被选择的行
			if(selectedRow!=-1)
			{   
			    Product product = new Product();
			    product = dao.getProduct(tableModel.getValueAt(selectedRow, 0).toString());
			    this.dispose();
				//减少购物车商品
			    ShoppingCar car = new ShoppingCar();
				car.setUser_id((dao.getCar(tableModel.getValueAt(selectedRow, 0).toString())).getUser_id());
	         	car.setGoods_id(tableModel.getValueAt(selectedRow, 0).toString());
			    car.setGoods_name(tableModel.getValueAt(selectedRow, 1).toString());
			    car.setPrice(tableModel.getValueAt(selectedRow, 2).toString());
			    car.setGoods_num(tableModel.getValueAt(selectedRow, 3).toString());
			    car.setOnegoods_sumprice(tableModel.getValueAt(selectedRow, 4).toString());
			    //只减少商品数量
			    int s = Integer.parseInt(car.getGoods_num())-1;
			    if(s>0)
			    {
			    	//获取商品总价
				    Number sp = Float.parseFloat(car.getPrice())*s;      //商品总价
				    Float sum = sp.floatValue();                         //商品总价转换
				    car.setGoods_num(String.valueOf(s));
				    car.setOnegoods_sumprice(String.valueOf(sum));
				    dao.updateCar(car);
			    }  
			    else
			    {
			    	dao.deleteCar(car);	
			    }
			    //商品放回商城
				int num = Integer.parseInt(product.getNum())+1;
				product.setNum(String.valueOf(num));
				dao.updateProduct(product);
				//判断购物车是否为空
				if(dao.findCar(userId)==null)
				{
					new car_view1(userId);
				}
				else
				{
					new car_view2(userId);
				}
			}
			else
    		{
    			JOptionPane.showMessageDialog(null,"请选择需要减少数量的商品");
    		}
			
		}
		//返回用户选择界面
		else if(e.getSource()==returnBtn)
		{
			this.dispose();
			new userOption_view(userId);
		}
		//进去商城购物
		else if(e.getSource()==goshopBtn)
		{
			this.dispose();
			new Market(userId);
		}
		//结算
		else if(e.getSource()==payBtn)
		{
			//
		}		
	}	      
}
