package Controladores;

import java.sql.ResultSet;
import java.util.Date;
import java.util.List;
import java.util.Vector;

import Negocio.Usuario;
import Persistencia.ADMPersistenciaUsuarios;


public class CtrlABMUsuarios {
	
/*Provee métodos para manejar los usuarios, se mete en el modelo llamando los
 * metodos que agregan, editan o borran usuarios.*/
	
	private static CtrlABMUsuarios instancia;
	private Vector<Usuario> usuarios;
	
	public static CtrlABMUsuarios getInstancia()
	{
		if (instancia == null)
			instancia = new CtrlABMUsuarios();
		return instancia;
	}
	
	public String[] obtenerTiposDeUsuario() throws Exception
	{
		try
		{
			return Usuario.getInstancia().obtenerTiposDeUsuario();
		}
		catch (Exception e) 
		{
			throw e;
		}
	}
	
	public void crearUsuario(String usuario, String contrasena, String nombre,int idTipoUsuario, Date fechaNacimiento,String mail) throws Exception
	{
		try
		{
			Usuario.getInstancia().AltaUsuario(usuario,contrasena,nombre,idTipoUsuario,fechaNacimiento,mail);
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
				
				this.agregarUsuario(v.elementAt(i));
			}
			
			return data;
		}
		catch(Exception e)
		{
			throw e;
		}
	}
	
	private void agregarUsuario(Usuario u)
	{
		if (usuarios == null)
			usuarios = new Vector<Usuario>();
		usuarios.add(u);
	}
	
	public String[] buscarUsuarioParaModificar(String usuario) throws Exception
	{
		try
		{	
			for (Usuario u:usuarios)
			{
				if (u.getUsuario().equals(usuario) && u.getEstado())
				{
					String[] s = new String[5];
					s[0] = u.getUsuario();
					s[1] = u.getNombre();
					s[2] = u.getFechaNac().toString();
					s[3] = u.getMail();
					s[4] = String.valueOf(u.getIdTipo());
					
					return s;
				}					
			}
			
			throw new Exception("No se ha encontrado al usuario");	
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
			Usuario.getInstancia().modificarUsuario(usuario, contrasena,passConfirmada, nombre, idTipoUsuario, fechaNacimiento, mail);
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
			Usuario.getInstancia().eliminarUsuario(usuario);
		}
		catch(Exception e) 
		{
			throw e;
		}
	}
		
	
}
