package fiuba.algo3.tpfinal.modelo;

public class Nodo<T> {
	private T dato;
	private Nodo<T> siguiente;
	private Nodo<T> anterior;

	public Nodo(T dato, Nodo<T> anterior, Nodo<T> siguiente) {
		this.dato = dato;
		this.siguiente = siguiente;
		this.anterior = anterior;
	}

	public T obtenerDato() {
		return dato;
	}

	public Nodo<T> obtenerSiguiente() {
		return siguiente;
	}

	public Nodo<T> obtenerAnterior() {
		return anterior;
	}

	public void modificarAnterior(Nodo<T> anterior) {
		this.anterior = anterior;
	}

	public void modificarSiguiente(Nodo<T> siguiente) {
		this.siguiente = siguiente;
	}
}
