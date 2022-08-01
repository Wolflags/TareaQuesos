package visual;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.math.RoundingMode;
import java.text.DecimalFormat;

import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import logical.Empresa;
import logical.Factura;
import logical.Queso;
import logical.QuesoCilindrico;
import logical.QuesoCilindricoHueco;
import logical.QuesoEsferico;
import sun.util.locale.provider.AuxLocaleProviderAdapter;

import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;

import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.ImageIcon;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class VerFactura extends JDialog {

	private static DecimalFormat df = new DecimalFormat("#.##");
	private final JPanel contentPanel = new JPanel();
	private JTextField txtCodigo;
	private JTable table;
	private static DefaultTableModel model;
	private static Object[] row;
	private Queso selected = null;
	private Factura auxFactura = null;
	private JButton btnVerArticulo;

	public VerFactura(Factura factura) {
		auxFactura = factura;
		df.setRoundingMode(RoundingMode.CEILING);
		setIconImage(Toolkit.getDefaultToolkit().getImage(VerFactura.class.getResource("/media/imgFactura32pxN.png")));
		setTitle("Ver factura");
		setResizable(false);
		setModal(true);
		setBounds(100, 100, 615, 400);
		setLocationRelativeTo(null);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			JPanel panelInfo = new JPanel();
			panelInfo.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
			panelInfo.setBounds(10, 11, 574, 116);
			contentPanel.add(panelInfo);
			panelInfo.setLayout(null);
			{
				JLabel lblCodigo = new JLabel("C\u00F3digo:");
				lblCodigo.setBounds(196, 28, 69, 14);
				panelInfo.add(lblCodigo);
			}
			{
				txtCodigo = new JTextField();
				txtCodigo.setEditable(false);
				txtCodigo.setBounds(345, 25, 170, 20);
				panelInfo.add(txtCodigo);
				txtCodigo.setColumns(10);
				txtCodigo.setText(auxFactura.getCodigo());
			}
			{
				JLabel lblCantArticulos = new JLabel("Cantidad de art\u00EDculos:");
				lblCantArticulos.setBounds(196, 53, 145, 14);
				panelInfo.add(lblCantArticulos);
			}
			{
				JLabel lblValorCantArticulos = new JLabel(Integer.toString(auxFactura.cantArticulos()));
				lblValorCantArticulos.setHorizontalAlignment(SwingConstants.RIGHT);
				lblValorCantArticulos.setFont(new Font("Tahoma", Font.BOLD, 11));
				lblValorCantArticulos.setBounds(446, 53, 69, 14);
				panelInfo.add(lblValorCantArticulos);
			}
			{
				JLabel lblSubtotal = new JLabel("Subtotal:");
				lblSubtotal.setBounds(196, 78, 145, 14);
				panelInfo.add(lblSubtotal);
			}
			{
				JLabel lblValorSubtotal = new JLabel("RD$" + df.format(auxFactura.getTotal()));
				lblValorSubtotal.setHorizontalAlignment(SwingConstants.RIGHT);
				lblValorSubtotal.setFont(new Font("Tahoma", Font.BOLD, 11));
				lblValorSubtotal.setBounds(446, 78, 69, 14);
				panelInfo.add(lblValorSubtotal);
			}
			{
				JLabel lblImagenFactura = new JLabel("");
				lblImagenFactura.setIcon(new ImageIcon(VerFactura.class.getResource("/media/imgFactura64px.png")));
				lblImagenFactura.setBounds(31, 15, 127, 77);
				panelInfo.add(lblImagenFactura);
			}
		}
		{
			JLabel lblArticulosCompra = new JLabel("Art\u00EDculos de la compra:");
			lblArticulosCompra.setBounds(10, 138, 150, 14);
			contentPanel.add(lblArticulosCompra);
		}
		{
			JPanel panelTabla = new JPanel();
			panelTabla.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
			panelTabla.setBounds(10, 163, 574, 164);
			contentPanel.add(panelTabla);
			panelTabla.setLayout(new BorderLayout(0, 0));
			{
				JScrollPane scrollPane = new JScrollPane();
				panelTabla.add(scrollPane, BorderLayout.CENTER);
				{
					table = new JTable();
					table.addMouseListener(new MouseAdapter() {
						@Override
						public void mouseClicked(MouseEvent e) {
							int index = table.getSelectedRow();
							if(index >= 0) {
								String codQueso = table.getValueAt(index, 0).toString();
								selected = Empresa.getInstance().buscarQuesoByCodigo(codQueso);
								btnVerArticulo.setEnabled(true);
							}
						}
					});
					table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
					model = new DefaultTableModel();
					String[] headers = {"Código", "Forma", "Volumen (cm^3)", "Precio"};
					model.setColumnIdentifiers(headers);
					table.setModel(model);
					scrollPane.setViewportView(table);
				}
			}
			loadQuesos();
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				btnVerArticulo = new JButton("Ver art\u00EDculo");
				btnVerArticulo.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						VerQueso verQueso = new VerQueso(selected);
						verQueso.setVisible(true);
					}
				});
				btnVerArticulo.setEnabled(false);
				btnVerArticulo.setActionCommand("OK");
				buttonPane.add(btnVerArticulo);
				getRootPane().setDefaultButton(btnVerArticulo);
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
	
	public  void loadQuesos() {
		model.setRowCount(0);
		row = new Object[model.getColumnCount()];
		for (Queso queso : auxFactura.getQuesos()) {
			row[0] = queso.getCodigo().toString();
			if(queso instanceof QuesoEsferico) {
				row[1] = "Esférico";
			}
			if(queso instanceof QuesoCilindrico) {
				row[1] = "Cilíndrico";
			}
			if(queso instanceof QuesoCilindricoHueco) {
				row[1] = "Cilíndrico Hueco";
			}
			row[2] = df.format(queso.Volumen());
			row[3] = df.format(queso.precioTotal());
			model.addRow(row);
		}
	}

}
