package interfaces;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JComboBox;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;


import java.time.format.DateTimeFormatter;
import java.time.LocalDateTime;

import java.util.ArrayList;
import java.util.List;

import logica.Clasificacion;
import logica.GrupoResolucion;
import logica.util.DTOTicket;
import interfaces.RegistroTicketB;
import javax.swing.DropMode;
import javax.swing.SwingConstants;


public class Menu extends JPanel {
	private JFrame frame;
<<<<<<< HEAD
	
	public Menu(JFrame f) {
		this.frame=f;
		setLayout(null);	
		
	frame = f;
	setLayout(null);
=======
	private CardLayout cardLayout;
	
	public Menu(JFrame f) {
	
	cardLayout = new CardLayout();	
		
>>>>>>> Nacho
	JButton btnNewButton = new JButton("Registrar Ticket");
	btnNewButton.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent arg0) {
			((MenuPrincipalMesa)frame).cambiarVentanaMenu(1);
		}
	});
	btnNewButton.setBounds(130, 48, 149, 23);
	this.add(btnNewButton);
	
	JButton btnNewButton_1 = new JButton("Consultar Ticket");
	btnNewButton_1.setBounds(130, 95, 147, 23);
	this.add(btnNewButton_1);
	
	JButton btnNewButton_2 = new JButton("Cerrar Ticket");
	btnNewButton_2.setBounds(130, 143, 147, 23);
	this.add(btnNewButton_2);
	
	JButton btnNewButton_3 = new JButton("Derivar Ticket");
	btnNewButton_3.setBounds(130, 189, 147, 23);
	this.add(btnNewButton_3);
}
}