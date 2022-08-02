package visual;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import com.sun.org.apache.xerces.internal.impl.dtd.models.DFAContentModel;

import logical.Cliente;
import logical.Empresa;
import logical.Factura;
import logical.Queso;
import logical.QuesoCilindrico;
import logical.QuesoCilindricoHueco;
import logical.QuesoEsferico;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.border.TitledBorder;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JRadioButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.Toolkit;

public class RealizarCompra extends JDialog {
//
	private DecimalFormat df = new DecimalFormat("#.##");
	private final JPanel contentPanel = new JPanel();
	private JTable tableAlmacen;
	private static DefaultTableModel modelAlmacen;
	private static DefaultTableModel modelCarrito;
	private static Object[] row;
	private Queso selectedAlmacen = null;
	private Queso selectedCarrito = null;
	private JTable tableCarrito;
	private ArrayList<Queso> almacen = new ArrayList<Queso>();
	private ArrayList<Queso> carrito = new ArrayList<Queso>();
	private JButton btnAgregar;
	private JButton btnEliminar;
	private JTextField txtCedulaExistente;
	private JTextField txtNombreExistente;
	private Cliente auxCliente = null;
	private JRadioButton rbClienteExistente;
	private JRadioButton rbClienteNuevo;
	private JPanel panelClienteExistente;
	private JTextField txtNombreNuevo;
	private JPanel panelClienteNuevo;
	private JTextField txtCedulaNuevo;
	private JTextField txtDireccionNuevo;
	private JTextField txtTelefono;
	private JPanel panelInfoCliente;
	private JLabel lblMontoTotal;
	private JLabel lblCantQuesos;
	private JLabel lblCantQuesosAlmacen;

