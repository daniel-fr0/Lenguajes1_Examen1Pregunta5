package leng1;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.Before;

public class DefinirProgramaTest {
    private Computadora computadora;

    @Before
    public void setUp() {
        computadora = new Computadora();
    }

    @Test
    public void testDefinirSinTipo() {
        String[] accion = {"DEFINIR", };

        assertEquals(computadora.definir(accion), "No se especificó el tipo a definir");
    }

    @Test
    public void testDefinirPrograma() {
        String nombre = "fibonacci";
        String lenguaje = "C";
        String[] accion = {"DEFINIR", "PROGRAMA", nombre, lenguaje};

        assertEquals(computadora.definir(accion), String.format("Se definió el programa '%s', ejecutable en '%s'", nombre, lenguaje));
    }

    @Test
    public void testDefinirProgramaSinNombre() {
        String[] accion = {"DEFINIR", "PROGRAMA"};

        assertEquals(computadora.definir(accion), "No se especificó el nombre del programa o el lenguaje\nUSO: DEFINIR PROGRAMA <nombre> <lenguaje>");
    }

    @Test
    public void testDefinirProgramaSinLenguaje() {
        String[] accion = {"DEFINIR", "PROGRAMA", "fibonacci"};

        assertEquals(computadora.definir(accion), "No se especificó el nombre del programa o el lenguaje\nUSO: DEFINIR PROGRAMA <nombre> <lenguaje>");
    }

    @Test
    public void testDefinirProgramaExistente() {
        String nombre = "fibonacci";
        String lenguaje = "C";
        String[] accion = {"DEFINIR", "PROGRAMA", nombre, lenguaje};

        assertEquals(computadora.definir(accion), String.format("Se definió el programa '%s', ejecutable en '%s'", nombre, lenguaje));
        assertEquals(computadora.definir(accion), "Ya existe un programa con ese nombre");
    }
}