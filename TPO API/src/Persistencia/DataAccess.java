package Persistencia;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
import java.util.Vector;

public class DataAccess {
	private static DataAccess dataAccess;
	private Connection instanciaDB;
	
	private DataAccess(){		
		instanciaDB = Conectar();			
	}
	
	public static DataAccess getConexion()
	{
		if (dataAccess== null)
			dataAccess = new DataAccess();
		return dataAccess;
	}
	
	private Connection Conectar() 
	{
		try 
		{
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            String dbConnectString = getConfiguracion();
            Connection con = DriverManager.getConnection (dbConnectString);
            
            return con;
		}
		catch (SQLException e)
		{
			System.out.println("Mensaje Error: " + e.getMessage());
			System.out.println("Stack Trace: " + e.getStackTrace());
			return null;
		}
		catch (Exception ex)
		{
			System.out.println("Mensaje Error: " + ex.getMessage());
			System.out.println("Stack Trace: " + ex.getStackTrace());
			return null;
		}
	}
	
	private String getConfiguracion()
	{
		String configuracion = "ConfigBD.txt";
	    Properties propiedades;
	    String connectionString = "";
	    
		try 
		{
			FileInputStream f = new FileInputStream(configuracion);	 
		    propiedades = new Properties();
		    propiedades.load(f);
		    f.close();
		    
		    connectionString = propiedades.getProperty("ConnectionString");		    		   
		}
		catch (Exception e) 
	    {
			System.out.println("Mensaje Error: " + e.getMessage());
			System.out.println("Stack Trace: " + e.getStackTrace());
	    }
		return connectionString;
	}

	public Connection getInstanciaDB() {
		if (instanciaDB != null)
		{
			try 
			{
				if (!instanciaDB.isClosed())
					return instanciaDB;	
				else return Conectar();
			} 
			catch (SQLException e) 
			{
				e.printStackTrace();
			}
		}
		
		return instanciaDB;
	}
	
	public void cerrarConexion()
	{
		try 
		{
			instanciaDB.close();
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
	}	
}
