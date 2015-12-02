package fiuba.algo3.tpfinal.modelo;

public class ElementoDeDiccionario<T> {

	private T llave;
	private String valor;

	public ElementoDeDiccionario(T llave, String valor) {
		this.setLlave(llave);
		this.setValor(valor);
	}

	public T getLlave() {
		return llave;
	}

	public void setLlave(T llave) {
		this.llave = llave;
	}

	public String getValor() {
		return valor;
	}

	public void setValor(String valor) {
		this.valor = valor;
	}
}
