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

	public void asociarUsuarioALista(Usuario usuario, Lista lista)
	{
		this.usuario = usuario;
	}
	
	public void bajaUsuario(){
		this.estado = false;
	}
}
