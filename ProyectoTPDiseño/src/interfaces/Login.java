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
<<<<<<< HEAD
import gestores.GestorUsuario;
=======
>>>>>>> Nacho

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
<<<<<<< HEAD
	private JFrame frame=this;
=======
>>>>>>> Nacho
	

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
		setTitle("Iniciar Sesion");
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 400, 600);
		contentPane = new JPanel();
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel textUsers = new JLabel("Usuario: ");
		textUsers.setBounds(31, 240, 160, 34);
		contentPane.add(textUsers);
		
		
		
		JTextPane textPane = new JTextPane();
		textPane.setBounds(191, 281, 150, 23);
		contentPane.add(textPane);
		
		JLabel lblContrasea = new JLabel("Contrase\u00F1a: ");
		lblContrasea.setBounds(31, 343, 188, 48);
		contentPane.add(lblContrasea);
		
		passwordField = new JPasswordField();
		passwordField.setToolTipText("");
		passwordField.setEchoChar('*');
		passwordField.setBounds(195, 409, 150, 20);
		passwordField.setBorder(null);
		contentPane.add(passwordField);
		
		JLabel label = new JLabel("");
		label.setBounds(165, 405, 24, 34);
		contentPane.add(label);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setBounds(133, 51, 138, 149);
		contentPane.add(lblNewLabel);
		
		
		JLabel lblNewLabel_1 = new JLabel("");
		contentPane.add(lblNewLabel_1);
		
		JButton btnIniciarSeccin = new JButton("Iniciar sesi\u00F3n");
		btnIniciarSeccin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
<<<<<<< HEAD
				GestorDB gestorDB = new GestorDB();
=======
				/*GestorDB gestorDB = new GestorDB();
>>>>>>> Nacho
				
				gestorDB.connectDatabase();
				Usuario usr = gestorDB.seleccionarUsuario(textPane.getText());
				gestorDB.cerrarConexion();
				
				
<<<<<<< HEAD
				GestorUsuario gestorUsuario = new GestorUsuario();
				Sesion s= gestorUsuario.setUsuarioLogueado(usr);
				
				MenuPrincipalMesa menu = new MenuPrincipalMesa(s);
				menu.setVisible(true);
				frame.dispose();
=======
				Sesion sesion = new Sesion(usr);*/
				MenuPrincipalMesa menu = new MenuPrincipalMesa();
				menu.setVisible(true);
>>>>>>> Nacho
				
				
				
			}
		});
		btnIniciarSeccin.setBounds(89, 521, 222, 23);
		contentPane.add(btnIniciarSeccin);
	}
}