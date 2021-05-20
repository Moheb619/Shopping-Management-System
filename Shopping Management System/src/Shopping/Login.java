package Shopping;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JOptionPane;

public class Login extends JFrame {

	private JPanel contentPane;
	private JTextField ShopPassword;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Login frame = new Login();
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
	public Login() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 800, 486);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Login");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 25));
		lblNewLabel.setBounds(330, 99, 79, 50);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("User");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblNewLabel_1.setBounds(262, 184, 46, 34);
		contentPane.add(lblNewLabel_1);
		
		JComboBox ShopUser = new JComboBox();
		
		ShopUser.setModel(new DefaultComboBoxModel(new String[] {"Admin", "Buyer"}));
		ShopUser.setBounds(340, 193, 112, 22);
		contentPane.add(ShopUser);
		
		JLabel ShopPasswordLabel = new JLabel("Password");
		ShopPasswordLabel.setFont(new Font("Tahoma", Font.PLAIN, 18));
		ShopPasswordLabel.setBounds(242, 255, 88, 34);
		contentPane.add(ShopPasswordLabel);
		
		ShopPassword = new JTextField();
		ShopPassword.setBounds(340, 265, 112, 20);
		contentPane.add(ShopPassword);
		ShopPassword.setColumns(10);
		
		ShopUser.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(ShopUser.getSelectedItem().toString().equals("Buyer")) {
					ShopPassword.setVisible(false);
					ShopPasswordLabel.setVisible(false);
				}
				else {
					ShopPassword.setVisible(true);
					ShopPasswordLabel.setVisible(true);
				}
			}
		});
		
		JLabel lblNewLabel_2 = new JLabel("Shopping Management");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 30));
		lblNewLabel_2.setBounds(240, 23, 365, 43);
		contentPane.add(lblNewLabel_2);
		
		JButton btnNewButton = new JButton("Login");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(ShopUser.getSelectedItem().toString().equals("Admin")) {
					if(ShopPassword.getText().equals("1234")) {
						dispose();
						Products p=new Products();
						p.setVisible(true);
					}
					else {
						JOptionPane.showInternalMessageDialog(null, "You Entered Wrong Password", "Wrong Password", JOptionPane.WARNING_MESSAGE);
					}
				}
				else {
					dispose();
					BuyProduct b=new BuyProduct();
					b.setVisible(true);
				}
			}
		});
		btnNewButton.setBounds(496, 264, 89, 23);
		contentPane.add(btnNewButton);
	}
}
