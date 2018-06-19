package Vistas;


import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Controladores.CtrlSesion;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.SwingConstants;
import javax.swing.UIManager;

import java.awt.Color;
import java.awt.Cursor;

import javax.swing.JPasswordField;
import javax.swing.JTextArea;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.JProgressBar;

public class LogIn extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtUsuario;
	private JPasswordField txtPasswordField;
	JTextArea textArea = new JTextArea();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LogIn frame = new LogIn();
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
	public LogIn() {
		setTitle("Iniciar sesi\u00F3n");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		
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
		
		JLabel avisoInicio = new JLabel("");
		avisoInicio.setBackground(Color.ORANGE);
		avisoInicio.setHorizontalAlignment(SwingConstants.CENTER);
		avisoInicio.setBounds(139, 223, 182, 20);
		contentPane.add(avisoInicio);
		
		JLabel lblIngreseContrasea = new JLabel("Contrase\u00F1a:");
		lblIngreseContrasea.setHorizontalAlignment(SwingConstants.RIGHT);
		lblIngreseContrasea.setBounds(49, 103, 80, 14);
		contentPane.add(lblIngreseContrasea);
		
		JButton btnIngresar = new JButton("Ingresar");
		btnIngresar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				avisoInicio.setText("Iniciando...");
				setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
				try
				{
					iniciarSesion();
					cambiarDeVentana();
				}
				catch(Exception e)
				{
					textArea.setText(e.getMessage());
				}
			}
		});
		
		btnIngresar.setBounds(139, 131, 182, 23);
		contentPane.add(btnIngresar);
		
		txtPasswordField = new JPasswordField();
		txtPasswordField.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
					avisoInicio.setText("Iniciando...");
					try {
						iniciarSesion();
						cambiarDeVentana();
					} catch (Exception ex) {
						textArea.setText(ex.getMessage());
					}
				}
			}
		});
		txtPasswordField.setBounds(139, 100, 182, 20);
		contentPane.add(txtPasswordField);
		
		
	}
	
	public void iniciarSesion() throws Exception{
		try 
		{
			CtrlSesion.getInstancia().LoginUser(txtUsuario.getText(), new String(txtPasswordField.getPassword()));
		}
		catch (Exception e)
		{
			throw e;
		}
	}
	
	public void cambiarDeVentana() {
		InicioDeUsuario inicio = new InicioDeUsuario();
		inicio.setVisible(true);
		LogIn.this.dispose();
	}
}
