package com.ljm.view;
/*
*@作者：李际明
*@功能：管理员对商品的管理操作
*
*/
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;
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
import javax.swing.RowSorter;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
import com.ljm.model.Product;
import com.ljm.model.User;
import com.ljm.operator.Dao;
import com.ljm.operator.DaoException;
import com.ljm.operator.DaoImplement;
import com.ljm.operator.jdbcTools;
import com.mysql.jdbc.Statement;
import com.sun.java.swing.plaf.windows.WindowsLookAndFeel;
import javafx.scene.text.Font;
public class Manager_view extends JFrame implements ActionListener
{
	//定义组件
    DefaultTableModel tableModel;
	private JTable table;
	public JScrollPane scrollPane;
	JLayeredPane layeredPane;
	private JPanel jp2,jp3;
	private JTextField text1,text2,text3,text4,text5;
	private JLabel jl_1;
	public JButton addBtn,updBtn,delBtn;
	ImageIcon image;
	//定义所需数据
	String[] columnNames = {"商品编号","商品名称","商品价格","商品简介","商品数量"};
	String [] column = new String[]{"商品编号","商品名称","商品价格","商品简介","商品数量"};
	Object[][] tableData = null;              
	Dao dao = new DaoImplement();
	//构造方法
	public Manager_view()
	{   
	    view_4();
	}
	//管理商品界面
	public void view_4()
	{   layeredPane = new JLayeredPane();                 //初始化分层面板
		image = new ImageIcon("image\\Manager\\xuhuabeijing.jpg");     //获取窗体背景图片
		//初始化窗口
		try {
			UIManager.setLookAndFeel(new WindowsLookAndFeel());       //将JFrame的LookAndFeel设定为Windows
		} catch (UnsupportedLookAndFeelException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		setTitle("管理商品");
		setSize(image.getIconWidth(),image.getIconHeight());
		this.setLayout(null);
		this.setResizable(false);
		this.setLocationRelativeTo(null);
		//初始化背景标签
		jl_1=new JLabel(image);
		jl_1.setBounds(0, 0,image.getIconWidth(),image.getIconHeight());
		//表格模型获取数据
	    tableData = dao.getProductInfo();                              //提取数据库中商品表的信息
		tableModel = new DefaultTableModel (tableData,columnNames);
		//初始化表格
		table = new JTable(tableModel);                                //表格模型实例化
		RowSorter sorter = new TableRowSorter(tableModel);
		table.setRowSorter(sorter);                                    //设置表的排序器
		//table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);   //设置为单选模式
		table.setForeground(Color.BLACK);                             //设置表格前景色
		table.setSelectionForeground(Color.RED);                       //设置被选择行的前景色
	    table.setRowHeight(30);                                        //设置表格行高
	    //把表格添加到滚动面板
	    scrollPane = new JScrollPane(table);
	    scrollPane.setBounds(100,50,700,300);
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
	    //创建一个新面板，放标签   文本框 
	    jp2 = new JPanel();     
	    jp2.setBounds(100, 365, 700, 30);
	    //在面板上添加标签   文本框 
	    jp2.add(new JLabel("商品编号"));
	    text1 = new JTextField(5);
	    jp2.add(text1);
	    jp2.add(new JLabel("商品名称"));
	    text2 = new JTextField(5);
	    jp2.add(text2);
	    jp2.add(new JLabel("商品价格"));
	    text3 = new JTextField(5);
	    jp2.add(text3);
	    jp2.add(new JLabel("商品简介"));
	    text4 = new JTextField(5);
	    jp2.add(text4);
	    jp2.add(new JLabel("商品数量"));
	    text5 = new JTextField(5);
	    jp2.add(text5);
	    jp2.setOpaque(false);
	    //创建一个新面板,放按钮
	    jp3 = new JPanel(new GridLayout(1,1,70,22));     
	    jp3.setBounds(275, 420, 350, 30);
	    jp3.setOpaque(false);
	    //初始化及添加按钮到panel中
	    addBtn = new JButton(new ImageIcon("image\\Manager\\tj.jpg"));
	    addBtn.setBounds(280, 420, 75, 33);
	    updBtn = new JButton(new ImageIcon("image\\Manager\\xg.jpg"));
	    delBtn = new JButton(new ImageIcon("image\\Manager\\sc.jpg"));
	    jp3.add(addBtn);
	    jp3.add(updBtn);
	    jp3.add(delBtn);
	    //注册监听
	    addBtn.addActionListener(this);	
	    updBtn.addActionListener(this);	
	    delBtn.addActionListener(this);	
	    layeredPane.add(jl_1,JLayeredPane.DEFAULT_LAYER);
	    layeredPane.add(scrollPane,JLayeredPane.MODAL_LAYER);
	    layeredPane.add(jp2,JLayeredPane.MODAL_LAYER);
	    layeredPane.add(jp3,JLayeredPane.MODAL_LAYER);
	    this.setLayeredPane(layeredPane);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
	}
    //对事件处理的方法
	public void actionPerformed(ActionEvent e) 
	{
		//添加商品
		if(e.getSource()==addBtn)
		{
			//选中添加
			int selectedRow = table.getSelectedRow();       //获取被选择的行
			if(selectedRow!=-1)
			{   
				this.dispose();
				Product product = new Product();
         		product.setGoods_id(tableModel.getValueAt(selectedRow, 0).toString());
		        product.setGoods_name(tableModel.getValueAt(selectedRow, 1).toString());
		        product.setPrice(tableModel.getValueAt(selectedRow, 2).toString());
		        product.setIntroduce(tableModel.getValueAt(selectedRow, 3).toString());
		        product.setNum(tableModel.getValueAt(selectedRow, 4).toString());
		        //只增加商品数量
		        int s = Integer.parseInt(product.getNum())+1;    
		        product.setNum(String.valueOf(s));
		        dao.updateProduct(product);
		        new Manager_view();   
			}
		    else
		    {  
		    	//添加不存在的商品
		        if(text1.getText().equals("")||text2.getText().equals("")||text3.getText().equals("")||text4.getText().equals(""))
		    	{
		        	JOptionPane.showMessageDialog(null, "请输入新商品的所有信息！","新商品输入添加提示",JOptionPane.WARNING_MESSAGE);
		    		text1.requestFocus();               //把输入焦点放置文本框text1
		        }  
		        else
		    	{
		        	this.dispose();
		    	    Product product = new Product();
		    		product.setGoods_id(text1.getText());
		    		product.setGoods_name(text2.getText());
		    		product.setPrice(text3.getText());
		    		product.setIntroduce(text4.getText());
		    		product.setNum(text5.getText());
		    		dao.addProduct(product);
		    		new Manager_view();
		        }	 					
		    }		
		}
		//修改商品
		else if(e.getSource()==updBtn)
		{
			int selectedRow = table.getSelectedRow();       //获取被选择的行
			if(selectedRow!=-1)
			{
			        Product product = new Product();
    		        product.setGoods_id(text1.getText());
    		        if(text1.getText().equals(""))
    		        {
    		        	product.setGoods_id(tableModel.getValueAt(selectedRow, 0).toString());
    		        }
    		        product.setGoods_name(text2.getText());
    		        if(text2.getText().equals(""))
    		        {
    		        	 product.setGoods_name(tableModel.getValueAt(selectedRow, 1).toString());
    		        }
    		        product.setPrice(text3.getText());
    		        if(text3.getText().equals(""))
    		        {
    		        	product.setPrice(tableModel.getValueAt(selectedRow, 2).toString());
    		        }
    		        product.setIntroduce(text4.getText());
    		        if(text4.getText().equals(""))
    		        {
    		        	product.setIntroduce(tableModel.getValueAt(selectedRow, 3).toString());
    		        }
    		        product.setNum(text5.getText());
    		        if(text5.getText().equals(""))
    		        {
    		        	 product.setNum(tableModel.getValueAt(selectedRow, 4).toString());
    		        }
    		        dao.updateProduct(product);
    		        tableModel.setValueAt(text1, selectedRow, 0);
	    			tableModel.setValueAt(text1, selectedRow, 1);
	    			tableModel.setValueAt(text1, selectedRow, 2);
	    			tableModel.setValueAt(text1, selectedRow, 3);
	    			tableModel.setValueAt(text1, selectedRow, 4);
    		        new Manager_view(); 
			}
			else
    		{
    			JOptionPane.showMessageDialog(null,"请先选择需要被修改的行");
    			//new Manager_view();
    		}
		}
		//删除商品
		else if(e.getSource()==delBtn)
		{
			int selectedRow = table.getSelectedRow();       //获取被选择的行
			//删除被选中商品的一件
			if(selectedRow!=-1)
			{
				//同步删除数据库对应的信息
				Product product = new Product();
   		        product.setGoods_id(tableModel.getValueAt(selectedRow, 0).toString());
		        product.setGoods_name(tableModel.getValueAt(selectedRow, 1).toString());
		        product.setPrice(tableModel.getValueAt(selectedRow, 2).toString());
		        product.setIntroduce(tableModel.getValueAt(selectedRow, 3).toString());
		        product.setNum(tableModel.getValueAt(selectedRow, 4).toString());
		        //商品数量为0时
		        if(Integer.parseInt(product.getNum())==0)
		        {
		        	String options[] = {"是","否"};
   		        	int value = JOptionPane.showOptionDialog(null, "是否将该商品下架？", "商品下架操作", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, null);
   		        	if(value!=JOptionPane.CLOSED_OPTION)
   		        	{
  		        		switch(value)
   		        		{
   		        		case 0:
   		        			this.dispose();
   		        			dao.deleteProduct(product);
   		        			new Manager_view();
   		        			break;
   		        		case 1:
   		        			this.dispose();
   		        			new Manager_view();
   		        	    }
		            }
   		        }
   		        //商品数量为1时
		        else if(Integer.parseInt(product.getNum())==1)
   		        {
		        	this.dispose();
   		        	int s = Integer.parseInt(product.getNum())-1;    
   		   		    product.setNum(String.valueOf(s));
   		   		    dao.updateProduct(product);
   		   		    new Manager_view();
   		        }
   		        //商品数量大于1时	
   		        else if(Integer.parseInt(product.getNum())>1)
   		        {
   		        	//只减少商品数量
   		        	int s = Integer.parseInt(product.getNum())-1;    
   		        	product.setNum(String.valueOf(s));
   		        	dao.updateProduct(product);
   		        	new Manager_view();
   		        }	
			}
			else
			{
				JOptionPane.showMessageDialog(null, "请选择需要被删除的商品","商品删除提示",JOptionPane.WARNING_MESSAGE);
			}
		}		
	}	
}

	   

	  
	    
