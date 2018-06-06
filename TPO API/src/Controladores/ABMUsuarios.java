package Controladores;

import java.util.Date;
import java.util.List;

import Negocio.Usuario;

public class ABMUsuarios {
/*Provee métodos para manejar los usuarios, se mete en el modelo llamando los
 * metodos que agregan, editan o borran usuarios.*/
	public List<Usuario> usuarios;
	
	public void crearLista(String user, String pass, String name, Date nac){
		Usuario nuevo = new Usuario(name, user, pass, nac);
		this.usuarios.add(nuevo);
	}
	
	public void darDeAltaUSuario(Usuario user){
		this.usuarios.get(this.usuarios.indexOf(user)).altaUsuario();
	}
	
	public void realizarModificacionUsuario(){
		
	}
	
	public void darDeBajaUsuario(Usuario user){
		this.usuarios.get(this.usuarios.indexOf(user)).bajaUsuario();
	}
}
