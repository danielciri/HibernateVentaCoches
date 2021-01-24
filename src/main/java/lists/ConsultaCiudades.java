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

public class ConsultaCiudades extends JFrame {

	private JPanel contentPane;
	private ArrayList<Ciudades> listaCiudades;
	private JTextField codigoPostal;
	private JLabel nombreComunidad;
	private JLabel nombrePais;
	private JButton btnLanzarConsulta;
	private JButton btnSiguiente, btnAnterior, btnIr;
	private int posicion = 0;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ConsultaCiudades frame = new ConsultaCiudades();
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
	public ConsultaCiudades() {
		setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 536, 352);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblConsultaPaises = new JLabel("Ciudades donde se encuentran disponibles nuestras agencias");
		lblConsultaPaises.setBounds(85, 10, 400, 35);
		contentPane.add(lblConsultaPaises);

		JLabel lblcod = new JLabel("Codigo postal");
		lblcod.setBounds(30, 100, 88, 15);
		contentPane.add(lblcod);

		JLabel lblNombre = new JLabel("Nomnbre comunidad:");
		lblNombre.setBounds(30, 130, 100, 15);
		contentPane.add(lblNombre);

		JLabel lblPais = new JLabel("Nombre Pais:");
		lblPais.setBounds(30, 160, 120, 15);
		contentPane.add(lblPais);

		codigoPostal = new JTextField("");
		codigoPostal.setBounds(188, 100, 66, 20);
		contentPane.add(codigoPostal);

		nombreComunidad = new JLabel("");
		nombreComunidad.setBounds(188, 130, 300, 15);
		contentPane.add(nombreComunidad);

		nombrePais = new JLabel("");
		nombrePais.setBounds(188, 160, 300, 15);
		contentPane.add(nombrePais);

		// BOTONES

		btnAnterior = new JButton("ANTERIOR");
		btnAnterior.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if ((posicion - 1) >= 0) {
					posicion--;
					pintarDatos(posicion);
				} else {
					JOptionPane.showMessageDialog(null, "Se encuentra en la primera Comunidad");
				}
			}
		});
		btnAnterior.setEnabled(false);
		btnAnterior.setBounds(140, 266, 114, 25);
		contentPane.add(btnAnterior);

		btnSiguiente = new JButton("SIGUIENTE");
		btnSiguiente.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if ((posicion + 1) <= (listaCiudades.size() - 1)) {
					posicion++;
					pintarDatos(posicion);
				} else {
					JOptionPane.showMessageDialog(null, "No hay mas Ciudades disponibles");
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
				Query<Ciudades> query = session.createQuery("from Ciudades");
				listaCiudades = (ArrayList<Ciudades>) query.list();
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
				for (int i = 0; i < listaCiudades.size(); i++) {
					if (Integer.parseInt(codigoPostal.getText()) == listaCiudades.get(i).getCodigoPostal()) {
						pintarDatos(i);
						posicion = i;
						aux = true;
					}
				}
				if (!aux) {
					JOptionPane.showMessageDialog(null, "La ID no se ha encontrado");
				}
			}
		});
		btnIr.setEnabled(false);
		btnIr.setBounds(270, 95, 47, 25);
		contentPane.add(btnIr);
	}

	private void pintarDatos(int posicion) {
		codigoPostal.setText(String.valueOf(listaCiudades.get(posicion).getCodigoPostal()));
		nombreComunidad.setText(String.valueOf(listaCiudades.get(posicion).getNombre()));
		nombrePais.setText(String.valueOf(listaCiudades.get(posicion).getPais().getNombre()));

	}
}