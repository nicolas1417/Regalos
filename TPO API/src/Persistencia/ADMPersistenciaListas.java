package Persistencia;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Vector;

import Controladores.CtrlSesion;
import Negocio.Lista;
import Negocio.Pago;
import Negocio.Usuario;
import Negocio.UsuarioDeLista;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

public class ADMPersistenciaListas 
{
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
			Connection con = DataAccess.getConexion().getInstanciaDB();
			insertLista = con.prepareStatement("INSERT INTO LISTA (fechaAgasajo, montoPorParticipante, montoRecaudado, fechaInicio, fechaFin, estado, mail, nombreAgasajado) VALUES(?,?,?,?,?,?,?,?)");
			
			DateFormat daf = new SimpleDateFormat("yyyy-M-d");
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
					
			DataAccess.getConexion().cerrarConexion();
			
			idLista = obtenerUltimoId();
			
			con = DataAccess.getConexion().getInstanciaDB();
			
			insertUsuarioDeLista = con.prepareStatement("INSERT INTO USUARIODELISTA (USUARIO,IDLISTA,ESTADO,IDPAGO) VALUES (?,?,?,?)");
			
			insertUsuarioDeLista.setString(1, logueado.getUsuario());
			insertUsuarioDeLista.setInt(2, idLista);
			insertUsuarioDeLista.setInt(3, 1);
			insertUsuarioDeLista.setInt(4, 0);
			
			insertUsuarioDeLista.execute();
			
