package logical;

import java.util.ArrayList;

public class Cliente {
	//Atributos
	private String nombre;
	private String direccion;
	private String telefono;
	private String cedula;
	private ArrayList<Factura> facturas = new ArrayList<Factura>();
	
	//Constructor
	public Cliente(String nombre, String direccion, String telefono, String cedula) {
		super();
		this.nombre = nombre;
		this.direccion = direccion;
		this.telefono = telefono;
		this.cedula = cedula;
	}

	//Getters y Setters
	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public String getCedula() {
		return cedula;
	}

	public void setCedula(String cedula) {
		this.cedula = cedula;
	}
	
	public ArrayList<Factura> getFacturas() {
		return facturas;
	}

	public void setFacturas(ArrayList<Factura> facturas) {
		this.facturas = facturas;
	}

	//Metodos
	public void insertarFactura(Factura factura) {
		facturas.add(factura);
	}

	
}
