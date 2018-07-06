package Negocio;

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
	
	public Usuario getUsuario()
	{
		return this.usuario;
	}
	
	public boolean getPagoRealizado()
	{
		return (pago != null);
	}
}
