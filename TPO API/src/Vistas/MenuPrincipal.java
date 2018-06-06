package Vistas;


import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.MenuBar;
import java.awt.TextArea;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Controladores.ABMListas;
import Negocio.Usuario;

import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JSeparator;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.SwingConstants;
import javax.swing.UIManager;

import java.awt.Color;
import javax.swing.JPasswordField;
import javax.swing.JTextArea;

public class MenuPrincipal extends JFrame {

	private JPanel contentPane;
	private JTextField txtUsuario;
	private JPasswordField txtPasswordField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MenuPrincipal frame = new MenuPrincipal();
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
	public MenuPrincipal() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		
		JMenuBar menuBar = new JMenuBar();
		menuBar.setVisible(false);
		setJMenuBar(menuBar);
		
		JMenu mnArchivo = new JMenu("Archivo");
		menuBar.add(mnArchivo);
		
		JMenuItem mntmSalir = new JMenuItem("Salir");
		mntmSalir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {				
				MenuPrincipal.this.dispose();
			}
		});
		mnArchivo.add(mntmSalir);
		
		JMenu mnEditar = new JMenu("Acerca De");
		menuBar.add(mnEditar);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblIngreseUsuario = new JLabel("Usuario:");
		lblIngreseUsuario.setHorizontalAlignment(SwingConstants.RIGHT);
		lblIngreseUsuario.setBounds(49, 72, 80, 14);
		contentPane.add(lblIngreseUsuario);
		
		txtUsuario = new JTextField();
		txtUsuario.setBounds(139, 69, 182, 20);
		contentPane.add(txtUsuario);
		txtUsuario.setColumns(10);
		
		JTextArea textArea = new JTextArea();
		textArea.setForeground(Color.RED);
		textArea.setBounds(139, 165, 182, 47);		
	    textArea.setWrapStyleWord(true);
	    textArea.setLineWrap(true);
	    textArea.setOpaque(false);
	    textArea.setEditable(false);
	    textArea.setFocusable(false);
	    textArea.setBackground(UIManager.getColor("Label.background"));
	    textArea.setFont(UIManager.getFont("Label.font"));
	    textArea.setBorder(UIManager.getBorder("Label.border"));
		contentPane.add(textArea);
		
		JLabel lblIngreseContrasea = new JLabel("Contrase\u00F1a:");
		lblIngreseContrasea.setHorizontalAlignment(SwingConstants.RIGHT);
		lblIngreseContrasea.setBounds(49, 103, 80, 14);
		contentPane.add(lblIngreseContrasea);
		
		JButton btnNewButton = new JButton("Ingresar");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try 
				{
					Usuario u = ABMListas.getInstancia().LoginUser(txtUsuario.getText(), new String(txtPasswordField.getPassword()));
					
					menuBar.setVisible(true);
					txtUsuario.setVisible(false);
					txtPasswordField.setVisible(false);
					lblIngreseUsuario.setVisible(false);
					lblIngreseContrasea.setVisible(false);	
					textArea.setVisible(false);
					menuBar.setVisible(true);
				}
				catch (Exception e)
				{
					textArea.setText(e.getMessage());
				}
			}
		});
		
		btnNewButton.setBounds(139, 131, 182, 23);
		contentPane.add(btnNewButton);
		
		
		
		txtPasswordField = new JPasswordField();
		txtPasswordField.setBounds(139, 100, 182, 20);
		contentPane.add(txtPasswordField);
		
		
	}
}
