package Negocio;

import java.util.Date;

public class Usuario {
	
	private String nombre;
	private Date fechaNacimiento;
	private String mail;
	private String usuario;
	private String password;
	private boolean estado;
	private TipoUsuario tipoUsuario;
	
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
	
	public void altaUsuario(){
		/*El usuario ya deberia estar creado, solo se lo reactiva.*/
		this.estado = true;
	}
	
	public void bajaUsuario(){
		this.estado = false;
	}
	
	public void borradoPermanenteDeUsuario(){
		
	}
	
	public void modificarUsuario(){
		
	}
}
