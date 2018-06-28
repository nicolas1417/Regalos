package Vistas;

import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

import Controladores.CtrlABMUsuarios;

import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;

public class BuscarUsuario extends JFrame {

	private JPanel contentPane;
	private JTextField textFieldUsuario;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try 
				{
					BuscarUsuario frame = new BuscarUsuario();
					frame.setVisible(true);
				}
				catch (Exception e) 
				{
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public BuscarUsuario() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		String[] columnas = {"Usuario", "Nombre", "Tipo","Mail","Estado"};
		Object[][] data = null;
		try
		{
			data = CtrlABMUsuarios.getInstancia().buscarUsuarios();
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
		table.setRowSorter(sorter);

		// creamos un scroll y le añadomos la tabla
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 10, 400, 150);
		scrollPane.setViewportView(table);
		getContentPane().add(scrollPane);
		
		JLabel lblUsuario = new JLabel("Usuario:");
		lblUsuario.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblUsuario.setHorizontalAlignment(SwingConstants.RIGHT);
		lblUsuario.setBounds(10, 171, 46, 14);
		contentPane.add(lblUsuario);
		
		JTextArea textAreaMsgError = new JTextArea();
		textAreaMsgError.setForeground(Color.RED);
		textAreaMsgError.setBounds(20, 204, 294, 47);		
	    textAreaMsgError.setWrapStyleWord(true);
	    textAreaMsgError.setLineWrap(true);
	    textAreaMsgError.setOpaque(false);
	    textAreaMsgError.setEditable(false);
	    textAreaMsgError.setFocusable(false);
	    textAreaMsgError.setBackground(UIManager.getColor("Label.background"));
	    textAreaMsgError.setFont(UIManager.getFont("Label.font"));
	    textAreaMsgError.setBorder(UIManager.getBorder("Label.border"));
		contentPane.add(textAreaMsgError);
		
		textFieldUsuario = new JTextField();
		textFieldUsuario.setBounds(66, 168, 152, 20);
		contentPane.add(textFieldUsuario);
		textFieldUsuario.setColumns(10);
		
		JButton btnModificar = new JButton("Modificar");
		btnModificar.setFont(new Font("Tahoma", Font.PLAIN, 11));
		btnModificar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(!textFieldUsuario.getText().equals(""))
				{
					AltaModUsuario altaModUsuario = new AltaModUsuario(textFieldUsuario.getText());
					
					altaModUsuario.setLocationRelativeTo(null);
					altaModUsuario.setVisible(true);	
				}
				else
					textAreaMsgError.setText("No ha ingresado un usuario para modificar");
			}
		});
		btnModificar.setBounds(228, 167, 86, 23);
		contentPane.add(btnModificar);
		
		JButton btnEliminar = new JButton("Eliminar");
		btnEliminar.setFont(new Font("Tahoma", Font.PLAIN, 11));
		btnEliminar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try
				{
					if(!textFieldUsuario.getText().equals(""))
					{
						CtrlABMUsuarios.getInstancia().eliminarUsuario(textFieldUsuario.getText());
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
		btnEliminar.setBounds(324, 167, 86, 23);
		contentPane.add(btnEliminar);
		
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				BuscarUsuario.this.dispose();
			}
		});
		btnCancelar.setFont(new Font("Tahoma", Font.PLAIN, 11));
		btnCancelar.setBounds(324, 200, 86, 23);
		contentPane.add(btnCancelar);
	}
}
