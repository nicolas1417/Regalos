package Vistas;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

import Controladores.CtrlABMListas;
import Controladores.CtrlSesion;

public class BuscarListas extends JFrame {

	private JPanel contentPane;
	private JTextField textFieldLista;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					BuscarListas frame = new BuscarListas();
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
	public BuscarListas() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 700, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		String[] columnas = {"id Lista", "Nombre Agasajado", "Fecha Agasajo", "Monto Por Participante","Monto Recaudado","Fecha Inicio","Fecha Fin", "Estado"};
		Object[][] data = null;
		try
		{
			data = CtrlABMListas.getInstancia().buscarListas(CtrlSesion.getInstancia().getUsuarioLogueado());
		}
		catch(Exception e)
		{
			e.getMessage();
		}
		
		// creamos el modelo con los datos 
		TableModel modelo = new DefaultTableModel(data, columnas) {
		    private static final long serialVersionUID = 1L;
		    public Class<?> getColumnClass(int column) {
		        return getValueAt(0, column).getClass();
		    }
		};

		// creamos la Table basados en el modelo de datos que hemos creado
		JTable table = new JTable(modelo);
		table.setEnabled(false);

		// ordenacion de filas (por defecto, al ser tipos primitivos)
		TableRowSorter<TableModel> sorter = new TableRowSorter<TableModel>(modelo);
		contentPane.setLayout(null);
		table.setRowSorter(sorter);

		// creamos un scroll y le añadomos la tabla
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 10, 664, 150);
		scrollPane.setViewportView(table);
		getContentPane().add(scrollPane);
		
		JLabel lblLista = new JLabel("C\u00F3digo Lista:");
		lblLista.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblLista.setHorizontalAlignment(SwingConstants.RIGHT);
		lblLista.setBounds(10, 171, 75, 14);
		contentPane.add(lblLista);
		
		JTextArea textAreaMsgError = new JTextArea();
		textAreaMsgError.setForeground(Color.RED);
		textAreaMsgError.setBounds(20, 204, 564, 47);		
	    textAreaMsgError.setWrapStyleWord(true);
	    textAreaMsgError.setLineWrap(true);
	    textAreaMsgError.setOpaque(false);
	    textAreaMsgError.setEditable(false);
	    textAreaMsgError.setFocusable(false);
	    textAreaMsgError.setBackground(UIManager.getColor("Label.background"));
	    textAreaMsgError.setFont(UIManager.getFont("Label.font"));
	    textAreaMsgError.setBorder(UIManager.getBorder("Label.border"));
		contentPane.add(textAreaMsgError);
		
		textFieldLista = new JTextField();
		textFieldLista.setBounds(95, 168, 271, 20);
		contentPane.add(textFieldLista);
		textFieldLista.setColumns(10);
		
		JButton btnModificar = new JButton("Modificar");
		btnModificar.setFont(new Font("Tahoma", Font.PLAIN, 11));
		btnModificar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) 
			{
				try
				{
					if(!textFieldLista.getText().equals(""))
					{
						
							AltaModLista altaModLista;
							altaModLista = new AltaModLista(Integer.parseInt(textFieldLista.getText()));
							altaModLista.setLocationRelativeTo(null);
							altaModLista.setVisible(true);	
					}
						else
							textAreaMsgError.setText("No ha ingresado un usuario para modificar");	
				}
				catch (Exception e) 
				{
					textAreaMsgError.setText(e.getMessage());	
				} 
			}
		});
		btnModificar.setBounds(376, 167, 86, 23);
		contentPane.add(btnModificar);
		
		JButton btnEliminar = new JButton("Eliminar");
		btnEliminar.setFont(new Font("Tahoma", Font.PLAIN, 11));
		btnEliminar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try
				{
					if(!textFieldLista.getText().equals(""))
					{
						CtrlABMListas.getInstancia().eliminarLista(Integer.parseInt(textFieldLista.getText()));
						textAreaMsgError.setForeground(Color.BLUE);
						textAreaMsgError.setText("Usuario Eliminado Correctamente");	
					}
					else
					{
						textAreaMsgError.setForeground(Color.RED);
						textAreaMsgError.setText("No ha ingresado un usuario para Eliminar");	
					}
				}
				catch(Exception ex)
				{
					textAreaMsgError.setText(ex.getMessage());
				}
			}
		});
		btnEliminar.setBounds(472, 167, 86, 23);
		contentPane.add(btnEliminar);
		
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				BuscarListas.this.dispose();
			}
		});
		btnCancelar.setFont(new Font("Tahoma", Font.PLAIN, 11));
		btnCancelar.setBounds(568, 167, 86, 23);
		contentPane.add(btnCancelar);
	}

}
