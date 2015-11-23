package fiuba.algo3.tpfinal.modelo;

import fiuba.algo3.tpfinal.modelo.error.ElementoNoEstaEnLaListaError;
import fiuba.algo3.tpfinal.modelo.error.PosicionFueraDeLosLimitesDeLaListaError;

import java.util.LinkedList;

public class ListaCircular<T> {
	
	private Nodo<T> raiz;
	private int cantidadDeNodos;
	
	public ListaCircular(){
		raiz = null;
		cantidadDeNodos = 0;
	}
	
	public int tamanio(){
		return cantidadDeNodos;
	}
	
	public Boolean estaVacia(){
		return this.tamanio()==0;
	}
	
	public void agregar(T dato) {
		Nodo<T> nodoNuevo;
		if(this.estaVacia()) {
			nodoNuevo = new Nodo<T>(dato, null, null);
			raiz = nodoNuevo;
			cantidadDeNodos++;
		} else if(this.tamanio() == 1) {
			nodoNuevo = new Nodo<T>(dato, raiz, raiz);
			raiz.modificarAnterior(nodoNuevo);
			raiz.modificarSiguiente(nodoNuevo);
			cantidadDeNodos++;
		} else {
			nodoNuevo = new Nodo<T>(dato, null, raiz);
			Nodo<T> ultimo = raiz.obtenerAnterior();
			nodoNuevo.modificarAnterior(ultimo);
			ultimo.modificarSiguiente(nodoNuevo);
			raiz.modificarAnterior(nodoNuevo);
			cantidadDeNodos++;
		}
	}
	
	public void moverAlSiguiente(){
		raiz = raiz.obtenerSiguiente();
	}
	
	//considero la posicion 0 como la posicion de la raiz
	public T obtenerElemento(Integer posicion){
		if ((posicion < 0)||(posicion > this.tamanio())){
			throw new PosicionFueraDeLosLimitesDeLaListaError();
		}
		Nodo<T> nodoBuscado = raiz;
		for(int x = 1; x <= posicion; x++ ){
			nodoBuscado = nodoBuscado.obtenerSiguiente();
		}
		return nodoBuscado.obtenerDato();
	}
	
	public void vaciar(){
		raiz = null;
		cantidadDeNodos = 0;
	}
	
	public LinkedList<T> obtenerElementos(){
		LinkedList<T> elementos = new LinkedList<T>();
		Nodo<T> nodoActual = raiz;
		for(int x = 0; x < cantidadDeNodos; x++){
			elementos.add(nodoActual.obtenerDato());
			nodoActual = nodoActual.obtenerSiguiente();
		}
		return elementos;
	}

	public T obtenerElementoSiguienteDe(T elemento) {
		T elementoBuscado = null;
		Nodo<T> nodoActual = raiz;
		for(int x = 0; x < this.tamanio(); x++) {
			if(nodoActual.obtenerDato() == elemento) {
				elementoBuscado = nodoActual.obtenerSiguiente().obtenerDato();
			}
			nodoActual = nodoActual.obtenerSiguiente();
		}
		if (elementoBuscado == null) {
			throw new ElementoNoEstaEnLaListaError();
		}
		return elementoBuscado;
	}

	public void moverAlAnterior() {
		raiz = raiz.obtenerAnterior();
	}
}
