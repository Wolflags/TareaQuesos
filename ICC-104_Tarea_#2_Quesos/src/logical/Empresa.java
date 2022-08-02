package logical;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.Serializable;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;

public class Empresa implements Serializable{
	//Atributos
	private ArrayList<Queso> quesos = new ArrayList<Queso>();
	private ArrayList<Factura> facturas = new ArrayList<Factura>();
	private ArrayList<Cliente> clientes = new ArrayList<Cliente>();
	public static Empresa empresa = null;
	private int generadorCodigoQueso;
	private int generadorCodigoFactura;
	//
	//Constructor
	private Empresa() {
		super();
		this.quesos = quesos;
		this.facturas = facturas;
		this.clientes = clientes;
		this.generadorCodigoQueso = 1;
		this.generadorCodigoFactura = 1;
		
	}
	
	public int getGeneradorCodigoQueso() {
		return generadorCodigoQueso;
	}

	public void setGeneradorCodigoQueso(int generadorCodigoQueso) {
		this.generadorCodigoQueso = generadorCodigoQueso;
	}

	public int getGeneradorCodigoFactura() {
		return generadorCodigoFactura;
	}

	public void setGeneradorCodigoFactura(int generadorCodigoFactura) {
		this.generadorCodigoFactura = generadorCodigoFactura;
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
	
	public static float formatearDecimales(Float numero, Integer numeroDecimales) {
	    return (float) (Math.round(numero * Math.pow(10, numeroDecimales)) / Math.pow(10, numeroDecimales));
	}

	public void CrearArchivo(Factura factura) {
		File archivo;
		
		FileWriter escritor;
		BufferedWriter bf;
		PrintWriter prescritor;
		
		try {
			archivo =  new File(factura.getCodigo()+".txt");
			FileOutputStream fos = new FileOutputStream(archivo);
			escritor = new FileWriter(archivo);
			bf = new BufferedWriter(new OutputStreamWriter(fos));
			
			bf.write("=================================");
			bf.newLine();
			bf.append("Bienvenidos!");
			bf.newLine();
			bf.append("Fecha: "+factura.getFecha().getDay()+"/"+factura.getFecha().getMonth()+"/"+(factura.getFecha().getYear()+1900));
			bf.newLine();
			bf.append("Cliente: "+factura.getCliente().getNombre());
			bf.newLine();
			bf.append("Telefono: "+factura.getCliente().getTelefono());
			bf.newLine();
			bf.append("=================================");
			bf.newLine();
			bf.append("Quesos:");
			bf.newLine();
			for(int i=0;i<factura.getQuesos().size();i++) {
				
				bf.append(factura.getQuesos().get(i).getCodigo()+" "+factura.getQuesos().get(i).Volumen()+" "+formatearDecimales(factura.getQuesos().get(i).precioTotal(), 2));
				
				bf.newLine();
			}
			bf.append("Total: "+formatearDecimales(factura.getTotal(), 2));
			bf.newLine();
			bf.append("=================================");
			bf.newLine();
			bf.append("Gracias por preferirnos!");
			bf.newLine();
			bf.close();
		}catch(Exception e) {
			
		}
		
	}
	
	public void hacerBkup() {
	
		return;
	}
	
}
