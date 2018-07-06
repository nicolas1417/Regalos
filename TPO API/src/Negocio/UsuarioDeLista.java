package Negocio;

import java.sql.SQLException;
import java.util.List;

public class UsuarioDeLista {
	private boolean estado;
	private Usuario usuario;
	private Pago pago = null;
		
	public UsuarioDeLista()
	{
		
	}
	
	public UsuarioDeLista(Usuario usuario,boolean estado,Pago pago)
	{
		this.usuario = usuario;
		this.estado = estado;
		this.pago = pago;
	}
	
	public List<String> buscarMisPagos(String logueado) throws SQLException{
		Pago obj = new Pago();
		return obj.buscarMisPagos(logueado);
	}
	
	public Usuario getUsuario()
	{
		return this.usuario;
	}
	
	public boolean getPagoRealizado()
	{
		return (pago != null);
	}
}
