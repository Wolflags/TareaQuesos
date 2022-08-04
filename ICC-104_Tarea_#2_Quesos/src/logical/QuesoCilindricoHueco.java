package logical;

import java.io.Serializable;

public class QuesoCilindricoHueco extends QuesoCilindrico implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	//Atributos
	protected float radioI;
//
	//Constructor
	public QuesoCilindricoHueco(float costoBase, float costoUnitario, String codigo, float radioE, float longitud,
			float radioI) {
		super(costoBase, costoUnitario, codigo, radioE, longitud);
		this.radioI = radioI;
	}
	
	//Getters y Setters
	public float getRadioI() {
		return radioI;
	}

	public void setRadioI(float radioI) {
		this.radioI = radioI;
	}
	
	@Override
	public float Volumen() {
		float volumen = (float)(Math.PI * longitud * (Math.pow(radioE, 2) - Math.pow(radioI, 2)));
		return volumen;
	}
	
}
