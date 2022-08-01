package logical;

import java.io.Serializable;
import java.util.ArrayList;

public class Empresa implements Serializable{
	//Atributos
	private ArrayList<Queso> quesos = new ArrayList<Queso>();
	private ArrayList<Factura> facturas = new ArrayList<Factura>();
	private ArrayList<Cliente> clientes = new ArrayList<Cliente>();
	public static Empresa empresa = null;
	public static int generadorCodigoQueso = 1;
	public static int generadorCodigoFactura = 1;
	//
	//Constructor
	private Empresa() {
		super();
		this.quesos = quesos;
		this.facturas = facturas;
		this.clientes = clientes;
	}
	
	//GetInstance
	public static Empresa getInstance() {
		if(empresa == null) {
			empresa = new Empresa();
		}
		return empresa;
	}
	
	//Getters y Setters
	public ArrayList<Queso> getQuesos() {
		return quesos;
	}

	public void setQuesos(ArrayList<Queso> quesos) {
		this.quesos = quesos;
	}

	public ArrayList<Factura> getFacturas() {
		return facturas;
	}

	public void setFacturas(ArrayList<Factura> facturas) {
		this.facturas = facturas;
	}

	public ArrayList<Cliente> getClientes() {
		return clientes;
	}

	public void setClientes(ArrayList<Cliente> clientes) {
		this.clientes = clientes;
	}

	//Metodos
	public void insertarQueso(Queso queso) {
		quesos.add(queso);
		generadorCodigoQueso++;
	}
	
	public void insertarFactura(Factura factura) {
		facturas.add(factura);
		generadorCodigoFactura++;
	}
	
	public void insertarCliente(Cliente cliente) {
		clientes.add(cliente);
	}
	
	public Factura buscarFacturaByCodigo(String codigo) {
		Factura aux = null;
		int i = 0;
		boolean encontrado = false;
		while(i < facturas.size() && !encontrado) {
			if(facturas.get(i).getCodigo().equalsIgnoreCase(codigo)) {
				aux = facturas.get(i);
				encontrado = true;
			}
			i++;
		}
		return aux;
	}

	public Queso buscarQuesoByCodigo(String codigo) {
		Queso aux = null;
		int i = 0;
		boolean encontrado = false;
		while(i < quesos.size() && !encontrado) {
			if(quesos.get(i).getCodigo().equalsIgnoreCase(codigo)) {
				aux = quesos.get(i);
				encontrado = true;
			}
			i++;
		}
		return aux;
	}
	
	public Cliente buscarClienteByCedula(String cedula) {
		Cliente aux = null;
		int i = 0;
		boolean encontrado = false;
		while(i < clientes.size() && !encontrado) {
			if(clientes.get(i).getCedula().equalsIgnoreCase(cedula)) {
				aux = clientes.get(i);
				encontrado = true;
			}
			i++;
		}
		return aux;
	}
	
	public float montoTotalEnCliente(String cedula) {
		float total = 0;
		Cliente aux = null;
		aux = buscarClienteByCedula(cedula);
		if(aux != null) {
			for(Factura factura : aux.getFacturas()) {
				total += factura.getTotal();
			}
		}
		return total;
	}
	
	public static void setControl(Empresa temp) {
		Empresa.empresa = temp;
		
	}
	
}
