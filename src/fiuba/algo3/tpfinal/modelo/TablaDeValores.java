package fiuba.algo3.tpfinal.modelo;

import java.util.LinkedList;

import fiuba.algo3.tpfinal.modelo.error.LaCartaIngresadaNoEstaEnLaTablaError;

public class TablaDeValores {
	
	private LinkedList<ElementoDeTabla<Carta>> elementos;
	
	public TablaDeValores (){
		elementos = new LinkedList<ElementoDeTabla<Carta>>();
	}
	
	public Boolean insercion(Carta carta, Integer valor){
		ElementoDeTabla<Carta> elementoNuevo = new ElementoDeTabla<Carta>(carta, valor);
		elementos.add(elementoNuevo);
		return true;
	}
	
	public Integer busqueda(Carta carta){
		Integer valorADevolver = null;
		for(ElementoDeTabla<Carta> elementoActual: elementos){
			if(elementoActual.getLlave().equals(carta)){
				valorADevolver = elementoActual.getValor();
			}
		}
		if(valorADevolver == null){
			throw new LaCartaIngresadaNoEstaEnLaTablaError();
		}
		return valorADevolver;
	}
	
	public Boolean borrar(Carta carta){
		Integer posicionElemento = null;
		for(Integer x = 0; x < elementos.size(); x++){
			if(elementos.get(x).getLlave().equals(carta)){
				posicionElemento = x;
			}
		}
		if(posicionElemento == null){
			throw new LaCartaIngresadaNoEstaEnLaTablaError();
		}
		elementos.remove(posicionElemento);
		return true;
	}
}
