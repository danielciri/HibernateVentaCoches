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

public class InsertarAgencia  extends JFrame {

	private JPanel contentPane;
	private JTextField ciudad;
	private JTextField codigo;
	private JTextField nombre;
	private JTextField telefono;
	private JTextField email;


	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					InsertarAgencia frame = new InsertarAgencia();
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
	public InsertarAgencia() {
		setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(50, 50, 50, 50));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Insertar Agencia nueva");
		lblNewLabel.setBounds(30, 10, 188, 15);
		contentPane.add(lblNewLabel);
		
		JLabel lblNumeroDeLinea = new JLabel("Ciudad");
		lblNumeroDeLinea.setBounds(40, 60, 200, 15);
		contentPane.add(lblNumeroDeLinea);
		
		JLabel lblNumeroDeEstacion = new JLabel("Codigo");
		lblNumeroDeEstacion.setBounds(40, 90, 200, 15);
		contentPane.add(lblNumeroDeEstacion);
		
		JLabel lblOrgen = new JLabel("Nombre");
		lblOrgen.setBounds(40, 120, 120, 15);
		contentPane.add(lblOrgen);
		
		JLabel lblTipoCambio = new JLabel("Telefono");
		lblTipoCambio.setBounds(40, 150, 150, 15);
		contentPane.add(lblTipoCambio);
		
		JLabel lblAnyo = new JLabel("Email");
		lblAnyo.setBounds(40, 180, 180, 15);
		contentPane.add(lblAnyo);

		
		ciudad = new JTextField();
		ciudad.setBounds(160, 60, 124, 19);
		contentPane.add(ciudad);
		ciudad.setColumns(10);
		
		codigo = new JTextField();
		codigo.setBounds(160, 90, 124, 19);
		contentPane.add(codigo);
		codigo.setColumns(10);
		
		nombre = new JTextField();
		nombre.setBounds(160, 120, 124, 19);
		contentPane.add(nombre);
		nombre.setColumns(10);

		
		telefono = new JTextField();
		telefono.setBounds(160, 150, 124, 19);
		contentPane.add(telefono);
		telefono.setColumns(10);
		
		email = new JTextField();
		email.setBounds(160, 180, 124, 19);
		contentPane.add(email);
		email.setColumns(10);
		
	
		
		JButton btnInsertar = new JButton("Insertar");
		btnInsertar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				AgenciaVentaCoche agenciaVentaCoche = new AgenciaVentaCoche();
				SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
				Session session = sessionFactory.openSession();
				Transaction transaction = session.beginTransaction();
				Ciudades ciudades = (Ciudades) session.createCriteria(Ciudades.class).add(Restrictions.eq("codigoPostal",Integer.valueOf(ciudad.getText()))).uniqueResult();

			
				agenciaVentaCoche.setCiudades(ciudades);
				agenciaVentaCoche.setCodigo(codigo.getText());
				agenciaVentaCoche.setNombre(nombre.getText());
		
				agenciaVentaCoche.setTelefono(Long.valueOf((telefono.getText())));
				agenciaVentaCoche.setEmail(email.getText());
			
			
				session.save(agenciaVentaCoche);
				session.getTransaction().commit();
				session.close();
				


			}
		});
		
		btnInsertar.setBounds(120, 300, 114, 50);
		contentPane.add(btnInsertar);
	}

}


