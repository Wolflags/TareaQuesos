package visual;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;

import logical.Cliente;
import logical.Empresa;

public class ModCliente extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField txtNombre;
	private JTextField txtCedula;
	private JTextField txtDireccion;
	private JTextField txtTelefono;
	private Cliente auxCliente = null;

	public ModCliente(Cliente cliente) {
		auxCliente = cliente;
		setIconImage(Toolkit.getDefaultToolkit().getImage(ModCliente.class.getResource("/media/imgModificarCliente32pxN.png")));
		setTitle("Modificar cliente");
		setResizable(false);
		setModal(true);
		setBounds(100, 100, 575, 253);
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
				JLabel imgRegCliente = new JLabel("");
				imgRegCliente.setIcon(new ImageIcon(ModCliente.class.getResource("/media/imgModificarCliente128pxB.png")));
				imgRegCliente.setBounds(10, 11, 154, 144);
				panel.add(imgRegCliente);
			}
			{
				JLabel lblNombre = new JLabel("Nombre:");
				lblNombre.setBounds(154, 30, 73, 14);
				panel.add(lblNombre);
			}
			{
				txtNombre = new JTextField();
				txtNombre.setBounds(237, 27, 300, 20);
				panel.add(txtNombre);
				txtNombre.setColumns(10);
			}
			{
				JLabel lblCedula = new JLabel("C\u00E9dula:");
				lblCedula.setBounds(154, 58, 73, 14);
				panel.add(lblCedula);
			}
			{
				txtCedula = new JTextField();
				txtCedula.setColumns(10);
				txtCedula.setBounds(237, 55, 300, 20);
				panel.add(txtCedula);
				txtCedula.setEnabled(false);
				txtCedula.setText(auxCliente.getCedula());
			}
			{
				JLabel lblDireccion = new JLabel("Direcci\u00F3n:");
				lblDireccion.setBounds(154, 86, 73, 14);
				panel.add(lblDireccion);
			}
			{
				txtDireccion = new JTextField();
				txtDireccion.setColumns(10);
				txtDireccion.setBounds(237, 83, 300, 20);
				panel.add(txtDireccion);
			}
			{
				JLabel lblTelefono = new JLabel("Tel\u00E9fono:");
				lblTelefono.setBounds(154, 114, 73, 14);
				panel.add(lblTelefono);
			}
			{
				txtTelefono = new JTextField();
				txtTelefono.setColumns(10);
				txtTelefono.setBounds(237, 111, 300, 20);
				panel.add(txtTelefono);
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
						if(isCompleted()) {
							auxCliente.setNombre(txtNombre.getText().toString());
							auxCliente.setDireccion(txtDireccion.getText().toString());
							auxCliente.setTelefono(txtTelefono.getText().toString());
							clean();
							JOptionPane.showMessageDialog(null, "Se ha modificado el cliente de manera satisfactoria", "Información", JOptionPane.INFORMATION_MESSAGE);
							dispose();
							VerCliente verCliente = new VerCliente(auxCliente);
							verCliente.setVisible(true);
						}
						else {
							JOptionPane.showMessageDialog(null, "Debe llenar todos los campos", "Advertencia", JOptionPane.WARNING_MESSAGE);
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
	
	private void clean() {
		txtNombre.setText("");
		txtDireccion.setText("");
		txtTelefono.setText("");
	}
	
	private boolean isCompleted() {
		boolean flag = false;
		if(!txtNombre.getText().toString().equalsIgnoreCase("") 
				&& !txtDireccion.getText().toString().equalsIgnoreCase("") 
				&& !txtTelefono.getText().toString().equalsIgnoreCase("")) {
			flag = true;
		}
		return flag;
	}

}
