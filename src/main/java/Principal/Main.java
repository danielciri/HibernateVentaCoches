package Principal;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import com.mysql.cj.x.protobuf.MysqlxCrud.Insert;
import com.mysql.cj.xdevapi.InsertParams;

import Inserts.HibernateUtil;
import Inserts.InsertarAgencia;
import Inserts.InsertarAnuncios;
import Inserts.InsertarCiudades;
import Inserts.InsertarPaises;
import Inserts.InsertarVehiculos;
import lists.ConsultaAgencia;
import lists.ConsultaAnuncio;
import lists.ConsultaCiudades;
import lists.ConsultaVehiculo;
import lists.ConsultasPaises;
import models.AgenciaVentaCoche;
import models.Anuncio;
import models.Vehiculo;
import updates.UpdateAgencia;
import updates.UpdateAnuncio;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public class Main extends JFrame implements ActionListener {
	private JMenuBar mb;
	private JMenu menuConsultas, menuInserts, menuUpdates;
	private JMenuItem cAgencias, cAnuncio, cCiudades, cPaises, cVehiculo;
	private JMenuItem insertarVehiculos;
	private JMenuItem insertarAnuncios;
	private JMenuItem updateAnuncio;
	private JMenuItem updateAgencia;
	private JMenuItem insertarAgencia;
	private JMenuItem insertarPais;
	private JMenuItem insertarCiudad;
	private JSeparator separator_1;
	private JLabel lblDatosAgencia;
	private JTable datosAgencia, datosAnuncio;
	private JLabel lblDatosAnuncio;
	private JTable tablaProcedencia;
	private JLabel tipoCambio;
	private JLabel numPlazas;
	private JLabel AnyoVehiculo;
	private JLabel tipoCombustible;
	private JLabel nombreAgenciaVentas, tipo, modelo, marca;
	private JTextField numeroOferta;
	private JButton btnAnterior, btnSiguiente;
	private int posicion = 0;
	private ArrayList<Vehiculo> arrayVehiculos;
	private ArrayList<Anuncio> arrayAnuncios;
	private ArrayList<AgenciaVentaCoche> arrayAgencia;
	private DefaultTableModel tabla1, tabla2;

	private JButton btnIr;

	public static void main(String[] ar) {
		Main formulario1 = new Main();
		formulario1.setBounds(100, 100, 710, 740);
		formulario1.setVisible(true);
	}

	public Main() {
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		getContentPane().setLayout(null);

		JLabel lblCodigoVehiculo = new JLabel("Codigo Vehiculo");
		lblCodigoVehiculo.setBounds(22, 30, 151, 15);
		getContentPane().add(lblCodigoVehiculo);

		JLabel lblCodigoAgencia = new JLabel("Codigo Agencia");
		lblCodigoAgencia.setBounds(300, 30, 151, 15);
		getContentPane().add(lblCodigoAgencia);

		JLabel lbltipo = new JLabel("Tipo:");
		lbltipo.setBounds(300, 70, 151, 15);
		getContentPane().add(lbltipo);

		JLabel lblModelo = new JLabel("Modelo:");
		lblModelo.setBounds(300, 100, 151, 15);
		getContentPane().add(lblModelo);

		JLabel lblMarca = new JLabel("Marca");
		lblMarca.setBounds(300, 130, 151, 15);
		getContentPane().add(lblMarca);

		numeroOferta = new JTextField("");
		numeroOferta.setBounds(125, 30, 50, 20);
		getContentPane().add(numeroOferta);

		nombreAgenciaVentas = new JLabel("");
		nombreAgenciaVentas.setBounds(471, 30, 280, 15);
		getContentPane().add(nombreAgenciaVentas);

		tipo = new JLabel("");
		tipo.setBounds(350, 70, 280, 15);
		getContentPane().add(tipo);

		modelo = new JLabel("");
		modelo.setBounds(350, 100, 280, 15);
		getContentPane().add(modelo);

		marca = new JLabel("");
		marca.setBounds(350, 130, 280, 15);
		getContentPane().add(marca);

		JSeparator separator = new JSeparator();
		separator.setBounds(35, 57, 642, 2);
		getContentPane().add(separator);

		JLabel lblTipoCambio = new JLabel("Tipo Cambio: ");
		lblTipoCambio.setBounds(22, 70, 115, 15);
		getContentPane().add(lblTipoCambio);

		JLabel lblNumPlazas = new JLabel("Num Plazas:");
		lblNumPlazas.setBounds(22, 100, 125, 15);
		getContentPane().add(lblNumPlazas);

		JLabel lblAnyo = new JLabel("Ano Vehiculo:");
		lblAnyo.setBounds(22, 130, 200, 15);
		getContentPane().add(lblAnyo);

		JLabel lblCombustible = new JLabel("Combustible:");
		lblCombustible.setBounds(22, 160, 200, 15);
		getContentPane().add(lblCombustible);

		tipoCambio = new JLabel("");
		tipoCambio.setBounds(100, 70, 66, 15);
		getContentPane().add(tipoCambio);

		numPlazas = new JLabel("");
		numPlazas.setBounds(100, 100, 66, 15);
		getContentPane().add(numPlazas);

		AnyoVehiculo = new JLabel("");
		AnyoVehiculo.setBounds(100, 130, 66, 15);
		getContentPane().add(AnyoVehiculo);

		tipoCombustible = new JLabel("");
		tipoCombustible.setBounds(100, 160, 66, 15);
		getContentPane().add(tipoCombustible);

		separator_1 = new JSeparator();
		separator_1.setBounds(35, 190, 642, 2);
		getContentPane().add(separator_1);

		lblDatosAgencia = new JLabel("Datos de la Agencia");
		lblDatosAgencia.setBounds(22, 210, 172, 15);
		getContentPane().add(lblDatosAgencia);

		JSeparator separator_2 = new JSeparator();
		separator_2.setBounds(35, 350, 642, 2);
		getContentPane().add(separator_2);

		lblDatosAnuncio = new JLabel("Datos del Anuncio");
		lblDatosAnuncio.setBounds(22, 370, 213, 15);
		getContentPane().add(lblDatosAnuncio);

		tablaProcedencia = new JTable();
		tablaProcedencia.setBounds(32, 410, 1, 1);
		getContentPane().add(tablaProcedencia);
		mb = new JMenuBar();
		setJMenuBar(mb);

		menuConsultas = new JMenu("Consultas");
		mb.add(menuConsultas);

		menuInserts = new JMenu("Inserts");
		mb.add(menuInserts);

		menuUpdates = new JMenu("Updates");
		mb.add(menuUpdates);

		cAgencias = new JMenuItem("Agencia");
		cAgencias.addActionListener(this);
		menuConsultas.add(cAgencias);

		cAnuncio = new JMenuItem("Anuncio");
		cAnuncio.addActionListener(this);
		menuConsultas.add(cAnuncio);

		cCiudades = new JMenuItem("Ciudades");
		cCiudades.addActionListener(this);
		menuConsultas.add(cCiudades);

		cPaises = new JMenuItem("Paises");
		cPaises.addActionListener(this);
		menuConsultas.add(cPaises);

		cVehiculo = new JMenuItem("Vehiculos");
		cVehiculo.addActionListener(this);
		menuConsultas.add(cVehiculo);

		insertarVehiculos = new JMenuItem("Vehiculo");
		insertarVehiculos.addActionListener(this);
		menuInserts.add(insertarVehiculos);

		insertarAnuncios = new JMenuItem("Anuncios");
		insertarAnuncios.addActionListener(this);
		menuInserts.add(insertarAnuncios);

		insertarAgencia = new JMenuItem("Agencia");
		insertarAgencia.addActionListener(this);
		menuInserts.add(insertarAgencia);
		
		insertarCiudad = new JMenuItem("Ciudades");
		insertarCiudad.addActionListener(this);
		menuInserts.add(insertarCiudad);
		
		insertarPais = new JMenuItem("Pais");
		insertarPais.addActionListener(this);
		menuInserts.add(insertarPais);
		
		updateAnuncio = new JMenuItem("Anuncio");
		updateAnuncio.addActionListener(this);
		menuUpdates.add(updateAnuncio);
		
		updateAgencia = new JMenuItem("Agencia");
		updateAgencia.addActionListener(this);
		menuUpdates.add(updateAgencia);

		// BOTONES

		btnAnterior = new JButton("ANTERIOR");
		btnAnterior.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if ((posicion - 1) >= 0) {
					posicion--;
					pintarCampos(posicion);
				} else {
					JOptionPane.showMessageDialog(null, "Primer registro alcanzado");
				}
			}
		});
		btnAnterior.setEnabled(false);
		btnAnterior.setBounds(194, 550, 114, 25);
		getContentPane().add(btnAnterior);

		btnSiguiente = new JButton("SIGUIENTE");
		btnSiguiente.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if ((posicion + 1) <= (arrayVehiculos.size() - 1)) {
					posicion++;
					pintarCampos(posicion);
				} else {
					JOptionPane.showMessageDialog(null, "Ultimo registro alcanzado");
				}
			}
		});
		btnSiguiente.setEnabled(false);
		btnSiguiente.setBounds(372, 550, 114, 25);
		getContentPane().add(btnSiguiente);

		JButton btnConsultar = new JButton("HACER CONSULTA");
		btnConsultar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Session session = HibernateUtil.getSessionFactory().openSession();
				Transaction transaction = session.beginTransaction();
				Query<Vehiculo> query = session.createQuery("from Vehiculo");
				arrayVehiculos = (ArrayList<Vehiculo>) query.list();
				Query<Anuncio> queryViajes = session.createQuery("from Anuncio");
				arrayAnuncios = (ArrayList<Anuncio>) queryViajes.list();
				Query<AgenciaVentaCoche> queryAgencia = session.createQuery("from AgenciaVentaCoche");
				arrayAgencia = (ArrayList<AgenciaVentaCoche>) queryAgencia.list();
				transaction.commit();
				session.close();

				btnSiguiente.setEnabled(true);
				btnAnterior.setEnabled(true);

				btnIr.setEnabled(true);
				pintarCampos(0);
			}
		});
		btnConsultar.setBounds(200, 590, 300, 70);
		getContentPane().add(btnConsultar);

		btnIr = new JButton("IR");
		btnIr.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				boolean aux = false;
				for (int i = 0; i < arrayVehiculos.size(); i++) {
					if (Integer.parseInt(numeroOferta.getText()) == arrayVehiculos.get(i).getNumeroOferta()) {
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
		btnIr.setBounds(235, 25, 47, 25);
		getContentPane().add(btnIr);
	}

	public void pintarCampos(int posicion) {
		numeroOferta.setText(String.valueOf(arrayVehiculos.get(posicion).getNumeroOferta()));
		nombreAgenciaVentas.setText(arrayVehiculos.get(posicion).getAgenciaVentaCoche().getCodigo());
		numPlazas.setText(String.valueOf(arrayVehiculos.get(posicion).getNumeroPlazas()));
		tipoCambio.setText(String.valueOf(arrayVehiculos.get(posicion).getTipoCambio()));
		AnyoVehiculo.setText(String.valueOf(arrayVehiculos.get(posicion).getAnyo()));
		tipoCombustible.setText(String.valueOf(arrayVehiculos.get(posicion).getCombustible()));
		tipo.setText(String.valueOf(arrayVehiculos.get(posicion).getTipo()));
		modelo.setText(String.valueOf(arrayVehiculos.get(posicion).getModelo()));
		marca.setText(String.valueOf(arrayVehiculos.get(posicion).getMarca()));

		datosAgencia = new JTable();
		tabla1 = new DefaultTableModel();
		tabla1.setColumnIdentifiers(new Object[] { "Codigo Postal Agencia", "Nombre agencia" });

		datosAnuncio = new JTable();
		tabla2 = new DefaultTableModel();
		tabla2.setColumnIdentifiers(new Object[] { "Codigo", "FechaPublicacion", "Precio", "Numero de km" });

		for (Anuncio v : arrayAnuncios) {

			if (arrayVehiculos.get(posicion).getNumeroOferta() == v.getVehiculo().getNumeroOferta()) {
				tabla2.addRow(new Object[] { v.getCodigo(), v.getFechaPublicacion(), v.getPrecio(), v.getNumKm() });
			}

		}
		for (AgenciaVentaCoche agenciaVentaCoche : arrayAgencia) {
			if (arrayVehiculos.get(posicion).getAgenciaVentaCoche().getCodigo() == agenciaVentaCoche.getCodigo()) {
				tabla1.addRow(new Object[] { agenciaVentaCoche.getCiudades().getCodigoPostal(),
						agenciaVentaCoche.getNombre() });
			}
		}

		datosAgencia.setModel(tabla1);
		JScrollPane panel = new JScrollPane(datosAgencia);
		panel.setBounds(32, 240, 500, 100);
		getContentPane().add(panel);

		datosAnuncio.setModel(tabla2);
		JScrollPane panel2 = new JScrollPane(datosAnuncio);
		panel2.setBounds(32, 400, 500, 100);
		getContentPane().add(panel2);
	}

	public void actionPerformed(ActionEvent e) {
		Container f = this.getContentPane();

		if (e.getSource() == cAgencias) {
			ConsultaAgencia consultaAgencia = new ConsultaAgencia();
			consultaAgencia.setVisible(true);
		}

		if (e.getSource() == cAnuncio) {
			ConsultaAnuncio consultaAnuncio = new ConsultaAnuncio();
			consultaAnuncio.setVisible(true);
		}
		if (e.getSource() == cCiudades) {
			ConsultaCiudades consultaCiudades = new ConsultaCiudades();
			consultaCiudades.setVisible(true);
		}
		if (e.getSource() == cPaises) {
			ConsultasPaises consultasPaises = new ConsultasPaises();
			consultasPaises.setVisible(true);
		}
		if (e.getSource() == cVehiculo) {
			ConsultaVehiculo consultaVehiculo = new ConsultaVehiculo();
			consultaVehiculo.setVisible(true);
		}
		if (e.getSource() == insertarAnuncios) {
			InsertarAnuncios insertarAnuncios = new InsertarAnuncios();
			insertarAnuncios.setVisible(true);

		}
		if (e.getSource() == insertarVehiculos) {
			InsertarVehiculos insertarVehiculosObjeto = new InsertarVehiculos();
			insertarVehiculosObjeto.setVisible(true);

		}
		if (e.getSource() == insertarAgencia) {
			InsertarAgencia insertarVehiculosObjeto = new InsertarAgencia();
			insertarVehiculosObjeto.setVisible(true);

		}
		if (e.getSource() == insertarCiudad) {
			InsertarCiudades insertarCiudades= new InsertarCiudades();
			insertarCiudades.setVisible(true);

		}
		if (e.getSource() == insertarPais) {
			InsertarPaises insertarPais= new InsertarPaises();
			insertarPais.setVisible(true);

		}
		if (e.getSource() == updateAnuncio) {
			UpdateAnuncio updateAnuncioObject = new UpdateAnuncio();
			updateAnuncioObject.setVisible(true);
		}

		if (e.getSource() == updateAgencia) {
			UpdateAgencia updateAgencias = new UpdateAgencia();
			updateAgencias.setVisible(true);
		}
	}
}