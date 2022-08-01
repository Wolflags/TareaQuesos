package visual;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import java.awt.Toolkit;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;

import logical.Cliente;
import logical.Empresa;

import java.awt.event.ActionListener;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.awt.event.ActionEvent;
import java.awt.Font;
import javax.swing.SwingConstants;

public class VerCliente extends JDialog {

	private static DecimalFormat df = new DecimalFormat("#.##");
	private final JPanel contentPanel = new JPanel();
	private JTextField txtNombre;
	private JTextField txtCedula;
	private Cliente auxCliente = null;
	private JTextField txtDireccion;
	private JTextField txtTelefono;

	public VerCliente(Cliente cliente) {
		df.setRoundingMode(RoundingMode.CEILING);
		auxCliente = cliente;
		setIconImage(Toolkit.getDefaultToolkit().getImage(VerCliente.class.getResource("/media/imgCliente32px.png")));
		setTitle("Perfil de cliente (" + auxCliente.getNombre() + ")");
		setModal(true);
		setResizable(false);
		setBounds(100, 100, 571, 373);
		setLocationRelativeTo(null);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			JPanel panelInfoGeneral = new JPanel();
			panelInfoGeneral.setBounds(5, 5, 546, 158);
			panelInfoGeneral.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
			contentPanel.add(panelInfoGeneral);
			panelInfoGeneral.setLayout(null);
			{
				JLabel imgCliente = new JLabel("");
				imgCliente.setIcon(new ImageIcon(VerCliente.class.getResource("/media/imgCliente128px.png")));
				imgCliente.setBounds(10, 11, 134, 134);
				panelInfoGeneral.add(imgCliente);
			}
			{
				JLabel lblNombre = new JLabel("Nombre:");
				lblNombre.setBounds(150, 31, 62, 14);
				panelInfoGeneral.add(lblNombre);
			}
			{
				txtNombre = new JTextField();
				txtNombre.setEditable(false);
				txtNombre.setBounds(222, 28, 300, 20);
				panelInfoGeneral.add(txtNombre);
				txtNombre.setColumns(10);
				txtNombre.setText(auxCliente.getNombre());
			}
			{
				JLabel lblCedula = new JLabel("C\u00E9dula:");
				lblCedula.setBounds(150, 59, 62, 14);
				panelInfoGeneral.add(lblCedula);
			}
			{
				txtCedula = new JTextField();
				txtCedula.setEditable(false);
				txtCedula.setBounds(222, 56, 300, 20);
				panelInfoGeneral.add(txtCedula);
				txtCedula.setColumns(10);
				txtCedula.setText(auxCliente.getCedula());
			}
			
			JLabel lblDireccion = new JLabel("Direcci\u00F3n:");
			lblDireccion.setBounds(150, 87, 62, 14);
			panelInfoGeneral.add(lblDireccion);
			
			txtDireccion = new JTextField();
			txtDireccion.setText((String) null);
			txtDireccion.setEditable(false);
			txtDireccion.setColumns(10);
			txtDireccion.setBounds(222, 84, 300, 20);
			panelInfoGeneral.add(txtDireccion);
			txtDireccion.setText(auxCliente.getDireccion());
			{
				JLabel lblTelefono = new JLabel("Tel\u00E9fono:");
				lblTelefono.setBounds(150, 115, 62, 14);
				panelInfoGeneral.add(lblTelefono);
			}
			{
				txtTelefono = new JTextField();
				txtTelefono.setText((String) null);
				txtTelefono.setEditable(false);
				txtTelefono.setColumns(10);
				txtTelefono.setBounds(222, 112, 300, 20);
				panelInfoGeneral.add(txtTelefono);
				txtTelefono.setText(auxCliente.getTelefono());
			}
		}
		
		JPanel panelInfoCompras = new JPanel();
		panelInfoCompras.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panelInfoCompras.setBounds(5, 173, 268, 117);
		contentPanel.add(panelInfoCompras);
		panelInfoCompras.setLayout(null);
		{
			JLabel lblComprasRealizadas = new JLabel("Cantidad de compras realizadas:");
			lblComprasRealizadas.setBounds(10, 11, 205, 14);
			panelInfoCompras.add(lblComprasRealizadas);
		}
		{
			JLabel lblCantCompras = new JLabel(Integer.toString(auxCliente.getFacturas().size()));
			lblCantCompras.setHorizontalAlignment(SwingConstants.RIGHT);
			lblCantCompras.setFont(new Font("Tahoma", Font.BOLD, 11));
			lblCantCompras.setBounds(199, 11, 58, 14);
			panelInfoCompras.add(lblCantCompras);
		}
		{
			JLabel lblTotalFacturas = new JLabel("Total en facturas:");
			lblTotalFacturas.setBounds(10, 36, 135, 14);
			panelInfoCompras.add(lblTotalFacturas);
		}
		{
			JLabel lblDineroTotal = new JLabel(df.format(Empresa.getInstance().montoTotalEnCliente(auxCliente.getCedula())));
			lblDineroTotal.setHorizontalAlignment(SwingConstants.RIGHT);
			lblDineroTotal.setFont(new Font("Tahoma", Font.BOLD, 11));
			lblDineroTotal.setBounds(199, 36, 58, 14);
			panelInfoCompras.add(lblDineroTotal);
		}
		{
			JPanel panelBotones = new JPanel();
			panelBotones.setLayout(null);
			panelBotones.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
			panelBotones.setBounds(283, 174, 268, 116);
			contentPanel.add(panelBotones);
			
			JButton btnVerFacturas = new JButton("    Ver facturas");
			btnVerFacturas.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					ListadoFacturas listFacturas = new ListadoFacturas(auxCliente);
					listFacturas.setVisible(true);
				}
			});
			btnVerFacturas.setIcon(new ImageIcon(VerCliente.class.getResource("/media/imgBuscar32px.png")));
			btnVerFacturas.setHorizontalAlignment(SwingConstants.LEFT);
			btnVerFacturas.setBounds(36, 11, 196, 41);
			panelBotones.add(btnVerFacturas);
			
			JButton brnModificar = new JButton("    Modificar");
			brnModificar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					dispose();
					ModCliente modCliente = new ModCliente(auxCliente);
					modCliente.setVisible(true);
				}
			});
			brnModificar.setIcon(new ImageIcon(VerCliente.class.getResource("/media/imgModificarCliente32pxB.png")));
			brnModificar.setHorizontalAlignment(SwingConstants.LEFT);
			brnModificar.setBounds(36, 63, 196, 41);
			panelBotones.add(brnModificar);
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton btnModificar = new JButton("Modificar");
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
