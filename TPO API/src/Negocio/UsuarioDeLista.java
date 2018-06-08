package Negocio;

public class UsuarioDeLista {
	private boolean pagoRealizado;
	private boolean estado;
	private Usuario usuario;
	private Lista lista;
	
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
		this.lista = lista;
	}
	
	public void bajaUsuario(){
		this.estado = false;
	}
}
