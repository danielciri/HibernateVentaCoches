package models;
// Generated 30 dic. 2020 15:22:15 by Hibernate Tools 5.4.18.Final

import java.util.HashSet;
import java.util.Set;

/**
 * Ciudades generated by hbm2java
 */
public class Ciudades implements java.io.Serializable {

	private int codigoPostal;
	private Pais pais;
	private String nombre;
	private Set agenciaVentaCoches = new HashSet(0);

	public Ciudades() {
	}

	public Ciudades(int codigoPostal, Pais pais, String nombre) {
		this.codigoPostal = codigoPostal;
		this.pais = pais;
		this.nombre = nombre;
	}

	public Ciudades(int codigoPostal, Pais pais, String nombre, Set agenciaVentaCoches) {
		this.codigoPostal = codigoPostal;
		this.pais = pais;
		this.nombre = nombre;
		this.agenciaVentaCoches = agenciaVentaCoches;
	}

	public int getCodigoPostal() {
		return this.codigoPostal;
	}

	public void setCodigoPostal(int codigoPostal) {
		this.codigoPostal = codigoPostal;
	}

	public Pais getPais() {
		return this.pais;
	}

	public void setPais(Pais pais) {
		this.pais = pais;
	}

	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Set getAgenciaVentaCoches() {
		return this.agenciaVentaCoches;
	}

	public void setAgenciaVentaCoches(Set agenciaVentaCoches) {
		this.agenciaVentaCoches = agenciaVentaCoches;
	}

}