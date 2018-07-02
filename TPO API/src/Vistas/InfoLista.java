package Vistas;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.util.List;

import Vistas.InicioDeUsuario;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Controladores.CtrlABMListas;
import Controladores.CtrlSesion;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class InfoLista extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					InfoLista frame = new InfoLista();
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
	public InfoLista() {
		
		setResizable(false);
		setTitle("Informaci\u00F3n de lista seleccionada");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 331);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNombreDelAgasajado = new JLabel("Nombre del agasajado:");
		lblNombreDelAgasajado.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNombreDelAgasajado.setBounds(10, 11, 140, 14);
		contentPane.add(lblNombreDelAgasajado);
		
		JLabel lblFechaDelAgasajo = new JLabel("Fecha del agasajo:");
		lblFechaDelAgasajo.setHorizontalAlignment(SwingConstants.RIGHT);
		lblFechaDelAgasajo.setBounds(10, 36, 140, 14);
		contentPane.add(lblFechaDelAgasajo);
		
		JLabel lblMontoPorParticipante = new JLabel("Monto por participante:");
		lblMontoPorParticipante.setHorizontalAlignment(SwingConstants.RIGHT);
		lblMontoPorParticipante.setBounds(10, 61, 140, 14);
		contentPane.add(lblMontoPorParticipante);
		
		JLabel lblMontoRecaudado = new JLabel("Monto recaudado:");
		lblMontoRecaudado.setHorizontalAlignment(SwingConstants.RIGHT);
		lblMontoRecaudado.setBounds(10, 86, 140, 14);
		contentPane.add(lblMontoRecaudado);
		
		JLabel lblFechaDeInicio = new JLabel("Fecha de inicio:");
		lblFechaDeInicio.setHorizontalAlignment(SwingConstants.RIGHT);
		lblFechaDeInicio.setBounds(10, 111, 140, 14);
		contentPane.add(lblFechaDeInicio);
		
		JLabel lblFechaDeFin = new JLabel("Fecha de fin:");
		lblFechaDeFin.setHorizontalAlignment(SwingConstants.RIGHT);
		lblFechaDeFin.setBounds(10, 136, 140, 14);
		contentPane.add(lblFechaDeFin);
		
		JLabel lblEstado = new JLabel("Estado de la lista: ");
		lblEstado.setHorizontalAlignment(SwingConstants.RIGHT);
		lblEstado.setBounds(10, 161, 140, 14);
		contentPane.add(lblEstado);
		
		JLabel lblMailDeLa = new JLabel("Mail de la lista:");
		lblMailDeLa.setHorizontalAlignment(SwingConstants.RIGHT);
		lblMailDeLa.setBounds(10, 186, 140, 14);
		contentPane.add(lblMailDeLa);
		
		JLabel lblPagoRealizado = new JLabel("Pago realizado:");
		lblPagoRealizado.setHorizontalAlignment(SwingConstants.RIGHT);
		lblPagoRealizado.setBounds(10, 211, 140, 14);
		contentPane.add(lblPagoRealizado);
		
		JLabel NombreAgasajado = new JLabel("----");
		NombreAgasajado.setBounds(160, 11, 274, 14);
		contentPane.add(NombreAgasajado);
		
		JLabel FechaAgasajo = new JLabel("----");
		FechaAgasajo.setBounds(160, 36, 274, 14);
		contentPane.add(FechaAgasajo);
		
		JLabel MontoParticipante = new JLabel("----");
		MontoParticipante.setBounds(160, 61, 274, 14);
		contentPane.add(MontoParticipante);
		
		JLabel MontoRecaudado = new JLabel("----");
		MontoRecaudado.setBounds(160, 86, 274, 14);
		contentPane.add(MontoRecaudado);
		
		JLabel FechaInicio = new JLabel("----");
		FechaInicio.setBounds(160, 111, 274, 14);
		contentPane.add(FechaInicio);
		
		JLabel FechaFin = new JLabel("----");
		FechaFin.setBounds(160, 136, 274, 14);
		contentPane.add(FechaFin);
		
		JLabel EstadoLista = new JLabel("----");
		EstadoLista.setBounds(160, 161, 274, 14);
		contentPane.add(EstadoLista);
		
		JLabel MailLista = new JLabel("----");
		MailLista.setBounds(160, 186, 274, 14);
		contentPane.add(MailLista);
		
		JLabel PagoRealizado = new JLabel("----");
		PagoRealizado.setBounds(160, 211, 274, 14);
		contentPane.add(PagoRealizado);
		
		JLabel lblMiEstadoEn = new JLabel("Mi estado en la lista:");
		lblMiEstadoEn.setHorizontalAlignment(SwingConstants.RIGHT);
		lblMiEstadoEn.setBounds(10, 236, 140, 14);
		contentPane.add(lblMiEstadoEn);
		
		JLabel EstadoUsuarioEnLista = new JLabel("----");
		EstadoUsuarioEnLista.setBounds(160, 236, 274, 14);
		contentPane.add(EstadoUsuarioEnLista);
		
		FechaAgasajo.setText(InicioDeUsuario.listaSeleccionada.get(1));
		MontoParticipante.setText(InicioDeUsuario.listaSeleccionada.get(2));
		MontoRecaudado.setText(InicioDeUsuario.listaSeleccionada.get(3));
		FechaInicio.setText(InicioDeUsuario.listaSeleccionada.get(4));
		FechaFin.setText(InicioDeUsuario.listaSeleccionada.get(5));
		if(InicioDeUsuario.listaSeleccionada.get(6).equals("1")) {
			EstadoLista.setText("Activa");
		}else {
			EstadoLista.setText("Inactiva");
		}
		MailLista.setText(InicioDeUsuario.listaSeleccionada.get(7));
		NombreAgasajado.setText(InicioDeUsuario.listaSeleccionada.get(8));
		if(InicioDeUsuario.listaSeleccionada.get(13).equals("1")) 
			EstadoUsuarioEnLista.setText("Activo");
		else
			EstadoUsuarioEnLista.setText("Dado de baja");
			 		
		JButton btnDarmeDeBaja = new JButton("Darme de baja de esta lista");
		btnDarmeDeBaja.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent arg0) {
				boolean baja = false;
					try {
						baja = CtrlABMListas.getInstancia().bajaParticipante(InicioDeUsuario.listaSeleccionada.get(0), CtrlSesion.getInstancia().getUsuarioLogueado().getUsuario());
					} catch (Exception e) {
						e.printStackTrace();
					}
				if(baja) 
					JOptionPane.showMessageDialog(InfoLista.this, "Usted se ha dado de baja de la lista", "Baja exitosa", JOptionPane.INFORMATION_MESSAGE);
				else
					JOptionPane.showMessageDialog(InfoLista.this, "No se pudo procesar la baja", "Error", JOptionPane.ERROR_MESSAGE);
			}
		});
		btnDarmeDeBaja.setBounds(10, 269, 205, 23);
		contentPane.add(btnDarmeDeBaja);
		
		
		if(InicioDeUsuario.listaSeleccionada.get(9).equals("1")){
			PagoRealizado.setText("Pagado");
		}else {
			PagoRealizado.setText("Pago pendiente");
		}
		//PagoRealizado.setText(InicioDeUsuario.listaSeleccionada.get(9));
	}
}
