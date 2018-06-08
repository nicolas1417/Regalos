package Controladores;

import java.sql.ResultSet;
import java.util.Date;
import java.util.List;
import java.util.Vector;

import Negocio.TipoUsuario;
import Negocio.Usuario;
import Persistencia.ADMPersistenciaUsuarios;


public class ABMUsuarios {
	
/*Provee métodos para manejar los usuarios, se mete en el modelo llamando los
 * metodos que agregan, editan o borran usuarios.*/
	
	public static Vector<Usuario> usuarios;
	private static ABMUsuarios instancia;
	
	public static ABMUsuarios getInstancia()
	{
		if (instancia == null)
			instancia = new ABMUsuarios();
		return instancia;
	}
	
	public Vector<TipoUsuario> obtenerTiposDeUsuario() throws Exception
	{
		try
		{
			return TipoUsuario.getInstancia().obtenerTiposDeUsuario();
		}
		catch (Exception e) 
		{
			throw e;
		}
	}
	
	public void crearUsuario(String usuario, String contrasena, String nombre,TipoUsuario tipoUsuario, Date fechaNacimiento,String mail) throws Exception
	{
		try
		{
			Usuario.getInstancia().AltaUsuario(usuario,contrasena,nombre,tipoUsuario,fechaNacimiento,mail);
		
			agregarUsuario(new Usuario(nombre, usuario, contrasena, fechaNacimiento, true, mail, tipoUsuario));
		}
		catch(Exception e)
		{
			throw e;
		}
	}
	
	private void agregarUsuario(Usuario usuario) 
	{
		if (usuarios == null)
			usuarios = new Vector<Usuario>();
		usuarios.add(usuario);
	}
	
	
	public void validarAltaUsuario(String usuario, String password, String passConfirmada) throws Exception
	{
		try
		{
			Usuario.getInstancia().validarAltaUsuario(usuario,password,passConfirmada);
		}
		catch(Exception e)
		{
			throw e;
		}
	}
	
	public Object[][] buscarUsuarios() throws Exception
	{
		try
		{
			Vector<Usuario> v = Usuario.getInstancia().buscarUsuarios();
			
			Object[][] data = new Object[v.size()][5]; 
			
			for(int i=0;i<v.size();i++)
			{
				data[i][0] = v.elementAt(i).getUsuario();
				data[i][1] = v.elementAt(i).getNombre();
				data[i][2] = v.elementAt(i).getCodTipo();
				data[i][3] = v.elementAt(i).getMail();
				data[i][4] = v.elementAt(i).getDescEstado();
			}
			
			return data;
		}
		catch(Exception e)
		{
			throw e;
		}
	}
}
