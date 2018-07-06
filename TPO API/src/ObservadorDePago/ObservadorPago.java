package ObservadorDePago;

import java.util.Observable;

public class ObservadorPago extends Observable{
	int montoRecaudado;
	int idLista;
	
	public ObservadorPago()
	{
		
	}
	
	public void setMontoRecaudado(int monto, int idLista) {
		this.montoRecaudado = monto;
		this.idLista = idLista;
		setChanged();
	    notifyObservers();
	}

	public int getMontoRecaudado() {
		return montoRecaudado;
	}
	
	public int getIdLista() {
		return idLista;
	}
}
