package leng1;

import static org.junit.Assert.*;

import java.util.Arrays;

import org.junit.Test;
import org.junit.Before;

public class ComputadoraTest {
	private Computadora computadora;

	@Before
	public void setUp() {
		computadora = new Computadora();
	}

	@Test
	public void testNoEjecutable() {
		// Se define el programa escrito en "Java"
		String nombre = "factorial";
		computadora.definir(new String[]{"DEFINIR", "PROGRAMA", nombre, "Java"});

		// No se debe poder ejecutar el programa
		String[] accion = {"EJECUTABLE", nombre};
		assertEquals(computadora.ejecutable(accion), String.format("No es posible ejecutar el programa '%s'", nombre));
	}

	@Test
	public void testEjecutable() {
		// Se define el programa escrito en "LOCAL"
		String nombre = "fibonacci";
		computadora.definir(new String[] {"DEFINIR", "PROGRAMA", nombre, "LOCAL"});

		// Se debe poder ejecutar el programa
		String[] accion = {"EJECUTABLE", nombre};
		assertEquals(computadora.ejecutable(accion), String.format("Si, es posible ejecutar el programa '%s'", nombre));
	}


	@Test
	public void testEjecutableSinNombre() {
		String[] accion = {"EJECUTABLE"};
		assertEquals(computadora.ejecutable(accion), "No se especificó el nombre del programa");
	}

	@Test
	public void testEjecutableNoExistente() {
		String nombre = "hola";
		String[] accion = {"EJECUTABLE", nombre};
		assertEquals(computadora.ejecutable(accion), "No existe un programa con ese nombre");
	}

	@Test
	public void testEjecucion() {
		String ejecucion = "$> DEFINIR PROGRAMA fibonacci LOCAL\n" +
						   "Se definió el programa 'fibonacci', ejecutable en 'LOCAL'\n" +
						   "$> EJECUTABLE fibonacci\n" +
						   "Si, es posible ejecutar el programa 'fibonacci'\n" +
						   "$> DEFINIR PROGRAMA factorial Java\n" +
						   "Se definió el programa 'factorial', ejecutable en 'Java'\n" +
						   "$> EJECUTABLE factorial\n" +
						   "No es posible ejecutar el programa 'factorial'\n" +
						   "$> DEFINIR INTERPRETE C Java\n" +
						   "Se definió un intérprete para 'Java', escrito en 'C'\n" +
						   "$> DEFINIR TRADUCTOR C Java C\n" +
						   "Se definió un traductor de 'Java' hacia 'C', escrito en 'C'\n" +
						   "$> EJECUTABLE factorial\n" +
						   "No es posible ejecutar el programa 'factorial'\n" +
						   "$> DEFINIR INTERPRETE LOCAL C\n" +
						   "Se definió un intérprete para 'C', escrito en 'LOCAL'\n" +
						   "$> EJECUTABLE factorial\n" +
						   "Si, es posible ejecutar el programa 'factorial'\n" +
						   "$> DEFINIR PROGRAMA holamundo Python3\n" +
						   "Se definió el programa 'holamundo', ejecutable en 'Python3'\n" +
						   "$> DEFINIR TRADUCTOR wtf42 Python3 LOCAL\n" +
						   "Se definió un traductor de 'Python3' hacia 'LOCAL', escrito en 'wtf42'\n" +
						   "$> EJECUTABLE holamundo\n" +
						   "No es posible ejecutar el programa 'holamundo'\n" +
						   "$> DEFINIR TRADUCTOR C wtf42 Java\n" +
						   "Se definió un traductor de 'wtf42' hacia 'Java', escrito en 'C'\n" +
						   "$> EJECUTABLE holamundo\n" +
						   "Si, es posible ejecutar el programa 'holamundo'";

		String[] partes = ejecucion.split("\n");
		String[] instrucciones = Arrays.stream(partes)
										.filter(p -> p.startsWith("$>"))
										.map(p -> p.substring(3).trim())
										.toArray(String[]::new);
		String[] respuestas = Arrays.stream(partes)
									.filter(p -> !p.startsWith("$>"))
									.map(p -> p.trim())
									.toArray(String[]::new);

		for (int i = 0; i < instrucciones.length; i++) {
			String[] accion = instrucciones[i].split(" ");
			if (accion[0] == "DEFINIR") {
				assertEquals(computadora.definir(accion), respuestas[i]);
			} else if (accion[0] == "EJECUTABLE") {
				assertEquals(computadora.ejecutable(accion), respuestas[i]);
			}
		}
	}

	@Test
	public void testCadena() {
		computadora.definir(new String[] {"DEFINIR", "INTERPRETE", "B", "A"});
		computadora.definir(new String[] {"DEFINIR", "INTERPRETE", "C", "B"});
		computadora.definir(new String[] {"DEFINIR", "INTERPRETE", "LOCAL", "C"});
		computadora.definir(new String[] {"DEFINIR", "PROGRAMA", "cadenaInterpretable", "A"});
		String resultado = computadora.ejecutable(new String[] {"EJECUTABLE", "cadenaInterpretable"});
		assertEquals(resultado, "Si, es posible ejecutar el programa 'cadenaInterpretable'");

		computadora.definir(new String[] {"DEFINIR", "TRADUCTOR", "X", "W", "Z"});
		computadora.definir(new String[] {"DEFINIR", "TRADUCTOR", "Y", "X", "Y"});
		computadora.definir(new String[] {"DEFINIR", "TRADUCTOR", "Z", "Y", "Z"});
		computadora.definir(new String[] {"DEFINIR", "TRADUCTOR", "B", "Z", "C"});
		computadora.definir(new String[] {"DEFINIR", "PROGRAMA", "cadenaTraducible", "W"});
		resultado = computadora.ejecutable(new String[] {"EJECUTABLE", "cadenaTraducible"});
		assertEquals(resultado, "Si, es posible ejecutar el programa 'cadenaTraducible'");
	}
}
