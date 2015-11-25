package fiuba.algo3.tpfinal.modelo;

public class CalculadorDeTruco {
	
	private TablaDeValores tabla;
	
	public CalculadorDeTruco() {
		tabla = new TablaDeValores();
		this.InsertarElementosEnTabla();
	}
	
	public int obtenerValorCarta(Carta carta) {
		return this.tabla.busqueda(carta);
	}
	
	// Solo para testear que estan todas las cartas en la tabla
	public boolean eliminarCarta(Carta carta) {
		return this.tabla.borrar(carta);
	}
	

	private void InsertarElementosEnTabla() {
		Carta carta = new NoFigura(1, Palo.ESPADA);
		tabla.insercion(carta, 13);
		carta = new NoFigura(1, Palo.BASTO);
		tabla.insercion(carta, 12);
		carta = new NoFigura(7, Palo.ESPADA);
		tabla.insercion(carta, 11);
		carta = new NoFigura(7, Palo.ORO);
		tabla.insercion(carta, 10);
		carta = new NoFigura(3, Palo.ESPADA);
		tabla.insercion(carta, 9);
		carta = new NoFigura(3, Palo.BASTO);
		tabla.insercion(carta, 9);
		carta = new NoFigura(3, Palo.ORO);
		tabla.insercion(carta, 9);
		carta = new NoFigura(3, Palo.COPA);
		tabla.insercion(carta, 9);
		carta = new NoFigura(2, Palo.ESPADA);
		tabla.insercion(carta, 8);
		carta = new NoFigura(2, Palo.BASTO);
		tabla.insercion(carta, 8);
		carta = new NoFigura(2, Palo.ORO);
		tabla.insercion(carta, 8);
		carta = new NoFigura(2, Palo.COPA);
		tabla.insercion(carta, 8);
		carta = new NoFigura(1, Palo.ORO);
		tabla.insercion(carta, 7);
		carta = new NoFigura(1, Palo.COPA);
		tabla.insercion(carta, 7);
		carta = new Figura(12, Palo.COPA);
		tabla.insercion(carta, 6);
		carta = new Figura(12, Palo.ESPADA);
		tabla.insercion(carta, 6);
		carta = new Figura(12, Palo.BASTO);
		tabla.insercion(carta, 6);
		carta = new Figura(12, Palo.ORO);
		tabla.insercion(carta, 6);
		carta = new Figura(11, Palo.COPA);
		tabla.insercion(carta, 5);
		carta = new Figura(11, Palo.ORO);
		tabla.insercion(carta, 5);
		carta = new Figura(11, Palo.BASTO);
		tabla.insercion(carta, 5);
		carta = new Figura(11, Palo.ESPADA);
		tabla.insercion(carta, 5);
		carta = new Figura(10, Palo.COPA);
		tabla.insercion(carta, 4);
		carta = new Figura(10, Palo.ORO);
		tabla.insercion(carta, 4);
		carta = new Figura(10, Palo.BASTO);
		tabla.insercion(carta, 4);
		carta = new Figura(10, Palo.ESPADA);
		tabla.insercion(carta, 4);
		carta = new NoFigura(7, Palo.COPA);
		tabla.insercion(carta, 3);
		carta = new NoFigura(7, Palo.BASTO);
		tabla.insercion(carta, 3);
		carta = new NoFigura(6, Palo.COPA);
		tabla.insercion(carta, 2);
		carta = new NoFigura(6, Palo.ESPADA);
		tabla.insercion(carta, 2);
		carta = new NoFigura(6, Palo.ORO);
		tabla.insercion(carta, 2);
		carta = new NoFigura(6, Palo.BASTO);
		tabla.insercion(carta, 2);
		carta = new NoFigura(5, Palo.COPA);
		tabla.insercion(carta, 1);
		carta = new NoFigura(5, Palo.ESPADA);
		tabla.insercion(carta, 1);
		carta = new NoFigura(5, Palo.ORO);
		tabla.insercion(carta, 1);
		carta = new NoFigura(5, Palo.BASTO);
		tabla.insercion(carta, 1);
		carta = new NoFigura(4, Palo.COPA);
		tabla.insercion(carta, 0);
		carta = new NoFigura(4, Palo.ESPADA);
		tabla.insercion(carta, 0);
		carta = new NoFigura(4, Palo.BASTO);
		tabla.insercion(carta, 0);
		carta = new NoFigura(4, Palo.ORO);
		tabla.insercion(carta, 0);
	}

}
