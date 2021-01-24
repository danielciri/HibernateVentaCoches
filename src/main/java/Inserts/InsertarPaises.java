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

import models.*;

import javax.swing.JLabel;

import javax.swing.JTextField;
import javax.swing.WindowConstants;
import javax.swing.JButton;
import java.awt.event.ActionListener;

import java.awt.event.ActionEvent;;

public class InsertarPaises  extends JFrame {

	private JPanel contentPane;
	private JTextField pNombre;



	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					InsertarPaises frame = new InsertarPaises();
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
	public InsertarPaises() {
		setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(50, 50, 50, 50));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Insertar Nuevo Pais");
		lblNewLabel.setBounds(30, 10, 188, 15);
		contentPane.add(lblNewLabel);
		
		JLabel lblNombre = new JLabel("Insertar Nuevo Pais");
		lblNombre.setBounds(30, 100, 188, 15);
		contentPane.add(lblNombre);
		


		
		pNombre = new JTextField();
		pNombre.setBounds(160, 100, 124, 19);
		contentPane.add(pNombre);
		pNombre.setColumns(10);
	
		
	
		
		JButton btnInsertar = new JButton("Insertar");
		btnInsertar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				AgenciaVentaCoche agenciaVentaCoche = new AgenciaVentaCoche();
				SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
				Session session = sessionFactory.openSession();
				Transaction transaction = session.beginTransaction();
			
				Pais pais = new Pais();
				pais.setNombre(pNombre.getText());
			
				
			
			
				session.save(pais);
				session.getTransaction().commit();
				session.close();
				


			}
		});
		
		btnInsertar.setBounds(120, 200, 114, 50);
		contentPane.add(btnInsertar);
	}

}