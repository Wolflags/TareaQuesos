package visual;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.math.RoundingMode;
import java.text.DecimalFormat;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import logical.Cliente;
import logical.Empresa;
import logical.Queso;
import logical.QuesoCilindrico;
import logical.QuesoCilindricoHueco;
import logical.QuesoEsferico;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.JTable;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ListadoClientes extends JDialog {

	private static DecimalFormat df = new DecimalFormat("#.##");
	private final JPanel contentPanel = new JPanel();
	private JTextField txtCedula;
	private JButton btnBuscar;
	private JTable table;
	private static DefaultTableModel model;
	private static Object[] row;
	private Cliente selected = null;
	private Cliente auxCliente = null;
	private JButton btnVerCliente;

	public ListadoClientes() {
		df.setRoundingMode(RoundingMode.CEILING);
		setTitle("Listado de clientes");
		setResizable(false);
		setModal(true);
		setBounds(100, 100, 619, 358);
		setLocationRelativeTo(null);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			JPanel panelBuscar = new JPanel();
			panelBuscar.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
			panelBuscar.setBounds(10, 11, 586, 60);
			contentPanel.add(panelBuscar);
			panelBuscar.setLayout(null);
			{
				JLabel lblBuscarCliente = new JLabel("C\u00E9dula:");
				lblBuscarCliente.setBounds(128, 22, 77, 14);
				panelBuscar.add(lblBuscarCliente);
			}
			{
				txtCedula = new JTextField();
				txtCedula.setBounds(208, 19, 170, 20);
				panelBuscar.add(txtCedula);
				txtCedula.setColumns(10);
			}
			{
				btnBuscar = new JButton("Buscar");
				btnBuscar.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						selected = Empresa.getInstance().buscarClienteByCedula(txtCedula.getText().toString());
						if(selected != null) {
							loadClienteBuscado(selected.getCedula());
						}
						else {
							JOptionPane.showMessageDialog(null, "No se ha encontrado un cliente con esa cédula", "Advertencia", JOptionPane.WARNING_MESSAGE);
						}
					}
				});
				btnBuscar.setBounds(388, 18, 89, 23);
				panelBuscar.add(btnBuscar);
			}
		}
		{
			JPanel panelTabla = new JPanel();
			panelTabla.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
			panelTabla.setBounds(10, 82, 586, 204);
			contentPanel.add(panelTabla);
			panelTabla.setLayout(new BorderLayout(0, 0));
			
			JScrollPane scrollPane = new JScrollPane();
			scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
			panelTabla.add(scrollPane, BorderLayout.CENTER);
			
			{
				table = new JTable();
				table.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseClicked(MouseEvent e) {
						int index = table.getSelectedRow();
						if(index >= 0) {
							String cedula = table.getValueAt(index, 0).toString();
							selected = Empresa.getInstance().buscarClienteByCedula(cedula);
							btnVerCliente.setEnabled(true);
						}
					}
				});
				table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
				model = new DefaultTableModel();
				String[] headers = {"Cédula", "Nombre", "Cantidad de facturas", "Total en facturas"};
				model.setColumnIdentifiers(headers);
				table.setModel(model);
				scrollPane.setViewportView(table);
			}
		}
		loadClientes();
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				btnVerCliente = new JButton("Ver cliente");
				btnVerCliente.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						dispose();
						VerCliente verCliente = new VerCliente(selected);
						verCliente.setVisible(true);
					}
				});
				btnVerCliente.setEnabled(false);
				btnVerCliente.setActionCommand("OK");
				buttonPane.add(btnVerCliente);
				getRootPane().setDefaultButton(btnVerCliente);
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
	
	public static void loadClientes() {
		model.setRowCount(0);
		row = new Object[model.getColumnCount()];
		for (Cliente cliente : Empresa.getInstance().getClientes()) {
			row[0] = cliente.getCedula().toString();
			row[1] = cliente.getNombre().toString();
			row[2] = Integer.toString(cliente.getFacturas().size());
			row[3] = df.format(Empresa.getInstance().montoTotalEnCliente(cliente.getCedula()));
			model.addRow(row);
		}
	}
	
	public static void loadClienteBuscado(String cedula) {
		model.setRowCount(0);
		row = new Object[model.getColumnCount()];
		Cliente aux = null;
		aux = Empresa.getInstance().buscarClienteByCedula(cedula);
		if(aux != null) {
			row[0] = aux.getCedula().toString();
			row[1] = aux.getNombre().toString();
			row[2] = Integer.toString(aux.getFacturas().size());
			row[3] = df.format(Empresa.getInstance().montoTotalEnCliente(aux.getCedula()));
			model.addRow(row);
		}
	}
	
}
