package visual;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import java.awt.event.ActionListener;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.awt.event.ActionEvent;
import java.awt.Toolkit;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.border.TitledBorder;


import logical.Empresa;


import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class Principal extends JFrame {
//
	private JPanel contentPane;
	private Dimension dim;
	static Socket sfd = null;
	static DataInputStream EntradaSocket;
	static DataOutputStream SalidaSocket;
	String texto;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				FileInputStream empresa;
				FileOutputStream empresa2;
				ObjectInputStream empresaRead;
				ObjectOutputStream empresaWrite;
				try {
					empresa = new FileInputStream ("empresa.dat");
					empresaRead = new ObjectInputStream(empresa);
					Empresa temp = (Empresa)empresaRead.readObject();
					Empresa.setControl(temp);
					empresa.close();
					empresaRead.close();
				} catch (FileNotFoundException e) {
					try {
						empresa2 = new  FileOutputStream("empresa.dat");
						empresaWrite = new ObjectOutputStream(empresa2);
						empresaWrite.writeObject(Empresa.getInstance());
						empresa2.close();
						empresaWrite.close();
					} catch (FileNotFoundException e1) {
					} catch (IOException e1) {
						// TODO Auto-generated catch block
					}
				} catch (IOException e) {
					
					
				} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				///////////////////////////////////////////////////
				
				try
			    {
			      sfd = new Socket("127.0.0.1",7000);
			      EntradaSocket = new DataInputStream(new BufferedInputStream(sfd.getInputStream()));
			      SalidaSocket = new DataOutputStream(new BufferedOutputStream(sfd.getOutputStream()));
			    }
			    catch (UnknownHostException uhe)
			    {
			      System.out.println("No se puede acceder al servidor.");
			      System.exit(1);
			    }
			    catch (IOException ioe)
			    {
			      System.out.println("Comunicación rechazada.");
			      System.exit(1);
			    }
				
				
				try {
					Principal frame = new Principal();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Principal() {
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				 FileOutputStream empresa2;
	                ObjectOutputStream empresaWrite;
	                try {
	                    empresa2 = new  FileOutputStream("empresa.dat");
	                    empresaWrite = new ObjectOutputStream(empresa2);
	                    empresaWrite.writeObject(Empresa.getInstance());
	                } catch (FileNotFoundException e1) {
	                    // TODO Auto-generated catch block
	                    e1.printStackTrace();
	                } catch (IOException e1) {
	                    // TODO Auto-generated catch block
	                    e1.printStackTrace();
	                }
				
			}
		});


		setIconImage(Toolkit.getDefaultToolkit().getImage(Principal.class.getResource("/media/imgLogo.jpg")));
		setResizable(false);
		setTitle("Complejo L\u00E1cteo de La Habana");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		dim = getToolkit().getScreenSize();
		setSize(dim.width, dim.height-40);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(211, 211, 211));
		contentPane.add(panel, BorderLayout.CENTER);
		panel.setLayout(null);
		
		JPanel panelClientes = new JPanel();
		panelClientes.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panelClientes.setBackground(new Color(65, 105, 225));
		panelClientes.setBounds(87, 78, 274, 215);
		panel.add(panelClientes);
		panelClientes.setLayout(null);
		
		JButton btnVerClientes = new JButton("               Ver clientes");
		btnVerClientes.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ListadoClientes listClientes = new ListadoClientes();
				listClientes.setVisible(true);
			}
		});
		btnVerClientes.setIcon(new ImageIcon(Principal.class.getResource("/media/imgListadoClientes64pxB.png")));
		btnVerClientes.setBounds(10, 68, 254, 73);
		panelClientes.add(btnVerClientes);
		
		JLabel lblClientes = new JLabel("CLIENTES");
		lblClientes.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblClientes.setHorizontalAlignment(SwingConstants.CENTER);
		lblClientes.setBounds(85, 11, 104, 14);
		panelClientes.add(lblClientes);
		
		JPanel panelQuesos = new JPanel();
		panelQuesos.setLayout(null);
		panelQuesos.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panelQuesos.setBackground(new Color(255, 215, 0));
		panelQuesos.setBounds(538, 78, 274, 215);
		panel.add(panelQuesos);
		
		JButton btnRegistrarQueso = new JButton("     Registrar queso");
		btnRegistrarQueso.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				RegistrarQueso regQueso = new RegistrarQueso();
				regQueso.setVisible(true);
			}
		});
		btnRegistrarQueso.setIcon(new ImageIcon(Principal.class.getResource("/media/imgQueso64px.png")));
		btnRegistrarQueso.setBounds(10, 36, 254, 73);
		panelQuesos.add(btnRegistrarQueso);
		
		JButton btnListaQuesos = new JButton("  Listado de quesos");
		btnListaQuesos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ListadoQuesos listQuesos = new ListadoQuesos();
				listQuesos.setVisible(true);
			}
		});
		btnListaQuesos.setIcon(new ImageIcon(Principal.class.getResource("/media/imgListadoQuesos64pxB.png")));
		btnListaQuesos.setBounds(10, 120, 254, 73);
		panelQuesos.add(btnListaQuesos);
		
		JLabel lblQuesos = new JLabel("QUESOS");
		lblQuesos.setHorizontalAlignment(SwingConstants.CENTER);
		lblQuesos.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblQuesos.setBounds(85, 11, 104, 14);
		panelQuesos.add(lblQuesos);
		
		JPanel panelFacturacion = new JPanel();
		panelFacturacion.setLayout(null);
		panelFacturacion.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panelFacturacion.setBackground(new Color(50, 205, 50));
		panelFacturacion.setBounds(989, 78, 274, 215);
		panel.add(panelFacturacion);
		
		JButton btnRealizarCompra = new JButton("          Realizar compra");
		btnRealizarCompra.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				RealizarCompra realCompra = new RealizarCompra();
				realCompra.setVisible(true);
			}
		});
		btnRealizarCompra.setIcon(new ImageIcon(Principal.class.getResource("/media/imgRealizarCompra64pxB.png")));
		btnRealizarCompra.setBounds(10, 36, 254, 73);
		panelFacturacion.add(btnRealizarCompra);
		
		JButton btnListaFacturas = new JButton("Listado de facturas");
		btnListaFacturas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ListadoFacturas listFacturas = new ListadoFacturas(null);
				listFacturas.setVisible(true);
			}
		});
		btnListaFacturas.setIcon(new ImageIcon(Principal.class.getResource("/media/imgFactura64pxB.png")));
		btnListaFacturas.setBounds(10, 120, 254, 73);
		panelFacturacion.add(btnListaFacturas);
		
		JLabel lblFacturacion = new JLabel("FACTURACI\u00D3N");
		lblFacturacion.setHorizontalAlignment(SwingConstants.CENTER);
		lblFacturacion.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblFacturacion.setBounds(55, 9, 165, 19);
		panelFacturacion.add(lblFacturacion);
	}

	public static void backup(String archivo) {
		try {
			File fileIn = new File(archivo+".txt");
			FileInputStream fis;
			fis = new FileInputStream(fileIn);
			BufferedReader br = new BufferedReader(new InputStreamReader (fis));
			
			File fileOut = new File(archivo+"_respaldo.txt");
			FileOutputStream fos;
			fos = new FileOutputStream(fileOut);
			BufferedWriter bw = new BufferedWriter(new OutputStreamWriter (fos));
			
			try {
				String texto;
				while ((texto = br.readLine()) != null) {
					String linea;
					try
					{
						SalidaSocket.writeUTF(texto);
						SalidaSocket.flush();
						linea = EntradaSocket.readUTF();
						bw.append(linea);
						System.out.println(linea);
						bw.newLine();
					}
					catch (IOException ioe)
					{
						System.out.println("Error: "+ioe);
					}
				}
				bw.close();
			} catch (IOException e1) {
				e1.printStackTrace();
			}

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    
	    
		
	}
}
