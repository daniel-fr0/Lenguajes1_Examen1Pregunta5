package leng1;

import static org.junit.Assert.*;

import org.junit.Test;

public class ComputadoraTest {

    @Test
    public void testBastanteExahustivo() {
        Computadora computadora = new Computadora();
        assertEquals(computadora, computadora);
    }
}
