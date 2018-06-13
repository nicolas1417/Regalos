package Negocio;

import java.util.Date;
import java.util.Vector;

import Persistencia.ADMPersistenciaUsuarios;


public class Usuario {
	
	private String nombre;
	private Date fechaNacimiento;
	private String mail;
	private String usuario;
	private String password;
	private boolean estado;
	private TipoUsuario tipoUsuario;
	
	private static Usuario instancia;
	
	public static Usuario getInstancia()
	{
		if (instancia == null)
			instancia = new Usuario();
		return instancia;
	}
	
	public Usuario()
	{
		
	}
		
	public Usuario(String nombre, String usuario, String pass, Date nacimiento,boolean estado, String mail,TipoUsuario tu){		
		this.nombre = nombre;
		this.usuario = usuario;
		this.password = pass;
		this.fechaNacimiento = nacimiento;
		this.estado = estado;
		this.mail = mail;
		this.tipoUsuario = tu;
	}
	
	public void AltaUsuario(String usuario, String contrasena, String nombre,int idTipoUsuario, Date fechaNacimiento,String mail) throws Exception
	{
		try
		{			
			ADMPersistenciaUsuarios.getInstancia().altaUsuario(usuario,contrasena,nombre,obtenerTipoUsuario(idTipoUsuario).getId(),fechaNacimiento,mail);
		}
		catch(Exception e)
		{
			throw e;
		}
	}	
	
	public void modificarUsuario(String usuario, String contrasena,String passConfirmada, String nombre,int idTipoUsuario, Date fechaNacimiento,String mail) throws Exception
	{
		try
		{
			if (!this.validarContrasena(contrasena, passConfirmada))
				throw new Exception("Las contrasenas no coinciden");
					
			ADMPersistenciaUsuarios.getInstancia().modificarUsuario(usuario, contrasena, nombre, obtenerTipoUsuario(idTipoUsuario), fechaNacimiento, mail);
		}
		catch(Exception e) 
		{
			throw e;
		}
	}
	
	private TipoUsuario obtenerTipoUsuario(int idTipoUsuario) throws Exception
	{
		try
		{
			Vector<TipoUsuario> v = TipoUsuario.getInstancia().obtenerTiposDeUsuario();
			TipoUsuario tu = null;
			
			for(int i=0;i<v.size();i++)
			{
				if (v.elementAt(i).getId() == idTipoUsuario)
				{
					tu = v.elementAt(i);
					return tu;
				}
			}
		}
		catch (Exception e)
		{
			throw e;
		}
		return null;
	}
	
	public void eliminarUsuario(String usuario) throws Exception
	{
		try
		{
			ADMPersistenciaUsuarios.getInstancia().eliminarUsuario(usuario);
		}
		catch(Exception e) 
		{
			throw e;
		}
	}
	
	public void validarAltaUsuario(String usuario, String password, String passConfirmada) throws Exception
	{
		try
		{
			if (!ADMPersistenciaUsuarios.getInstancia().validarUsuario(usuario))
				throw new Exception("Usuario ya existe");
			
			if(!validarContrasena(password,passConfirmada))
				throw new Exception("Las contrasenas no coinciden");
		}
		catch (Exception e)
		{
			throw e;
		}
	}
	
	public boolean validarContrasena(String password, String passConfirmada) 
	{
		return (password.equals(passConfirmada));
	}
	
	public Vector<Usuario> buscarUsuarios() throws Exception
	{
		try
		{
			return ADMPersistenciaUsuarios.getInstancia().buscarUsuarios();
		}
		catch(Exception e)
		{
			throw e;
		}
	}
	
	public String[] obtenerTiposDeUsuario() throws Exception
	{
		try
		{
			Vector<TipoUsuario> v = TipoUsuario.getInstancia().obtenerTiposDeUsuario();
			String[] s = new String[v.size()];
			
			for(int i=0;i<v.size();i++)
			{
				s[i] = v.elementAt(i).getCodigo();
			}
			
			return s;
		}
		catch (Exception e) 
		{
			throw e;
		}
	}

	public String getNombre() {
		return nombre;
	}
	
	public String getUsuario() {
		return usuario;
	}
	
	public Date getFechaNac() {
		return fechaNacimiento;
	}
	
	public String getCodTipo() {
		return tipoUsuario.getCodigo();
	}
	
	public int getIdTipo() {
		return tipoUsuario.getId();
	}
	
	public String getMail() {
		return mail;
	}
	
	public String getDescEstado() {
		return estado?"Activo":"Inhabilitado";
	}
	
	public boolean getEstado() {
		return estado;
	}	
}
