
public class Carta {
	private Integer valor;
	private String palo;
	public Carta(Integer valor, String palo){
		this.valor = valor;
		this.palo = palo;
	}
	public Integer obtenerValor(){
		return this.valor;
	}
	public String obtenerPalo(){
		return this.palo;
	}

}
