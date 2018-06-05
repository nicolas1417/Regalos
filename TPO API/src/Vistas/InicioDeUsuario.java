package Vistas;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JMenuBar;
import javax.swing.JLabel;
import javax.swing.JList;
import java.awt.List;

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
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Bienvenido @usuario");
		lblNewLabel.setBounds(0, 0, 442, 14);
		contentPane.add(lblNewLabel);
		
		JLabel lblMisListas = new JLabel("Mis listas:");
		lblMisListas.setBounds(0, 22, 46, 14);
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
