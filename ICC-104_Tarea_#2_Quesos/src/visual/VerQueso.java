package visual;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Toolkit;
import java.math.RoundingMode;
import java.text.DecimalFormat;

import javax.swing.border.TitledBorder;

import logical.Queso;
import logical.QuesoCilindrico;
import logical.QuesoCilindricoHueco;
import logical.QuesoEsferico;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.ImageIcon;
import javax.swing.JTextField;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class VerQueso extends JDialog {

	private static DecimalFormat df = new DecimalFormat("#.##");
	private final JPanel contentPanel = new JPanel();
	private JTextField txtCodigo;
	private JTextField txtForma;
	private Queso auxQueso = null;
	private JPanel panelCilindricoHueco;
	private String forma;
	private JPanel panelCilindrico;
	private JPanel panelEsferico;

	public VerQueso(Queso queso) {
		auxQueso = queso;
		df.setRoundingMode(RoundingMode.CEILING);
		setIconImage(Toolkit.getDefaultToolkit().getImage(VerQueso.class.getResource("/media/imgQueso32pxN.png")));
		if(auxQueso instanceof QuesoEsferico) {
			forma = "Esférico";
		}
		if(auxQueso instanceof QuesoCilindrico) {
			forma = "Cilíndrico";
		}
		if(auxQueso instanceof QuesoCilindricoHueco) {
			forma = "Cilíndrico Hueco";
		}
		setTitle("Queso " + forma + " (" + auxQueso.getCodigo() + ")");
		setModal(true);
		setResizable(false);
		setBounds(100, 100, 615, 434);
		setLocationRelativeTo(null);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			JPanel panelGeneral = new JPanel();
			panelGeneral.setBounds(5, 5, 584, 225);
			panelGeneral.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
			contentPanel.add(panelGeneral);
			panelGeneral.setLayout(null);
			{
				JLabel lblImagenQueso = new JLabel("");
				lblImagenQueso.setIcon(new ImageIcon(VerQueso.class.getResource("/media/imgQueso128pxB.png")));
				lblImagenQueso.setBounds(10, 11, 165, 140);
				panelGeneral.add(lblImagenQueso);
			}
			{
				JLabel lblCodigo = new JLabel("C\u00F3digo:");
				lblCodigo.setBounds(185, 60, 67, 14);
				panelGeneral.add(lblCodigo);
			}
			{
				txtCodigo = new JTextField();
				txtCodigo.setEditable(false);
				txtCodigo.setBounds(304, 57, 170, 20);
				panelGeneral.add(txtCodigo);
				txtCodigo.setColumns(10);
				txtCodigo.setText(auxQueso.getCodigo());
			}
			{
				JLabel lblPrecioBase = new JLabel("Precio base:");
				lblPrecioBase.setBounds(185, 88, 88, 14);
				panelGeneral.add(lblPrecioBase);
			}
			
			JLabel lblPrecioUnitario = new JLabel("Precio unitario:");
			lblPrecioUnitario.setBounds(185, 116, 88, 14);
			panelGeneral.add(lblPrecioUnitario);
			
			JLabel lblForma = new JLabel("Forma:");
			lblForma.setBounds(185, 29, 67, 14);
			panelGeneral.add(lblForma);
			
			txtForma = new JTextField();
			txtForma.setEditable(false);
			txtForma.setColumns(10);
			txtForma.setBounds(304, 26, 170, 20);
			panelGeneral.add(txtForma);
			
			JLabel lblPrecioTotal = new JLabel("Precio total:");
			lblPrecioTotal.setBounds(185, 141, 107, 14);
			panelGeneral.add(lblPrecioTotal);
			
			JLabel lblValorPrecioTotal = new JLabel("RD$" + df.format(auxQueso.precioTotal()));
			lblValorPrecioTotal.setFont(new Font("Tahoma", Font.BOLD, 11));
			lblValorPrecioTotal.setHorizontalAlignment(SwingConstants.RIGHT);
			lblValorPrecioTotal.setBounds(378, 144, 96, 14);
			panelGeneral.add(lblValorPrecioTotal);
			
			JLabel lblValorPrecioBase = new JLabel("RD$" + df.format(auxQueso.getCostoBase()));
			lblValorPrecioBase.setHorizontalAlignment(SwingConstants.RIGHT);
			lblValorPrecioBase.setFont(new Font("Tahoma", Font.BOLD, 11));
			lblValorPrecioBase.setBounds(378, 88, 96, 14);
			panelGeneral.add(lblValorPrecioBase);
			
			JLabel lblValorPrecioUnitario = new JLabel("RD$" + df.format(auxQueso.getCostoUnitario()));
			lblValorPrecioUnitario.setHorizontalAlignment(SwingConstants.RIGHT);
			lblValorPrecioUnitario.setFont(new Font("Tahoma", Font.BOLD, 11));
			lblValorPrecioUnitario.setBounds(378, 116, 96, 14);
			panelGeneral.add(lblValorPrecioUnitario);
			
			JLabel lblVolumen = new JLabel("Volumen:");
			lblVolumen.setBounds(185, 166, 107, 14);
			panelGeneral.add(lblVolumen);
			
			JLabel lblValorVolumen = new JLabel(df.format(auxQueso.Volumen()) + " cm^3");
			lblValorVolumen.setHorizontalAlignment(SwingConstants.RIGHT);
			lblValorVolumen.setFont(new Font("Tahoma", Font.BOLD, 11));
			lblValorVolumen.setBounds(378, 169, 96, 14);
			panelGeneral.add(lblValorVolumen);
			
			JLabel lblEstado = new JLabel("Estado:");
			lblEstado.setBounds(185, 191, 107, 14);
			panelGeneral.add(lblEstado);
			
			JLabel lblValorEstado = new JLabel(auxQueso.getEstado());
			lblValorEstado.setHorizontalAlignment(SwingConstants.RIGHT);
			lblValorEstado.setFont(new Font("Tahoma", Font.BOLD, 11));
			lblValorEstado.setBounds(351, 194, 123, 14);
			panelGeneral.add(lblValorEstado);
			if(auxQueso instanceof QuesoEsferico) {
				txtForma.setText("Esférico");
			}
			if(auxQueso instanceof QuesoCilindrico) {
				txtForma.setText("Cilíndrico");
			}
			if(auxQueso instanceof QuesoCilindricoHueco) {
				txtForma.setText("Cilíndrico Hueco");
			}
		}
		
		panelCilindricoHueco = new JPanel();
		panelCilindricoHueco.setVisible(false);
		panelCilindricoHueco.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panelCilindricoHueco.setBounds(5, 241, 584, 100);
		contentPanel.add(panelCilindricoHueco);
		panelCilindricoHueco.setLayout(null);
		if(auxQueso instanceof QuesoCilindricoHueco) {
			panelCilindricoHueco.setVisible(true);
		}
		
		JLabel lblImagenCilindroHueco = new JLabel("");
		lblImagenCilindroHueco.setIcon(new ImageIcon(VerQueso.class.getResource("/media/imgCilindroHueco128pxB.png")));
		lblImagenCilindroHueco.setBounds(39, 11, 83, 78);
		panelCilindricoHueco.add(lblImagenCilindroHueco);
		
		JLabel lblRadioInterior = new JLabel("Radio interior:");
		lblRadioInterior.setBounds(185, 11, 107, 14);
		panelCilindricoHueco.add(lblRadioInterior);
		
		JLabel lblRadioExterior = new JLabel("Radio exterior:");
		lblRadioExterior.setBounds(185, 43, 107, 14);
		panelCilindricoHueco.add(lblRadioExterior);
		
		JLabel lblValorRadioInteriorCH = new JLabel("");
		lblValorRadioInteriorCH.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblValorRadioInteriorCH.setHorizontalAlignment(SwingConstants.RIGHT);
		lblValorRadioInteriorCH.setBounds(334, 11, 140, 14);
		panelCilindricoHueco.add(lblValorRadioInteriorCH);
		
		JLabel lblValorRadioExteriorCH = new JLabel("");
		lblValorRadioExteriorCH.setHorizontalAlignment(SwingConstants.RIGHT);
		lblValorRadioExteriorCH.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblValorRadioExteriorCH.setBounds(334, 43, 140, 14);
		panelCilindricoHueco.add(lblValorRadioExteriorCH);
		
		JLabel lblLongitud = new JLabel("Longitud:");
		lblLongitud.setBounds(185, 75, 107, 14);
		panelCilindricoHueco.add(lblLongitud);
		
		JLabel lblValorLongitudCH = new JLabel("");
		lblValorLongitudCH.setHorizontalAlignment(SwingConstants.RIGHT);
		lblValorLongitudCH.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblValorLongitudCH.setBounds(334, 75, 140, 14);
		panelCilindricoHueco.add(lblValorLongitudCH);
		if(auxQueso instanceof QuesoCilindricoHueco) {
			lblValorRadioInteriorCH.setText(df.format(((QuesoCilindricoHueco)auxQueso).getRadioI()) + " cm");
			lblValorRadioExteriorCH.setText(df.format(((QuesoCilindricoHueco)auxQueso).getRadioE()) + " cm");
			lblValorLongitudCH.setText(df.format(((QuesoCilindricoHueco)auxQueso).getLongitud()) + " cm");
		}
		
		panelCilindrico = new JPanel();
		panelCilindrico.setVisible(false);
		panelCilindrico.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panelCilindrico.setBounds(5, 241, 584, 100);
		contentPanel.add(panelCilindrico);
		panelCilindrico.setLayout(null);
		if(auxQueso instanceof QuesoCilindrico) {
			panelCilindrico.setVisible(true);
		}
		
		JLabel lblImagenCilindro = new JLabel("");
		lblImagenCilindro.setIcon(new ImageIcon(VerQueso.class.getResource("/media/imgCilindro64pxB.png")));
		lblImagenCilindro.setBounds(39, 11, 83, 78);
		panelCilindrico.add(lblImagenCilindro);
		
		JLabel lblRadioC = new JLabel("Radio:");
		lblRadioC.setBounds(185, 23, 107, 14);
		panelCilindrico.add(lblRadioC);
		
		JLabel lblLongitudC = new JLabel("Longitud:");
		lblLongitudC.setBounds(185, 55, 107, 14);
		panelCilindrico.add(lblLongitudC);
		
		JLabel lblValorRadioC = new JLabel("");
		lblValorRadioC.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblValorRadioC.setHorizontalAlignment(SwingConstants.RIGHT);
		lblValorRadioC.setBounds(367, 23, 107, 14);
		panelCilindrico.add(lblValorRadioC);
		
		JLabel lblValorLongitudC = new JLabel("");
		lblValorLongitudC.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblValorLongitudC.setHorizontalAlignment(SwingConstants.RIGHT);
		lblValorLongitudC.setBounds(367, 55, 107, 14);
		panelCilindrico.add(lblValorLongitudC);
		
		if(auxQueso instanceof QuesoCilindrico){
			lblValorRadioC.setText(df.format(((QuesoCilindrico)auxQueso).getRadioE()) + " cm");
			lblValorLongitudC.setText(df.format(((QuesoCilindrico)auxQueso).getLongitud()) + " cm");
		}
		
		panelEsferico = new JPanel();
		panelEsferico.setVisible(false);
		panelEsferico.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panelEsferico.setBounds(5, 241, 584, 100);
		contentPanel.add(panelEsferico);
		panelEsferico.setLayout(null);
		if(auxQueso instanceof QuesoEsferico) {
			panelEsferico.setVisible(true);
		}
		
		JLabel lblImagenEsfera = new JLabel("");
		lblImagenEsfera.setIcon(new ImageIcon(VerQueso.class.getResource("/media/imgEsfera64pxB.png")));
		lblImagenEsfera.setBounds(39, 11, 83, 78);
		panelEsferico.add(lblImagenEsfera);
		
		JLabel lblRadioE = new JLabel("Radio:");
		lblRadioE.setBounds(185, 43, 107, 14);
		panelEsferico.add(lblRadioE);
		
		JLabel lblValorRadioE = new JLabel("");
		lblValorRadioE.setHorizontalAlignment(SwingConstants.RIGHT);
		lblValorRadioE.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblValorRadioE.setBounds(334, 43, 140, 14);
		panelEsferico.add(lblValorRadioE);
		
		if(auxQueso instanceof QuesoEsferico){
			lblValorRadioE.setText(df.format(((QuesoEsferico)auxQueso).getRadio()));
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
						if(auxQueso.getEstado().equalsIgnoreCase("Disponible")) {
							ModQueso modQueso = new ModQueso(auxQueso);
							modQueso.setVisible(true);
						}
						else {
							JOptionPane.showMessageDialog(null, "No se puede modificar un artículo vendido", "Advertencia", JOptionPane.WARNING_MESSAGE);
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
