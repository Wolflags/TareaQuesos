package logical;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;

public class Factura implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	//Atributos
	private ArrayList<Queso> quesos = new ArrayList<Queso>();
	private String codigo;
	private float total;
	private Date fecha;
	private Cliente cliente;
	//
	//Constructor
	public Factura(ArrayList<Queso> quesos, String codigo, Cliente cliente) {
		super();
		this.quesos = quesos;
		this.codigo = codigo;
		float total = 0;
		for(Queso queso : quesos) {
			total += queso.precioTotal();
		}
		this.total = total;
		this.fecha = new Date();
		this.cliente = cliente;
	}

	//Getters y Setters
	public String getCodigo() {
		return codigo;
	}

	public ArrayList<Queso> getQuesos() {
		return quesos;
	}

	public float getTotal() {
		return total;
	}
	
	public int cantArticulos() {
		int cant = 0;
		for(Queso queso : quesos) {
			cant++;
		}
		return cant;
	}

	public Date getFecha() {
		return fecha;
	}

	public Cliente getCliente() {
		return cliente;
	}
	
}
