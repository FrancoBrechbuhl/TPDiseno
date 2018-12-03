package interfaces;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.DropMode;
import javax.swing.SwingConstants;
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
import java.lang.Exception;

import logica.*;
import gestores.*;
import logica.util.*;
import interfaces.*;

public class RegistroTicketA extends JPanel {
	private JFrame frame;
	private JTextField nroLegajo;
	private JTextField descripcion;
	

	/**
	 * Create the frame.
	 */
	public RegistroTicketA(JFrame f) {
		this.frame=f;

		ArrayList<String> clasificaciones = new ArrayList<String>();
		
		setLayout(null);

		JLabel lblNmeroDeLegajo = new JLabel("N\u00FAmero de legajo");
		lblNmeroDeLegajo.setBounds(17, 14, 102, 14);
		this.add(lblNmeroDeLegajo);

		nroLegajo = new JTextField();
		nroLegajo.setBounds(129, 11, 86, 20);
		this.add(nroLegajo);
		nroLegajo.setColumns(10);

		JLabel lblDescripcin = new JLabel("Descripci\u00F3n");
		lblDescripcin.setBounds(17, 39, 54, 14);
		this.add(lblDescripcin);

		descripcion = new JTextField();
		descripcion.setBounds(14, 64, 277, 96);
		this.add(descripcion);
		descripcion.setColumns(10);

		JLabel lblClasificacin = new JLabel("Clasificaci\u00F3n");
		lblClasificacin.setBounds(14, 172, 71, 14);
		this.add(lblClasificacin);

		JComboBox comboBoxClasificaciones = new JComboBox<String>();
		for(int i=0;i<clasificaciones.size();i++) {
			comboBoxClasificaciones.addItem(clasificaciones.get(i));
		}

		comboBoxClasificaciones.setBounds(95, 169, 196, 20);

		this.add(comboBoxClasificaciones);


		DateTimeFormatter dtf= DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
		LocalDateTime now = LocalDateTime.now();
		String dateTime= dtf.format(now);

		JLabel lblNewLabel = new JLabel("Fecha y hora de apertura: "+ dateTime);
		lblNewLabel.setBounds(14, 197, 325, 14);
		this.add(lblNewLabel);

		JButton btnNewButton = new JButton("Aceptar");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if( nroLegajo.getText().isEmpty() || descripcion.getText().isEmpty() ) {
					JOptionPane.showMessageDialog(frame,
						    "Los campos no pueden ser nulos",
						    "Error",
						    JOptionPane.ERROR_MESSAGE);
				}
				else {
					GestorSistemaPersonal gestorPersonal = new GestorSistemaPersonal();
					boolean e = gestorPersonal.consultaEmpleado(nroLegajo.getText());

					if(!e) {
					JOptionPane.showMessageDialog(frame,
						    "Ingrese un n�mero de legajo v�lido",
						    "Error",
						    JOptionPane.ERROR_MESSAGE);
					}
					else {
					try {
						GestorTicket gestorTicket = new GestorTicket();
						DTOTicket dtoTicket= new DTOTicket(nroLegajo.getText(), descripcion.getText(),comboBoxClasificaciones.getSelectedItem().toString(),now);
						gestorTicket.registrarTicket(dtoTicket, ((MenuPrincipalMesa)frame).getSesion());
						JOptionPane.showMessageDialog(frame, "Ticket guardado exitosamente", "Exito", JOptionPane.INFORMATION_MESSAGE);
						((MenuPrincipalMesa)frame).cambiarVentana(2,dtoTicket);

					}catch(Exception E) { JOptionPane.showMessageDialog(frame,
						    "Hubo un error al guardar el ticket",
						    "Error",
						    JOptionPane.ERROR_MESSAGE); }
				}

				}
			}
		});
		btnNewButton.setBounds(190, 212, 92, 23);
		this.add(btnNewButton);

		JButton btnNewButton_1 = new JButton("Cancelar");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				((MenuPrincipalMesa)frame).cambiarVentanaMenu(3);
			}
		});
		btnNewButton_1.setBounds(295, 212, 92, 23);
		this.add(btnNewButton_1);
	}
}
