package models;
// Generated 30 dic. 2020 15:22:15 by Hibernate Tools 5.4.18.Final

import java.util.HashSet;
import java.util.Set;

/**
 * AgenciaVentaCoche generated by hbm2java
 */
public class AgenciaVentaCoche implements java.io.Serializable {

	private String codigo;
	private Ciudades ciudades;
	private String nombre;
	private long telefono;
	private String email;
	private Set anuncios = new HashSet(0);
	private Set vehiculos = new HashSet(0);

	public AgenciaVentaCoche() {
	}

	public AgenciaVentaCoche(String codigo, Ciudades ciudades, String nombre, long telefono, String email) {
		this.codigo = codigo;
		this.ciudades = ciudades;
		this.nombre = nombre;
		this.telefono = telefono;
		this.email = email;
	}

	public AgenciaVentaCoche(String codigo, Ciudades ciudades, String nombre, long telefono, String email, Set anuncios,
			Set vehiculos) {
		this.codigo = codigo;
		this.ciudades = ciudades;
		this.nombre = nombre;
		this.telefono = telefono;
		this.email = email;
		this.anuncios = anuncios;
		this.vehiculos = vehiculos;
	}

	public String getCodigo() {
		return this.codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public Ciudades getCiudades() {
		return this.ciudades;
	}

	public void setCiudades(Ciudades ciudades) {
		this.ciudades = ciudades;
	}

	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public long getTelefono() {
		return this.telefono;
	}

	public void setTelefono(long telefono) {
		this.telefono = telefono;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Set getAnuncios() {
		return this.anuncios;
	}

	public void setAnuncios(Set anuncios) {
		this.anuncios = anuncios;
	}

	public Set getVehiculos() {
		return this.vehiculos;
	}

	public void setVehiculos(Set vehiculos) {
		this.vehiculos = vehiculos;
	}

}
