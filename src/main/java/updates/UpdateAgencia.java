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
import models.AgenciaVentaCoche;
import models.Anuncio;

public class UpdateAgencia extends JFrame {

	private JPanel contentPane;
	private ArrayList<AgenciaVentaCoche> agencia;
	private JButton btnLanzarConsulta;
	private JButton btnCancelarConsulta;
	private JButton btnSiguiente, btnAnterior, btnGuardar;
	private JLabel codigo, ciudad, nombre;
	private JTextField telefono, email;
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
					UpdateAgencia frame = new UpdateAgencia();
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
	public UpdateAgencia() {
		setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 536, 421);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblConsultaAnuncios = new JLabel("Actualizar datos");
		lblConsultaAnuncios.setBounds(210, 10, 180, 35);
		contentPane.add(lblConsultaAnuncios);

		JLabel lblCodigoAnuncio = new JLabel("Ciudad");
		lblCodigoAnuncio.setBounds(30, 100, 100, 15);
		contentPane.add(lblCodigoAnuncio);

		JLabel lblAgencia = new JLabel("Codigo");
		lblAgencia.setBounds(30, 130, 100, 15);
		contentPane.add(lblAgencia);

		JLabel lblTipo = new JLabel("Nombre");
		lblTipo.setBounds(30, 160, 120, 15);
		contentPane.add(lblTipo);

		JLabel lblPrecio = new JLabel("Telefono");
		lblPrecio.setBounds(30, 190, 120, 15);
		contentPane.add(lblPrecio);

		JLabel lblNumKm = new JLabel("Email");
		lblNumKm.setBounds(30, 220, 100, 15);
		contentPane.add(lblNumKm);


		// BOTONES

		btnLanzarConsulta = new JButton("Ejecutar Consulta");
		btnLanzarConsulta.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Session session = HibernateUtil.getSessionFactory().openSession();
				Transaction transaction = session.beginTransaction();
				Query<AgenciaVentaCoche> query = session.createQuery("from AgenciaVentaCoche");
				agencia = (ArrayList<AgenciaVentaCoche>) query.list();
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

		

		codigo = new JLabel("");
		codigo.setBounds(165, 130, 200, 15);
		contentPane.add(codigo);

		ciudad = new JLabel("");
		ciudad.setBounds(165, 160, 200, 15);
		contentPane.add(ciudad);

		telefono = new JTextField("");
		telefono.setBounds(165, 190, 66, 20);
		contentPane.add(telefono);

		email = new JTextField("");
		email.setBounds(165, 220, 200, 20);
		contentPane.add(email);

		nombre = new JLabel("");
		nombre.setBounds(165, 250, 66, 20);
		contentPane.add(nombre);

		btnGuardar = new JButton("Guardar Cambios");
		btnGuardar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					Session session = HibernateUtil.getSessionFactory().openSession();
					Transaction transaction = session.beginTransaction();
					AgenciaVentaCoche agenciaVentaCoche = session.get(AgenciaVentaCoche.class, codigo.getText());
					agenciaVentaCoche.setTelefono(Long.valueOf(telefono.getText()));
					agenciaVentaCoche.setEmail(email.getText());
					session.update(agenciaVentaCoche);
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
				for (int i = 0; i < agencia.size(); i++) {
					if (codigo.getText() == agencia.get(i).getCodigo()) {
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
	
		codigo.setText(String.valueOf(agencia.get(posicion).getCodigo()));
		ciudad.setText(String.valueOf(agencia.get(posicion).getCiudades().getCodigoPostal()));
		telefono.setText(String.valueOf(agencia.get(posicion).getTelefono()));
		email.setText(String.valueOf(agencia.get(posicion).getEmail()));
		nombre.setText(String.valueOf(agencia.get(posicion).getNombre()));

	}

}
