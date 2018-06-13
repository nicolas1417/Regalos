package Negocio;

public class UsuarioDeLista {
	private boolean pagoRealizado;
	private boolean estado;
	private Usuario usuario;
	
	
	private static UsuarioDeLista instancia;
	
	public static UsuarioDeLista getInstancia()
	{
		if (instancia == null)
			instancia = new UsuarioDeLista();
		return instancia;
	}
	
	public UsuarioDeLista()
	{
		
	}
	
	public UsuarioDeLista(Usuario usuario, boolean pagoRealizado, boolean estado)
	{
		this.usuario = usuario;
		this.pagoRealizado = pagoRealizado;
		this.estado = estado;
	}

	public void asociarUsuarioALista(Usuario usuario, Lista lista)
	{
		this.usuario = usuario;
	}
	
	public void bajaUsuario(){
		this.estado = false;
	}
	
	public Usuario getUsuario()
	{
		return this.usuario;
	}
}
