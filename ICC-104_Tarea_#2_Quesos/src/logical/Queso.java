package logical;

import java.io.Serializable;

public abstract class Queso implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	//Atributos
	protected float costoBase;
	protected float costoUnitario; 
	protected String codigo;
	protected String estado;
	//
	//Constructor
	public Queso(float costoBase, float costoUnitario, String codigo) {
		super();
		this.costoBase = costoBase;
		this.costoUnitario = costoUnitario;
		this.codigo = codigo;
		this.estado = "Disponible";
	}
	
	//Getters y Setters
	public float getCostoBase() {
		return costoBase;
	}

	public void setCostoBase(float costoBase) {
		this.costoBase = costoBase;
	}

	public float getCostoUnitario() {
		return costoUnitario;
	}

	public void setCostoUnitario(float costoUnitario) {
		this.costoUnitario = costoUnitario;
	}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}
	
	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	//Metodos
	public abstract float Volumen();
	
	public float precioTotal() {
		float total = costoBase + (costoUnitario * Volumen());
		return total;
	}
	
}
