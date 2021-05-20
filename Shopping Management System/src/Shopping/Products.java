package Shopping;

import java.io.BufferedReader;
import java.io.FileReader;
import java.awt.EventQueue;
import java.io.PrintWriter;
import java.io.File;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import javax.swing.JScrollPane;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Products extends JFrame {

	private JPanel contentPane;
	private JTextField ProID;
	private JTextField ProName;
	private JTextField ProPrice;
	private DefaultTableModel model;
	private JTable table;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Products frame = new Products();
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
	public Products() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 619, 505);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("  Manage Products");
		lblNewLabel.setBounds(192, 31, 205, 35);
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 24));
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Product ID");
		lblNewLabel_1.setBounds(66, 105, 79, 14);
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 15));
		contentPane.add(lblNewLabel_1);
		
		ProID = new JTextField();
		ProID.setBounds(163, 102, 86, 20);
		contentPane.add(ProID);
		ProID.setColumns(10);
		
		ProName = new JTextField();
		ProName.setBounds(429, 104, 86, 20);
		ProName.setColumns(10);
		contentPane.add(ProName);
		
		ProPrice = new JTextField();
		ProPrice.setBounds(163, 165, 86, 20);
		ProPrice.setColumns(10);
		contentPane.add(ProPrice);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(33, 345, 529, 102);
		contentPane.add(scrollPane);
		
		table = new JTable();
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int i=table.getSelectedRow();
				ProID.setText(model.getValueAt(i,0).toString());
				ProName.setText(model.getValueAt(i,1).toString());
				ProPrice.setText(model.getValueAt(i,2).toString());
			}
		});
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
		
		JLabel lblNewLabel_1_1 = new JLabel("Name");
		lblNewLabel_1_1.setBounds(340, 105, 79, 14);
		lblNewLabel_1_1.setFont(new Font("Tahoma", Font.PLAIN, 15));
		contentPane.add(lblNewLabel_1_1);
		
		JLabel lblNewLabel_1_2 = new JLabel("Price");
		lblNewLabel_1_2.setBounds(66, 166, 79, 14);
		lblNewLabel_1_2.setFont(new Font("Tahoma", Font.PLAIN, 15));
		contentPane.add(lblNewLabel_1_2);
		
		
		
		JButton btnNewButton = new JButton("Add");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(ProID.getText().equals("") || ProName.getText().equals("") || ProPrice.getText().equals("")) {
					JOptionPane.showMessageDialog(null,"Please Complete the information");
				}
				else {
					row[0]=ProID.getText();
					row[1]=ProName.getText();
					row[2]=ProPrice.getText();
					model.addRow(row);
					try {
						File f=new File("Product.txt");
				         PrintWriter out = new PrintWriter(f);
						for(int j=0;j<table.getRowCount();j++) {
								out.println(table.getValueAt(j,0)+" "+table.getValueAt(j,1)+" "+table.getValueAt(j,2));
						}
						out.close();
					}
					catch(Exception ex) {
						System.out.println("Not writeable");
					}
					ProID.setText("");
					ProName.setText("");
					ProPrice.setText("");
					JOptionPane.showMessageDialog(null,"Product Added Successfully");
				}
			}
		});
		btnNewButton.setBounds(66, 244, 89, 23);
		contentPane.add(btnNewButton);
		
		JButton btnEdit = new JButton("Update");
		btnEdit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int i=table.getSelectedRow();
				if(i>=0) {
					model.setValueAt(ProID.getText(),i,0);
					model.setValueAt(ProName.getText(),i,1);
					model.setValueAt(ProPrice.getText(),i,2);
					
					try {
						File f=new File("Product.txt");
				         PrintWriter out = new PrintWriter(f);
						for(int j=0;j<table.getRowCount();j++) {
								out.println(table.getValueAt(j,0)+" "+table.getValueAt(j,1)+" "+table.getValueAt(j,2));
						}
						out.close();
					}
					catch(Exception ex) {
						System.out.println("Not writeable");
					}
					JOptionPane.showMessageDialog(null,"Udpated successfully");
				}
				else
					JOptionPane.showMessageDialog(null,"Select row to update");
			}
		});
		btnEdit.setBounds(192, 244, 89, 23);
		contentPane.add(btnEdit);
		
		JButton btnDelete = new JButton("Delete");
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int i=table.getSelectedRow();
				if(i>=0) {
					try {
						File f=new File("Product.txt");
				         PrintWriter out = new PrintWriter(f);
						for(int j=0;j<table.getRowCount();j++) {
							if(j!=i) {
								out.println(table.getValueAt(j,0)+" "+table.getValueAt(j,1)+" "+table.getValueAt(j,2));
							}
						}
						out.close();
					}
					catch(Exception ex) {
						System.out.println("Not writeable");
					}
					model.removeRow(i);
					JOptionPane.showMessageDialog(null,"Deleted Successfully");
				}
				else {
					JOptionPane.showMessageDialog(null,"Select a row first");
				}
			}
		});
		btnDelete.setBounds(333, 244, 89, 23);
		contentPane.add(btnDelete);
		
		JButton btnClear = new JButton("Clear");
		btnClear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ProID.setText("");
				ProName.setText("");
				ProPrice.setText("");
			}
		});
		btnClear.setBounds(446, 244, 89, 23);
		contentPane.add(btnClear);
		
		JLabel lblProduct = new JLabel(" Product List");
		lblProduct.setBounds(231, 299, 131, 35);
		lblProduct.setFont(new Font("Tahoma", Font.PLAIN, 21));
		contentPane.add(lblProduct);
		
		JLabel lblNewLabel_2 = new JLabel("Login");
		lblNewLabel_2.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				dispose();
				Login frame=new Login();
				frame.setVisible(true);
			}
		});
		lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblNewLabel_2.setBounds(66, 34, 63, 35);
		contentPane.add(lblNewLabel_2);
		
		
	}
}
