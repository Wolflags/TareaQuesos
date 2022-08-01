package visual;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import logical.Empresa;
import logical.Queso;
import logical.QuesoCilindrico;
import logical.QuesoCilindricoHueco;
import logical.QuesoEsferico;

import java.awt.Toolkit;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import javax.swing.JRadioButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class RegistrarQueso extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField txtCodigo;
	private JRadioButton rbEsferico;
	private JRadioButton rbCilindrico;
	private JRadioButton rbCilindricoHueco;
	private JPanel panelEsferico;
	private JPanel panelCilindrico;
	private JPanel panelCilindricoHueco;

	public RegistrarQueso() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(RegistrarQueso.class.getResource("/media/imgQueso64px.png")));
		setResizable(false);
		setModal(true);
		setTitle("Registrar Queso");
		setBounds(100, 100, 619, 425);
		setLocationRelativeTo(null);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		JPanel panelGeneral = new JPanel();
		panelGeneral.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panelGeneral.setBounds(10, 30, 582, 122);
		contentPanel.add(panelGeneral);
		panelGeneral.setLayout(null);
		
		JLabel lblInfoGenerales = new JLabel("Informaciones generales:");
		lblInfoGenerales.setBounds(10, 11, 170, 14);
		panelGeneral.add(lblInfoGenerales);
		
		JLabel lblCodigo = new JLabel("C\u00F3digo:");
		lblCodigo.setBounds(10, 36, 57, 14);
		panelGeneral.add(lblCodigo);
		
		txtCodigo = new JTextField();
		txtCodigo.setEditable(false);
		txtCodigo.setBounds(114, 36, 170, 20);
		txtCodigo.setText("Q-" + Empresa.generadorCodigoQueso);
		panelGeneral.add(txtCodigo);
		txtCodigo.setColumns(10);
		
		JLabel lblPrecioBase = new JLabel("Precio base:");
		lblPrecioBase.setBounds(306, 36, 94, 14);
		panelGeneral.add(lblPrecioBase);
		
		JSpinner spnPrecioBase = new JSpinner();
		spnPrecioBase.setModel(new SpinnerNumberModel(new Float(0), new Float(0), null, new Float(5)));
		spnPrecioBase.setBounds(394, 33, 170, 20);
		panelGeneral.add(spnPrecioBase);
		
		JLabel lblPrecioUnitario = new JLabel("Precio unitario:");
		lblPrecioUnitario.setBounds(10, 77, 94, 14);
		panelGeneral.add(lblPrecioUnitario);
		
		JSpinner spnPrecioUnitario = new JSpinner();
		spnPrecioUnitario.setModel(new SpinnerNumberModel(new Float(0), new Float(0), null, new Float(5)));
		spnPrecioUnitario.setBounds(114, 77, 170, 20);
		panelGeneral.add(spnPrecioUnitario);
		
		JPanel panelTipo = new JPanel();
		panelTipo.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panelTipo.setBounds(10, 163, 582, 88);
		contentPanel.add(panelTipo);
		panelTipo.setLayout(null);
		
		JLabel lblFormaQueso = new JLabel("Forma del queso:");
		lblFormaQueso.setBounds(10, 11, 120, 14);
		panelTipo.add(lblFormaQueso);
		
		rbEsferico = new JRadioButton("Esf\u00E9rico");
		rbEsferico.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(rbEsferico.isSelected()) {
					//rbEsferico.setSelected(true);
					rbCilindrico.setSelected(false);
					rbCilindricoHueco.setSelected(false);
					panelEsferico.setVisible(true);
					panelCilindrico.setVisible(false);
				}
			}
		});
		rbEsferico.setSelected(true);
		rbEsferico.setBounds(67, 42, 109, 23);
		panelTipo.add(rbEsferico);
		
		rbCilindrico = new JRadioButton("Cil\u00EDndrico");
		rbCilindrico.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(rbCilindrico.isSelected()) {
					rbEsferico.setSelected(false);
					rbCilindricoHueco.setSelected(false);
					panelCilindrico.setVisible(true);
					panelEsferico.setVisible(false);
				}
			}
		});
		rbCilindrico.setBounds(242, 42, 98, 23);
		panelTipo.add(rbCilindrico);
		
		rbCilindricoHueco = new JRadioButton("Cil\u00EDndrico Hueco");
		rbCilindricoHueco.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(rbCilindricoHueco.isSelected()) {
					rbEsferico.setSelected(false);
					rbCilindrico.setSelected(false);
					panelCilindricoHueco.setVisible(true);
					panelEsferico.setVisible(false);
					panelCilindrico.setVisible(false);
				}
			}
		});
		rbCilindricoHueco.setBounds(396, 42, 136, 23);
		panelTipo.add(rbCilindricoHueco);
		
		panelEsferico = new JPanel();
		panelEsferico.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panelEsferico.setBounds(10, 262, 582, 80);
		contentPanel.add(panelEsferico);
		panelEsferico.setLayout(null);
		
		JLabel lblRadioEsferico = new JLabel("Radio (cm):");
		lblRadioEsferico.setBounds(10, 14, 75, 14);
		panelEsferico.add(lblRadioEsferico);
		
		JSpinner spnRadioEsferico = new JSpinner();
		spnRadioEsferico.setModel(new SpinnerNumberModel(new Float(0), new Float(0), null, new Float(1)));
		spnRadioEsferico.setBounds(114, 11, 170, 20);
		panelEsferico.add(spnRadioEsferico);
		
		panelCilindrico = new JPanel();
		panelCilindrico.setVisible(false);
		panelCilindrico.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panelCilindrico.setBounds(10, 262, 582, 80);
		contentPanel.add(panelCilindrico);
		panelCilindrico.setLayout(null);
		
		JLabel lblRadioCilindrico = new JLabel("Radio (cm):");
		lblRadioCilindrico.setBounds(10, 14, 75, 14);
		panelCilindrico.add(lblRadioCilindrico);
		
		JSpinner spnRadioCilindrico = new JSpinner();
		spnRadioCilindrico.setModel(new SpinnerNumberModel(new Float(0), new Float(0), null, new Float(1)));
		spnRadioCilindrico.setBounds(114, 11, 170, 20);
		panelCilindrico.add(spnRadioCilindrico);
		
		JLabel lblLongitudCilindrica = new JLabel("Longitud (cm):");
		lblLongitudCilindrica.setBounds(306, 14, 95, 14);
		panelCilindrico.add(lblLongitudCilindrica);
		
		JSpinner spnLongitudCilindrica = new JSpinner();
		spnLongitudCilindrica.setModel(new SpinnerNumberModel(new Float(0), new Float(0), null, new Float(1)));
		spnLongitudCilindrica.setBounds(394, 11, 170, 20);
		panelCilindrico.add(spnLongitudCilindrica);
		
		panelCilindricoHueco = new JPanel();
		panelCilindricoHueco.setVisible(false);
		panelCilindricoHueco.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panelCilindricoHueco.setBounds(10, 262, 582, 80);
		contentPanel.add(panelCilindricoHueco);
		panelCilindricoHueco.setLayout(null);
		
		JLabel lblRadioCilindricoHueco = new JLabel("Radio exterior:");
		lblRadioCilindricoHueco.setBounds(10, 14, 100, 14);
		panelCilindricoHueco.add(lblRadioCilindricoHueco);
		
		JSpinner spnRadioCilindricoHueco = new JSpinner();
		spnRadioCilindricoHueco.setModel(new SpinnerNumberModel(new Float(0), new Float(0), null, new Float(1)));
		spnRadioCilindricoHueco.setBounds(114, 11, 170, 20);
		panelCilindricoHueco.add(spnRadioCilindricoHueco);
		
		JLabel lblLongitudCilindricaHueca = new JLabel("Longitud (cm):");
		lblLongitudCilindricaHueca.setBounds(306, 14, 95, 14);
		panelCilindricoHueco.add(lblLongitudCilindricaHueca);
		
		JSpinner spnLongitudCilindricaHueca = new JSpinner();
		spnLongitudCilindricaHueca.setModel(new SpinnerNumberModel(new Float(0), new Float(0), null, new Float(1)));
		spnLongitudCilindricaHueca.setBounds(394, 11, 170, 20);
		panelCilindricoHueco.add(spnLongitudCilindricaHueca);
		
		JLabel lblRadioInterior = new JLabel("Radio interior:");
		lblRadioInterior.setBounds(10, 50, 150, 14);
		panelCilindricoHueco.add(lblRadioInterior);
		
		JSpinner spnRadioInterior = new JSpinner();
		spnRadioInterior.setModel(new SpinnerNumberModel(new Float(0), new Float(0), null, new Float(1)));
		spnRadioInterior.setBounds(114, 47, 170, 20);
		panelCilindricoHueco.add(spnRadioInterior);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton btnRegistrar = new JButton("Registrar");
				btnRegistrar.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						if(isComplete()) {
							Queso q1 = null;
							String codigo = txtCodigo.getText().toString();
							float costoBase = (float) spnPrecioBase.getValue();
							float costoUnitario = (float) spnPrecioUnitario.getValue();
							if(rbEsferico.isSelected()) {
								float radio = (float) spnRadioEsferico.getValue();
								q1 = new QuesoEsferico(costoBase, costoUnitario, codigo, radio);
								Empresa.getInstance().insertarQueso(q1);
								clean();
								JOptionPane.showMessageDialog(null, "Operación exitosa", "Información", JOptionPane.INFORMATION_MESSAGE);
							}
							if(rbCilindrico.isSelected()) {
								float radio = (float) spnRadioCilindrico.getValue();
								float longitud = (float) spnLongitudCilindrica.getValue();
								q1 = new QuesoCilindrico(costoBase, costoUnitario, codigo, radio, longitud);
								Empresa.getInstance().insertarQueso(q1);
								clean();
								JOptionPane.showMessageDialog(null, "Operación exitosa", "Información", JOptionPane.INFORMATION_MESSAGE);
							}
							if(rbCilindricoHueco.isSelected()) {
								if((float)spnRadioCilindricoHueco.getValue() > (float)spnRadioInterior.getValue()) {
									float radioE = (float) spnRadioCilindricoHueco.getValue();
									float longitud = (float) spnLongitudCilindricaHueca.getValue();
									float radioI = (float) spnRadioInterior.getValue();
									q1 = new QuesoCilindricoHueco(costoBase, costoUnitario, codigo, radioE, longitud, radioI);
									Empresa.getInstance().insertarQueso(q1);
									clean();
									JOptionPane.showMessageDialog(null, "Operación exitosa", "Información", JOptionPane.INFORMATION_MESSAGE);
								}
								else {
									JOptionPane.showMessageDialog(null, "El radio interior debe ser menor que el radio exterior", "Advertencia", JOptionPane.WARNING_MESSAGE);
								}
							}
						}
						else {
							JOptionPane.showMessageDialog(null, "Debe llenar todos los campos", "Advertencia", JOptionPane.WARNING_MESSAGE);
						}
					}

					private boolean isComplete() {
						boolean completo = false;
						if(rbEsferico.isSelected()) {
							if((float)spnPrecioBase.getValue() != 0 && (float)spnPrecioUnitario.getValue() != 0 && (float)spnRadioEsferico.getValue() != 0) {
								completo = true;
							}
						}
						if(rbCilindrico.isSelected()) {
							if((float)spnPrecioBase.getValue() != 0 && (float)spnPrecioUnitario.getValue() != 0 && (float)spnRadioCilindrico.getValue() != 0
									&& (float)spnLongitudCilindrica.getValue() != 0) {
								completo = true;
							}
						}
						if(rbCilindricoHueco.isSelected()) {
							if((float)spnPrecioBase.getValue() != 0 && (float)spnPrecioUnitario.getValue() != 0 && (float)spnRadioCilindricoHueco.getValue() != 0
									&& (float)spnLongitudCilindricaHueca.getValue() != 0 && (float)spnRadioInterior.getValue() != 0) {
								completo = true;
							}
						}
						return completo;
					}

					private void clean() {
						txtCodigo.setText("Q-" + Empresa.generadorCodigoQueso);
						spnPrecioBase.setValue((float)0.0);
						spnPrecioUnitario.setValue((float)0.0);
						spnRadioEsferico.setValue((float)0.0);
						spnRadioCilindrico.setValue((float)0.0);
						spnLongitudCilindrica.setValue((float)0.0);
						spnRadioCilindricoHueco.setValue((float)0.0);
						spnLongitudCilindricaHueca.setValue((float)0.0);
						spnRadioInterior.setValue((float)0.0);			
					}
				});
				btnRegistrar.setActionCommand("OK");
				buttonPane.add(btnRegistrar);
				getRootPane().setDefaultButton(btnRegistrar);
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
