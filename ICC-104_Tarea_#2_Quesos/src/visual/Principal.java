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
import java.awt.event.ActionEvent;
import java.awt.Toolkit;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.border.TitledBorder;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Font;

public class Principal extends JFrame {

	private JPanel contentPane;
	private Dimension dim;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
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
}
