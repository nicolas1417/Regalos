package Vistas;

import java.awt.Color;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.border.EmptyBorder;

import Controladores.CtrlABMListas;
import Negocio.Usuario;

import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;
import java.awt.event.ActionEvent;
import javax.swing.JList;

public class AltaModLista extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textFieldFechaAgasajo;
	private JTextField textFieldMonto;
	private JLabel lblFechaFin;
	private JTextField textFieldFechaFin;
	private JLabel lblNewLabel_2;
	private JTextField textFieldMail;
	private JTextField textFieldAgasajado;
	private JLabel lblNewLabel_3;
	private JLabel mensaje;
	private JTextField textFieldFechaInicio;
	private JTextArea textAreaMsgError;
	private List<String> usuariosParaAgregar;
	
	private int listaMod = 0;
	
	/**
	 * Create the frame.
	 * @throws Exception 
	 */
	public AltaModLista(int listaMod) throws Exception {
		setResizable(false);
		setTitle("Alta de listas");
		this.listaMod = listaMod;
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 600, 372);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		DefaultListModel<String> model = new DefaultListModel<String>();
		JList<String> listNueva = new JList<String>(model);
		listNueva.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
		listNueva.setBounds(339, 39, 243, 178);
		
		JLabel lblNewLabel = new JLabel("Fecha del agasajo:");
		lblNewLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel.setBounds(10, 198, 135, 14);
		contentPane.add(lblNewLabel);
		
		textFieldFechaAgasajo = new JTextField();
		textFieldFechaAgasajo.setBounds(155, 195, 100, 20);
		contentPane.add(textFieldFechaAgasajo);
		textFieldFechaAgasajo.setColumns(10);
		
		textFieldMonto = new JTextField();
		textFieldMonto.setBounds(155, 68, 100, 20);
		contentPane.add(textFieldMonto);
		textFieldMonto.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("Monto por participante:");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_1.setBounds(10, 71, 135, 14);
		contentPane.add(lblNewLabel_1);
		
		
		
		lblFechaFin = new JLabel("Fecha fin:");
		lblFechaFin.setHorizontalAlignment(SwingConstants.RIGHT);
		lblFechaFin.setBounds(10, 167, 135, 14);
		contentPane.add(lblFechaFin);
		
		textFieldFechaFin = new JTextField();
		textFieldFechaFin.setBounds(155, 164, 100, 20);
		contentPane.add(textFieldFechaFin);
		textFieldFechaFin.setColumns(10);
		
		lblNewLabel_2 = new JLabel("Mail:");
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_2.setBounds(10, 133, 135, 14);
		contentPane.add(lblNewLabel_2);
		
		textFieldMail = new JTextField();
		textFieldMail.setBounds(155, 130, 174, 20);
		contentPane.add(textFieldMail);
		textFieldMail.setColumns(10);
		
		textFieldAgasajado = new JTextField();
		textFieldAgasajado.setBounds(155, 37, 174, 20);
		contentPane.add(textFieldAgasajado);
		textFieldAgasajado.setColumns(10);
		
		lblNewLabel_3 = new JLabel("Nombre del agasajado:");
		lblNewLabel_3.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_3.setBounds(10, 40, 135, 14);
		contentPane.add(lblNewLabel_3);
		
		mensaje = new JLabel("");
		mensaje.setBounds(10, 243, 414, 23);
		contentPane.add(mensaje);
		
		JButton btnGuardar = new JButton("Guardar");
		btnGuardar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try{
					SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
					mensaje.setForeground(Color.BLUE);
					
					if(listaMod == 0)
					{
						//Crea la lista junto con los usuarios de lista que se seleccionaron
						CtrlABMListas.getInstancia().crearLista(textFieldAgasajado.getText(), Integer.parseInt(textFieldMonto.getText()), formatter.parse(textFieldFechaInicio.getText()), textFieldMail.getText(), formatter.parse(textFieldFechaFin.getText()) , formatter.parse(textFieldFechaAgasajo.getText()));
					    mensaje.setText("Lista guardada correctamente!");
					}
					else 
					{
						CtrlABMListas.getInstancia().modificarLista(listaMod,formatter.parse(textFieldFechaAgasajo.getText()), Integer.parseInt(textFieldMonto.getText()), formatter.parse(textFieldFechaFin.getText()), textFieldMail.getText(), formatter.parse(textFieldFechaInicio.getText()));						
					    mensaje.setText("Lista Modificada correctamente!");
					}			
				}catch(Exception ex) {
					mensaje.setForeground(Color.RED);
					mensaje.setText(ex.getMessage());
				}
			}
		});
		btnGuardar.setBounds(250, 311, 100, 23);
		contentPane.add(btnGuardar);
		
		JLabel lblNewLabel_4 = new JLabel("Fecha de inicio:");
		lblNewLabel_4.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_4.setBounds(10, 102, 135, 14);
		contentPane.add(lblNewLabel_4);
		
		textFieldFechaInicio = new JTextField();
		textFieldFechaInicio.setBounds(155, 99, 100, 20);
		contentPane.add(textFieldFechaInicio);
		textFieldFechaInicio.setColumns(10);
		
		JLabel lblUsuariosParaAgregar = new JLabel("Usuarios para agregar:");
		lblUsuariosParaAgregar.setBounds(339, 14, 243, 14);
		contentPane.add(lblUsuariosParaAgregar);
		
		JButton btnAgregar = new JButton("Agregar");
		btnAgregar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//Clic botón agregar
				List<String> lista = new ArrayList<String>();
				lista = listNueva.getSelectedValuesList();
				usuariosParaAgregar = new ArrayList<String>();
				for(String item : lista) {
					usuariosParaAgregar.add(item);
				}
			}
		});
		btnAgregar.setBounds(427, 227, 89, 23);
		contentPane.add(btnAgregar);
		Vector<Usuario> lista = new Vector<Usuario>();
		lista = CtrlABMListas.getInstancia().buscarUsuarios();
		for (Usuario item : lista) {
			model.addElement(item.getUsuario());
		}
		
		textAreaMsgError = new JTextArea();
		textAreaMsgError.setForeground(Color.RED);
		textAreaMsgError.setBounds(126, 219, 271, 47);		
	    textAreaMsgError.setWrapStyleWord(true);
	    textAreaMsgError.setLineWrap(true);
	    textAreaMsgError.setOpaque(false);
	    textAreaMsgError.setEditable(false);
	    textAreaMsgError.setFocusable(false);
	    textAreaMsgError.setBackground(UIManager.getColor("Label.background"));
	    textAreaMsgError.setFont(UIManager.getFont("Label.font"));
	    textAreaMsgError.setBorder(UIManager.getBorder("Label.border"));
		contentPane.add(textAreaMsgError);
		
		
		contentPane.add(listNueva);
		
		setearVistaModificacion();
		
	}
	
	private void setearVistaModificacion()
	{
		try
		{
			if(this.listaMod != 0)
			{
				String[] lista = CtrlABMListas.getInstancia().buscarListaParaModificar(this.listaMod);
			
				if(lista != null)
				{
					textFieldFechaAgasajo.setText(lista[0]);
					textFieldMonto.setText(lista[1]);
					textFieldFechaFin.setText(lista[2]);
					textFieldMail.setText(lista[3]);
					textFieldFechaInicio.setText(lista[4]);
					textFieldAgasajado.setText(lista[5]);
					textFieldAgasajado.setEnabled(false);
				}
				else 
					throw new Exception("La lista a Modificar no existe");
			}
		}
		catch(Exception e)
		{
			textAreaMsgError.setText(e.getMessage());
		}
	}
}
