package Persistencia;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Vector;

import Negocio.Lista;
import Negocio.Usuario;
import Negocio.UsuarioDeLista;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

public class ADMPersistenciaListas {

	private Connection laConexion;
	private static ADMPersistenciaListas instancia;
	
	private ADMPersistenciaListas()
	{
		
	}
	
	public static ADMPersistenciaListas getInstancia()
	{
		if (instancia == null)
			instancia = new ADMPersistenciaListas();
		return instancia;
	}
	
	public void altaLista(String nombreAgasajado, int montoParticipante, java.util.Date fechaInicio, String mail, java.util.Date fechaFin, java.util.Date fechaDelAgasajo, List<String> usuarios, Usuario logueado) throws Exception 
	{
		PreparedStatement insertLista;
		PreparedStatement insertUsuarioDeLista;
		int idLista = 0;
		try {
			DateFormat daf = new SimpleDateFormat("yyyy-M-d");
			
			laConexion = DataAccess.Conectar();
			
			insertLista = laConexion.prepareStatement("INSERT INTO LISTA (fechaAgasajo, montoPorParticipante, montoRecaudado, fechaInicio, fechaFin, estado, mail, nombreAgasajado) VALUES(?,?,?,?,?,?,?,?)");
			String fechaAgaParaSQL = daf.format(fechaDelAgasajo);
			insertLista.setDate(1, java.sql.Date.valueOf(fechaAgaParaSQL));			
			insertLista.setInt(2, montoParticipante);
			insertLista.setInt(3, 0);
			String fechaIniParaSQL = daf.format(fechaInicio);
			insertLista.setDate(4, java.sql.Date.valueOf(fechaIniParaSQL));
			String fechaFinParaSQL = daf.format(fechaFin);
			insertLista.setDate(5, java.sql.Date.valueOf(fechaFinParaSQL));
			insertLista.setInt(6, 1);
			insertLista.setString(7, mail);
			insertLista.setString(8, nombreAgasajado);
			
			insertLista.execute();
					
			laConexion.close();
			
			idLista = obtenerUltimoId();
			
			laConexion = DataAccess.Conectar();
			
			insertUsuarioDeLista = laConexion.prepareStatement("INSERT INTO USUARIODELISTA (USUARIO,IDLISTA,ESTADO,PAGADO) VALUES (?,?,?,?)");
			
			insertUsuarioDeLista.setString(1, logueado.getUsuario());
			insertUsuarioDeLista.setInt(2, idLista);
			insertUsuarioDeLista.setInt(3, 1);
			insertUsuarioDeLista.setInt(4, 0);
			
			insertUsuarioDeLista.execute();
			
			laConexion.close();
		}catch(Exception ex){
			ex.printStackTrace();
			throw ex;
		}
		
		PreparedStatement s2;
		try {
				
			for(String item : usuarios) {
				laConexion = DataAccess.Conectar();
				s2 = laConexion.prepareStatement("INSERT INTO USUARIODELISTA(usuario, idlista, estado) VALUES(?,?,?)");
				s2.setString(1, item);
				s2.setInt(2,idLista);
				s2.setInt(3, 1);
				s2.execute();
				laConexion.close();
			}
		}catch (Exception ex) {
			
		}		
	}
	
	public List<List<String>> buscarMisListas(String logueado) throws SQLException {
		List<List<String>> res = new ArrayList<List<String>>();
		try {
			PreparedStatement s;
			laConexion = DataAccess.Conectar();
			s = laConexion.prepareStatement("select * from LISTA INNER JOIN USUARIODELISTA ON USUARIODELISTA.idLista=LISTA.idLista where usuario = '" + logueado + "';");
			ResultSet rs = s.executeQuery();
			int i = 0;
			while(rs.next()) {
				List<String> subLista = new ArrayList<String>();
				subLista.add(rs.getString(1));
				subLista.add(rs.getString(2));
				subLista.add(rs.getString(3));
				subLista.add(rs.getString(4));
				subLista.add(rs.getString(5));
				subLista.add(rs.getString(6));
				subLista.add(rs.getString(7));
				subLista.add(rs.getString(8));
				subLista.add(rs.getString(9));
				subLista.add(rs.getString(10));
				subLista.add(rs.getString(11));
				subLista.add(rs.getString(12));
				subLista.add(rs.getString(13));
				subLista.add(rs.getString(14));
				res.add(i, subLista);
				i++;
			}
			return res;
			
		}catch(Exception ex) {
			throw ex;
		}
	}
	
