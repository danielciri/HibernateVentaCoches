package lists;

import java.awt.BorderLayout;
import Inserts.HibernateUtil;
import models.*;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JOptionPane;
import javax.swing.border.EmptyBorder;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.WindowConstants;

import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.event.ActionEvent;

public class ConsultaAgencia extends JFrame {

	private JPanel contentPane;
	private ArrayList<AgenciaVentaCoche> agencias;
	private JTextField codigo;
	private JLabel nombre;
	private JLabel telefono;
	private JLabel email;
	private JLabel ciudades;
	private JButton btnLanzarConsulta;
	private JButton  btnSiguiente, btnAnterior, btnIr;
	private int posicion = 0;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ConsultaAgencia frame = new ConsultaAgencia();
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
	public ConsultaAgencia() {
		setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 536, 352);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblConsultasAgencias = new JLabel("Consulta Agencias");
		lblConsultasAgencias.setBounds(210, 10, 180, 35);
		contentPane.add(lblConsultasAgencias);
		
		JLabel lblcod = new JLabel("Codigo Agencia");
		lblcod.setBounds(30, 100, 88, 15);
		contentPane.add(lblcod);
		
		JLabel lblNombre = new JLabel("Nomnbre:");
		lblNombre.setBounds(30, 130, 66, 15);
		contentPane.add(lblNombre);
		
		JLabel lblTel = new JLabel("Telefono:");
		lblTel.setBounds(30, 160, 120, 15);
		contentPane.add(lblTel);
		
		JLabel lblEma = new JLabel("Email:");
		lblEma.setBounds(30, 190, 66, 15);
		contentPane.add(lblEma);
		
		JLabel lblCiudad = new JLabel("Ciudad:");
		lblCiudad.setBounds(30, 220, 66, 15);
		contentPane.add(lblCiudad);
		
		codigo = new JTextField("");
		codigo.setBounds(188, 100, 66, 20);
		contentPane.add(codigo);
		
		nombre = new JLabel("");
		nombre.setBounds(188, 130, 300, 15);
		contentPane.add(nombre);
		
		telefono = new JLabel("");
		telefono.setBounds(188, 160, 300, 15);
		contentPane.add(telefono);
		
		email = new JLabel("");
		email.setBounds(188, 190, 300, 15);
		contentPane.add(email);
		
		ciudades = new JLabel("");
		ciudades.setBounds(188, 220, 300, 15);
		contentPane.add(ciudades);
		
		//BOTONES

		
		btnAnterior = new JButton("ANTERIOR");
		btnAnterior.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if((posicion - 1) >= 0) {
					posicion--;
					pintarDatos(posicion);
				} else {
					JOptionPane.showMessageDialog(null, "Se encuentra en la primera agencia");
				}
			}
		});
		btnAnterior.setEnabled(false);
		btnAnterior.setBounds(140, 266, 114, 25);
		contentPane.add(btnAnterior);
		
		btnSiguiente = new JButton("SIGUIENTE");
		btnSiguiente.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if((posicion + 1) <= (agencias.size()-1)) {
					posicion++;
					pintarDatos(posicion);
				} else {
					JOptionPane.showMessageDialog(null, "No hay mas agencias disponibles");
				}
			}
		});
		btnSiguiente.setEnabled(false);
		btnSiguiente.setBounds(270, 266, 114, 25);
		contentPane.add(btnSiguiente);
		
	
		
		btnLanzarConsulta = new JButton("LANZAR CONSULTA");
		btnLanzarConsulta.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Session session = HibernateUtil.getSessionFactory().openSession();
				Transaction transaction = session.beginTransaction();
				Query<AgenciaVentaCoche> query = session.createQuery("from AgenciaVentaCoche");
				agencias = (ArrayList<AgenciaVentaCoche>) query.list();
				transaction.commit();
				btnSiguiente.setEnabled(true);
				btnAnterior.setEnabled(true);
			
				pintarDatos(0);
				btnIr.setEnabled(true);
				session.close();
			}
		});
		btnLanzarConsulta.setBounds(202, 40, 170, 25);
		contentPane.add(btnLanzarConsulta);
		
	
		
		btnIr = new JButton("IR");
		btnIr.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				boolean aux = false;
				for(int i = 0 ; i < agencias.size() ; i++) {
					if(codigo.getText() == agencias.get(i).getCodigo()) {
						pintarDatos(i);
						posicion = i;
						aux = true;
					}
				}
				if(!aux) {
					JOptionPane.showMessageDialog(null, "La ID no se ha encontrado");
				}
			}
		});
		btnIr.setEnabled(false);
		btnIr.setBounds(270, 95, 47, 25);
		contentPane.add(btnIr);
	}
	
	private void pintarDatos(int posicion) {
		codigo.setText(String.valueOf(agencias.get(posicion).getCodigo()));
		nombre.setText(String.valueOf(agencias.get(posicion).getNombre()));
		telefono.setText(String.valueOf(agencias.get(posicion).getTelefono()));
		email.setText(String.valueOf(agencias.get(posicion).getEmail()));
		ciudades.setText(String.valueOf(agencias.get(posicion).getCiudades().getNombre()));
	}
}
