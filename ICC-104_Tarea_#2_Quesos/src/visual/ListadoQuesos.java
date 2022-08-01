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
import javax.swing.table.TableColumnModel;

import logical.Cliente;
import logical.Empresa;
import logical.Queso;
import logical.QuesoCilindrico;
import logical.QuesoCilindricoHueco;
import logical.QuesoEsferico;

import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.JScrollPane;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Toolkit;
import javax.swing.JRadioButton;
import javax.swing.border.TitledBorder;

public class ListadoQuesos extends JDialog {
//
	private static DecimalFormat df = new DecimalFormat("#.##");
	private final JPanel contentPanel = new JPanel();
	private JTable table;
	private static DefaultTableModel model;
	private static Object[] row;
	private Queso selected = null;
	private JButton btnVerQueso;
	private JRadioButton rbTodos;
	private JRadioButton rbDisponibles;
	private JRadioButton rbVendidos;

	public ListadoQuesos() {
		df.setRoundingMode(RoundingMode.CEILING);
		setIconImage(Toolkit.getDefaultToolkit().getImage(ListadoQuesos.class.getResource("/media/imgListado64px.png")));
		setTitle("Listado de Quesos");
		setResizable(false);
		setModal(true);
		setBounds(100, 100, 756, 314);
		setLocationRelativeTo(null);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			JPanel panelTabla = new JPanel();
			panelTabla.setBounds(5, 73, 724, 160);
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
								btnVerQueso.setEnabled(true);
							}
						}
					});
					table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
					model = new DefaultTableModel();
					String[] headers = {"Código", "Forma", "Volumen (cm^3)", "Costo Base", "Costo Unitario", "Costo Total", "Estado"};
					model.setColumnIdentifiers(headers);
					table.setModel(model);
					scrollPane.setViewportView(table);
				}
			}
			{
				JPanel panelFiltro = new JPanel();
				panelFiltro.setBounds(5, 11, 724, 51);
				contentPanel.add(panelFiltro);
				panelFiltro.setLayout(null);
				
				rbTodos = new JRadioButton("Todos");
				rbTodos.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						if(rbTodos.isSelected()) {
							rbDisponibles.setSelected(false);
							rbVendidos.setSelected(false);
							loadQuesos();
						}
					}
				});
				rbTodos.setSelected(true);
				rbTodos.setBounds(98, 14, 109, 23);
				panelFiltro.add(rbTodos);

				
				rbDisponibles = new JRadioButton("Disponibles");
				rbDisponibles.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						if(rbDisponibles.isSelected()) {
							rbTodos.setSelected(false);
							rbVendidos.setSelected(false);
							loadQuesos();
						}
					}
				});
				rbDisponibles.setBounds(307, 14, 109, 23);
				panelFiltro.add(rbDisponibles);
				
				rbVendidos = new JRadioButton("Vendidos");
				rbVendidos.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						if(rbVendidos.isSelected()) {
							rbTodos.setSelected(false);
							rbDisponibles.setSelected(false);
							loadQuesos();
						}
					}
				});
				rbVendidos.setBounds(516, 14, 109, 23);
				panelFiltro.add(rbVendidos);

			}
			loadQuesos();
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				btnVerQueso = new JButton("Ver queso");
				btnVerQueso.setEnabled(false);
				btnVerQueso.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						VerQueso verQueso = new VerQueso(selected);
						verQueso.setVisible(true);
					}
				});
				btnVerQueso.setActionCommand("OK");
				buttonPane.add(btnVerQueso);
				getRootPane().setDefaultButton(btnVerQueso);
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
	public void loadQuesos() {
		ArrayList<Queso> listado = new ArrayList<Queso>();
		listado.clear();
		if(rbTodos.isSelected()) {
			listado = Empresa.getInstance().getQuesos();
		}
		if(rbDisponibles.isSelected()) {
			for(Queso queso : Empresa.getInstance().getQuesos()) {
				if(queso.getEstado().equalsIgnoreCase("Disponible")) {
					listado.add(queso);
				}
			}
		}
		if(rbVendidos.isSelected()) {
			for(Queso queso : Empresa.getInstance().getQuesos()) {
				if(queso.getEstado().equalsIgnoreCase("Vendido")) {
					listado.add(queso);
				}
			}
		}
		model.setRowCount(0);
		row = new Object[model.getColumnCount()];
		for (Queso queso : listado) {
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
			row[3] = df.format(queso.getCostoBase());
			row[4] = df.format(queso.getCostoUnitario());
			row[5] = df.format(queso.precioTotal());
			row[6] = queso.getEstado().toString();
			model.addRow(row);
		}

		
	}
}
