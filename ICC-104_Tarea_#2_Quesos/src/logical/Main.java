package logical;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Queso q1 = null;
		Queso q2 = null;
		Queso q3 = null;
		q1 = new QuesoEsferico(200, 100, "1234", 4);
		q2 = new QuesoCilindrico(10, 20, "12", 3, 5);
		q3 = new QuesoCilindricoHueco(10, 20, "12", 5, 6, 3);
		
		System.out.println(q1.Volumen());
		System.out.println(q1.precioTotal());
		//System.out.println((float)(4/3));
		System.out.println(q2.Volumen());
		System.out.println(q2.precioTotal());
		
		System.out.println(q3.Volumen());
	}
	
	

}
