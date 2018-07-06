package Vistas;

import java.util.Observable;
import java.util.Observer;

import Vistas.InicioDeUsuario;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Controladores.CtrlABMListas;
import Controladores.CtrlSesion;
import ObservadorDePago.ObservadorPago;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class InfoLista extends JFrame implements Observer{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	JLabel NombreAgasajado = new JLabel("----");
	JLabel FechaAgasajo = new JLabel("----");
	JLabel MontoParticipante = new JLabel("----");
	JLabel MontoRecaudado = new JLabel("----");
	JLabel FechaInicio = new JLabel("----");
	JLabel FechaFin = new JLabel("----");
	JLabel EstadoLista = new JLabel("----");
	JLabel MailLista = new JLabel("----");
	JLabel EstadoUsuarioEnLista = new JLabel("----");
	JLabel lblAdminLista = new JLabel("----");
	JLabel adminLista = new JLabel("----");
	JButton btnDarmeDeBaja = new JButton("Darme de baja de esta lista");
	private ObservadorPago miObservador;
	int idLista;
	
	/**
	 * Create the frame.
	 */
	public InfoLista(ObservadorPago obsPago) {
		
		miObservador = obsPago;
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
		
		JLabel lblEstado = new JLabel("Estado de la lista:");
		lblEstado.setHorizontalAlignment(SwingConstants.RIGHT);
		lblEstado.setBounds(10, 161, 140, 14);
		contentPane.add(lblEstado);
		
		JLabel lblMailDeLa = new JLabel("Mail de la lista:");
		lblMailDeLa.setHorizontalAlignment(SwingConstants.RIGHT);
		lblMailDeLa.setBounds(10, 186, 140, 14);
		contentPane.add(lblMailDeLa);
		
		NombreAgasajado.setBounds(160, 11, 274, 14);
		contentPane.add(NombreAgasajado);
		
		FechaAgasajo.setBounds(160, 36, 274, 14);
		contentPane.add(FechaAgasajo);
		
		MontoParticipante.setBounds(160, 61, 274, 14);
		contentPane.add(MontoParticipante);
		
		MontoRecaudado.setBounds(160, 86, 274, 14);
		contentPane.add(MontoRecaudado);
		
		FechaInicio.setBounds(160, 111, 274, 14);
		contentPane.add(FechaInicio);
		
		FechaFin.setBounds(160, 136, 274, 14);
		contentPane.add(FechaFin);
		
		EstadoLista.setBounds(160, 161, 274, 14);
		contentPane.add(EstadoLista);
		
		MailLista.setBounds(160, 186, 274, 14);
		contentPane.add(MailLista);
		
		JLabel lblMiEstadoEn = new JLabel("Mi estado en la lista:");
		lblMiEstadoEn.setHorizontalAlignment(SwingConstants.RIGHT);
		lblMiEstadoEn.setBounds(10, 211, 140, 14);
		contentPane.add(lblMiEstadoEn);
		
		EstadoUsuarioEnLista.setBounds(160, 211, 274, 14);
		contentPane.add(EstadoUsuarioEnLista);
		
		setearEtiquetas();
		
		btnDarmeDeBaja.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent arg0) {
				if (btnDarmeDeBaja.isEnabled()) {
					boolean baja = false;
					try {
						baja = CtrlABMListas.getInstancia().bajaParticipante(InicioDeUsuario.listaSeleccionada.get(0),
								InicioDeUsuario.listaSeleccionada.get(14),CtrlSesion.getInstancia().getUsuarioLogueado().getUsuario());
					} catch (Exception e) {
						e.printStackTrace();
					}
					if (baja) {
						JOptionPane.showMessageDialog(InfoLista.this, "Usted se ha dado de baja de la lista",
								"Baja exitosa", JOptionPane.INFORMATION_MESSAGE);
						try {
							InicioDeUsuario.misListas = CtrlABMListas.getInstancia().buscarMisListas();
						} catch (Exception e) {
							e.printStackTrace();
						}
						InicioDeUsuario.listaSeleccionada.set(12, "0");
						setearEtiquetas();
					} else
						JOptionPane.showMessageDialog(InfoLista.this, "No se ha procesado la baja. El usuario logueado es el administrador de la lista", "Error",
								JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		btnDarmeDeBaja.setBounds(10, 269, 205, 23);
		contentPane.add(btnDarmeDeBaja);		
		
		lblAdminLista = new JLabel("Administrador de la lista:");
		lblAdminLista.setHorizontalAlignment(SwingConstants.RIGHT);
		lblAdminLista.setBounds(10, 236, 140, 14);
		contentPane.add(lblAdminLista);
		
		adminLista.setBounds(160, 236, 274, 14);
		contentPane.add(adminLista);
	}
	
	private void setearEtiquetas() {
		try
		{
			idLista = Integer.parseInt(InicioDeUsuario.listaSeleccionada.get(0));
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
			if(InicioDeUsuario.listaSeleccionada.get(12).equals("1")) {
				EstadoUsuarioEnLista.setText("Activo");
				btnDarmeDeBaja.setEnabled(true);
			}
			else {
				EstadoUsuarioEnLista.setText("Dado de baja");
				btnDarmeDeBaja.setEnabled(false);
			}
			
			adminLista.setText(InicioDeUsuario.listaSeleccionada.get(14));
			}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}

	@Override
	public void update(Observable arg0, Object arg1) {
		if (miObservador.getIdLista() == this.idLista)
			MontoRecaudado.setText(String.valueOf(miObservador.getMontoRecaudado()));	
	}
}
