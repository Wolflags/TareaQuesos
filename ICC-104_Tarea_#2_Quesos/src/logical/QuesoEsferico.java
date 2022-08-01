package logical;

public class QuesoEsferico extends Queso {
	//Atributos
	protected float radio;
	
	//Constructor
	public QuesoEsferico(float costoBase, float costoUnitario, String codigo, float radio) {
		super(costoBase, costoUnitario, codigo);
		this.radio = radio;
	}
	
	//Getters y Setters
	public float getRadio() {
		return radio;
	}

	public void setRadio(float radio) {
		this.radio = radio;
	}
	
	@Override
	public float Volumen() {
		float x = (float) 4/3;
		float volumen = (float)(x * Math.PI * Math.pow(radio, 3));
		return volumen;
	}


}
