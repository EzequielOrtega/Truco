package fiuba.algo3.tpfinal.modelo;

public class ListaCircular<T> {
	
	private Nodo<T> raiz;
	private Integer cantidadDeNodos;
	
	public ListaCircular(){
		raiz = null;
		cantidadDeNodos = 0;
	}
	
	public Integer tamanio(){
		return cantidadDeNodos;
	}
	
	public Boolean estaVacia(){
		return this.tamanio()==0;
	}
	
	public void agregar(T dato){
		Nodo<T> nodoNuevo;
		if(this.estaVacia()){
			nodoNuevo = new Nodo<T>(dato, null, null);
			raiz = nodoNuevo;
			cantidadDeNodos++;
		}else if(this.tamanio() == 1){
			nodoNuevo = new Nodo<T>(dato, raiz, raiz);
			raiz.modificarAnterior(nodoNuevo);
			raiz.modificarSiguiente(nodoNuevo);
			cantidadDeNodos++;
		}else{
			nodoNuevo = new Nodo<T>(dato, null, raiz);
			Nodo<T> ultimo = raiz.obtenerAnterior();
			nodoNuevo.modificarAnterior(ultimo);
			ultimo.modificarSiguiente(nodoNuevo);
			raiz.modificarAnterior(nodoNuevo);
			cantidadDeNodos++;
		}
	}
}
