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

public class ConsultaAnuncio extends JFrame {

	private JPanel contentPane;
	private ArrayList<Anuncio> anuncios;
	private JTextField codigoAnuncio;
	private JLabel precio;
	private JLabel codigoAgencia;
	private JLabel fechaPublicacion;
	private JLabel agenciaCod;
	private JLabel numOferta;
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
					ConsultaAnuncio frame = new ConsultaAnuncio();
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
	public ConsultaAnuncio() {
		setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 536, 352);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblConsultaAnuncios = new JLabel("Consultas Anuncios");
		lblConsultaAnuncios.setBounds(210, 10, 180, 35);
		contentPane.add(lblConsultaAnuncios);

		JLabel lblcodigoAnuncio = new JLabel("Codigo Anuncio");
		lblcodigoAnuncio.setBounds(30, 100, 88, 15);
		contentPane.add(lblcodigoAnuncio);

		JLabel lblPrecio = new JLabel("Precio");
		lblPrecio.setBounds(30, 130, 120, 15);
		contentPane.add(lblPrecio);

		JLabel lblCodigoAgencia = new JLabel("Codigo agencia");
		lblCodigoAgencia.setBounds(30, 160, 120, 15);
		contentPane.add(lblCodigoAgencia);

		JLabel lblFecha = new JLabel("Fecha Publicacion");
		lblFecha.setBounds(30, 190, 120, 15);
		contentPane.add(lblFecha);

		JLabel lblNumeroVehiculo = new JLabel("Numero de vehiculo");
		lblNumeroVehiculo.setBounds(30, 220, 120, 15);
		contentPane.add(lblNumeroVehiculo);

		JLabel lbl = new JLabel("Km recorridos del vehiculo");
		lbl.setBounds(30, 250, 180, 15);
		contentPane.add(lbl);

		codigoAnuncio = new JTextField("");
		codigoAnuncio.setBounds(170, 100, 66, 20);
		contentPane.add(codigoAnuncio);

		precio = new JLabel("");
		precio.setBounds(188, 130, 139, 15);
		contentPane.add(precio);

		codigoAgencia = new JLabel("");
		codigoAgencia.setBounds(188, 160, 139, 15);
		contentPane.add(codigoAgencia);

		fechaPublicacion = new JLabel("");
		fechaPublicacion.setBounds(188, 190, 66, 15);
		contentPane.add(fechaPublicacion);

		numOferta = new JLabel("");
		numOferta.setBounds(188, 220, 66, 15);
		contentPane.add(numOferta);

		agenciaCod = new JLabel("");
		agenciaCod.setBounds(188, 250, 66, 15);
		contentPane.add(agenciaCod);

		// BOTONES

		btnAnterior = new JButton("Volver atras");
		btnAnterior.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if ((posicion - 1) >= 0) {
					posicion--;
					pintarDatos(posicion);
				} else {
					JOptionPane.showMessageDialog(null, "No hay mas anuncios disponibles");
				}
			}
		});
		btnAnterior.setEnabled(false);
		btnAnterior.setBounds(140, 280, 114, 25);
		contentPane.add(btnAnterior);

		btnSiguiente = new JButton("Siguiente");
		btnSiguiente.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if ((posicion + 1) <= (anuncios.size() - 1)) {
					posicion++;
					pintarDatos(posicion);
				} else {
					JOptionPane.showMessageDialog(null, "Esta en el primer anuncio");
				}
			}
		});
		btnSiguiente.setEnabled(false);
		btnSiguiente.setBounds(270, 280, 114, 25);
		contentPane.add(btnSiguiente);

		btnLanzarConsulta = new JButton("Realizar consulta");
		btnLanzarConsulta.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Session session = HibernateUtil.getSessionFactory().openSession();
				Transaction transaction = session.beginTransaction();
				Query<Anuncio> query = session.createQuery("from Anuncio");
				anuncios = (ArrayList<Anuncio>) query.list();
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
				for (int i = 0; i < anuncios.size(); i++) {
					if (Integer.parseInt(codigoAnuncio.getText()) == anuncios.get(i).getCodigo()) {
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
		btnIr.setBounds(248, 95, 47, 25);
		contentPane.add(btnIr);
	}

	private void pintarDatos(int posicion) {
		codigoAnuncio.setText(String.valueOf(anuncios.get(posicion).getCodigo()));
		agenciaCod.setText(String.valueOf(anuncios.get(posicion).getNumKm()));
		precio.setText(String.valueOf(anuncios.get(posicion).getPrecio()));
		codigoAgencia.setText(String.valueOf(anuncios.get(posicion).getAgenciaVentaCoche().getCodigo()));
		fechaPublicacion.setText(String.valueOf(anuncios.get(posicion).getFechaPublicacion()));
		numOferta.setText(String.valueOf(anuncios.get(posicion).getVehiculo().getNumeroOferta()));
	}
}
