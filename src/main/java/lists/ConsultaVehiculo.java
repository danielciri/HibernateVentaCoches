package lists;

import java.awt.BorderLayout;
import models.*;
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

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import Inserts.HibernateUtil;

public class ConsultaVehiculo extends JFrame {

	private JPanel contentPane;
	private ArrayList<Vehiculo> vehiculos;
	private JLabel agenciaVentaCoche, marca, modelo, numPlazas, anyo, combustible, tipo, tipoCambio;
	private JTextField numOferta;
	private JButton btnLanzarConsulta;
	private JButton btnCancelarConsulta;
	private JButton btnSiguiente, btnAnterior;
	private int posicion = 0;
	private JButton btnIr;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ConsultaVehiculo frame = new ConsultaVehiculo();
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
	public ConsultaVehiculo() {
		setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 536, 421);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblConsultasVehiculos = new JLabel("Consultas Vehiculos");
		lblConsultasVehiculos.setBounds(210, 10, 180, 35);
		contentPane.add(lblConsultasVehiculos);

		JLabel lblNumOfer = new JLabel("Numero Oferta");
		lblNumOfer.setBounds(30, 100, 100, 15);
		contentPane.add(lblNumOfer);

		JLabel lblCodAgen = new JLabel("Codigo de agencia");
		lblCodAgen.setBounds(30, 130, 120, 15);
		contentPane.add(lblCodAgen);

		JLabel lblMarca = new JLabel("Marca");
		lblMarca.setBounds(30, 160, 100, 15);
		contentPane.add(lblMarca);

		JLabel lblModelo = new JLabel("Modelo");
		lblModelo.setBounds(30, 190, 100, 15);
		contentPane.add(lblModelo);

		JLabel lblNumPlazas = new JLabel("Numero de plazas");
		lblNumPlazas.setBounds(30, 220, 120, 15);
		contentPane.add(lblNumPlazas);

		JLabel lblAnyo = new JLabel("Combustible ");
		lblAnyo.setBounds(30, 280, 120, 15);
		contentPane.add(lblAnyo);

		JLabel lblCombustible = new JLabel("Tipo");
		lblCombustible.setBounds(30, 310, 120, 15);
		contentPane.add(lblCombustible);

		JLabel lblTipoCambio = new JLabel("Anyo");
		lblTipoCambio.setBounds(30, 250, 120, 15);
		contentPane.add(lblTipoCambio);

		JLabel lblTipo = new JLabel("Tipo Cambio ");
		lblTipo.setBounds(30, 340, 120, 15);
		contentPane.add(lblTipo);

		// BOTONES

		btnAnterior = new JButton("ANTERIOR");
		btnAnterior.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if ((posicion - 1) >= 0) {
					posicion--;
					llenarCampos(posicion);
				} else {
					JOptionPane.showMessageDialog(null, "Nos encontramos en el primer vehiculo");
				}
			}
		});
		btnAnterior.setEnabled(false);
		btnAnterior.setBounds(140, 400, 114, 25);
		contentPane.add(btnAnterior);

		btnSiguiente = new JButton("SIGUIENTE");
		btnSiguiente.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if ((posicion + 1) <= (vehiculos.size() - 1)) {
					posicion++;
					llenarCampos(posicion);
				} else {
					JOptionPane.showMessageDialog(null, "No Disponemos de mas vehiculos en nuestra Consesionaria");
				}
			}
		});
		btnSiguiente.setEnabled(false);
		btnSiguiente.setBounds(270, 400, 114, 25);
		contentPane.add(btnSiguiente);

		btnLanzarConsulta = new JButton("Realizar Consulta");
		btnLanzarConsulta.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Session session = HibernateUtil.getSessionFactory().openSession();
				Transaction transaction = session.beginTransaction();
				Query<Vehiculo> query = session.createQuery("from Vehiculo");
				vehiculos = (ArrayList<Vehiculo>) query.list();
				transaction.commit();
				btnSiguiente.setEnabled(true);
				btnAnterior.setEnabled(true);
				llenarCampos(0);
				btnIr.setEnabled(true);
				session.close();

			}
		});
		btnLanzarConsulta.setBounds(202, 40, 135, 25);
		contentPane.add(btnLanzarConsulta);

		numOferta = new JTextField("");
		numOferta.setBounds(165, 100, 66, 20);
		contentPane.add(numOferta);

		agenciaVentaCoche = new JLabel("");
		agenciaVentaCoche.setBounds(165, 130, 200, 15);
		contentPane.add(agenciaVentaCoche);

		marca = new JLabel("");
		marca.setBounds(165, 160, 200, 15);
		contentPane.add(marca);

		modelo = new JLabel("");
		modelo.setBounds(165, 190, 66, 15);
		contentPane.add(modelo);

		numPlazas = new JLabel("");
		numPlazas.setBounds(165, 220, 66, 15);
		contentPane.add(numPlazas);

		tipoCambio = new JLabel("");
		tipoCambio.setBounds(165, 340, 66, 15);
		contentPane.add(tipoCambio);

		anyo = new JLabel("");
		anyo.setBounds(165, 250, 66, 15);
		contentPane.add(anyo);

		combustible = new JLabel("");
		combustible.setBounds(165, 280, 66, 15);
		contentPane.add(combustible);

		tipo = new JLabel("");
		tipo.setBounds(165, 310, 66, 15);
		contentPane.add(tipo);

		btnIr = new JButton("IR");
		btnIr.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				boolean aux = false;
				for (int i = 0; i < vehiculos.size(); i++) {
					if (Integer.parseInt(numOferta.getText()) == vehiculos.get(i).getNumeroOferta()) {
						llenarCampos(i);
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

	private void llenarCampos(int posicion) {
		numOferta.setText(String.valueOf(vehiculos.get(posicion).getNumeroOferta()));
		agenciaVentaCoche.setText(String.valueOf(vehiculos.get(posicion).getAgenciaVentaCoche().getCodigo()));
		marca.setText(String.valueOf(vehiculos.get(posicion).getMarca()));
		modelo.setText(String.valueOf(vehiculos.get(posicion).getModelo()));
		numPlazas.setText(String.valueOf(vehiculos.get(posicion).getNumeroPlazas()));
		tipoCambio.setText(String.valueOf(vehiculos.get(posicion).getTipoCambio()));
		anyo.setText(String.valueOf(vehiculos.get(posicion).getAnyo()));
		combustible.setText(String.valueOf(vehiculos.get(posicion).getCombustible()));
		tipo.setText(vehiculos.get(posicion).getTipo());
	}
}
