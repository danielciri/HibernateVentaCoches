package updates;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.WindowConstants;
import javax.swing.border.EmptyBorder;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import Inserts.HibernateUtil;
import models.Anuncio;

public class UpdateAnuncio extends JFrame {

	private JPanel contentPane;
	private ArrayList<Anuncio> anuncios;
	private JButton btnLanzarConsulta;
	private JButton btnCancelarConsulta;
	private JButton btnSiguiente, btnAnterior, btnGuardar;
	private JLabel codigoAgencia, fechaPublicacion, numOferta;
	private JTextField precioVehiculo, numKm, codigoAnuncio;
	private int posicion = 0;
	private JButton btnIr;
	private JLabel lblactualizarDespuesDe;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UpdateAnuncio frame = new UpdateAnuncio();
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
	public UpdateAnuncio() {
		setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 536, 421);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblConsultaAnuncios = new JLabel("Actulizar datos");
		lblConsultaAnuncios.setBounds(210, 10, 180, 35);
		contentPane.add(lblConsultaAnuncios);

		JLabel lblCodigoAnuncio = new JLabel("Codigo Anuncio");
		lblCodigoAnuncio.setBounds(30, 100, 100, 15);
		contentPane.add(lblCodigoAnuncio);

		JLabel lblAgencia = new JLabel("Codigo Agencia");
		lblAgencia.setBounds(30, 130, 100, 15);
		contentPane.add(lblAgencia);

		JLabel lblTipo = new JLabel("Fecha publicacion");
		lblTipo.setBounds(30, 160, 120, 15);
		contentPane.add(lblTipo);

		JLabel lblPrecio = new JLabel("Precio");
		lblPrecio.setBounds(30, 190, 120, 15);
		contentPane.add(lblPrecio);

		JLabel lblNumKm = new JLabel("Numero de Km");
		lblNumKm.setBounds(30, 220, 100, 15);
		contentPane.add(lblNumKm);

		JLabel lblNumOferta = new JLabel("Numero de oferta");
		lblNumOferta.setBounds(30, 250, 120, 15);
		contentPane.add(lblNumOferta);

		// BOTONES

		btnLanzarConsulta = new JButton("Ejecutar Consulta");
		btnLanzarConsulta.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Session session = HibernateUtil.getSessionFactory().openSession();
				Transaction transaction = session.beginTransaction();
				Query<Anuncio> query = session.createQuery("from Anuncio");
				anuncios = (ArrayList<Anuncio>) query.list();
				transaction.commit();

				btnGuardar.setEnabled(true);
				btnIr.setEnabled(true);
				session.close();
				pintarCampos(0);
				posicion = 0;
			}
		});
		btnLanzarConsulta.setBounds(202, 40, 170, 25);
		contentPane.add(btnLanzarConsulta);

		codigoAnuncio = new JTextField("");
		codigoAnuncio.setBounds(165, 100, 66, 20);
		contentPane.add(codigoAnuncio);

		codigoAgencia = new JLabel("");
		codigoAgencia.setBounds(165, 130, 200, 15);
		contentPane.add(codigoAgencia);

		fechaPublicacion = new JLabel("");
		fechaPublicacion.setBounds(165, 160, 200, 15);
		contentPane.add(fechaPublicacion);

		precioVehiculo = new JTextField("");
		precioVehiculo.setBounds(165, 190, 66, 20);
		contentPane.add(precioVehiculo);

		numKm = new JTextField("");
		numKm.setBounds(165, 220, 66, 20);
		contentPane.add(numKm);

		numOferta = new JLabel("");
		numOferta.setBounds(165, 250, 66, 20);
		contentPane.add(numOferta);

		btnGuardar = new JButton("Guardar Cambios");
		btnGuardar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					Session session = HibernateUtil.getSessionFactory().openSession();
					Transaction transaction = session.beginTransaction();
					Anuncio anuncio = session.get(Anuncio.class, Integer.parseInt(codigoAnuncio.getText()));
					anuncio.setNumKm(Float.valueOf(numKm.getText()));
					anuncio.setPrecio(Float.valueOf(precioVehiculo.getText()));
					session.update(anuncio);
					transaction.commit();
					session.close();
				} catch (HibernateException he) {
					he.printStackTrace();
				}
			}
		});
		btnGuardar.setEnabled(false);
		btnGuardar.setBounds(371, 275, 200, 25);
		contentPane.add(btnGuardar);

		btnIr = new JButton("IR");
		btnIr.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				boolean aux = false;
				for (int i = 0; i < anuncios.size(); i++) {
					if (Integer.parseInt(codigoAnuncio.getText()) == anuncios.get(i).getCodigo()) {
						pintarCampos(i);
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
		btnIr.setBounds(233, 95, 47, 25);
		contentPane.add(btnIr);

	}

	private void pintarCampos(int posicion) {
		codigoAnuncio.setText(String.valueOf(anuncios.get(posicion).getCodigo()));
		codigoAgencia.setText(String.valueOf(anuncios.get(posicion).getAgenciaVentaCoche().getCodigo()));
		fechaPublicacion.setText(String.valueOf(anuncios.get(posicion).getFechaPublicacion()));
		precioVehiculo.setText(String.valueOf(anuncios.get(posicion).getPrecio()));
		numKm.setText(String.valueOf(anuncios.get(posicion).getNumKm()));
		numOferta.setText(String.valueOf(anuncios.get(posicion).getVehiculo().getNumeroOferta()));

	}

}
