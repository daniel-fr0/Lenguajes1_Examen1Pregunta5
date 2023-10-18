package leng1;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.Before;

public class GrafoTest {
	private Grafo<String> grafo;

	@Before
	public void setUp() {
		grafo = new Grafo<String>();
	}

	@Test
	public void testAgregarVertice() {
		grafo.agregarVertice("A");
		assertTrue(grafo.contieneVertice("A"));
	}

	@Test
	public void testContieneVertice() {
		grafo.agregarVertice("A");
		assertTrue(grafo.contieneVertice("A"));
		assertFalse(grafo.contieneVertice("B"));
	}

	@Test
	public void testAgregarArista() {
		// Sin peso
		grafo.agregarVertice("A");
		grafo.agregarVertice("B");
		grafo.agregarArista("A", "B");
		assertTrue(grafo.hayCamino("A", "B"));

		// Con peso
		grafo.agregarVertice("C");
		grafo.agregarVertice("D");
		grafo.agregarArista("C", "D", "5");
		assertTrue(grafo.hayCamino("C", "D"));
	}

	@Test
	public void testHayCamino() {
		grafo.agregarVertice("A");
		grafo.agregarVertice("B");
		grafo.agregarVertice("C");
		grafo.agregarVertice("D");
		grafo.agregarVertice("E");
		grafo.agregarVertice("F");
		grafo.agregarVertice("G");
		grafo.agregarVertice("H");
		grafo.agregarVertice("I");
		grafo.agregarVertice("J");
		grafo.agregarArista("A", "B");
		grafo.agregarArista("B", "C");
		grafo.agregarArista("C", "D");
		grafo.agregarArista("D", "E");
		grafo.agregarArista("E", "F");
		grafo.agregarArista("F", "G");
		grafo.agregarArista("G", "H");
		grafo.agregarArista("H", "I");
		grafo.agregarArista("I", "J");
		assertTrue(grafo.hayCamino("A", "J"));
	}

	@Test
	public void testNoHayCamino() {
		grafo.agregarVertice("A");
		grafo.agregarVertice("B");
		grafo.agregarVertice("C");
		grafo.agregarVertice("D");
		grafo.agregarVertice("E");
		grafo.agregarVertice("F");
		grafo.agregarVertice("G");
		grafo.agregarVertice("H");
		grafo.agregarVertice("I");
		grafo.agregarVertice("J");
		grafo.agregarArista("A", "B");
		grafo.agregarArista("B", "C");
		grafo.agregarArista("C", "D");
		grafo.agregarArista("D", "E");
		grafo.agregarArista("F", "G");
		grafo.agregarArista("G", "H");
		grafo.agregarArista("H", "I");
		grafo.agregarArista("I", "J");
		assertFalse(grafo.hayCamino("A", "J"));
	}

	@Test
	public void multiplesCaminos() {
		grafo.agregarVertice("A");
		grafo.agregarVertice("B");
		grafo.agregarVertice("C");
		grafo.agregarVertice("D");
		grafo.agregarVertice("E");
		grafo.agregarVertice("F");
		grafo.agregarVertice("G");
		grafo.agregarVertice("H");
		grafo.agregarVertice("I");
		grafo.agregarVertice("J");
		grafo.agregarArista("A", "B");
		grafo.agregarArista("B", "C");
		grafo.agregarArista("C", "D");
		grafo.agregarArista("D", "E");
		grafo.agregarArista("E", "F");
		grafo.agregarArista("F", "G");
		grafo.agregarArista("G", "H");
		grafo.agregarArista("H", "I");
		grafo.agregarArista("I", "J");
		assertTrue(grafo.hayCamino("A", "J"));
		assertTrue(grafo.hayCamino("A", "F"));
		assertTrue(grafo.hayCamino("B", "J"));
		assertTrue(grafo.hayCamino("C", "J"));
		assertFalse(grafo.hayCamino("A", "K"));
	}
}
