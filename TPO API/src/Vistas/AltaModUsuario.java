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
import Controladores.CtrlABMUsuarios;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;

public class AltaModUsuario extends JFrame {

	private JPanel contentPane;
	private JTextField textFieldUsuario;
	private JTextField textFieldPassword;
	private JTextField textFieldConfPass;
	private JTextField textFieldNombre;
	private JTextField textFieldFechaNac;
	private JTextField textFieldMail;
	private JComboBox<String> comboBoxTipo;
	private JTextArea textAreaMsgError;
	
	private String[] s = null;
	private String usuarioMod = "";

	/**
	 * Create the frame.
	 */
	public AltaModUsuario(String usuarioMod) {
		this.usuarioMod = usuarioMod;
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblUsuario = new JLabel("Usuario: ");
		lblUsuario.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblUsuario.setHorizontalAlignment(SwingConstants.RIGHT);
		lblUsuario.setBounds(64, 8, 58, 14);
		contentPane.add(lblUsuario);
		
		textFieldUsuario = new JTextField();
		textFieldUsuario.setBounds(126, 6, 172, 20);
		contentPane.add(textFieldUsuario);
		textFieldUsuario.setColumns(10);
		
		JLabel lblContrasena = new JLabel("Contrase\u00F1a:");
		lblContrasena.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblContrasena.setHorizontalAlignment(SwingConstants.RIGHT);
		lblContrasena.setBounds(43, 32, 77, 14);
		contentPane.add(lblContrasena);
		
		textFieldPassword = new JTextField();
		textFieldPassword.setBounds(126, 30, 172, 20);
		contentPane.add(textFieldPassword);
		textFieldPassword.setColumns(10);
		
		JLabel lblConfirmarContrasea = new JLabel("Confirmar Contrase\u00F1a: ");
		lblConfirmarContrasea.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblConfirmarContrasea.setHorizontalAlignment(SwingConstants.RIGHT);
		lblConfirmarContrasea.setBounds(10, 56, 113, 14);
		contentPane.add(lblConfirmarContrasea);
		
		textFieldConfPass = new JTextField();
		textFieldConfPass.setBounds(126, 54, 172, 20);
		contentPane.add(textFieldConfPass);
		textFieldConfPass.setColumns(10);
		
		JLabel lblNombre = new JLabel("Nombre:");
		lblNombre.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblNombre.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNombre.setBounds(20, 81, 100, 14);
		contentPane.add(lblNombre);
		
		textFieldNombre = new JTextField();
		textFieldNombre.setBounds(126, 79, 172, 20);
		contentPane.add(textFieldNombre);
		textFieldNombre.setColumns(10);
		
		comboBoxTipo = new JComboBox<String>();
		comboBoxTipo.setBounds(126, 102, 172, 20);
		contentPane.add(comboBoxTipo);
		
		try {
			s = CtrlABMUsuarios.getInstancia().obtenerTiposDeUsuario();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		for( int i=0; i<s.length ; i ++ )
		{			
			comboBoxTipo.addItem(s[i]);
		}
		
		JLabel lblTipo = new JLabel("Tipo:");
		lblTipo.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblTipo.setHorizontalAlignment(SwingConstants.RIGHT);
		lblTipo.setBounds(74, 104, 46, 14);
		contentPane.add(lblTipo);
		
		JLabel lblFechaDeNacimiento = new JLabel("Fecha de Nacimiento:");
		lblFechaDeNacimiento.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblFechaDeNacimiento.setHorizontalAlignment(SwingConstants.RIGHT);
		lblFechaDeNacimiento.setBounds(10, 129, 110, 14);
		contentPane.add(lblFechaDeNacimiento);
		
		textFieldFechaNac = new JTextField();
		textFieldFechaNac.setBounds(126, 127, 172, 20);
		contentPane.add(textFieldFechaNac);
		textFieldFechaNac.setColumns(10);
		
		JLabel lblMail = new JLabel("Mail:");
		lblMail.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblMail.setHorizontalAlignment(SwingConstants.RIGHT);
		lblMail.setBounds(74, 155, 46, 14);
		contentPane.add(lblMail);
		
		textFieldMail = new JTextField();
		textFieldMail.setBounds(126, 153, 172, 20);
		contentPane.add(textFieldMail);
		textFieldMail.setColumns(10);
		
		textAreaMsgError = new JTextArea();
		textAreaMsgError.setForeground(Color.RED);
		textAreaMsgError.setBounds(126, 207, 271, 47);		
	    textAreaMsgError.setWrapStyleWord(true);
	    textAreaMsgError.setLineWrap(true);
	    textAreaMsgError.setOpaque(false);
	    textAreaMsgError.setEditable(false);
	    textAreaMsgError.setFocusable(false);
	    textAreaMsgError.setBackground(UIManager.getColor("Label.background"));
	    textAreaMsgError.setFont(UIManager.getFont("Label.font"));
	    textAreaMsgError.setBorder(UIManager.getBorder("Label.border"));
		contentPane.add(textAreaMsgError);
		
		JButton btnGuardar = new JButton("Guardar");
		btnGuardar.setFont(new Font("Tahoma", Font.PLAIN, 11));
		btnGuardar.addActionListener(new ActionListener() {
			@SuppressWarnings("deprecation")
			public void actionPerformed(ActionEvent arg0) {
				try
				{
					int idTipoUsuario = comboBoxTipo.getSelectedIndex();
					if (usuarioMod.equals(""))
					{
					    CtrlABMUsuarios.getInstancia().validarAltaUsuario(textFieldUsuario.getText(),textFieldPassword.getText(),textFieldConfPass.getText());				    
					    CtrlABMUsuarios.getInstancia().crearUsuario(textFieldUsuario.getText(),textFieldPassword.getText(),textFieldNombre.getText(),idTipoUsuario+1,new Date(textFieldFechaNac.getText()), textFieldMail.getText());
					    
					    textAreaMsgError.setForeground(Color.BLUE);
					    textAreaMsgError.setText("Usuario guardado correctamente!");
					}
					else
					{						
						CtrlABMUsuarios.getInstancia().modificarUsuario(textFieldUsuario.getText(),textFieldPassword.getText(),textFieldConfPass.getText(),textFieldNombre.getText(),idTipoUsuario+1,new Date(textFieldFechaNac.getText()), textFieldMail.getText());
						textAreaMsgError.setForeground(Color.BLUE);
					    textAreaMsgError.setText("Usuario Modificado correctamente!");
					}
				}
				catch(Exception e)
				{				
					textAreaMsgError.setForeground(Color.RED);
					textAreaMsgError.setText(e.getMessage());
				}
				
			}
		});
		btnGuardar.setBounds(127, 181, 80, 23);
		contentPane.add(btnGuardar);
		
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AltaModUsuario.this.dispose();
			}
		});
		btnCancelar.setFont(new Font("Tahoma", Font.PLAIN, 11));
		btnCancelar.setBounds(217, 181, 80, 23);
		contentPane.add(btnCancelar);
		
		setearVistaModificacion();
	}
	
	private void setearVistaModificacion()
	{
		try
		{
			if(!this.usuarioMod.equals(""))
			{
				String[] usuario = CtrlABMUsuarios.getInstancia().buscarUsuarioParaModificar(this.usuarioMod);
			
				if(usuario != null)
				{
					textFieldUsuario.setText(usuario[0]);
					textFieldUsuario.setEnabled(false);
					
					textFieldNombre.setText(usuario[1]);
					textFieldFechaNac.setText(usuario[2]);
					textFieldMail.setText(usuario[3]);
					comboBoxTipo.setSelectedIndex(Integer.parseInt(usuario[4])-1);	
				}
				else 
					throw new Exception("El usuario a Modificar no existe");
			}
		}
		catch(Exception e)
		{
			textAreaMsgError.setText(e.getMessage());
		}
	}
}
