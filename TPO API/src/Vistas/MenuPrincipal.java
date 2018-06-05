package Vistas;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
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

public class MenuPrincipal extends JFrame {

	private JPanel contentPane;
	private JTextField ingresoUsuario;
	private JTextField textField;

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
		setBounds(100, 100, 310, 210);
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu mnArchivo = new JMenu("Archivo");
		menuBar.add(mnArchivo);
		
		JMenuItem mntmSalir = new JMenuItem("Salir");
		mntmSalir.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
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
		lblIngreseUsuario.setBounds(10, 11, 80, 14);
		contentPane.add(lblIngreseUsuario);
		
		ingresoUsuario = new JTextField();
		ingresoUsuario.setBounds(100, 8, 182, 20);
		contentPane.add(ingresoUsuario);
		ingresoUsuario.setColumns(10);
		
		JButton btnNewButton = new JButton("Ingresar");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		btnNewButton.setBounds(100, 70, 91, 23);
		contentPane.add(btnNewButton);
		
		JLabel lblIngreseContrasea = new JLabel("Contrase\u00F1a:");
		lblIngreseContrasea.setHorizontalAlignment(SwingConstants.RIGHT);
		lblIngreseContrasea.setBounds(10, 42, 80, 14);
		contentPane.add(lblIngreseContrasea);
		
		textField = new JTextField();
		textField.setBounds(100, 39, 182, 20);
		contentPane.add(textField);
		textField.setColumns(10);
	}
}
