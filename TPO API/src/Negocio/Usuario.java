package Negocio;

import java.util.Date;

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
	
	public Usuario()
	{
		
	}
	
	public static Usuario getInstancia()
	{
		if (instancia == null)
			instancia = new Usuario();
		return instancia;
	}
	
	public Usuario(String nombre, String usuario, String pass, Date nacimiento){
		this.nombre = nombre;
		this.usuario = usuario;
		this.password = pass;
		this.fechaNacimiento = nacimiento;
		this.estado = true;
		this.mail = nombre + "@user.com";
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
	
	public void AltaUsuario(String usuario, String contrasena, String nombre,TipoUsuario tipoUsuario, Date fechaNacimiento,String mail) throws Exception
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
	
	public void bajaUsuario(){
		this.estado = false;
	}
	
	public void borradoPermanenteDeUsuario(){
		
	}
	
	public void modificarUsuario(){
		
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
	
	private boolean validarContrasena(String password, String passConfirmada) 
	{
		return (password.equals(passConfirmada));
	}
}
