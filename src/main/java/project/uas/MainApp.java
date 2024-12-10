package project.uas;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import java.awt.Font;
import javax.swing.JButton;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.util.List;
import java.awt.event.ActionEvent;

public class MainApp extends JFrame {

	private JPanel contentPane;
	private JTextField textUsername;
	private JTextField textName;
	private JPasswordField textPassword;
	private JPasswordField textRePassword;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainApp frame = new MainApp();
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
	public MainApp() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 600, 520);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblUsername = new JLabel("Username");
		lblUsername.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblUsername.setHorizontalAlignment(SwingConstants.CENTER);
		lblUsername.setBounds(10, 88, 114, 37);
		contentPane.add(lblUsername);
		
		JLabel lblName = new JLabel("Name");
		lblName.setHorizontalAlignment(SwingConstants.CENTER);
		lblName.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblName.setBounds(10, 161, 114, 37);
		contentPane.add(lblName);
		
		JLabel lblRePassword = new JLabel("Re-Password");
		lblRePassword.setHorizontalAlignment(SwingConstants.CENTER);
		lblRePassword.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblRePassword.setBounds(10, 314, 114, 37);
		contentPane.add(lblRePassword);
		
		textUsername = new JTextField();
		textUsername.setBounds(180, 90, 260, 37);
		contentPane.add(textUsername);
		textUsername.setColumns(10);
		
		textName = new JTextField();
		textName.setColumns(10);
		textName.setBounds(180, 163, 260, 37);
		contentPane.add(textName);
		
		textPassword = new JPasswordField();
		textPassword.setBounds(180, 239, 260, 37);
		contentPane.add(textPassword);
				
		JLabel lblRegis = new JLabel("Registration");
		lblRegis.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblRegis.setHorizontalAlignment(SwingConstants.CENTER);
		lblRegis.setBounds(206, 10, 176, 37);
		contentPane.add(lblRegis);
		
		JLabel lblPassword = new JLabel("Password");
		lblPassword.setHorizontalAlignment(SwingConstants.CENTER);
		lblPassword.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblPassword.setBounds(10, 237, 114, 37);
		contentPane.add(lblPassword);
		
		textRePassword = new JPasswordField();
		textRePassword.setBounds(180, 316, 260, 37);
		contentPane.add(textRePassword);
		
		JButton btnSubmit = new JButton("Submit");
		btnSubmit.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		        try {
		            String username = textUsername.getText();
		            String name = textName.getText();
		            String password = new String(textPassword.getPassword());
		            String rePassword = new String(textRePassword.getPassword());

		            // Validasi apakah password dan rePassword cocok
		            if (!password.equals(rePassword)) {
		                JOptionPane.showMessageDialog(null, "Password tidak cocok!", "Error", JOptionPane.ERROR_MESSAGE);
		                return;
		            }

		            // Enkripsi password
		            String hashedPassword = HashUtil.hashSHA256(password);

		            // Simpan ke database
		            Users user = new Users(username, name, hashedPassword);
		            UserDao userDao = new UserDao();
		            userDao.saved(user);
		            
		            System.out.println("Username: " + username);
		            System.out.println("Name: " + name);
		            System.out.println("Encrypted Password: " + hashedPassword);

		            JOptionPane.showMessageDialog(null, "Data berhasil disimpan!", "Success", JOptionPane.INFORMATION_MESSAGE);
		            dispose();

		        } catch (Exception ex) {
		            ex.printStackTrace();
		            JOptionPane.showMessageDialog(null, "Terjadi kesalahan: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
		        }
		    }
		});

		btnSubmit.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnSubmit.setBounds(231, 388, 151, 37);
		contentPane.add(btnSubmit);
		
	}
	
	private void loadUserData() {
        try {
            UserDao userDao = new UserDao();
            List<Users> users = userDao.findAll();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
