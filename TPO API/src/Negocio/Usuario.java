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
	private boolean tipoUsuario;
	
	
	public Usuario()
	{
		
	}
		
	public Usuario(String nombre, String usuario, String pass, Date nacimiento,boolean estado, String mail,boolean tu){		
		this.nombre = nombre;
		this.usuario = usuario;
		this.password = pass;
		this.fechaNacimiento = nacimiento;
		this.estado = estado;
		this.mail = mail;
		this.tipoUsuario = tu;
	}
	
	public void AltaUsuario(String usuario, String contrasena, String nombre,boolean tipoUsuario, Date fechaNacimiento,String mail) throws Exception
	{
		try
		{			
			ADMPersistenciaUsuarios.getInstancia().altaUsuario(usuario,contrasena,nombre,tipoUsuario,fechaNacimiento,mail);
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
			ADMPersistenciaUsuarios.getInstancia().modificarUsuario(usuario, contrasena, nombre, idTipoUsuario, fechaNacimiento, mail);
		}
		catch(Exception e) 
		{
			throw e;
		}
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
	
	public Vector<Usuario> buscarUsuariosParticipantes() throws Exception
	{
		try
		{
			return ADMPersistenciaUsuarios.getInstancia().buscarUsuariosParticipantes();
		}
		catch(Exception e)
		{
			throw e;
		}
	}

	public String getNombre() {
		return this.nombre;
	}
	
	public String getUsuario() {
		return this.usuario;
	}
	
	public Date getFechaNac() {
		return this.fechaNacimiento;
	}
	
	public boolean getTipo() {
		return this.tipoUsuario;
	}
	
	public int getTipoEnNumero() {
		if(this.tipoUsuario)
			return 1;
		else
			return 0;
	}
	
	public String getMail() {
		return this.mail;
	}
	
	public String getDescEstado() {
		return estado?"Activo":"Inhabilitado";
	}
	
	public boolean getEstado() {
		return this.estado;
	}	
}
