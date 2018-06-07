package Vistas;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.util.Date;
import java.util.Vector;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.JComboBox;
import javax.swing.JButton;
import Controladores.ABMUsuarios;
import Negocio.TipoUsuario;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;

public class AltaUsuario extends JFrame {

	private JPanel contentPane;
	private JTextField textFieldUsuario;
	private JTextField textFieldPassword;
	private JTextField textFieldConfPass;
	private JTextField textFieldNombre;
	private JTextField textFieldFechaNac;
	private JTextField textFieldMail;
	
	private Vector<TipoUsuario> v = null;
	private static AltaUsuario instancia;
	
	public static AltaUsuario getInstancia()
	{
		if (instancia == null)
			instancia = new AltaUsuario();
		return instancia;
	}

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AltaUsuario frame = new AltaUsuario();
					frame.setVisible(true);	
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public AltaUsuario() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblUsuario = new JLabel("Usuario: ");
		lblUsuario.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblUsuario.setHorizontalAlignment(SwingConstants.RIGHT);
		lblUsuario.setBounds(64, 26, 58, 14);
		contentPane.add(lblUsuario);
		
		textFieldUsuario = new JTextField();
		textFieldUsuario.setBounds(126, 23, 172, 20);
		contentPane.add(textFieldUsuario);
		textFieldUsuario.setColumns(10);
		
		JLabel lblContrasena = new JLabel("Contrase\u00F1a:");
		lblContrasena.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblContrasena.setHorizontalAlignment(SwingConstants.RIGHT);
		lblContrasena.setBounds(43, 51, 77, 14);
		contentPane.add(lblContrasena);
		
		textFieldPassword = new JTextField();
		textFieldPassword.setBounds(126, 48, 172, 20);
		contentPane.add(textFieldPassword);
		textFieldPassword.setColumns(10);
		
		JLabel lblConfirmarContrasea = new JLabel("Confirmar Contrase\u00F1a: ");
		lblConfirmarContrasea.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblConfirmarContrasea.setHorizontalAlignment(SwingConstants.RIGHT);
		lblConfirmarContrasea.setBounds(10, 76, 113, 14);
		contentPane.add(lblConfirmarContrasea);
		
		textFieldConfPass = new JTextField();
		textFieldConfPass.setBounds(126, 73, 172, 20);
		contentPane.add(textFieldConfPass);
		textFieldConfPass.setColumns(10);
		
		JLabel lblNombre = new JLabel("Nombre:");
		lblNombre.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblNombre.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNombre.setBounds(20, 103, 100, 14);
		contentPane.add(lblNombre);
		
		textFieldNombre = new JTextField();
		textFieldNombre.setBounds(126, 100, 172, 20);
		contentPane.add(textFieldNombre);
		textFieldNombre.setColumns(10);
		
		JComboBox<String> comboBoxTipo = new JComboBox<String>();
		comboBoxTipo.setBounds(126, 129, 172, 20);
		contentPane.add(comboBoxTipo);
		
		try {
			v = ABMUsuarios.getInstancia().obtenerTiposDeUsuario();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		for( int i=0; i<v.size() ; i ++ )
		{
			TipoUsuario tu = v.elementAt(i);
			comboBoxTipo.addItem(tu.getCodigo());
		}
		
		JLabel lblTipo = new JLabel("Tipo:");
		lblTipo.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblTipo.setHorizontalAlignment(SwingConstants.RIGHT);
		lblTipo.setBounds(74, 132, 46, 14);
		contentPane.add(lblTipo);
		
		JLabel lblFechaDeNacimiento = new JLabel("Fecha de Nacimiento:");
		lblFechaDeNacimiento.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblFechaDeNacimiento.setHorizontalAlignment(SwingConstants.RIGHT);
		lblFechaDeNacimiento.setBounds(10, 157, 110, 14);
		contentPane.add(lblFechaDeNacimiento);
		
		textFieldFechaNac = new JTextField();
		textFieldFechaNac.setBounds(126, 154, 172, 20);
		contentPane.add(textFieldFechaNac);
		textFieldFechaNac.setColumns(10);
		
		JLabel lblMail = new JLabel("Mail:");
		lblMail.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblMail.setHorizontalAlignment(SwingConstants.RIGHT);
		lblMail.setBounds(74, 183, 46, 14);
		contentPane.add(lblMail);
		
		textFieldMail = new JTextField();
		textFieldMail.setBounds(126, 180, 172, 20);
		contentPane.add(textFieldMail);
		textFieldMail.setColumns(10);
		
		JTextArea textArea = new JTextArea();
		textArea.setForeground(Color.RED);
		textArea.setBounds(126, 215, 271, 47);		
	    textArea.setWrapStyleWord(true);
	    textArea.setLineWrap(true);
	    textArea.setOpaque(false);
	    textArea.setEditable(false);
	    textArea.setFocusable(false);
	    textArea.setBackground(UIManager.getColor("Label.background"));
	    textArea.setFont(UIManager.getFont("Label.font"));
	    textArea.setBorder(UIManager.getBorder("Label.border"));
		contentPane.add(textArea);
		
		JButton btnGuardar = new JButton("Guardar");
		btnGuardar.addActionListener(new ActionListener() {
			@SuppressWarnings("deprecation")
			public void actionPerformed(ActionEvent arg0) {
				try
				{
					TipoUsuario tu = v.elementAt(comboBoxTipo.getSelectedIndex());
					
				    ABMUsuarios.getInstancia().validarAltaUsuario(textFieldUsuario.getText(),textFieldPassword.getText(),textFieldConfPass.getText());				    
				    ABMUsuarios.getInstancia().crearUsuario(textFieldUsuario.getText(),textFieldPassword.getText(),textFieldNombre.getText(),tu,new Date(textFieldFechaNac.getText()), textFieldMail.getText());
				    
				    textArea.setForeground(Color.BLUE);
				    textArea.setText("Usuario guardado correctamente!");
				}
				catch(Exception e)
				{				
					textArea.setForeground(Color.RED);
					textArea.setText(e.getMessage());
				}
				
			}
		});
		btnGuardar.setBounds(308, 179, 89, 23);
		contentPane.add(btnGuardar);
	}
}
