package leng1;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.Before;

public class DefinirTraductorTest {
	private Computadora computadora;

	@Before
	public void setUp() {
		computadora = new Computadora();
	}
	
	@Test
	public void testDefinirTraductor() {
		String base = "C";
		String origen = "Python";
		String destino = "Java";
		String[] accion = {"DEFINIR", "TRADUCTOR", base, origen, destino};

		assertEquals(computadora.definir(accion), String.format("Se definio un traductor de '%s' hacia '%s', escrito en '%s'", origen, destino, base));
	}
	
	@Test
	public void testDefinirTraductorSinLenguajeBase() {
		String[] accion = {"DEFINIR", "TRADUCTOR"};

		assertEquals(computadora.definir(accion), "No se especifico el lenguaje base, el lenguaje origen o el lenguaje destino\nUSO: DEFINIR TRADUCTOR <lenguaje base> <lenguaje origen> <lenguaje destino>");
	}

	@Test
	public void testDefinirTraductorSinLenguaje() {
		String[] accion = {"DEFINIR", "TRADUCTOR", "C"};

		assertEquals(computadora.definir(accion), "No se especifico el lenguaje base, el lenguaje origen o el lenguaje destino\nUSO: DEFINIR TRADUCTOR <lenguaje base> <lenguaje origen> <lenguaje destino>");
	}

	@Test
	public void testDefinirTraductorExistente() {
		String base = "C";
		String origen = "Python";
		String destino = "Java";
		String[] accion = {"DEFINIR", "TRADUCTOR", base, origen, destino};

		assertEquals(computadora.definir(accion), String.format("Se definio un traductor de '%s' hacia '%s', escrito en '%s'", origen, destino, base));
		assertEquals(computadora.definir(accion), "Ya existe un traductor para ese lenguaje origen y destino, escrito en ese lenguaje base");
	}
}
