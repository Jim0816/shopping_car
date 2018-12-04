package com.ljm.view;
/*
 * @作者：李际明
 * @功能：商城界面
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
import com.sun.xml.internal.ws.org.objectweb.asm.Label;

public class Market extends JFrame implements ActionListener
{
	//定义组件
	private JLabel jl1,jl2;
	JLayeredPane layeredPane;
	private JTable table;
	public JScrollPane scrollPane;
	DefaultTableModel tableModel;
	public JButton Btn1,Btn2,Btn3;
	private JPanel jp1;
	//定义所需数据
	String[] columnNames = {"商品编号","商品名称","商品价格","商品简介","商品库存"};
	String [] column = new String[]{"商品编号","商品名称","商品价格","商品简介","商品库存"};
	Object[][] tableData = null;              
	Dao dao = new DaoImplement();
	private String userId;
	public Market(String userId) 
	{
		this.userId = userId;
		layeredPane = new JLayeredPane();                 //初始化分层面板
		//初始化窗口
		setTitle("商城");
		setSize(900,640);
		this.setLayout(null);
		this.setResizable(false);
		this.setLocationRelativeTo(null);
	    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    this.setLayeredPane(layeredPane);
		//设置面板的第一层背景
		jl1=new JLabel(new ImageIcon("image\\Market\\pobu.jpg"));
		jl1.setBounds(0,90,900,520);
		jl2=new JLabel(new ImageIcon("image\\Market\\xuezhishangcheng.jpg"));
		jl2.setBounds(0,0,900,108);
		//表格模型获取数据
	    tableData = dao.getProductInfo();                              //提取数据库中商品表的信息
		tableModel = new DefaultTableModel (tableData,columnNames);
		//初始化表格
		table = new JTable(tableModel);                                //表格模型实例化
		table.setRowSorter(new TableRowSorter(tableModel));            //设置表的排序器
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);   //设置为单选模式
		table.setForeground(Color.BLUE);                             //设置表格前景色
		table.setSelectionForeground(Color.BLACK);                    //设置被选择行的前景色
	    table.setRowHeight(30);                                        //设置表格行高
	    //把表格添加到滚动面板
	    scrollPane = new JScrollPane(table);
	    scrollPane.setBounds(120,200,700,300);
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
	    //创建一个新面板,放按钮
	    jp1 = new JPanel(new GridLayout(1,1,160,22));     
	    jp1.setBounds(200, 555, 500, 53);
	    jp1.setOpaque(false);
	    //初始化及添加按钮到panel中
	    Btn1 = new JButton(new ImageIcon("image\\Market\\shouhuo.jpg"));
	    Btn2 = new JButton(new ImageIcon("image\\Market\\jrgwc.jpg"));
	    Btn3 = new JButton(new ImageIcon("image\\Market\\gouwuche.jpg"));
	    jp1.add(Btn1);
	    jp1.add(Btn2);
	    jp1.add(Btn3);
	    //监听按钮
	    Btn1.addActionListener(this);
	    Btn2.addActionListener(this);
	    Btn3.addActionListener(this);
	    //加入到不同层development面板
	    layeredPane.add(jl1,JLayeredPane.DEFAULT_LAYER);
	    layeredPane.add(jl2,JLayeredPane.DEFAULT_LAYER);
	    layeredPane.add(scrollPane,JLayeredPane.MODAL_LAYER);
	    layeredPane.add(jp1,JLayeredPane.MODAL_LAYER);
	    this.setLayeredPane(layeredPane);
	    this.setVisible(true);
	}
    //按钮事件处理
	public void actionPerformed(ActionEvent e) 
	{   //搜索商品
		if(e.getSource()==Btn1)
		{
			this.dispose();
			new FindGoods(userId);
		}
		//将选中商品加入购物车
		else if(e.getSource()==Btn2)
		{
			//选中添加
			int selectedRow = table.getSelectedRow();       //获取被选择的行
			if(selectedRow!=-1)
			{
			    this.dispose();
				new NumOption(userId,tableModel.getValueAt(selectedRow, 1).toString());
			}
			else
    		{
    			JOptionPane.showMessageDialog(null,"请选择需要添加的商品");
    		}
		}
		//前往购物车
		else if(e.getSource()==Btn3)
		{
			this.dispose();
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
}
