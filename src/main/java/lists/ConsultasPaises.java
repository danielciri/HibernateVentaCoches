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

public class ConsultasPaises extends JFrame {

	private JPanel contentPane;
	private ArrayList<Pais> paises;
	private JTextField nomPaises;

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
					ConsultasPaises frame = new ConsultasPaises();
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
	public ConsultasPaises() {
		setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 536, 352);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblConsultaPaises = new JLabel("Paises donde se encuentran disponibles nuestras agencias");
		lblConsultaPaises.setBounds(85, 10, 400, 35);
		contentPane.add(lblConsultaPaises);

		JLabel lblcod = new JLabel("Pais");
		lblcod.setBounds(30, 100, 88, 15);
		contentPane.add(lblcod);

		nomPaises = new JTextField("");
		nomPaises.setBounds(188, 100, 66, 20);
		contentPane.add(nomPaises);

		// BOTONES

		btnAnterior = new JButton("ANTERIOR");
		btnAnterior.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if ((posicion - 1) >= 0) {
					posicion--;
					pintarDatos(posicion);
				} else {
					JOptionPane.showMessageDialog(null, "Se encuentra en el primer Pais");
				}
			}
		});
		btnAnterior.setEnabled(false);
		btnAnterior.setBounds(140, 266, 114, 25);
		contentPane.add(btnAnterior);

		btnSiguiente = new JButton("SIGUIENTE");
		btnSiguiente.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if ((posicion + 1) <= (paises.size() - 1)) {
					posicion++;
					pintarDatos(posicion);
				} else {
					JOptionPane.showMessageDialog(null, "No hay mas Paises disponibles");
				}
			}
		});
		btnSiguiente.setEnabled(false);
		btnSiguiente.setBounds(270, 266, 114, 25);
		contentPane.add(btnSiguiente);

		btnLanzarConsulta = new JButton("Realizar Consulta");
		btnLanzarConsulta.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Session session = HibernateUtil.getSessionFactory().openSession();
				Transaction transaction = session.beginTransaction();
				Query<Pais> query = session.createQuery("from Pais");
				paises = (ArrayList<Pais>) query.list();
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
				for (int i = 0; i < paises.size(); i++) {
					if (nomPaises.getText() == paises.get(i).getNombre()) {
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
		nomPaises.setText(String.valueOf(paises.get(posicion).getNombre()));

	}
}
