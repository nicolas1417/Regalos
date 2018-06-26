package Persistencia;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public final class DataAccess {	
	
	public static Connection Conectar() 
	{
		try 
		{
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            String dbConnectString = getConfiguracion();
            
            return DriverManager.getConnection(dbConnectString);
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
	
	private static String getConfiguracion()
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
	
}
