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
import org.hibernate.criterion.Restrictions;
import org.hibernate.internal.ExceptionMapperStandardImpl;
import org.hibernate.query.Query;

import models.*;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.WindowConstants;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.awt.event.ActionEvent;;

public class InsertarVehiculos extends JFrame {

	private JPanel contentPane;
	private JTextField marca;
	private JTextField modelo;
	private JTextField numeroPlazas;
	private JTextField tipoCambio;
	private JTextField anyo;
	private JTextField combustible;
	private JTextField tipo;
	private JTextField codAgencia;
	private JTextField numeroOferta;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					InsertarVehiculos frame = new InsertarVehiculos();
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
	public InsertarVehiculos() {
		setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(50, 50, 50, 50));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblNewLabel = new JLabel("Insertar Vehiculo para la agencia");
		lblNewLabel.setBounds(30, 10, 188, 15);
		contentPane.add(lblNewLabel);

		JLabel lblNumeroDeLinea = new JLabel("Marca");
		lblNumeroDeLinea.setBounds(40, 60, 200, 15);
		contentPane.add(lblNumeroDeLinea);

		JLabel lblNumeroDeEstacion = new JLabel("Modelo");
		lblNumeroDeEstacion.setBounds(40, 90, 200, 15);
		contentPane.add(lblNumeroDeEstacion);

		JLabel lblOrgen = new JLabel("Numero de plazas ");
		lblOrgen.setBounds(40, 120, 120, 15);
		contentPane.add(lblOrgen);

		JLabel lblTipoCambio = new JLabel("Tipo Cambio ");
		lblTipoCambio.setBounds(40, 150, 150, 15);
		contentPane.add(lblTipoCambio);

		JLabel lblAnyo = new JLabel("Ano vehiculo ");
		lblAnyo.setBounds(40, 180, 180, 15);
		contentPane.add(lblAnyo);

		JLabel lblcombustible = new JLabel("Combustible ");
		lblcombustible.setBounds(40, 210, 150, 15);
		contentPane.add(lblcombustible);

		JLabel lblTipo = new JLabel("Tipo  ");
		lblTipo.setBounds(40, 240, 150, 15);
		contentPane.add(lblTipo);

		JLabel lblCodAgencia = new JLabel("Codigo Agencia ");
		lblCodAgencia.setBounds(40, 270, 150, 15);
		contentPane.add(lblCodAgencia);

		JLabel lblNumOferta = new JLabel("Numero Oferta  ");
		lblNumOferta.setBounds(40, 300, 150, 15);
		contentPane.add(lblNumOferta);

		marca = new JTextField();
		marca.setBounds(210, 60, 124, 19);
		contentPane.add(marca);
		marca.setColumns(10);

		modelo = new JTextField();
		modelo.setBounds(220, 90, 124, 19);
		contentPane.add(modelo);
		modelo.setColumns(10);

		numeroPlazas = new JTextField();
		numeroPlazas.setBounds(210, 120, 124, 19);
		contentPane.add(numeroPlazas);
		numeroPlazas.setColumns(10);

		tipoCambio = new JTextField();
		tipoCambio.setBounds(210, 150, 124, 19);
		contentPane.add(tipoCambio);
		tipoCambio.setColumns(10);

		anyo = new JTextField();
		anyo.setBounds(210, 180, 124, 19);
		contentPane.add(anyo);
		anyo.setColumns(10);

		combustible = new JTextField();
		combustible.setBounds(210, 210, 124, 19);
		contentPane.add(combustible);
		combustible.setColumns(10);

		tipo = new JTextField();
		tipo.setBounds(210, 240, 124, 19);
		contentPane.add(tipo);
		tipo.setColumns(10);

		codAgencia = new JTextField();
		codAgencia.setBounds(210, 270, 124, 19);
		contentPane.add(codAgencia);
		codAgencia.setColumns(10);

		numeroOferta = new JTextField();
		numeroOferta.setBounds(210, 300, 124, 19);
		contentPane.add(numeroOferta);
		numeroOferta.setColumns(10);

		JButton btnInsertar = new JButton("Insertar");
		btnInsertar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				boolean validado = false;
				Vehiculo vehiculo = new Vehiculo();
				SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
				Session session = sessionFactory.openSession();
				Transaction transaction = session.beginTransaction();

				
					AgenciaVentaCoche agenciaVentaCoche = (AgenciaVentaCoche) session
							.createCriteria(AgenciaVentaCoche.class)
							.add(Restrictions.eq("codigo", codAgencia.getText())).uniqueResult();

					vehiculo.setAgenciaVentaCoche(agenciaVentaCoche);
					vehiculo.setNumeroOferta(Long.valueOf(numeroOferta.getText()));
					vehiculo.setMarca(marca.getText());
					vehiculo.setModelo(modelo.getText());
					vehiculo.setNumeroPlazas(Long.valueOf(numeroPlazas.getText()));
					vehiculo.setTipoCambio(tipoCambio.getText());
					vehiculo.setAnyo(Lib.Utils.parseFecha((anyo.getText())));
					vehiculo.setCombustible(combustible.getText());
					vehiculo.setTipo(tipo.getText());

			
					JOptionPane.showMessageDialog(null, "Datos no validos");
			
					JOptionPane.showMessageDialog(null, "Datos no validos");
		

			
			}
		});

		btnInsertar.setBounds(120, 360, 114, 50);
		contentPane.add(btnInsertar);
	}

}

