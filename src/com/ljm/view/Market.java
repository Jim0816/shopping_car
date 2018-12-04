package com.ljm.view;
/*
 * @���ߣ������
 * @���ܣ��̳ǽ���
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
	//�������
	private JLabel jl1,jl2;
	JLayeredPane layeredPane;
	private JTable table;
	public JScrollPane scrollPane;
	DefaultTableModel tableModel;
	public JButton Btn1,Btn2,Btn3;
	private JPanel jp1;
	//������������
	String[] columnNames = {"��Ʒ���","��Ʒ����","��Ʒ�۸�","��Ʒ���","��Ʒ���"};
	String [] column = new String[]{"��Ʒ���","��Ʒ����","��Ʒ�۸�","��Ʒ���","��Ʒ���"};
	Object[][] tableData = null;              
	Dao dao = new DaoImplement();
	private String userId;
	public Market(String userId) 
	{
		this.userId = userId;
		layeredPane = new JLayeredPane();                 //��ʼ���ֲ����
		//��ʼ������
		setTitle("�̳�");
		setSize(900,640);
		this.setLayout(null);
		this.setResizable(false);
		this.setLocationRelativeTo(null);
	    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    this.setLayeredPane(layeredPane);
		//�������ĵ�һ�㱳��
		jl1=new JLabel(new ImageIcon("image\\Market\\pobu.jpg"));
		jl1.setBounds(0,90,900,520);
		jl2=new JLabel(new ImageIcon("image\\Market\\xuezhishangcheng.jpg"));
		jl2.setBounds(0,0,900,108);
		//���ģ�ͻ�ȡ����
	    tableData = dao.getProductInfo();                              //��ȡ���ݿ�����Ʒ�����Ϣ
		tableModel = new DefaultTableModel (tableData,columnNames);
		//��ʼ�����
		table = new JTable(tableModel);                                //���ģ��ʵ����
		table.setRowSorter(new TableRowSorter(tableModel));            //���ñ��������
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);   //����Ϊ��ѡģʽ
		table.setForeground(Color.BLUE);                             //���ñ��ǰ��ɫ
		table.setSelectionForeground(Color.BLACK);                    //���ñ�ѡ���е�ǰ��ɫ
	    table.setRowHeight(30);                                        //���ñ���и�
	    //�ѱ����ӵ��������
	    scrollPane = new JScrollPane(table);
	    scrollPane.setBounds(120,200,700,300);
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
	    //����һ�������,�Ű�ť
	    jp1 = new JPanel(new GridLayout(1,1,160,22));     
	    jp1.setBounds(200, 555, 500, 53);
	    jp1.setOpaque(false);
	    //��ʼ������Ӱ�ť��panel��
	    Btn1 = new JButton(new ImageIcon("image\\Market\\shouhuo.jpg"));
	    Btn2 = new JButton(new ImageIcon("image\\Market\\jrgwc.jpg"));
	    Btn3 = new JButton(new ImageIcon("image\\Market\\gouwuche.jpg"));
	    jp1.add(Btn1);
	    jp1.add(Btn2);
	    jp1.add(Btn3);
	    //������ť
	    Btn1.addActionListener(this);
	    Btn2.addActionListener(this);
	    Btn3.addActionListener(this);
	    //���뵽��ͬ��development���
	    layeredPane.add(jl1,JLayeredPane.DEFAULT_LAYER);
	    layeredPane.add(jl2,JLayeredPane.DEFAULT_LAYER);
	    layeredPane.add(scrollPane,JLayeredPane.MODAL_LAYER);
	    layeredPane.add(jp1,JLayeredPane.MODAL_LAYER);
	    this.setLayeredPane(layeredPane);
	    this.setVisible(true);
	}
    //��ť�¼�����
	public void actionPerformed(ActionEvent e) 
	{   //������Ʒ
		if(e.getSource()==Btn1)
		{
			this.dispose();
			new FindGoods(userId);
		}
		//��ѡ����Ʒ���빺�ﳵ
		else if(e.getSource()==Btn2)
		{
			//ѡ�����
			int selectedRow = table.getSelectedRow();       //��ȡ��ѡ�����
			if(selectedRow!=-1)
			{
			    this.dispose();
				new NumOption(userId,tableModel.getValueAt(selectedRow, 1).toString());
			}
			else
    		{
    			JOptionPane.showMessageDialog(null,"��ѡ����Ҫ��ӵ���Ʒ");
    		}
		}
		//ǰ�����ﳵ
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
