package Shopping;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;


import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.io.BufferedReader;
import java.io.FileReader;

import javax.swing.JTable;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class BuyProduct extends JFrame {

	private JPanel contentPane;
	private JTextField ProQuantity;
	private JTable table;
	private DefaultTableModel model;
	public DefaultTableModel model1;
	public JTable table_1;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					BuyProduct frame = new BuyProduct();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public BuyProduct() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 786, 496);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		ProQuantity = new JTextField();
		ProQuantity.setBounds(101, 239, 86, 20);
		contentPane.add(ProQuantity);
		ProQuantity.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("Quantity");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNewLabel.setBounds(28, 238, 86, 19);
		contentPane.add(lblNewLabel);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(28, 332, 379, 102);
		contentPane.add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		model=new DefaultTableModel();
		Object[] column= {"ID","Name","Price"};
		Object[] row=new Object[3];
		model.setColumnIdentifiers(column);
		table.setModel(model);
		scrollPane.setViewportView(table);
		
		try {
			FileReader fr = new FileReader("Product.txt");
	        BufferedReader reader = new BufferedReader(fr);
	        
	        String line;
	        while( (line = reader.readLine()) != null) {
	            String [] parts = line.split(" ");
	            int id=Integer.parseInt(parts[0]);
	            String name=parts[1];
	            int price=Integer.parseInt(parts[2]);
	            
	            row[0]=id;
				row[1]=name;
				row[2]=price;
				model.addRow(row);
	        }
	        reader.close();
		}
		catch(Exception e) {
			System.out.println("File doesn't Exist");
		}
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(427, 85, 321, 135);
		contentPane.add(scrollPane_1);
		
		table_1 = new JTable();
		scrollPane_1.setViewportView(table_1);
		model1=new DefaultTableModel();
		Object[] column1= {"ID","Name","Price","Quantity"};
		Object[] row1=new Object[4];
		model1.setColumnIdentifiers(column1);
		table_1.setModel(model1);
		scrollPane_1.setViewportView(table_1);
		
		JButton btnNewButton = new JButton("Add");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int i=table.getSelectedRow();
				if(ProQuantity.getText().equals("") || !(i>=0)) {
					JOptionPane.showMessageDialog(null, "Input your information properly");
				}
				else {
					row1[0]=table.getValueAt(i, 0);
					row1[1]=table.getValueAt(i, 1);
					row1[2]=table.getValueAt(i, 2);
					row1[3]=ProQuantity.getText();
					model1.addRow(row1);
					JOptionPane.showMessageDialog(null, "Product Added to your cart successfully");
				}
			}
		});
		btnNewButton.setBounds(427, 258, 120, 23);
		contentPane.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Remove");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int i=table_1.getSelectedRow();
				if(i>=0) {
					model1.removeRow(i);
					JOptionPane.showMessageDialog(null, "Product Removed Successfully");
				}
				else {
					JOptionPane.showMessageDialog(null, "Select the Product to remove");
				}
				
			}
		});
		btnNewButton_1.setBounds(628, 258, 120, 23);
		contentPane.add(btnNewButton_1);
		
		JLabel lblNewLabel_1 = new JLabel("Buy Product");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 25));
		lblNewLabel_1.setBounds(304, 22, 143, 31);
		contentPane.add(lblNewLabel_1);
		
		JButton btnNewButton_2 = new JButton("Clear");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ProQuantity.setText("");
			}
		});
		btnNewButton_2.setBounds(200, 238, 77, 23);
		contentPane.add(btnNewButton_2);
		
		JLabel lblNewLabel_2 = new JLabel("Cart");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblNewLabel_2.setBounds(574, 61, 94, 14);
		contentPane.add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("Select Your Product And input the Quantity");
		lblNewLabel_3.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblNewLabel_3.setBounds(28, 115, 332, 36);
		contentPane.add(lblNewLabel_3);
		
		JButton btnNewButton_3 = new JButton("Purchase");
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int cnt=table_1.getRowCount();
				int total_price=0;
				int total_quantity=0;
				for(int j=0;j<cnt;j++) {
					int price;
					int quantity;
					price=Integer.parseInt(table_1.getValueAt(j, 2).toString());
					quantity=Integer.parseInt(table_1.getValueAt(j, 3).toString());
					total_quantity+=quantity;
					total_price+=(price*quantity);
				}
				String p="Thanks for your purchasing"+"\n\n"+"You Have Brought "+String.valueOf(total_quantity)+" products"+"\n\n"+"Total Price : BDT "+String.valueOf(total_price)+" Taka";
				JOptionPane.showInternalMessageDialog(null, p, "Purchase Detail", JOptionPane.INFORMATION_MESSAGE);
			}
		});
		btnNewButton_3.setFont(new Font("Tahoma", Font.PLAIN, 17));
		btnNewButton_3.setBounds(524, 326, 129, 58);
		contentPane.add(btnNewButton_3);
		
		JLabel lblNewLabel_4 = new JLabel("Product List");
		lblNewLabel_4.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNewLabel_4.setBounds(178, 307, 99, 14);
		contentPane.add(lblNewLabel_4);
		
		JLabel lblNewLabel_5 = new JLabel("Login");
		lblNewLabel_5.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				dispose();
				Login frame=new Login();
				frame.setVisible(true);
			}
		});
		lblNewLabel_5.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblNewLabel_5.setBounds(74, 22, 68, 31);
		contentPane.add(lblNewLabel_5);
	}
}
