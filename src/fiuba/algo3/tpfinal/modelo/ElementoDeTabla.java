package fiuba.algo3.tpfinal.modelo;

public class ElementoDeTabla<T> {
	
	private T llave;
	private Integer valor;
	
	public ElementoDeTabla(T llave, Integer valor){
		this.setLlave(llave);
		this.setValor(valor);
	}

	public T getLlave() {
		return llave;
	}

	public void setLlave(T llave) {
		this.llave = llave;
	}

	public Integer getValor() {
		return valor;
	}

	public void setValor(Integer valor) {
		this.valor = valor;
	}
}
