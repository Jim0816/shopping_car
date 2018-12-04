package com.ljm.view;
/*
*@���ߣ������
*@���ܣ�����Ա����Ʒ�Ĺ������
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
	//�������
    DefaultTableModel tableModel;
	private JTable table;
	public JScrollPane scrollPane;
	JLayeredPane layeredPane;
	private JPanel jp2,jp3;
	private JTextField text1,text2,text3,text4,text5;
	private JLabel jl_1;
	public JButton addBtn,updBtn,delBtn;
	ImageIcon image;
	//������������
	String[] columnNames = {"��Ʒ���","��Ʒ����","��Ʒ�۸�","��Ʒ���","��Ʒ����"};
	String [] column = new String[]{"��Ʒ���","��Ʒ����","��Ʒ�۸�","��Ʒ���","��Ʒ����"};
	Object[][] tableData = null;              
	Dao dao = new DaoImplement();
	//���췽��
	public Manager_view()
	{   
	    view_4();
	}
	//������Ʒ����
	public void view_4()
	{   layeredPane = new JLayeredPane();                 //��ʼ���ֲ����
		image = new ImageIcon("image\\Manager\\xuhuabeijing.jpg");     //��ȡ���屳��ͼƬ
		//��ʼ������
		try {
			UIManager.setLookAndFeel(new WindowsLookAndFeel());       //��JFrame��LookAndFeel�趨ΪWindows
		} catch (UnsupportedLookAndFeelException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		setTitle("������Ʒ");
		setSize(image.getIconWidth(),image.getIconHeight());
		this.setLayout(null);
		this.setResizable(false);
		this.setLocationRelativeTo(null);
		//��ʼ��������ǩ
		jl_1=new JLabel(image);
		jl_1.setBounds(0, 0,image.getIconWidth(),image.getIconHeight());
		//���ģ�ͻ�ȡ����
	    tableData = dao.getProductInfo();                              //��ȡ���ݿ�����Ʒ�����Ϣ
		tableModel = new DefaultTableModel (tableData,columnNames);
		//��ʼ�����
		table = new JTable(tableModel);                                //���ģ��ʵ����
		RowSorter sorter = new TableRowSorter(tableModel);
		table.setRowSorter(sorter);                                    //���ñ��������
		//table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);   //����Ϊ��ѡģʽ
		table.setForeground(Color.BLACK);                             //���ñ��ǰ��ɫ
		table.setSelectionForeground(Color.RED);                       //���ñ�ѡ���е�ǰ��ɫ
	    table.setRowHeight(30);                                        //���ñ���и�
	    //�ѱ����ӵ��������
	    scrollPane = new JScrollPane(table);
	    scrollPane.setBounds(100,50,700,300);
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
	    //����һ������壬�ű�ǩ   �ı��� 
	    jp2 = new JPanel();     
	    jp2.setBounds(100, 365, 700, 30);
	    //���������ӱ�ǩ   �ı��� 
	    jp2.add(new JLabel("��Ʒ���"));
	    text1 = new JTextField(5);
	    jp2.add(text1);
	    jp2.add(new JLabel("��Ʒ����"));
	    text2 = new JTextField(5);
	    jp2.add(text2);
	    jp2.add(new JLabel("��Ʒ�۸�"));
	    text3 = new JTextField(5);
	    jp2.add(text3);
	    jp2.add(new JLabel("��Ʒ���"));
	    text4 = new JTextField(5);
	    jp2.add(text4);
	    jp2.add(new JLabel("��Ʒ����"));
	    text5 = new JTextField(5);
	    jp2.add(text5);
	    jp2.setOpaque(false);
	    //����һ�������,�Ű�ť
	    jp3 = new JPanel(new GridLayout(1,1,70,22));     
	    jp3.setBounds(275, 420, 350, 30);
	    jp3.setOpaque(false);
	    //��ʼ������Ӱ�ť��panel��
	    addBtn = new JButton(new ImageIcon("image\\Manager\\tj.jpg"));
	    addBtn.setBounds(280, 420, 75, 33);
	    updBtn = new JButton(new ImageIcon("image\\Manager\\xg.jpg"));
	    delBtn = new JButton(new ImageIcon("image\\Manager\\sc.jpg"));
	    jp3.add(addBtn);
	    jp3.add(updBtn);
	    jp3.add(delBtn);
	    //ע�����
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
    //���¼�����ķ���
	public void actionPerformed(ActionEvent e) 
	{
		//�����Ʒ
		if(e.getSource()==addBtn)
		{
			//ѡ�����
			int selectedRow = table.getSelectedRow();       //��ȡ��ѡ�����
			if(selectedRow!=-1)
			{   
				this.dispose();
				Product product = new Product();
         		product.setGoods_id(tableModel.getValueAt(selectedRow, 0).toString());
		        product.setGoods_name(tableModel.getValueAt(selectedRow, 1).toString());
		        product.setPrice(tableModel.getValueAt(selectedRow, 2).toString());
		        product.setIntroduce(tableModel.getValueAt(selectedRow, 3).toString());
		        product.setNum(tableModel.getValueAt(selectedRow, 4).toString());
		        //ֻ������Ʒ����
		        int s = Integer.parseInt(product.getNum())+1;    
		        product.setNum(String.valueOf(s));
		        dao.updateProduct(product);
		        new Manager_view();   
			}
		    else
		    {  
		    	//��Ӳ����ڵ���Ʒ
		        if(text1.getText().equals("")||text2.getText().equals("")||text3.getText().equals("")||text4.getText().equals(""))
		    	{
		        	JOptionPane.showMessageDialog(null, "����������Ʒ��������Ϣ��","����Ʒ���������ʾ",JOptionPane.WARNING_MESSAGE);
		    		text1.requestFocus();               //�����뽹������ı���text1
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
		//�޸���Ʒ
		else if(e.getSource()==updBtn)
		{
			int selectedRow = table.getSelectedRow();       //��ȡ��ѡ�����
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
    			JOptionPane.showMessageDialog(null,"����ѡ����Ҫ���޸ĵ���");
    			//new Manager_view();
    		}
		}
		//ɾ����Ʒ
		else if(e.getSource()==delBtn)
		{
			int selectedRow = table.getSelectedRow();       //��ȡ��ѡ�����
			//ɾ����ѡ����Ʒ��һ��
			if(selectedRow!=-1)
			{
				//ͬ��ɾ�����ݿ��Ӧ����Ϣ
				Product product = new Product();
   		        product.setGoods_id(tableModel.getValueAt(selectedRow, 0).toString());
		        product.setGoods_name(tableModel.getValueAt(selectedRow, 1).toString());
		        product.setPrice(tableModel.getValueAt(selectedRow, 2).toString());
		        product.setIntroduce(tableModel.getValueAt(selectedRow, 3).toString());
		        product.setNum(tableModel.getValueAt(selectedRow, 4).toString());
		        //��Ʒ����Ϊ0ʱ
		        if(Integer.parseInt(product.getNum())==0)
		        {
		        	String options[] = {"��","��"};
   		        	int value = JOptionPane.showOptionDialog(null, "�Ƿ񽫸���Ʒ�¼ܣ�", "��Ʒ�¼ܲ���", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, null);
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
   		        //��Ʒ����Ϊ1ʱ
		        else if(Integer.parseInt(product.getNum())==1)
   		        {
		        	this.dispose();
   		        	int s = Integer.parseInt(product.getNum())-1;    
   		   		    product.setNum(String.valueOf(s));
   		   		    dao.updateProduct(product);
   		   		    new Manager_view();
   		        }
   		        //��Ʒ��������1ʱ	
   		        else if(Integer.parseInt(product.getNum())>1)
   		        {
   		        	//ֻ������Ʒ����
   		        	int s = Integer.parseInt(product.getNum())-1;    
   		        	product.setNum(String.valueOf(s));
   		        	dao.updateProduct(product);
   		        	new Manager_view();
   		        }	
			}
			else
			{
				JOptionPane.showMessageDialog(null, "��ѡ����Ҫ��ɾ������Ʒ","��Ʒɾ����ʾ",JOptionPane.WARNING_MESSAGE);
			}
		}		
	}	
}

	   

	  
	    
