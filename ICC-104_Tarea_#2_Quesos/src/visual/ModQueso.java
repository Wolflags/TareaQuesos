package visual;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import logical.Queso;
import java.awt.Toolkit;
import javax.swing.border.TitledBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.ImageIcon;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ModQueso extends JDialog {
//
	private final JPanel contentPanel = new JPanel();
	private Queso auxQueso = null;
	private JSpinner spnPrecioUnitario;
	private JSpinner spnPrecioBase;

	public ModQueso(Queso queso) {
		setIconImage(Toolkit.getDefaultToolkit().getImage(ModQueso.class.getResource("/media/imgQueso32pxN.png")));
		setTitle("Modificar queso");
		setModal(true);
		setResizable(false);
		auxQueso = queso;
		setBounds(100, 100, 528, 247);
		setLocationRelativeTo(null);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new BorderLayout(0, 0));
		{
			JPanel panel = new JPanel();
			panel.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
			contentPanel.add(panel, BorderLayout.CENTER);
			panel.setLayout(null);
			{
				JLabel lblImagenQueso = new JLabel("");
				lblImagenQueso.setIcon(new ImageIcon(ModQueso.class.getResource("/media/imgQueso128pxB.png")));
				lblImagenQueso.setBounds(10, 11, 189, 139);
				panel.add(lblImagenQueso);
			}
			{
				JLabel lblPrecioBase = new JLabel("Precio base:");
				lblPrecioBase.setBounds(191, 54, 102, 14);
				panel.add(lblPrecioBase);
			}
			{
				spnPrecioBase = new JSpinner();
				spnPrecioBase.setModel(new SpinnerNumberModel(new Float(0), new Float(0), null, new Float(5)));
				spnPrecioBase.setBounds(303, 51, 170, 20);
				panel.add(spnPrecioBase);
				spnPrecioBase.setValue(auxQueso.getCostoBase());
			}
			{
				JLabel lblPrecioUnitario = new JLabel("Precio unitario:");
				lblPrecioUnitario.setBounds(191, 85, 102, 14);
				panel.add(lblPrecioUnitario);
			}
			{
				spnPrecioUnitario = new JSpinner();
				spnPrecioUnitario.setModel(new SpinnerNumberModel(new Float(0), new Float(0), null, new Float(5)));
				spnPrecioUnitario.setBounds(303, 82, 170, 20);
				panel.add(spnPrecioUnitario);
				spnPrecioUnitario.setValue(auxQueso.getCostoUnitario());
			}
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton btnModificar = new JButton("Modificar");
				btnModificar.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						if((float)spnPrecioBase.getValue() != 0 && (float)spnPrecioUnitario.getValue() != 0) {
							auxQueso.setCostoBase((float)spnPrecioBase.getValue());
							auxQueso.setCostoUnitario((float) spnPrecioUnitario.getValue());
							JOptionPane.showMessageDialog(null, "Se ha modificado el queso de manera exitosa", "Información", JOptionPane.INFORMATION_MESSAGE);
							dispose();
						}
						else {
							JOptionPane.showMessageDialog(null, "Ingrese valores reales", "Advertencia", JOptionPane.WARNING_MESSAGE);
						}
					}
				});
				btnModificar.setActionCommand("OK");
				buttonPane.add(btnModificar);
				getRootPane().setDefaultButton(btnModificar);
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

}
