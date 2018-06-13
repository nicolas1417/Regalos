package Vistas;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Controladores.CtrlSesion;
import Negocio.Usuario;

import javax.swing.JMenuBar;
import javax.swing.JLabel;
import javax.swing.JList;
import java.awt.List;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

import java.awt.Component;
import javax.swing.Box;
import javax.swing.JDialog;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JSeparator;

public class InicioDeUsuario extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					InicioDeUsuario frame = new InicioDeUsuario();
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
	public InicioDeUsuario() {
		setTitle("P\u00E1gina de inicio");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu mnArchivo = new JMenu("Archivo");
		menuBar.add(mnArchivo);
		
		JMenuItem mntmSalir = new JMenuItem("Salir");
		mntmSalir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				InicioDeUsuario.this.dispose();
			}
		});
		
		JMenuItem mntmCerrarSesin = new JMenuItem("Cerrar sesi\u00F3n");
		mntmCerrarSesin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				InicioDeUsuario.this.dispose();
				LogIn inicio = new LogIn();
				inicio.setVisible(true);
			}
		});
		mnArchivo.add(mntmCerrarSesin);
		mnArchivo.add(mntmSalir);
		
		JMenu mnAdministrar = new JMenu("Administrar");
		menuBar.add(mnAdministrar);
		
		/*
		 * Si el usuario es administrador se muestran estos controles sino no.
		 * */
		if(CtrlSesion.getInstancia().getUsuarioLogueado().getIdTipo() == 1) {
			JMenuItem mntmAltaDeUsuario = new JMenuItem("Alta de Usuario");
			mntmAltaDeUsuario.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					AltaModUsuario altaModUsuario = new AltaModUsuario("");
					altaModUsuario.setLocationRelativeTo(null);
					altaModUsuario.setVisible(true);
				}
			});
			mnAdministrar.add(mntmAltaDeUsuario);
			
			JMenuItem mntmBuscarUsuarios = new JMenuItem("Buscar Usuarios");
			mntmBuscarUsuarios.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					BuscarUsuarios buscarUsuario = new BuscarUsuarios();
					buscarUsuario.setLocationRelativeTo(null);
					buscarUsuario.setVisible(true);
				}
			});
			mnAdministrar.add(mntmBuscarUsuarios);
			
			JSeparator separator = new JSeparator();
			mnAdministrar.add(separator);
			
			JMenuItem mntmNewMenuItem = new JMenuItem("Alta de Lista");
			mntmNewMenuItem.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					AltaModLista darDeAltaLista;
					try {
						darDeAltaLista = new AltaModLista(0);
						darDeAltaLista.setVisible(true);
					} catch (Exception ex) {
						// TODO Auto-generated catch block
						ex.printStackTrace();
					}
				}
			});
			mnAdministrar.add(mntmNewMenuItem);
		}
		
		
		
		JMenuItem mntmBajaDeLista = new JMenuItem("Buscar Listas");
		mntmBajaDeLista.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				BuscarListas bl = new BuscarListas();
				bl.setLocationRelativeTo(null);
				bl.setVisible(true);
			}
		});
		mnAdministrar.add(mntmBajaDeLista);
		
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Bienvenido " + CtrlSesion.getInstancia().getUsuarioLogueado().getNombre());
		lblNewLabel.setBounds(0, 0, 442, 14);
		contentPane.add(lblNewLabel);
		
		JLabel lblMisListas = new JLabel("Mis listas:");
		lblMisListas.setBounds(0, 22, 119, 14);
		contentPane.add(lblMisListas);
		
		List list = new List();
		list.setBounds(0, 42, 235, 200);
		contentPane.add(list);
		
		List list_1 = new List();
		list_1.setBounds(241, 42, 191, 200);
		contentPane.add(list_1);
		
		JLabel lblMisPagos = new JLabel("Mis pagos:");
		lblMisPagos.setBounds(240, 22, 192, 14);
		contentPane.add(lblMisPagos);
	}
}
