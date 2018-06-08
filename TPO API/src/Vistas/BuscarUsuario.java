package Vistas;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;

import Controladores.ABMUsuarios;

import javax.swing.JTable;

public class BuscarUsuario extends JFrame {

	private JPanel contentPane;
	private JTable table;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					BuscarUsuario frame = new BuscarUsuario();
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
	public BuscarUsuario() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		String[] columnas = {"Usuario", "Nombre", "Tipo","Mail","Estado"};
		Object[][] data = null;
		try
		{
			data = ABMUsuarios.getInstancia().buscarUsuarios();
		}
		catch(Exception e)
		{
			e.getMessage();
		}
		
		final JTable table = new JTable(data, columnas);
		 table.setPreferredScrollableViewportSize(new Dimension(400, 100));
		 //Creamos un JscrollPane y le agregamos la JTable
		 JScrollPane scrollPane = new JScrollPane(table);
		 //Agregamos el JScrollPane al contenedor
		 getContentPane().add(scrollPane, BorderLayout.CENTER);
		
		table.setBounds(124, 116, 1, 1);
		contentPane.add(table);
	}
}