	public RealizarCompra() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(RealizarCompra.class.getResource("/media/imgRealizarCompra32pxN.png")));
		crearAlmacen();
		df.setRoundingMode(RoundingMode.CEILING);
		setTitle("Realizar compra");
		setModal(true);
		setResizable(false);
		setBounds(100, 100, 811, 590);
		setLocationRelativeTo(null);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			JPanel panelAlmacen = new JPanel();
			panelAlmacen.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
			panelAlmacen.setBounds(10, 316, 310, 111);
			contentPanel.add(panelAlmacen);
			panelAlmacen.setLayout(new BorderLayout(0, 0));
			{
				JScrollPane scrollPane = new JScrollPane();
				panelAlmacen.add(scrollPane, BorderLayout.CENTER);
				{
					tableAlmacen = new JTable();
					tableAlmacen.addMouseListener(new MouseAdapter() {
						@Override
						public void mouseClicked(MouseEvent e) {
							int index = tableAlmacen.getSelectedRow();
							if(index >= 0) {
								String codQueso = tableAlmacen.getValueAt(index, 0).toString();
								selectedAlmacen = Empresa.getInstance().buscarQuesoByCodigo(codQueso);
								btnAgregar.setEnabled(true);
								btnEliminar.setEnabled(false);
							}
						}
					});
					tableAlmacen.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
					modelAlmacen = new DefaultTableModel();
					String[] headers = {"Código", "Volumen (cm^3)", "Precio"};
					modelAlmacen.setColumnIdentifiers(headers);
					tableAlmacen.setModel(modelAlmacen);
					
					scrollPane.setViewportView(tableAlmacen);
				}
				loadQuesos();
			}
			{
				JPanel panelCarrito = new JPanel();
				panelCarrito.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
				panelCarrito.setBounds(474, 316, 310, 111);
				contentPanel.add(panelCarrito);
				panelCarrito.setLayout(new BorderLayout(0, 0));
				
				JScrollPane scrollPane = new JScrollPane();
				panelCarrito.add(scrollPane, BorderLayout.CENTER);

				{
					tableCarrito = new JTable();
					tableCarrito.addMouseListener(new MouseAdapter() {
						@Override
						public void mouseClicked(MouseEvent e) {
							int index = tableCarrito.getSelectedRow();
							if(index >= 0) {
								String codQueso = tableCarrito.getValueAt(index, 0).toString();
								selectedCarrito = Empresa.getInstance().buscarQuesoByCodigo(codQueso);
								btnAgregar.setEnabled(false);
								btnEliminar.setEnabled(true);
							}
						}
					});
					tableCarrito.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
					modelCarrito = new DefaultTableModel();
					String[] headers = {"Código", "Volumen (cm^3)", "Precio"};
					modelCarrito.setColumnIdentifiers(headers);
					tableCarrito.setModel(modelCarrito);
					scrollPane.setViewportView(tableCarrito);
				}
				loadCarrito();
			}

			btnAgregar = new JButton("Agregar >>");
			btnAgregar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					carrito.add(selectedAlmacen);
					almacen.remove(buscarIndiceEnAlmacen(selectedAlmacen));
					loadQuesos();
					loadCarrito();
					btnAgregar.setEnabled(false);
					loadLabels();
				}
			});
			btnAgregar.setEnabled(false);
			btnAgregar.setBounds(345, 342, 105, 23);
			contentPanel.add(btnAgregar);

			btnEliminar = new JButton("<< Eliminar");
			btnEliminar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					carrito.remove(buscarIndiceEnCarrito(selectedCarrito));
					almacen.add(selectedCarrito);
					loadQuesos();
					loadCarrito();
					btnEliminar.setEnabled(false);
					loadLabels();
				}
			});
			btnEliminar.setEnabled(false);
			btnEliminar.setBounds(345, 376, 105, 23);
			contentPanel.add(btnEliminar);
		}
		{
			JPanel panelCliente = new JPanel();
			panelCliente.setBounds(10, 11, 774, 99);
			contentPanel.add(panelCliente);
			panelCliente.setLayout(null);
			{
				rbClienteExistente = new JRadioButton("Cliente existente");
				rbClienteExistente.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						if(rbClienteExistente.isSelected()) {
							rbClienteNuevo.setSelected(false);
							panelClienteExistente.setVisible(true);
							panelClienteNuevo.setVisible(false);
						}
					}
				});
				rbClienteExistente.setSelected(true);
				rbClienteExistente.setBounds(140, 36, 135, 23);
				panelCliente.add(rbClienteExistente);
			}
			{
				rbClienteNuevo = new JRadioButton("Cliente nuevo");
				rbClienteNuevo.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						if(rbClienteNuevo.isSelected()) {
							rbClienteExistente.setSelected(false);
							panelClienteExistente.setVisible(false);
							panelClienteNuevo.setVisible(true);
						}
					}
				});
				rbClienteNuevo.setBounds(505, 36, 109, 23);
				panelCliente.add(rbClienteNuevo);
			}
		}
		{
			panelClienteExistente = new JPanel();
			panelClienteExistente.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
			panelClienteExistente.setBounds(10, 121, 774, 128);
			contentPanel.add(panelClienteExistente);
			panelClienteExistente.setLayout(null);
			{
				JLabel lblCedulaExistente = new JLabel(" C\u00E9dula:");
				lblCedulaExistente.setBounds(242, 42, 66, 14);
				panelClienteExistente.add(lblCedulaExistente);
			}
			{
				txtCedulaExistente = new JTextField();
				txtCedulaExistente.setBounds(302, 39, 170, 20);
				panelClienteExistente.add(txtCedulaExistente);
				txtCedulaExistente.setColumns(10);
			}
			{
				JButton btnBuscar = new JButton("Buscar");
				btnBuscar.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						auxCliente = Empresa.getInstance().buscarClienteByCedula(txtCedulaExistente.getText().toString());
						if(auxCliente != null) {
							txtNombreExistente.setText(auxCliente.getNombre());
							panelInfoCliente.setVisible(true);
						}
						else {
							txtNombreExistente.setText("");
							JOptionPane.showMessageDialog(null, "No se ha encontrado un cliente con esa cédula", "Advertencia", JOptionPane.WARNING_MESSAGE);
						}
					}
				});
				btnBuscar.setBounds(495, 38, 89, 23);
				panelClienteExistente.add(btnBuscar);
			}
			{
				panelInfoCliente = new JPanel();
				panelInfoCliente.setVisible(false);
				panelInfoCliente.setBounds(113, 67, 548, 48);
				panelClienteExistente.add(panelInfoCliente);
				panelInfoCliente.setLayout(null);
				{
					JLabel lblNombreExistente = new JLabel("Nombre:");
					lblNombreExistente.setBounds(10, 17, 70, 14);
					panelInfoCliente.add(lblNombreExistente);
				}
				{
					txtNombreExistente = new JTextField();
					txtNombreExistente.setEditable(false);
					txtNombreExistente.setBounds(64, 14, 360, 20);
					panelInfoCliente.add(txtNombreExistente);
					txtNombreExistente.setColumns(10);
					if(auxCliente != null) {
						txtNombreExistente.setText(auxCliente.getNombre());
					}
					else {
						txtNombreExistente.setText("");
					}
				}
				{
					JButton btnVerCliente = new JButton("Ver cliente");
					btnVerCliente.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e) {
							if(auxCliente != null) {
								VerCliente verCliente = new VerCliente(auxCliente);
								verCliente.setVisible(true);
							}
							else {
								JOptionPane.showMessageDialog(null, "No existe un cliente con esa cédula", "Advertencia", JOptionPane.WARNING_MESSAGE);
							}
						}
					});
					btnVerCliente.setBounds(434, 13, 104, 23);
					panelInfoCliente.add(btnVerCliente);
				}
			}
		}
		{
			panelClienteNuevo = new JPanel();
			panelClienteNuevo.setVisible(false);
			panelClienteNuevo.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
			panelClienteNuevo.setBounds(10, 121, 774, 128);
			contentPanel.add(panelClienteNuevo);
			panelClienteNuevo.setLayout(null);
			{
				JLabel lblNombreNuevo = new JLabel("Nombre:");
				lblNombreNuevo.setBounds(50, 20, 100, 14);
				panelClienteNuevo.add(lblNombreNuevo);
			}
			{
				txtNombreNuevo = new JTextField();
				txtNombreNuevo.setBounds(130, 17, 300, 20);
				panelClienteNuevo.add(txtNombreNuevo);
				txtNombreNuevo.setColumns(10);
			}
			{
				JLabel lblCedulaNuevo = new JLabel("C\u00E9dula:");
				lblCedulaNuevo.setBounds(470, 20, 100, 14);
				panelClienteNuevo.add(lblCedulaNuevo);
			}
			{
				txtCedulaNuevo = new JTextField();
				txtCedulaNuevo.setBounds(540, 17, 180, 20);
				panelClienteNuevo.add(txtCedulaNuevo);
				txtCedulaNuevo.setColumns(10);
			}
			{
				JLabel lblDireccionNuevo = new JLabel("Direcci\u00F3n:");
				lblDireccionNuevo.setBounds(50, 75, 100, 14);
				panelClienteNuevo.add(lblDireccionNuevo);
			}
			{
				txtDireccionNuevo = new JTextField();
				txtDireccionNuevo.setBounds(130, 72, 300, 20);
				panelClienteNuevo.add(txtDireccionNuevo);
				txtDireccionNuevo.setColumns(10);
			}
			{
				JLabel lblTelefono = new JLabel("Tel\u00E9fono:");
				lblTelefono.setBounds(470, 75, 100, 14);
				panelClienteNuevo.add(lblTelefono);
			}
			{
				txtTelefono = new JTextField();
				txtTelefono.setBounds(540, 72, 180, 20);
				panelClienteNuevo.add(txtTelefono);
				txtTelefono.setColumns(10);
			}
		}
		{
			JLabel lblTotalFactura = new JLabel("Total a pagar:");
			lblTotalFactura.setBounds(499, 463, 113, 14);
			contentPanel.add(lblTotalFactura);
		}
		{
			lblMontoTotal = new JLabel(df.format(montoTotalCarrito()));
			lblMontoTotal.setFont(new Font("Tahoma", Font.BOLD, 11));
			lblMontoTotal.setHorizontalAlignment(SwingConstants.RIGHT);
			lblMontoTotal.setBounds(650, 463, 105, 14);
			contentPanel.add(lblMontoTotal);
		}
		{
			JLabel lblCantArticulos = new JLabel("Cantidad de art\u00EDculos:");
			lblCantArticulos.setBounds(499, 438, 141, 14);
			contentPanel.add(lblCantArticulos);
		}
		{
			lblCantQuesos = new JLabel(Integer.toString(carrito.size()));
			lblCantQuesos.setHorizontalAlignment(SwingConstants.RIGHT);
			lblCantQuesos.setFont(new Font("Tahoma", Font.BOLD, 11));
			lblCantQuesos.setBounds(650, 438, 105, 14);
			contentPanel.add(lblCantQuesos);
		}
		{
			JLabel lblCantDisponible = new JLabel("Cantidad de quesos disponibles:");
			lblCantDisponible.setBounds(57, 438, 195, 14);
			contentPanel.add(lblCantDisponible);
		}
		{
			lblCantQuesosAlmacen = new JLabel(Integer.toString(almacen.size()));
			lblCantQuesosAlmacen.setFont(new Font("Tahoma", Font.BOLD, 11));
			lblCantQuesosAlmacen.setHorizontalAlignment(SwingConstants.RIGHT);
			lblCantQuesosAlmacen.setBounds(230, 438, 90, 14);
			contentPanel.add(lblCantQuesosAlmacen);
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton btnRealizarFactura = new JButton("Realizar factura");
				btnRealizarFactura.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						if(isCompleted()) {
							Factura factura = null;
							if(rbClienteNuevo.isSelected()) {
								auxCliente = new Cliente(txtNombreNuevo.getText().toString(), txtDireccionNuevo.getText().toString(), 
										txtTelefono.getText().toString(), txtCedulaNuevo.getText().toString());
								Empresa.getInstance().insertarCliente(auxCliente);
							}
							
							ArrayList<Queso> articulosFactura = new ArrayList<Queso>();
							articulosFactura = (ArrayList<Queso>)carrito.clone();
							eliminarQuesosDeAlmacen();
							factura = new Factura(articulosFactura,"F-" + Empresa.getInstance().getGeneradorCodigoFactura(), auxCliente);
							Empresa.getInstance().insertarFactura(factura);
							auxCliente.insertarFactura(factura);
							Empresa.getInstance().CrearArchivo(factura);
							Principal.backup(factura.getCodigo());
							clean();
							JOptionPane.showMessageDialog(null, "Compra realizada satisfactoriamente", "Información", JOptionPane.INFORMATION_MESSAGE);
						}
					}
				});
				btnRealizarFactura.setActionCommand("OK");
				buttonPane.add(btnRealizarFactura);
				getRootPane().setDefaultButton(btnRealizarFactura);
			}
			{
				JButton btnCancelar = new JButton("Cancelar");
				btnCancelar.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						dispose();
					}
				});
				btnCancelar.setActionCommand("Cancel");
				buttonPane.add(btnCancelar);
			}
		}
	}
	
	private void crearAlmacen() {
		for (Queso queso : Empresa.getInstance().getQuesos()) {
			if(queso.getEstado().equalsIgnoreCase("Disponible")) {
				almacen.add(queso);
			}
		}
	}
	
	private void clean() {
		rbClienteExistente.setSelected(true);
		rbClienteNuevo.setSelected(false);
		panelClienteExistente.setVisible(true);
		panelInfoCliente.setVisible(false);
		panelClienteNuevo.setVisible(false);
		txtCedulaExistente.setText("");
		txtNombreExistente.setText("");
		txtNombreNuevo.setText("");
		txtCedulaNuevo.setText("");
		txtDireccionNuevo.setText("");
		txtTelefono.setText("");
		loadQuesos();
		carrito.clear();
		loadCarrito();
		loadLabels();
	}
	
	private void loadQuesos() {
		modelAlmacen.setRowCount(0);
		row = new Object[modelAlmacen.getColumnCount()];
		for(Queso queso : almacen) {
			if(queso.getEstado().equalsIgnoreCase("Disponible")) {
				row[0] = queso.getCodigo();
				row[1] = df.format(queso.Volumen());
				row[2] = df.format(queso.precioTotal());	//El df.format se utilizó para que se redondee a 2 cifras decimales
				modelAlmacen.addRow(row);
			}
		}
	}
	
	private boolean isCompleted() {
		boolean flag = false;
		if(carrito.size() > 0) {
			if(rbClienteExistente.isSelected()) {
				if(auxCliente != null) {
					flag = true;
				}
				else {
					JOptionPane.showMessageDialog(null, "Debe escribir la cédula del cliente existente", "Advertencia", JOptionPane.WARNING_MESSAGE);
				}
			}
			if(rbClienteNuevo.isSelected()) {
				if(!txtNombreNuevo.getText().toString().equalsIgnoreCase("") && !txtCedulaNuevo.getText().toString().equalsIgnoreCase("")
						&& !txtDireccionNuevo.getText().toString().equalsIgnoreCase("") && !txtTelefono.getText().toString().equalsIgnoreCase("")) {
					flag = true;
				}
				else {
					JOptionPane.showMessageDialog(null, "Debe llenar todos los campos", "Advertencia", JOptionPane.WARNING_MESSAGE);
				}
			}
		}
		else {
			JOptionPane.showMessageDialog(null, "El carrito está vacío, por favor, coloque al menos un artículo", "Advertencia", JOptionPane.WARNING_MESSAGE);
		}
		return flag;
	}
	
	private void loadCarrito() {
		modelCarrito.setRowCount(0);
		row = new Object[modelCarrito.getColumnCount()];
		for (Queso queso : carrito) {
			row[0] = queso.getCodigo();
			row[1] = df.format(queso.Volumen());
			row[2] = df.format(queso.precioTotal());	//El df.format se utilizó para que se redondee a 2 cifras decimales
			modelCarrito.addRow(row);
		}
	}
	
	private void loadLabels() {
		lblMontoTotal.setText(df.format(montoTotalCarrito()));
		lblCantQuesos.setText(Integer.toString(carrito.size()));
		lblCantQuesosAlmacen.setText(Integer.toString(almacen.size()));
	}
	
	private int buscarIndiceEnAlmacen(Queso queso) {
		int i = 0;
		int index = -1;
		boolean encontrado = false;
		while(i < almacen.size() && !encontrado) {
			if(almacen.get(i).equals(queso)) {
				encontrado = true;
				index = i;
			}
			i++;
		}
		return index;
	}
	
	private int buscarIndiceEnCarrito(Queso queso) {
		int i = 0;
		int index = -1;
		boolean encontrado = false;
		while(i < carrito.size() && !encontrado) {
			if(carrito.get(i).equals(queso)) {
				encontrado = true;
				index = i;
			}
			i++;
		}
		return index;
	}
	
	private void eliminarQuesosDeAlmacen() {
		for(Queso queso : carrito) {
			queso.setEstado("Vendido");
		}
	}
	
	private float montoTotalCarrito() {
		float total = 0;
		for(Queso queso : carrito) {
			total += queso.precioTotal();
		}
		return total;
	}
}
