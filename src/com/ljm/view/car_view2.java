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
 * @���ߣ������
 * @���ܣ����ﳵ������
 */
public class car_view2 extends JFrame implements ActionListener
{
	//�������
    DefaultTableModel tableModel;
	private JTable table;
	public JScrollPane scrollPane;
	JLayeredPane layeredPane;
	private JPanel jp1,jp2,jp3,jp4;
	private JTextField text1,text2;
	private JLabel j1,j2;
	public JButton addBtn,updBtn,delBtn,payBtn,goshopBtn,returnBtn;
	ImageIcon image;
	//������������
	String[] columnNames = {"��Ʒ���","��Ʒ����","��Ʒ�۸�","��Ʒ����","�ܼ�"};
	String [] column = new String[]{"��Ʒ���","��Ʒ����","��Ʒ�۸�","��Ʒ����","�ܼ�"};
	Object[][] tableData = null;              
	Dao dao = new DaoImplement();
	private String userId;
	//���췽��
	public car_view2(String userId)
	{   
		this.userId = userId;
	    view_14();
	}
	//������Ʒ����
	public void view_14()
	{   layeredPane = new JLayeredPane();                 //��ʼ���ֲ����
		image = new ImageIcon("image\\car_view\\gwcbj.jpg");     //��ȡ���屳��ͼƬ
		//��ʼ������
		setTitle("���ﳵ");
		setSize(image.getIconWidth(),image.getIconHeight());   //918*518
		this.setLayout(null);
		this.setResizable(false);
		this.setLocationRelativeTo(null);
		//��ʼ��������ǩ
		j1=new JLabel(image);
		j1.setBounds(0, 0,image.getIconWidth(),image.getIconHeight());
		//���ģ�ͻ�ȡ����
	    tableData = dao.getCarInfo(userId);                              //��ȡ���ݿ�����Ʒ�����Ϣ
		tableModel = new DefaultTableModel (tableData,columnNames);
		//��ʼ�����
		table = new JTable(tableModel);                                //���ģ��ʵ����
		table.setRowSorter(new TableRowSorter(tableModel));            //���ñ��������
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);   //����Ϊ��ѡģʽ
		table.setForeground(Color.BLACK);                             //���ñ��ǰ��ɫ
		table.setSelectionForeground(Color.BLUE);                       //���ñ�ѡ���е�ǰ��ɫ
	    table.setRowHeight(30);                                        //���ñ���и�
	    //��ȡ�ܼ������еĺ�
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
	    //�ѱ����ӵ��������
	    scrollPane = new JScrollPane(table);
	    scrollPane.setBounds(109,50,700,250);
	    //���ñ��͹������͸��
	    DefaultTableCellRenderer render = new DefaultTableCellRenderer();
	    render.setOpaque(false);
	    for(int i=0;i<column.length;i++)
	    {
	    	table.getColumn(column[i]).setCellRenderer(render);
	    }
	    table.setOpaque(false);
	    scrollPane.setOpaque(false);
	    scrollPane.getViewport().setOpaque(false);
	    //����һ�������jp1�������޸���Ʒ���������ÿ�
	    jp1 = new JPanel();     
	    jp1.setBounds(350, 325, 200, 30);
	    jp1.setOpaque(false);
	    text1 = new JTextField(2);
	    jp1.add(text1);
	    //����һ�������jp2��������Ʒ������ť
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
	   //����һ�������jp3��������ü�����صı�ǩ���ı���
	    jp3 = new JPanel(new GridLayout(2,1,20,50));     
	    jp3.setBounds(0, 370, 100, 100);
	    jp3.setOpaque(false);
	    j2=new JLabel(new ImageIcon("image\\car_view\\heji.jpg"));
	    text2 = new JTextField(8);
	    text2.setText(String.valueOf(sumPrice));
	    jp3.add(j2);
	    jp3.add(text2);
	    //�½�һ�����jp4�����롰��ȥ�̳����������㡱��ť
	    jp4 = new JPanel(new GridLayout(1,1,110,32));
	    jp4.setBounds(275, 443, 360, 28);
	    jp4.setOpaque(false);
	    payBtn = new JButton(new ImageIcon("image\\car_view\\jiesuan.jpg"));
	    goshopBtn = new JButton(new ImageIcon("image\\car_view\\shangcheng.jpg"));
	    returnBtn = new JButton(new ImageIcon("image\\car_view\\fanhui.jpg"));
	    jp4.add(payBtn);
	    jp4.add(goshopBtn);
	    jp4.add(returnBtn);
	    //ע�����
	    addBtn.addActionListener(this);	
	    updBtn.addActionListener(this);	
	    delBtn.addActionListener(this);	
	    payBtn.addActionListener(this);
	    goshopBtn.addActionListener(this);
	    returnBtn.addActionListener(this);
	    //���ֲ㲼��
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
    //���¼�����ķ���
	public void actionPerformed(ActionEvent e) 
	{
		//�����Ʒ����
		if(e.getSource()==addBtn)
		{
			//ѡ�����
			int selectedRow = table.getSelectedRow();       //��ȡ��ѡ�����
			if(selectedRow!=-1)
			{   
			    Product product = new Product();
			    product = dao.getProduct(tableModel.getValueAt(selectedRow, 0).toString());
			    if(Integer.parseInt(product.getNum())>=1)
			    {
			    	this.dispose();
			    	//���̳���ȡ��Ʒ
				    int num = Integer.parseInt(product.getNum())-1;
				    product.setNum(String.valueOf(num));
				    dao.updateProduct(product);
				    //���뵽���ﳵ
					ShoppingCar car = new ShoppingCar();
					car.setUser_id((dao.getCar(tableModel.getValueAt(selectedRow, 0).toString())).getUser_id());
	         		car.setGoods_id(tableModel.getValueAt(selectedRow, 0).toString());
			        car.setGoods_name(tableModel.getValueAt(selectedRow, 1).toString());
			        car.setPrice(tableModel.getValueAt(selectedRow, 2).toString());
			        car.setGoods_num(tableModel.getValueAt(selectedRow, 3).toString());
			        car.setOnegoods_sumprice(tableModel.getValueAt(selectedRow, 4).toString());
			        //ֻ������Ʒ����
			        int s = Integer.parseInt(car.getGoods_num())+1;
			        //��ȡ��Ʒ�ܼ�
			        Number sp = Float.parseFloat(car.getPrice())*s;      //��Ʒ�ܼ�
			        Float sum = sp.floatValue();                             //��Ʒ�ܼ�ת��
			        car.setGoods_num(String.valueOf(s));
			        car.setOnegoods_sumprice(String.valueOf(sum));
			        dao.updateCar(car);
			        new car_view2(userId);
			    }
			    else
			    {
			    	JOptionPane.showMessageDialog(null,"����Ʒ��治��");
			    }       
			}
			else
    		{
    			JOptionPane.showMessageDialog(null,"��ѡ����Ҫ����ӵ���Ʒ");
    		}
		}
		//������Ʒ����
		else if(e.getSource()==updBtn)
		{
			//ѡ�����
			int selectedRow = table.getSelectedRow();       //��ȡ��ѡ�����
			if(selectedRow!=-1)
			{
				Product product = new Product();
			    product = dao.getProduct(tableModel.getValueAt(selectedRow, 0).toString());
				//���ù��ﳵ����Ʒ������
				ShoppingCar car = new ShoppingCar();
				car.setUser_id((dao.getCar(tableModel.getValueAt(selectedRow, 0).toString())).getUser_id());
         		car.setGoods_id(tableModel.getValueAt(selectedRow, 0).toString());
		        car.setGoods_name(tableModel.getValueAt(selectedRow, 1).toString());
		        car.setPrice(tableModel.getValueAt(selectedRow, 2).toString());
		        car.setGoods_num(tableModel.getValueAt(selectedRow, 3).toString());
		        car.setOnegoods_sumprice(tableModel.getValueAt(selectedRow, 4).toString());
		        //�ı������õ���Ʒ����
		        int s = Integer.parseInt(text1.getText());  
		        //���õ���Ʒ�����빺�ﳵԭ�������ıȽ�ֵ
		        int addnum = s-Integer.parseInt(tableModel.getValueAt(selectedRow, 3).toString());     
		        if(s>0)
		        {
		        	//���ﳵ������Ʒ���޸ķ�ʽ
			        if(addnum>0)
			        {
				        if(Integer.parseInt(product.getNum())<addnum)
				        {
				        	JOptionPane.showMessageDialog(null,"����Ʒ��治��");
				        }
				        else
				        {
				        	this.dispose();
				        	//���̳���ȡ��Ʒ
						    int num = Integer.parseInt(product.getNum())-addnum;         //���빺�ﳵ���̳ǵ���Ʒ����
						    product.setNum(String.valueOf(num));
						    dao.updateProduct(product);
						    //��ӵ����ﳵ
					        int num1 = Integer.parseInt(car.getGoods_num())+addnum;      //���ﳵ������Ʒ�����Ʒ����
					        Number sp = Float.parseFloat(car.getPrice())*num1;           //��Ʒ���ܼ�
					        Float sum = sp.floatValue();                                 //��Ʒ�ܼ�ת��
					        car.setGoods_num(String.valueOf(s));
					        car.setOnegoods_sumprice(String.valueOf(sum));
					        dao.updateCar(car);
						    new car_view2(userId);
				        }
			        }
			        //���ٹ��ﳵ��Ʒ���޸ķ�ʽ
			        else if(addnum<0)
			        {
			        	this.dispose();
			        	//�̳ǵ���Ʒ��������
			        	int num = Integer.parseInt(product.getNum())-addnum;       //���ﳵ������Ʒ���̳ǵ���Ʒ����     
			        	product.setNum(String.valueOf(num));
					    dao.updateProduct(product);
					    //���ﳵ������Ʒ
					    int num1 = Integer.parseInt(car.getGoods_num())+addnum;      //���ﳵ������Ʒ�����Ʒ����
				        Number sp = Float.parseFloat(car.getPrice())*num1;           //��Ʒ���ܼ�
				        Float sum = sp.floatValue();                                 //��Ʒ�ܼ�ת��
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
		        	int n1 = Integer.parseInt(tableModel.getValueAt(selectedRow, 3).toString());   //���ﳵ��ǰ��Ʒ����
		        	int n2 = Integer.parseInt(product.getNum());                                   //�̳��и���Ʒ������
		        	this.dispose(); 
		        	dao.deleteCar(car);
		        	product.setNum(String.valueOf(n1+n2));
		        	dao.updateProduct(product);
					//�жϹ��ﳵ�Ƿ�Ϊ��
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
    			JOptionPane.showMessageDialog(null,"��ѡ����Ҫ�޸���������Ʒ");
    		}
		}
		//������Ʒ����
		else if(e.getSource()==delBtn)
		{
			//ѡ�����
			int selectedRow = table.getSelectedRow();       //��ȡ��ѡ�����
			if(selectedRow!=-1)
			{   
			    Product product = new Product();
			    product = dao.getProduct(tableModel.getValueAt(selectedRow, 0).toString());
			    this.dispose();
				//���ٹ��ﳵ��Ʒ
			    ShoppingCar car = new ShoppingCar();
				car.setUser_id((dao.getCar(tableModel.getValueAt(selectedRow, 0).toString())).getUser_id());
	         	car.setGoods_id(tableModel.getValueAt(selectedRow, 0).toString());
			    car.setGoods_name(tableModel.getValueAt(selectedRow, 1).toString());
			    car.setPrice(tableModel.getValueAt(selectedRow, 2).toString());
			    car.setGoods_num(tableModel.getValueAt(selectedRow, 3).toString());
			    car.setOnegoods_sumprice(tableModel.getValueAt(selectedRow, 4).toString());
			    //ֻ������Ʒ����
			    int s = Integer.parseInt(car.getGoods_num())-1;
			    if(s>0)
			    {
			    	//��ȡ��Ʒ�ܼ�
				    Number sp = Float.parseFloat(car.getPrice())*s;      //��Ʒ�ܼ�
				    Float sum = sp.floatValue();                         //��Ʒ�ܼ�ת��
				    car.setGoods_num(String.valueOf(s));
				    car.setOnegoods_sumprice(String.valueOf(sum));
				    dao.updateCar(car);
			    }  
			    else
			    {
			    	dao.deleteCar(car);	
			    }
			    //��Ʒ�Ż��̳�
				int num = Integer.parseInt(product.getNum())+1;
				product.setNum(String.valueOf(num));
				dao.updateProduct(product);
				//�жϹ��ﳵ�Ƿ�Ϊ��
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
    			JOptionPane.showMessageDialog(null,"��ѡ����Ҫ������������Ʒ");
    		}
			
		}
		//�����û�ѡ�����
		else if(e.getSource()==returnBtn)
		{
			this.dispose();
			new userOption_view(userId);
		}
		//��ȥ�̳ǹ���
		else if(e.getSource()==goshopBtn)
		{
			this.dispose();
			new Market(userId);
		}
		//����
		else if(e.getSource()==payBtn)
		{
			//
		}		
	}	      
}
