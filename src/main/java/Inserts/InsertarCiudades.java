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

public class InsertarCiudades  extends JFrame {

	private JPanel contentPane;
	private JTextField nomPais;
	private JTextField codigoPostal;
	private JTextField nombre;



	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					InsertarCiudades frame = new InsertarCiudades();
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
	public InsertarCiudades() {
		setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(50, 50, 50, 50));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Insertar Nuevas ciudades ");
		lblNewLabel.setBounds(30, 10, 188, 15);
		contentPane.add(lblNewLabel);
		
		JLabel lblNumeroDeLinea = new JLabel("Nombre Pais");
		lblNumeroDeLinea.setBounds(40, 60, 200, 15);
		contentPane.add(lblNumeroDeLinea);
		
		JLabel lblNumeroDeEstacion = new JLabel("Codigo Postal");
		lblNumeroDeEstacion.setBounds(40, 90, 200, 15);
		contentPane.add(lblNumeroDeEstacion);
		
		JLabel lblOrgen = new JLabel("Nombre Ciudad");
		lblOrgen.setBounds(40, 120, 120, 15);
		contentPane.add(lblOrgen);
		


		
		nomPais = new JTextField();
		nomPais.setBounds(160, 60, 124, 19);
		contentPane.add(nomPais);
		nomPais.setColumns(10);
		
		codigoPostal = new JTextField();
		codigoPostal.setBounds(160, 90, 124, 19);
		contentPane.add(codigoPostal);
		codigoPostal.setColumns(10);
		
		nombre = new JTextField();
		nombre.setBounds(160, 120, 124, 19);
		contentPane.add(nombre);
		nombre.setColumns(10);

	
		
	
		
		JButton btnInsertar = new JButton("Insertar");
		btnInsertar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				Ciudades ciudades = new Ciudades();
				SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
				Session session = sessionFactory.openSession();
				Transaction transaction = session.beginTransaction();
				Pais paises = (Pais) session.createCriteria(Pais.class).add(Restrictions.eq("nombre",nomPais.getText())).uniqueResult();

			
		
				ciudades.setPais(paises);
				ciudades.setNombre(nombre.getText());
				ciudades.setCodigoPostal(Integer.parseInt(codigoPostal.getText()));
				
			
				session.save(ciudades);
				session.getTransaction().commit();
				session.close();
				JOptionPane.showMessageDialog(null, "Datos insertados Correctament");
				


			}
		});
		
		btnInsertar.setBounds(120, 200, 114, 50);
		contentPane.add(btnInsertar);
	}

}
