package leng1;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.Before;

public class DefinirInterpreteTest {
	private Computadora computadora;

	@Before
	public void setUp() {
		computadora = new Computadora();
	}
	
	@Test
	public void testDefinirInterprete() {
		String lenguajeBase = "C";
		String lenguaje = "Python";
		String[] accion = {"DEFINIR", "INTERPRETE", lenguajeBase, lenguaje};

		assertEquals(computadora.definir(accion), String.format("Se definió un interprete para '%s', escrito en '%s'", lenguaje, lenguajeBase));
	}
	
	@Test
	public void testDefinirInterpreteSinLenguajeBase() {
		String[] accion = {"DEFINIR", "INTERPRETE"};

		assertEquals(computadora.definir(accion), "No se especificó el lenguaje base o el lenguaje\nUSO: DEFINIR INTERPRETE <lenguaje base> <lenguaje>");
	}

	@Test
	public void testDefinirInterpreteSinLenguaje() {
		String[] accion = {"DEFINIR", "INTERPRETE", "C"};

		assertEquals(computadora.definir(accion), "No se especificó el lenguaje base o el lenguaje\nUSO: DEFINIR INTERPRETE <lenguaje base> <lenguaje>");
	}

	@Test
	public void testDefinirInterpreteExistente() {
		String lenguajeBase = "C";
		String lenguaje = "Python";
		String[] accion = {"DEFINIR", "INTERPRETE", lenguajeBase, lenguaje};

		assertEquals(computadora.definir(accion), String.format("Se definió un interprete para '%s', escrito en '%s'", lenguaje, lenguajeBase));
		assertEquals(computadora.definir(accion), "Ya existe un interprete para ese lenguaje escrito en ese lenguaje base");
	}
}
