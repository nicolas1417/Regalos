package Vistas;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Controladores.ABMListas;

import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Date;
import java.awt.event.ActionEvent;

public class AltaLista extends JFrame {

	private JPanel contentPane;
	private JTextField textFieldAgasajo;
	private JTextField textFieldMonto;
	private JLabel lblFechaFin;
	private JTextField textFieldFechaFin;
	private JLabel lblNewLabel_2;
	private JTextField textFieldMail;
	private JTextField textFieldUsuario;
	private JLabel lblNewLabel_3;
	private JLabel mensaje;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AltaLista frame = new AltaLista();
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
	public AltaLista() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Fecha del agasajo:");
		lblNewLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel.setBounds(10, 40, 127, 14);
		contentPane.add(lblNewLabel);
		
		textFieldAgasajo = new JTextField();
		textFieldAgasajo.setBounds(147, 37, 117, 20);
		contentPane.add(textFieldAgasajo);
		textFieldAgasajo.setColumns(10);
		
		textFieldMonto = new JTextField();
		textFieldMonto.setBounds(147, 68, 117, 20);
		contentPane.add(textFieldMonto);
		textFieldMonto.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("Monto por participante:");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_1.setBounds(10, 71, 127, 14);
		contentPane.add(lblNewLabel_1);
		
		lblFechaFin = new JLabel("Fecha fin:");
		lblFechaFin.setHorizontalAlignment(SwingConstants.RIGHT);
		lblFechaFin.setBounds(20, 102, 117, 14);
		contentPane.add(lblFechaFin);
		
		textFieldFechaFin = new JTextField();
		textFieldFechaFin.setBounds(147, 99, 117, 20);
		contentPane.add(textFieldFechaFin);
		textFieldFechaFin.setColumns(10);
		
		lblNewLabel_2 = new JLabel("Mail:");
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_2.setBounds(92, 133, 46, 14);
		contentPane.add(lblNewLabel_2);
		
		textFieldMail = new JTextField();
		textFieldMail.setBounds(147, 130, 117, 20);
		contentPane.add(textFieldMail);
		textFieldMail.setColumns(10);
		
		textFieldUsuario = new JTextField();
		textFieldUsuario.setBounds(147, 161, 117, 20);
		contentPane.add(textFieldUsuario);
		textFieldUsuario.setColumns(10);
		
		lblNewLabel_3 = new JLabel("Usuario:");
		lblNewLabel_3.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_3.setBounds(91, 164, 46, 14);
		contentPane.add(lblNewLabel_3);
		
		mensaje = new JLabel("");
		mensaje.setBounds(92, 192, 224, 58);
		contentPane.add(mensaje);
		
		JButton btnGuardar = new JButton("Guardar");
		btnGuardar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try{
					ABMListas.getInstancia().crearLista(textFieldAgasajo.getText(), Integer.parseInt(textFieldMonto.getText()), textFieldFechaFin.getText(), textFieldMail.getText(), textFieldUsuario.getText());
					
					mensaje.setForeground(Color.BLUE);
				    mensaje.setText("Lista guardada correctamente!");
				}catch(Exception ex) {
					mensaje.setForeground(Color.RED);
					mensaje.setText(ex.getMessage());
				}
				
				
			}
		});
		btnGuardar.setBounds(335, 227, 89, 23);
		contentPane.add(btnGuardar);
	}
}
