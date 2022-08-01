package logical;

import java.io.Serializable;
import java.util.ArrayList;

public class Factura implements Serializable{
	//Atributos
	private ArrayList<Queso> quesos = new ArrayList<Queso>();
	private String codigo;
	private float total;
	
	//Constructor
	public Factura(ArrayList<Queso> quesos, String codigo) {
		super();
		this.quesos = quesos;
		this.codigo = codigo;
		float total = 0;
		for(Queso queso : quesos) {
			total += queso.precioTotal();
		}
		this.total = total;
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
}
