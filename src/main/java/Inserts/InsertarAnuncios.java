package Inserts;

import models.*;
import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.CriteriaQuery;
import org.hibernate.criterion.Restrictions;
import org.hibernate.internal.ExceptionMapperStandardImpl;
import org.hibernate.query.Query;

import models.*;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.WindowConstants;
import javax.persistence.criteria.CriteriaBuilder;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.awt.event.ActionEvent;

public class InsertarAnuncios extends JFrame {

	private JPanel contentPane;
	private JTextField codigo;
	private JTextField agenciaCode;
	private JTextField fechaPublicacion;
	private JTextField precio;
	private JTextField numKm;
	private JTextField numOfertaVehiculo;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					InsertarAnuncios frame = new InsertarAnuncios();
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
	public InsertarAnuncios() {
		vista();

	}

	private void vista() {
		setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(50, 50, 50, 50));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblNewLabel = new JLabel("Insertar Anuncio de la agencia");
		lblNewLabel.setBounds(30, 10, 188, 15);
		contentPane.add(lblNewLabel);

		JLabel lblNumeroDeLinea = new JLabel("Codigo ");
		lblNumeroDeLinea.setBounds(40, 60, 200, 15);
		contentPane.add(lblNumeroDeLinea);

		JLabel lblNumeroDeEstacion = new JLabel("Codigo de la Agencia");
		lblNumeroDeEstacion.setBounds(40, 90, 200, 15);
		contentPane.add(lblNumeroDeEstacion);

		JLabel lblOrgen = new JLabel("Fecha de publicacion");
		lblOrgen.setBounds(40, 120, 120, 15);
		contentPane.add(lblOrgen);

		JLabel lblTipoCambio = new JLabel("Precio ");
		lblTipoCambio.setBounds(40, 150, 150, 15);
		contentPane.add(lblTipoCambio);

		JLabel lblAnyo = new JLabel("Numero Kilometros ");
		lblAnyo.setBounds(40, 180, 180, 15);
		contentPane.add(lblAnyo);
		JLabel lblOferta = new JLabel("Numero de oferta ");
		lblOferta.setBounds(40, 210, 150, 15);
		contentPane.add(lblOferta);

		codigo = new JTextField();
		codigo.setBounds(210, 60, 124, 19);
		contentPane.add(codigo);
		codigo.setColumns(10);

		agenciaCode = new JTextField();
		agenciaCode.setBounds(220, 90, 124, 19);
		contentPane.add(agenciaCode);
		agenciaCode.setColumns(10);

		fechaPublicacion = new JTextField();
		fechaPublicacion.setBounds(210, 120, 124, 19);
		contentPane.add(fechaPublicacion);
		fechaPublicacion.setColumns(10);

		precio = new JTextField();
		precio.setBounds(210, 150, 124, 19);
		contentPane.add(precio);
		precio.setColumns(10);

		numKm = new JTextField();
		numKm.setBounds(210, 180, 124, 19);
		contentPane.add(numKm);
		numKm.setColumns(10);

		numOfertaVehiculo = new JTextField();
		numOfertaVehiculo.setBounds(210, 210, 124, 19);
		contentPane.add(numOfertaVehiculo);
		numOfertaVehiculo.setColumns(10);

		JButton btnInsertar = new JButton("Insertar");
		btnInsertar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Anuncio anuncio = new Anuncio();
				boolean validado = false;
				SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
				Session session = sessionFactory.openSession();
				Transaction transaction = session.beginTransaction();

				
			
					AgenciaVentaCoche agenciaVentaCoche = (AgenciaVentaCoche) session.createCriteria(AgenciaVentaCoche.class).add(Restrictions.eq("codigo", agenciaCode.getText()))
							.uniqueResult();
					Vehiculo vehiculos = (Vehiculo) session.createCriteria(Vehiculo.class)
							.add(Restrictions.eq("numeroOferta", Long.valueOf(numOfertaVehiculo.getText()))).uniqueResult();
					anuncio.setAgenciaVentaCoche(agenciaVentaCoche);
					anuncio.setVehiculo(vehiculos);
					anuncio.setCodigo(Integer.valueOf(codigo.getText()));
					anuncio.setPrecio(Float.valueOf(precio.getText()));
					anuncio.setNumKm(Float.valueOf(numKm.getText()));
					anuncio.setFechaPublicacion(Lib.Utils.parseFecha((fechaPublicacion.getText())));

					
					session.save(anuncio);
					session.getTransaction().commit();
					session.close();
				
				

			}
		});
		btnInsertar.setBounds(120, 250, 114, 50);
		contentPane.add(btnInsertar);
	}

}
