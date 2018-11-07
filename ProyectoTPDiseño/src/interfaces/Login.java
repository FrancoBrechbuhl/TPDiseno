package interfaces;

import interfaces.image.*;
import interfaces.MenuPrincipalMesa;
import logica.*;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Color;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextPane;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;

import gestores.GestorDB;

import javax.swing.SwingConstants;
import javax.swing.JSeparator;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.List;
import java.awt.event.ActionEvent;
import java.awt.SystemColor;



public class Login extends JFrame {
	private JPanel contentPane;
	private JPasswordField passwordField;
	

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
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 400, 600);
		this.setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		JLabel textUsers = new JLabel("Usuario: ");
		textUsers.setForeground(new Color(249, 79, 52));
		textUsers.setFont(new Font("Yu Gothic UI Light", Font.BOLD, 30));
		textUsers.setBounds(31, 240, 160, 34);
		contentPane.add(textUsers);
		
		
		
		JTextPane textPane = new JTextPane();
		textPane.setFont(new Font("Tahoma", Font.PLAIN, 14));
		textPane.setBounds(191, 281, 150, 23);
		contentPane.add(textPane);
		
		JLabel lblContrasea = new JLabel("Contrase\u00F1a: ");
		lblContrasea.setForeground(new Color(249, 79, 52));
		lblContrasea.setFont(new Font("Yu Gothic UI Light", Font.BOLD, 30));
		lblContrasea.setBounds(31, 343, 188, 48);
		contentPane.add(lblContrasea);
		passwordField = new JPasswordField();
		passwordField.setFont(new Font("Tahoma", Font.PLAIN, 14));
		passwordField.setToolTipText("");
		passwordField.setEchoChar('*');
		passwordField.setBounds(195, 409, 150, 20);
		passwordField.setBorder(null);
		contentPane.add(passwordField);
		
		JLabel label = new JLabel("");
		label.setVerticalAlignment(SwingConstants.TOP);
		//label.setIcon(new ImageIcon(Login.class.getResource("/image/llave-inclinada.png")));
		label.setBounds(165, 405, 24, 34);
		contentPane.add(label);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		//lblNewLabel.setIcon(new ImageIcon(Login.class.getResource("/image/grupo.png")));
		lblNewLabel.setBounds(133, 51, 138, 149);
		contentPane.add(lblNewLabel);
		
		JSeparator separator = new JSeparator();
		separator.setToolTipText("");
		separator.setBackground(Color.RED);
		separator.setForeground(Color.RED);
		separator.setBounds(192, 304, 150, 2);
		contentPane.add(separator);
		
		JSeparator separator_1 = new JSeparator();
		separator_1.setToolTipText("");
		separator_1.setForeground(Color.RED);
		separator_1.setBackground(Color.RED);
		separator_1.setBounds(188, 432, 153, 2);
		contentPane.add(separator_1);
		
		JLabel lblNewLabel_1 = new JLabel("");
		//lblNewLabel_1.setIcon(new ImageIcon(Login.class.getResource("/image/Usuario_peq.png")));
		lblNewLabel_1.setBounds(157, 260, 32, 48);
		contentPane.add(lblNewLabel_1);
		
		JButton btnIniciarSeccin = new JButton("Iniciar sesi\u00F3n");
		btnIniciarSeccin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				/*GestorDB gestorDB = new GestorDB();
				
				gestorDB.connectDatabase();
				Usuario usr = gestorDB.seleccionarUsuario(textPane.getText());
				gestorDB.cerrarConexion();
				
				
				Sesion sesion = new Sesion(usr);*/
				MenuPrincipalMesa menu = new MenuPrincipalMesa();
				menu.setVisible(true);
				
				
				
			}
		});
		btnIniciarSeccin.setBackground(new Color(249, 79, 52));
		btnIniciarSeccin.setForeground(Color.white);
		btnIniciarSeccin.setBounds(89, 521, 222, 23);
		contentPane.add(btnIniciarSeccin);
	}
}
