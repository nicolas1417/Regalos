package Vistas;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.border.EmptyBorder;

import Controladores.CtrlABMListas;
import Controladores.CtrlABMUsuarios;
import Negocio.Usuario;

import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Vector;
import java.awt.event.ActionEvent;
import javax.swing.JList;
import java.awt.List;
import javax.swing.JComboBox;

public class AltaModLista extends JFrame {

	private JPanel contentPane;
	private JTextField textFieldAgasajo;
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
	
	private int listaMod = 0;
	
	/**
	 * Create the frame.
	 * @throws Exception 
	 */
	public AltaModLista(int listaMod) throws Exception {
		this.listaMod = listaMod;
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 615, 372);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Fecha del agasajo:");
		lblNewLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel.setBounds(10, 40, 156, 14);
		contentPane.add(lblNewLabel);
		
		textFieldAgasajo = new JTextField();
		textFieldAgasajo.setBounds(176, 37, 117, 20);
		contentPane.add(textFieldAgasajo);
		textFieldAgasajo.setColumns(10);
		
		textFieldMonto = new JTextField();
		textFieldMonto.setBounds(176, 68, 117, 20);
		contentPane.add(textFieldMonto);
		textFieldMonto.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("Monto por participante:");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_1.setBounds(10, 71, 156, 14);
		contentPane.add(lblNewLabel_1);
		
		
		
		lblFechaFin = new JLabel("Fecha fin:");
		lblFechaFin.setHorizontalAlignment(SwingConstants.RIGHT);
		lblFechaFin.setBounds(20, 102, 146, 14);
		contentPane.add(lblFechaFin);
		
		textFieldFechaFin = new JTextField();
		textFieldFechaFin.setBounds(176, 99, 117, 20);
		contentPane.add(textFieldFechaFin);
		textFieldFechaFin.setColumns(10);
		
		lblNewLabel_2 = new JLabel("Mail:");
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_2.setBounds(92, 133, 74, 14);
		contentPane.add(lblNewLabel_2);
		
		textFieldMail = new JTextField();
		textFieldMail.setBounds(176, 130, 117, 20);
		contentPane.add(textFieldMail);
		textFieldMail.setColumns(10);
		
		textFieldAgasajado = new JTextField();
		textFieldAgasajado.setBounds(176, 195, 117, 20);
		contentPane.add(textFieldAgasajado);
		textFieldAgasajado.setColumns(10);
		
		lblNewLabel_3 = new JLabel("Nombre del agasajado:");
		lblNewLabel_3.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_3.setBounds(10, 198, 157, 14);
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
						CtrlABMListas.getInstancia().crearLista(formatter.parse(textFieldAgasajo.getText()), Integer.parseInt(textFieldMonto.getText()), formatter.parse(textFieldFechaFin.getText()), textFieldMail.getText(), formatter.parse(textFieldFechaInicio.getText()), textFieldAgasajado.getText());
					    mensaje.setText("Lista guardada correctamente!");
					}
					else 
					{
						CtrlABMListas.getInstancia().modificarLista(listaMod,formatter.parse(textFieldAgasajo.getText()), Integer.parseInt(textFieldMonto.getText()), formatter.parse(textFieldFechaFin.getText()), textFieldMail.getText(), formatter.parse(textFieldFechaInicio.getText()));						
					    mensaje.setText("Lista Modificada correctamente!");
					}			
				}catch(Exception ex) {
					mensaje.setForeground(Color.RED);
					mensaje.setText(ex.getMessage());
				}
				
				
			}
		});
		btnGuardar.setBounds(335, 299, 89, 23);
		contentPane.add(btnGuardar);
		
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				AltaModLista.this.dispose();
			}
		});
		btnCancelar.setBounds(237, 299, 89, 23);
		contentPane.add(btnCancelar);
		
		JLabel lblNewLabel_4 = new JLabel("Fecha de inicio:");
		lblNewLabel_4.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_4.setBounds(43, 164, 124, 14);
		contentPane.add(lblNewLabel_4);
		
		textFieldFechaInicio = new JTextField();
		textFieldFechaInicio.setBounds(176, 161, 117, 20);
		contentPane.add(textFieldFechaInicio);
		textFieldFechaInicio.setColumns(10);
		
		JLabel lblUsuariosParaAgregar = new JLabel("Usuarios para agregar:");
		lblUsuariosParaAgregar.setBounds(299, 17, 124, 14);
		contentPane.add(lblUsuariosParaAgregar);
		
		JButton btnAgregar = new JButton("Agregar");
		btnAgregar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnAgregar.setBounds(335, 67, 89, 23);
		contentPane.add(btnAgregar);
		Vector<Usuario> lista = new Vector<Usuario>();
		lista = CtrlABMListas.getInstancia().buscarUsuarios();		
		JComboBox comboBoxUsers = new JComboBox();
		comboBoxUsers.setBounds(303, 37, 156, 20);
		for (Usuario item : lista) {
			comboBoxUsers.addItem(item.getUsuario());
		}
		contentPane.add(comboBoxUsers);	
		
		textAreaMsgError = new JTextArea();
		textAreaMsgError.setForeground(Color.RED);
		textAreaMsgError.setBounds(126, 229, 271, 47);		
	    textAreaMsgError.setWrapStyleWord(true);
	    textAreaMsgError.setLineWrap(true);
	    textAreaMsgError.setOpaque(false);
	    textAreaMsgError.setEditable(false);
	    textAreaMsgError.setFocusable(false);
	    textAreaMsgError.setBackground(UIManager.getColor("Label.background"));
	    textAreaMsgError.setFont(UIManager.getFont("Label.font"));
	    textAreaMsgError.setBorder(UIManager.getBorder("Label.border"));
		contentPane.add(textAreaMsgError);
		
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
					textFieldAgasajo.setText(lista[0]);
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
