package logical;

public class QuesoCilindrico extends Queso {
	//Atributos
	protected float radioE;
	protected float longitud;
	
	//Constructor
	public QuesoCilindrico(float costoBase, float costoUnitario, String codigo, float radioE, float longitud) {
		super(costoBase, costoUnitario, codigo);
		this.radioE = radioE;
		this.longitud = longitud;
	}

	//Getters y Setters
	public float getRadioE() {
		return radioE;
	}

	public void setRadioE(float radio) {
		this.radioE = radio;
	}

	public float getLongitud() {
		return longitud;
	}

	public void setLongitud(float longitud) {
		this.longitud = longitud;
	}

	@Override
	public float Volumen() {
		float volumen = (float)(Math.PI * Math.pow(radioE, 2) * longitud);
		return volumen;
	}

}
