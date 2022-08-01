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
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import logical.Cliente;
import logical.Empresa;
import logical.Factura;
import logical.Queso;
import logical.QuesoCilindrico;
import logical.QuesoCilindricoHueco;
import logical.QuesoEsferico;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Toolkit;

public class ListadoFacturas extends JDialog {
//
	private static DecimalFormat df = new DecimalFormat("#.##");
	private final JPanel contentPanel = new JPanel();
	private JTable table;
	private static DefaultTableModel model;
	private static Object[] row;
	private Factura selected = null;
	private Cliente auxCliente = null;
	private JButton btnVerFactura;

	public ListadoFacturas(Cliente cliente) {
		setIconImage(Toolkit.getDefaultToolkit().getImage(ListadoFacturas.class.getResource("/media/imgBuscar32pxN.png")));
		auxCliente = cliente;
		df.setRoundingMode(RoundingMode.CEILING);
		if(auxCliente != null) {
			setTitle("Listado de facturas (" + auxCliente.getNombre() + ")");
		}
		else {
			setTitle("Listado de facturas");
		}
		setModal(true);
		setResizable(false);
		setBounds(100, 100, 450, 300);
		setLocationRelativeTo(null);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new BorderLayout(0, 0));
		{
			JPanel panel = new JPanel();
			panel.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
			contentPanel.add(panel, BorderLayout.CENTER);
			panel.setLayout(new BorderLayout(0, 0));
			{
				JScrollPane scrollPane = new JScrollPane();
				panel.add(scrollPane, BorderLayout.CENTER);
				{
					table = new JTable();
					table.addMouseListener(new MouseAdapter() {
						@Override
						public void mouseClicked(MouseEvent e) {
							int index = table.getSelectedRow();
							if(index >= 0) {
								String codFactura = table.getValueAt(index, 0).toString();
								selected = Empresa.getInstance().buscarFacturaByCodigo(codFactura);
								btnVerFactura.setEnabled(true);
							}
						}
					});
					table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
					model = new DefaultTableModel();
					String[] headers = {"Código", "Cantidad de artículos", "Monto total"};
					model.setColumnIdentifiers(headers);
					table.setModel(model);
					
					scrollPane.setViewportView(table);
				}
			}
			loadFacturas();
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				btnVerFactura = new JButton("Ver factura");
				btnVerFactura.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						VerFactura verFactura = new VerFactura(selected);
						verFactura.setVisible(true);
					}
				});
				btnVerFactura.setEnabled(false);
				btnVerFactura.setActionCommand("OK");
				buttonPane.add(btnVerFactura);
				getRootPane().setDefaultButton(btnVerFactura);
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
	
	private void loadFacturas() {
		model.setRowCount(0);
		row = new Object[model.getColumnCount()];
		if(auxCliente != null) {
			for (Factura factura : auxCliente.getFacturas()) {
				row[0] = factura.getCodigo().toString();
				row[1] = Integer.toString(factura.cantArticulos());
				row[2] = df.format(factura.getTotal());
				model.
				addRow(row);
			}
		}
		else {
			for (Factura factura : Empresa.getInstance().getFacturas()) {
				row[0] = factura.getCodigo().toString();
				row[1] = Integer.toString(factura.cantArticulos());
				row[2] = df.format(factura.getTotal());
				model.
				addRow(row);
			}
		}
	}
}