			DataAccess.getConexion().cerrarConexion();
		}catch(Exception ex){
			ex.printStackTrace();
			throw ex;
		}
		
		PreparedStatement s2;
		try {
				
			for(String item : usuarios) {
				Connection con = DataAccess.getConexion().getInstanciaDB();
				s2 = con.prepareStatement("INSERT INTO USUARIODELISTA(usuario, idlista, estado) VALUES(?,?,?)");
				s2.setString(1, item);
				s2.setInt(2,idLista);
				s2.setInt(3, 1);
				s2.execute();
				DataAccess.getConexion().cerrarConexion();
			}
		}catch (Exception ex) {
			
		}		
	}
	
	public boolean bajaParticipante(String id, String usuario) throws Exception {
		try {
			PreparedStatement s;
			Connection con = DataAccess.getConexion().getInstanciaDB();
			s = con.prepareStatement("update USUARIODELISTA SET estado = 0 WHERE idLista = " + id + " AND usuario = '" + usuario + "'");
			s.execute();
			DataAccess.getConexion().cerrarConexion();
			return true;
		}catch(Exception ex) {
			throw ex;
		}
	}
	
	public List<List<String>> buscarMisListas(String logueado) throws SQLException {
		List<List<String>> res = new ArrayList<List<String>>();
		try {
			PreparedStatement s;
			Connection con = DataAccess.getConexion().getInstanciaDB();
			s = con.prepareStatement("select * from LISTA INNER JOIN USUARIODELISTA ON USUARIODELISTA.idLista=LISTA.idLista where usuario = '" + logueado + "';");
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
			DataAccess.getConexion().cerrarConexion();
			return res;
			
		}catch(Exception ex) {
			throw ex;
		}
	}
	
	private int obtenerUltimoId() throws SQLException {
		try
		{
			PreparedStatement s;
			Connection con = DataAccess.getConexion().getInstanciaDB();
			s = con.prepareStatement("select MAX(idLista) from lista");
			ResultSet rs = s.executeQuery();
			
			rs.next();			
			int res = rs.getInt(1);
			
			DataAccess.getConexion().cerrarConexion();
			
			return res;
		}
		catch(Exception e)
		{
			throw e;
		}
	}
	
	private int obtenerMontoRecaudado(int idLista) throws SQLException {
		try
		{
			PreparedStatement s;
			Connection con = DataAccess.getConexion().getInstanciaDB();
			s = con.prepareStatement("select montoRecaudado from lista where idlista = ?");
			s.setInt(1, idLista);
			ResultSet rs = s.executeQuery();
			
			rs.next();			
			int res = rs.getInt(1);
			
			DataAccess.getConexion().cerrarConexion();
			
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
			Connection con = DataAccess.getConexion().getInstanciaDB();
			PreparedStatement s = con.prepareStatement("SELECT l.idLista,l.nombreAgasajado, l.fechaAgasajo, l.montoPorParticipante, l.montoRecaudado, l.fechaInicio, l.fechaFin,l.mail, l.estado, u.usuario, u.contrasena, u.nombre,u.tipo, u.fechaNacimiento, u.mail, u.estado FROM USUARIO u, USUARIODELISTA ul, LISTA l WHERE u.usuario = ? AND u.tipo = 1 AND u.usuario = ul.usuario AND ul.idLista = l.idLista;");		
			
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
				
				UsuarioDeLista ul = new UsuarioDeLista(u, true, null);
						
				Lista l = new Lista(idLista, nombreAgasajado, fechaAgasajo, montoParticipante, montoRecaudado, fechaInicio, fechaFin, estadoLista==1, mailLista, ul);
											
				v.add(l);
			}										
			
			DataAccess.getConexion().cerrarConexion();
					
			return v;
		}
		catch(Exception e)
		{
			throw e;
		}
	}
	
	public void modificarLista(int idLista, java.util.Date fechaAga, int montoPart, java.util.Date fechaF, String correo, java.util.Date fechaI) throws Exception
	{
		Connection con = DataAccess.getConexion().getInstanciaDB();
		PreparedStatement s;
		
		try
		{
			s = con.prepareStatement("UPDATE LISTA SET fechaAgasajo = ?,montoPorParticipante = ?,fechaFin = ?, mail = ?, fechaInicio = ? WHERE idLista = ?");
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
			
			DataAccess.getConexion().cerrarConexion();
		}
		catch(Exception e)
		{
			throw e;
		}
	}
	
	public void eliminarLista(int idLista) throws Exception
	{
		Connection con = DataAccess.getConexion().getInstanciaDB();
		PreparedStatement s;
		try
		{
			s = con.prepareStatement("UPDATE lista SET estado = 0 where idlista = ?");
			s.setInt(1,idLista);
			s.execute();
			
			DataAccess.getConexion().cerrarConexion();
		}
		catch(Exception e) 
		{
			throw e;
		}
	}
	
	public List<UsuarioDeLista> obtenerParticipantes(int idLista) throws Exception
	{
		List<UsuarioDeLista> v = new ArrayList<UsuarioDeLista>();
		try
		{
			Connection con = DataAccess.getConexion().getInstanciaDB();
			PreparedStatement s = con.prepareStatement("select u.usuario,u.contrasena,u.nombre,u.tipo,u.fechaNacimiento,u.mail,ul.estado,p.monto,p.fechaMov from usuariodelista ul inner join usuario u on ul.usuario = u.usuario left join pago p on ul.idPago = p.idPago where u.tipo = 0 and ul.idlista = ?");		
			
			s.setInt(1,idLista);
			
			ResultSet result = s.executeQuery();
			
			while(result.next())
			{
				String user = result.getString(1);
				String contrasena = result.getString(2);
				String nombre = result.getString(3);
				int idTipoUsuario = result.getInt(4);
				
				Date fecha_nac = result.getDate(5);
				String mail = result.getString(6);
				
				int estado = result.getInt(7);
				boolean tipoUsuario = idTipoUsuario > 0 ? true : false;
				
				int monto = result.getInt(8);
				Date fechaMov = result.getDate(9);				
				
				Usuario u = new Usuario(nombre,user,contrasena,fecha_nac,estado==1,mail,tipoUsuario);
			
				Pago p = null;
				if (monto != 0)
					p = new Pago(monto, fechaMov);
				
				UsuarioDeLista ul = new UsuarioDeLista(u,estado==1,p);
				
				v.add(ul);
			}										
			
			DataAccess.getConexion().cerrarConexion();
					
			return v;
		}
		catch(Exception e)
		{
			throw e;
		}
	}
	
	public int RegistrarPago(String usuario,int idLista,int monto,int idPago) throws Exception
	{
		int montoRecaudado = obtenerMontoRecaudado(idLista);
		montoRecaudado = montoRecaudado + monto;
		
		Connection con = DataAccess.getConexion().getInstanciaDB();
		PreparedStatement s;
		try
		{
			s = con.prepareStatement("UPDATE USUARIODELISTA SET idPago = ? WHERE idLista = ? and usuario = ?");
			s.setInt(1,idPago);
			s.setInt(2,idLista);
			s.setString(3,usuario);
			s.execute();
			
			DataAccess.getConexion().cerrarConexion();
		}
		catch(Exception e) 
		{
			throw e;
		}
		
		con = DataAccess.getConexion().getInstanciaDB();
		PreparedStatement s2;
		try
		{
			s2 = con.prepareStatement("UPDATE LISTA SET montoRecaudado = ? WHERE idLista = ?");
			s2.setInt(1,montoRecaudado);
			s2.setInt(2,idLista);
			s2.execute();
			
			DataAccess.getConexion().cerrarConexion();
		}
		catch(Exception e) 
		{
			throw e;
		}
		return montoRecaudado;
	}
}
