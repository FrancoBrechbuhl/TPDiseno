package interfaces;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JButton;

import java.util.ArrayList;
import java.util.List;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import gestores.*;
import logica.*;


public class RegistroTicketB extends JPanel {
	private MenuPrincipalMesa frame;
	private JTextField textObservaciones;
	private GestorDB gestorDB;
	private JComboBox comboBoxGrupo;
	private JComboBox JComboBoxClasificacion;

	public RegistroTicketB(MenuPrincipalMesa f) {
		this.frame=f;
		setLayout(null);
		
		this.gestorDB = new GestorDB();
		
		this.gestorDB.connectDatabase();
		List<Clasificacion> clasificacionesTicket = this.gestorDB.seleccionarClasificaciones();
		this.gestorDB.cerrarConexion();
		
		String[] estadosTicket = {"Abierto en mesa de ayuda","Abierto derivado a grupo","Solucionado a la espera ok","Cerrado"};
		
		JLabel lblObservaciones = new JLabel("Observaciones");
		lblObservaciones.setBounds(28, 11, 145, 14);
		this.add(lblObservaciones);
		
		textObservaciones = new JTextField();
		textObservaciones.setBounds(28, 33, 183, 74);
		this.add(textObservaciones);
		textObservaciones.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("Estado");
		lblNewLabel.setBounds(38, 121, 80, 14);
		this.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Clasificaci\u00F3n");
		lblNewLabel_1.setBounds(38, 146, 113, 14);
		this.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Grupo resoluci\u00F3n");
		lblNewLabel_2.setBounds(38, 171, 113, 14);
		this.add(lblNewLabel_2);
		
		JComboBoxClasificacion = new JComboBox();
		for(Clasificacion c : clasificacionesTicket) {
			JComboBoxClasificacion.addItem(c.getNombre());
		}
		JComboBoxClasificacion.setBounds(179, 143, 183, 20);
		
		String nombreClas = (String)JComboBoxClasificacion.getSelectedItem();
		
		List<GrupoResolucion> grupos = new ArrayList<GrupoResolucion>();
		
		for(Clasificacion c : clasificacionesTicket) {
			if(c.getNombre().equals(nombreClas)) {
				grupos = c.getGrupos();
			}
		}
		
		comboBoxGrupo = new JComboBox();
		for(GrupoResolucion gr : grupos) {
			comboBoxGrupo.addItem(gr.getNombre());
		}
		
		JComboBox comboBoxEstado = new JComboBox(estadosTicket);
		comboBoxEstado.setBounds(179, 118, 183, 20);
		this.add(comboBoxEstado);
		
		JComboBoxClasificacion.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				List<GrupoResolucion> grup = new ArrayList<GrupoResolucion>();
				int i = JComboBoxClasificacion.getSelectedIndex();
				for (Clasificacion c : clasificacionesTicket){
					if (c.getNombre().equals(JComboBoxClasificacion.getSelectedItem())) {
						grup.addAll(c.getGrupos());
						for(GrupoResolucion gr : c.getGrupos()) {
						}
					}
				}
				frame.refreshVentana(grup, clasificacionesTicket, i);
			}
		});
		
		this.add(JComboBoxClasificacion);
		
		comboBoxGrupo = new JComboBox();
		comboBoxGrupo.setBounds(179, 168, 183, 20);
		
		this.add(comboBoxGrupo);
		
		
		JButton btnNewButton = new JButton("Cancelar");
		btnNewButton.setBounds(259, 202, 101, 23);
		this.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Aceptar");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if( textObservaciones.getText().isEmpty()) {
					JOptionPane.showMessageDialog(frame,
						    "Los campos no pueden ser nulos",
						    "Error",
						    JOptionPane.ERROR_MESSAGE);
				}
				else {
					int nroTicket = frame.getTicketEnProceso().getNroTicket();
					//TODO: recuperarTicket(nroTicket) 
					//TODO: setearObservaciones
					if(comboBoxEstado.getSelectedItem()=="Cerrado") {
						//TODO: Cerrar-Gestorn(nroTicket)
					}
					else if(comboBoxEstado.getSelectedItem()=="Abierto derivado grupo"){
						String grupo=comboBoxGrupo.getSelectedItem().toString();
						//TODO: Derivar-Gestor(grupo,nroTicket)	
					}
					JOptionPane.showMessageDialog(frame, "Cambios guardados", "Exito", JOptionPane.INFORMATION_MESSAGE);
					
					
					
				}
			}
		});
		btnNewButton_1.setBounds(146, 202, 101, 23);
		this.add(btnNewButton_1);
	}

	public JComboBox getComboBoxGrupo() {
		return comboBoxGrupo;
	}

	public void setComboBoxGrupo(JComboBox comboBoxGrupo, List<Clasificacion> clas) {
		this.comboBoxGrupo = comboBoxGrupo;
		comboBoxGrupo.setBounds(179, 168, 183, 20);
		comboBoxGrupo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				List<GrupoResolucion> grup = new ArrayList<GrupoResolucion>();
				int i = JComboBoxClasificacion.getSelectedIndex();
				for (Clasificacion c : clas){
					if (c.getNombre() == comboBoxGrupo.getSelectedItem())
						grup.addAll(c.getGrupos());
				}
				frame.refreshVentana(grup, clas, i);
			}
		});
		this.add(comboBoxGrupo);
	}
	
	public void keepSelectedClass(int i) {
		JComboBoxClasificacion.setSelectedIndex(i);
	}
	
	

}
