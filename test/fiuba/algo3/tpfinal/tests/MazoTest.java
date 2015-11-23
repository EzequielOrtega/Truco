package fiuba.algo3.tpfinal.tests;

import fiuba.algo3.tpfinal.modelo.Carta;
import fiuba.algo3.tpfinal.modelo.Mazo;
import fiuba.algo3.tpfinal.modelo.Palo;
import fiuba.algo3.tpfinal.modelo.error.NoHayMasCartasError;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;


public class MazoTest {

    private Mazo nuevoMazo;
    
    @Before
    public void setup(){
    	this.nuevoMazo = new Mazo();
    }

    @Test
    public void testCrearMazoExitoso() {
        assertEquals(40, nuevoMazo.cantidadDeCartasRestantes());
    }

    @Ignore
    @Test
    public void testMezclarMazoExitoso() {
    	List<Carta> orden1 = nuevoMazo.getCartas();
        nuevoMazo.mezclar();
        List<Carta> orden2 = nuevoMazo.getCartas();
        Boolean cartasMezcladas = false;
        for(int x = 0; x < 40; x++){
        	if(!(orden1.get(x).mismoPaloQue(orden2.get(x))) || (orden1.get(x).getValor())!=(orden2.get(x).getValor())) {
        		cartasMezcladas = true;
                break;
        	}
        }
        assertTrue(cartasMezcladas);
    }

    @Test
    public void testAgarrarCartaDevuelveLaUltimaCartaDelMazo() {
        // Mazo ordenado, las ultimas cartas son los valores mas altos de espada.
        Carta carta1 = nuevoMazo.agarrarCarta();
        Carta carta2 = nuevoMazo.agarrarCarta();
        assertEquals(12, carta1.getValor());
        assertEquals(11, carta2.getValor());
        assertEquals(Palo.ESPADA, carta1.getPalo());
        assertEquals(Palo.ESPADA, carta2.getPalo());
    }

    @Test (expected = NoHayMasCartasError.class)
    public void testAgarrarCartaDevuelveErrorSiNoQuedanCartas() {
        for (int i=1; i<=41; i++)
            nuevoMazo.agarrarCarta();
    }



}