	private int obtenerUltimoId() throws SQLException {
		try
		{
			PreparedStatement s;
			laConexion = DataAccess.Conectar();
			s = laConexion.prepareStatement("select MAX(idLista) from lista");
			ResultSet rs = s.executeQuery();
			
			rs.next();			
			int res = rs.getInt(1);
			
			laConexion.close();
			
			return res;
		}
		catch(Exception e)
		{
			throw e;
		}
	}
	
	public Vector<Lista> buscarListas(String usuario) throws Exception
	{
		Vector<Lista> v = new Vector<Lista>();
		try
		{
			laConexion = DataAccess.Conectar();
			PreparedStatement s = laConexion.prepareStatement("SELECT l.idLista,l.nombreAgasajado, l.fechaAgasajo, l.montoPorParticipante, l.montoRecaudado, l.fechaInicio, l.fechaFin,l.mail, l.estado, u.usuario, u.contrasena, u.nombre,u.tipo, u.fechaNacimiento, u.mail, u.estado FROM USUARIO u, USUARIODELISTA ul, LISTA l WHERE u.usuario = ? AND u.tipo = 1 AND u.usuario = ul.usuario AND ul.idLista = l.idLista;");		
			
			s.setString(1,usuario);
			
			ResultSet result = s.executeQuery();
			
			while(result.next())
			{
				//Datos Lista
				int idLista = result.getInt(1);
				String nombreAgasajado = result.getString(2);
				Date fechaAgasajo = result.getDate(3);
				int montoParticipante = result.getInt(4);
				int montoRecaudado = result.getInt(5);
				Date fechaInicio = result.getDate(6);
				Date fechaFin = result.getDate(7);
				String mailLista = result.getString(8);
				int estadoLista = result.getInt(9);	
				
				//Datos admin de la lista
				String user = result.getString(10);
				String contrasena = result.getString(11);
				String nombre = result.getString(12);
				int idTipoUsuario = result.getInt(13);
				Date fecha_nac = result.getDate(14);
				String mailUsuario = result.getString(15);
				int estadoUsuario = result.getInt(16);
				boolean tipousuario;
				if(idTipoUsuario > 0)
					tipousuario = true;
				else
					tipousuario = false;				
				
				Usuario u = new Usuario(nombre,user,contrasena,fecha_nac,estadoUsuario==1,mailUsuario,tipousuario);
				
				UsuarioDeLista ul = new UsuarioDeLista(u, true, true);
						
				Lista l = new Lista(idLista, nombreAgasajado, fechaAgasajo, montoParticipante, montoRecaudado, fechaInicio, fechaFin, estadoLista==1, mailLista, ul);
											
				v.add(l);
			}										
			
			laConexion.close();
					
			return v;
		}
		catch(Exception e)
		{
			throw e;
		}
	}
	
	public void modificarLista(int idLista, java.util.Date fechaAga, int montoPart, java.util.Date fechaF, String correo, java.util.Date fechaI) throws Exception
	{
		laConexion = DataAccess.Conectar();
		PreparedStatement s;
		
		try
		{
			s = laConexion.prepareStatement("UPDATE LISTA SET fechaAgasajo = ?,montoPorParticipante = ?,fechaFin = ?, mail = ?, fechaInicio = ? WHERE idLista = ?");
			DateFormat daf = new SimpleDateFormat("yyyy-M-d");
			String fechaParaSQL = daf.format(fechaAga);
			s.setDate(1, java.sql.Date.valueOf(fechaParaSQL));
			s.setInt(2, montoPart);
			fechaParaSQL = daf.format(fechaF);
			s.setDate(3, java.sql.Date.valueOf(fechaParaSQL));			
			s.setString(4,correo);
			fechaParaSQL = daf.format(fechaI);
			s.setDate(5, java.sql.Date.valueOf(fechaParaSQL));
			s.setInt(6, idLista);
						
			s.execute();
			
			laConexion.close();
		}
		catch(Exception e)
		{
			throw e;
		}
	}
	
	public void eliminarLista(int idLista) throws Exception
	{
		laConexion = DataAccess.Conectar();
		PreparedStatement s;
		try
		{
			s = laConexion.prepareStatement("UPDATE lista SET estado = 0 where idlista = ?");
			s.setInt(1,idLista);
			s.execute();
			
			laConexion.close();
		}
		catch(Exception e) 
		{
			throw e;
		}
	}
}
