package Controladores;

import java.util.Date;
import java.util.List;
import java.util.Vector;

import Negocio.Usuario;


public class CtrlABMUsuarios {
	
/*Provee métodos para manejar los usuarios, se mete en el modelo llamando los
 * metodos que agregan, editan o borran usuarios.*/
	
	private static CtrlABMUsuarios instancia;	
	
	public static CtrlABMUsuarios getInstancia()
	{
		if (instancia == null)
			instancia = new CtrlABMUsuarios();
		return instancia;
	}
	
	public void crearUsuario(String usuario, String contrasena, String nombre,boolean idTipoUsuario, Date fechaNacimiento,String mail) throws Exception
	{
		try
		{
			Usuario nuevo = new Usuario();
			nuevo.AltaUsuario(usuario,contrasena,nombre,idTipoUsuario,fechaNacimiento,mail);
		}
		catch(Exception e)
		{
			throw e;
		}
	}	
	
	public Object[][] buscarUsuarios() throws Exception
	{
		Usuario obj = new Usuario();
		try
		{
			Vector<Usuario> v = obj.buscarUsuarios();
			
			Object[][] data = new Object[v.size()][5]; 
			
			for(int i=0;i<v.size();i++)
			{
				data[i][0] = v.elementAt(i).getUsuario();
				data[i][1] = v.elementAt(i).getNombre();
				data[i][2] = v.elementAt(i).getDescTipo();
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
	
	public String[] buscarUsuarioParaModificar(String usuario) throws Exception
	{
		try
		{	Usuario obj = new Usuario();
			Vector<Usuario> v = obj.buscarUsuarios();
			
			for (Usuario u:v)
			{
				if (u.getUsuario().equals(usuario) && u.getEstado())
				{
					String[] s = new String[5];
					s[0] = u.getUsuario();
					s[1] = u.getNombre();
					s[2] = u.getFechaNac().toString();
					s[3] = u.getMail();
					s[4] = u.getTipo() ?"1":"0";
					
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
	
	public void modificarUsuario(String usuario, String contrasena, String nombre,boolean TipoUsuario, Date fechaNacimiento,String mail) throws Exception
	{
		try
		{	
			int idTipoUsuario;
			if(TipoUsuario)
				idTipoUsuario = 1;
			else
				idTipoUsuario = 0;
			Usuario obj = new Usuario();
			obj.modificarUsuario(usuario, contrasena, nombre, idTipoUsuario, fechaNacimiento, mail);
		}
		catch(Exception e) 
		{
			throw e;
		}
	}
	
	public void eliminarUsuario(String usuario) throws Exception
	{
		Usuario obj = new Usuario();
		try
		{
			obj.eliminarUsuario(usuario);
		}
		catch(Exception e) 
		{
			throw e;
		}
	}
		
	
}
