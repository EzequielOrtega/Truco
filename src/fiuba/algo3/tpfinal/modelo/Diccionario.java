package fiuba.algo3.tpfinal.modelo;

public class Diccionario {
	
	private static TablaDeDiccionario tabla;
	
	static {
		tabla = new TablaDeDiccionario();
		
		Carta anchoEspada = new NoFigura(1, Palo.ESPADA);
		Carta dosEspada = new NoFigura(2, Palo.ESPADA);
		Carta tresEspada = new NoFigura(3, Palo.ESPADA);
		Carta cuatroEspada = new NoFigura(4, Palo.ESPADA);
		Carta cincoEspada = new NoFigura(5, Palo.ESPADA);
		Carta seisEspada = new NoFigura(6, Palo.ESPADA);
		Carta sieteEspada = new NoFigura(7, Palo.ESPADA);
		Carta sotaEspada = new Figura(10, Palo.ESPADA);
		Carta caballoEspada = new Figura(11, Palo.ESPADA);
		Carta reyEspada = new Figura(12, Palo.ESPADA);
		
		Carta anchoBasto = new NoFigura(1, Palo.BASTO);
		Carta dosBasto = new NoFigura(2, Palo.BASTO);
		Carta tresBasto = new NoFigura(3, Palo.BASTO);
		Carta cuatroBasto = new NoFigura(4, Palo.BASTO);
		Carta cincoBasto = new NoFigura(5, Palo.BASTO);
		Carta seisBasto = new NoFigura(6, Palo.BASTO);
		Carta sieteBasto = new NoFigura(7, Palo.BASTO);
		Carta sotaBasto = new Figura(10, Palo.BASTO);
		Carta caballoBasto = new Figura(11, Palo.BASTO);
		Carta reyBasto = new Figura(12, Palo.BASTO);
		
		Carta anchoCopa = new NoFigura(1, Palo.COPA);
		Carta dosCopa = new NoFigura(2, Palo.COPA);
		Carta tresCopa = new NoFigura(3, Palo.COPA);
		Carta cuatroCopa = new NoFigura(4, Palo.COPA);
		Carta cincoCopa = new NoFigura(5, Palo.COPA);
		Carta seisCopa = new NoFigura(6, Palo.COPA);
		Carta sieteCopa = new NoFigura(7, Palo.COPA);
		Carta sotaCopa = new Figura(10, Palo.COPA);
		Carta caballoCopa = new Figura(11, Palo.COPA);
		Carta reyCopa = new Figura(12, Palo.COPA);
		
		Carta anchoOro = new NoFigura(1, Palo.ORO);
		Carta dosOro = new NoFigura(2, Palo.ORO);
		Carta tresOro = new NoFigura(3, Palo.ORO);
		Carta cuatroOro = new NoFigura(4, Palo.ORO);
		Carta cincoOro = new NoFigura(5, Palo.ORO);
		Carta seisOro = new NoFigura(6, Palo.ORO);
		Carta sieteOro = new NoFigura(7, Palo.ORO);
		Carta sotaOro = new Figura(10, Palo.ORO);
		Carta caballoOro = new Figura(11, Palo.ORO);
		Carta reyOro = new Figura(12, Palo.ORO);
		
		tabla.insercion(anchoEspada, "Uno de espada");
		tabla.insercion(dosEspada, "Dos de espada");
		tabla.insercion(tresEspada, "Tres de espada");
		tabla.insercion(cuatroEspada, "Cuatro de espada");
		tabla.insercion(cincoEspada, "Cinco de espada");
		tabla.insercion(seisEspada, "Seis de espada");
		tabla.insercion(sieteEspada, "Siete de espada");
		tabla.insercion(sotaEspada, "Diez de espada");
		tabla.insercion(caballoEspada, "Once de espada");
		tabla.insercion(reyEspada, "Doce de espada");
		
		tabla.insercion(anchoBasto, "Uno de basto");
		tabla.insercion(dosBasto, "Dos de basto");
		tabla.insercion(tresBasto, "Tres de basto");
		tabla.insercion(cuatroBasto, "Cuatro de basto");
		tabla.insercion(cincoBasto, "Cinco de basto");
		tabla.insercion(seisBasto, "Seis de basto");
		tabla.insercion(sieteBasto, "Siete de basto");
		tabla.insercion(sotaBasto, "Diez de basto");
		tabla.insercion(caballoBasto, "Once de basto");
		tabla.insercion(reyBasto, "Doce de basto");
		
		tabla.insercion(anchoCopa, "Uno de copa");
		tabla.insercion(dosCopa, "Dos de copa");
		tabla.insercion(tresCopa, "Tres de copa");
		tabla.insercion(cuatroCopa, "Cuatro de copa");
		tabla.insercion(cincoCopa, "Cinco de copa");
		tabla.insercion(seisCopa, "Seis de copa");
		tabla.insercion(sieteCopa, "Siete de copa");
		tabla.insercion(sotaCopa, "Diez de copa");
		tabla.insercion(caballoCopa, "Once de copa");
		tabla.insercion(reyCopa, "Doce de copa");
		
		tabla.insercion(anchoOro, "Uno de oro");
		tabla.insercion(dosOro, "Dos de oro");
		tabla.insercion(tresOro, "Tres de oro");
		tabla.insercion(cuatroOro, "Cuatro de oro");
		tabla.insercion(cincoOro, "Cinco de oro");
		tabla.insercion(seisOro, "Seis de oro");
		tabla.insercion(sieteOro, "Siete de oro");
		tabla.insercion(sotaOro, "Diez de oro");
		tabla.insercion(caballoOro, "Once de oro");
		tabla.insercion(reyOro, "Doce de oro");
	}
	
	public Diccionario() {
		
	}
	
	public String Busqueda (Carta carta) {
		return tabla.busqueda(carta);
	}
}
