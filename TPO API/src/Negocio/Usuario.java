package Negocio;

import java.util.Date;

public class Usuario {

	private int id;
	private String nombre;
	private Date fechaNacimiento;
	private String mail;
	private String usuario;
	private String password;
	private boolean estado;
	
	public Usuario(String nombre, String usuario, String pass, Date nacimiento){
		this.nombre = nombre;
		this.usuario = usuario;
		this.password = pass;
		this.fechaNacimiento = nacimiento;
		this.estado = true;
		this.mail = nombre + "@user.com";
	}
	
	public Usuario(int id, String nombre, String usuario, String pass, Date nacimiento,boolean estado, String mail){
		this.id = id;
		this.nombre = nombre;
		this.usuario = usuario;
		this.password = pass;
		this.fechaNacimiento = nacimiento;
		this.estado = estado;
		this.mail = mail;
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
