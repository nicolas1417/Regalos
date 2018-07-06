package Vistas;

import java.awt.EventQueue;

import javax.swing.DefaultListModel;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Controladores.CtrlABMListas;
import Controladores.CtrlSesion;
import ObservadorDePago.ObservadorPago;
import Servicios.AvisoCierre;
import Servicios.AvisoInicio;
import Servicios.AvisoRegalo;
import Servicios.InformarPago;

import javax.swing.JMenuBar;
import javax.swing.JLabel;
import javax.swing.JList;

import java.util.List;
import javax.swing.JMenu;
import javax.swing.JMenuItem;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JSeparator;
import javax.swing.ListSelectionModel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.Font;
import javax.swing.UIManager;

public class InicioDeUsuario extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	public static List<List<String>> misListas;
	public static List<String> listaSeleccionada;
	public ObservadorPago miObservador;
	public static List<String> misPagos;

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
	 * @throws Exception 
	 */
	public InicioDeUsuario() throws Exception {
		setResizable(false);
		
		miObservador = new ObservadorPago();
		
		//Inicio - WORKERS - Mail Automáticos
		AvisoRegalo hiloNotificarRegalos = null;
		try 
		{
			hiloNotificarRegalos = new AvisoRegalo();
		}
		catch (InterruptedException e1) 
		{
			e1.printStackTrace();
		}
		hiloNotificarRegalos.start();
		
		AvisoCierre hiloAvisoCierre = null;
		
		try
		{
			hiloAvisoCierre = new AvisoCierre();
		}
		catch(InterruptedException e1)
		{
			e1.printStackTrace();
		}
		hiloAvisoCierre.start();
		
		AvisoInicio hiloAvisoInicio = null;
		
		try
		{
			hiloAvisoInicio = new AvisoInicio();
		}
		catch(InterruptedException e1)
		{
			e1.printStackTrace();
		}
		hiloAvisoInicio.start();
		
		InformarPago hiloInformarPago = null;
		try
		{
			hiloInformarPago = new InformarPago(miObservador);
		}
		catch(InterruptedException e1)
		{
			e1.printStackTrace();
		}
		hiloInformarPago.start();	
		//FIN - WORKERS - Mail Automáticos
		
		setTitle("P\u00E1gina de inicio");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 473, 473);
		
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
		
		/*
		 * Si el usuario es administrador se muestran estos controles sino no.
		 * */
		if(CtrlSesion.getInstancia().getUsuarioLogueado().getTipo()) {
			
			JMenu mnAdministrar = new JMenu("Administrar");
			menuBar.add(mnAdministrar);
			
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
			
			JMenuItem mntmBuscarListas = new JMenuItem("Buscar Listas");
			mntmBuscarListas.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					BuscarListas bl = new BuscarListas();
					bl.setLocationRelativeTo(null);
					bl.setVisible(true);
				}
			});
			mnAdministrar.add(mntmBuscarListas);
		}
		
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Bienvenido " + CtrlSesion.getInstancia().getUsuarioLogueado().getNombre());
		lblNewLabel.setForeground(UIManager.getColor("Menu.selectionBackground"));
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblNewLabel.setBounds(0, 0, 442, 14);
		contentPane.add(lblNewLabel);
		
		JLabel lblMisListas = new JLabel("Mis listas:");
		lblMisListas.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblMisListas.setBounds(10, 25, 119, 14);
		contentPane.add(lblMisListas);
		
		DefaultListModel<String> model = new DefaultListModel<String>();
		JList<String> listMisListas = new JList<String>(model);
		listMisListas.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent arg0) {
				String seleccionada = listMisListas.getSelectedValue();
			
				for(List<String> item : misListas) {
					if(seleccionada.equals(item.get(0)+"| Agasajado: "+item.get(8)))
						listaSeleccionada = item;
				}
				
				InfoLista obj = new InfoLista(miObservador);
				miObservador.addObserver(obj);
				obj.setVisible(true);
			}
		});
		listMisListas.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		misListas = CtrlABMListas.getInstancia().buscarMisListas();
		for (List<String> item : misListas) {
			model.addElement(item.get(0)+ "| Agasajado: " +item.get(8));
		}
		listMisListas.setBounds(10, 42, 200, 361);
		contentPane.add(listMisListas);
		
		DefaultListModel<String> model2 = new DefaultListModel<String>();
		JList<String> list_1 = new JList<String>(model2);
		list_1.setBounds(246, 42, 201, 361);
		contentPane.add(list_1);
		
		JLabel lblMisPagos = new JLabel("Mis pagos:");
		lblMisPagos.setFont(new Font("Tahoma", Font.PLAIN, 12));
		
		misPagos = CtrlABMListas.getInstancia().buscarMisPagos();
		for (String item : misPagos) {
			model2.addElement(item);
		}
		lblMisPagos.setBounds(246, 25, 192, 14);
		contentPane.add(lblMisPagos);
		
		
	}
}
